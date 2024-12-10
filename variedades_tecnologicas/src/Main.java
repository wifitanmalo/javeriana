import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Scanner;

public class Main extends JFrame
{
    // medidas de la ventana
    int width = 500, height = 500;

    // objeto del negocio
    private Negocio local;

    // mensaje de error
    JLabel mensaje_error = new JLabel("Ingrese un numero positivo", SwingConstants.CENTER);
    JLabel dinero_insuficiente = new JLabel("Dinero insuficiente", SwingConstants.CENTER);


    // crea los paneles a utilizar
    private JPanel menu_principal = new JPanel();
    private JPanel efectivo = new JPanel();
    private JPanel menu_operador = new JPanel();


    // ---------- constructor de la clase Main ----------
    public Main()
    {
        // objeto de la clase Negocio
        this.local = new Negocio(35000,12500);

        // inicia el panel inicial
        panel_inicio(local);
        menu_principal.setVisible(true);

        setTitle("Variedades tecnológicas");
        setBounds(0, 0, width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    // ------------------------ objeto de fotocopiadora ------------------------
    public void set_local(Negocio local)
    {
        this.local = local;
    }
    public Negocio get_local()
    {
        return local;
    }


    // metodo para conocer la distancia en x
    public int distancia_x(JLabel objeto)
    {
        return (objeto.getX()+objeto.getWidth()) + 10;
    }
    public int distancia_x(JButton objeto)
    {
        return (objeto.getX()+objeto.getWidth() + 10);
    }
    public int distancia_x(JTextField objeto)
    {
        return (objeto.getX()+objeto.getWidth()) + 10;
    }


    // metodo para conocer la distancia en y
    public int distancia_y(JLabel objeto)
    {
        return (objeto.getY()+objeto.getHeight()) + 10;
    }
    public int distancia_y(JButton objeto)
    {
        return (objeto.getY()+objeto.getHeight()) + 10;
    }
    public int distancia_y(JTextField objeto)
    {
        return (objeto.getY()+objeto.getHeight()) + 10;
    }


    // metodo para agregar un mensaje de error numerico
    public void mensaje_error(JLabel mensaje, JButton boton, JPanel panel, boolean mostrar)
    {
        // texto de error
        mensaje.setBounds(((width-500) / 2),
                distancia_y(boton),
                500,
                30); // posicion y tamaño
        mensaje.setForeground(Color.RED); // color del texto
        mensaje.setFont(new Font("Verdana", 1, 16));
        mensaje.setVisible(mostrar);
        panel.add(mensaje);
    }


    // metodo para llamar el panel inicial
    private void panel_inicio(Negocio local)
    {

        menu_principal.setLayout(null);
        this.getContentPane().add(menu_principal);
        menu_principal.setBackground(Color.lightGray);

        // carga el logo del negocio
        ImageIcon imagen = new ImageIcon("logo.png");
        JLabel logo = new JLabel();
        logo.setBounds(((width-204) / 2),
                ((height-202) / 8),
                204,
                200);
        logo.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH)));
        menu_principal.add(logo);

        // nombre del negocio
        JLabel nombre = new JLabel("Variedades tecnologicas", SwingConstants.CENTER);
        nombre.setBounds(0,
                distancia_y(logo),
                500,
                40);
        nombre.setForeground(Color.darkGray);
        nombre.setFont(new Font("Verdana", 3, 25));
        menu_principal.add(nombre);

        // boton para el menu de los minutos
        JButton minutos = new JButton("Operadores");
        minutos.setBounds(( (width-150)/2),
                distancia_y(nombre),
                150,
                40);
        minutos.setFont(new Font("Verdana", 1, 16));
        menu_principal.add(minutos);

        // boton para el menu de las fotocopiadoras
        JButton fotocopias = new JButton("Fotocopias");
        fotocopias.setBounds(( (width-150)/2),
                distancia_y(minutos),
                150,
                40);
        // habilita / desabilita el boton
        // Fotocopias.setEnabled(false);
        // define un atajo del teclado (alt + tecla)
        // Fotocopias.setMnemonic('a');
        fotocopias.setFont(new Font("Verdana", 1, 16));
        menu_principal.add(fotocopias);

        // boton el cierre del dia
        JButton cierre = new JButton("Cerrar");
        cierre.setBounds(( (width-150)/2),
                distancia_y(fotocopias),
                150,
                40);
        cierre.setFont(new Font("Verdana", 1, 16));
        menu_principal.add(cierre);

        // evento de operador
        ActionListener opera = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menu_principal.setVisible(false);
                panel_operador(local);
                menu_operador.setVisible(true);
            }
        };
        minutos.addActionListener(opera);

        // evento de cierre
        ActionListener cerrar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menu_principal.setVisible(false);
                cierre_dia(local);
            }
        };
        cierre.addActionListener(cerrar);
    }


    // metodo para llamar el panel de operador
    private void panel_operador(Negocio local)
    {
        int valor_total[]={0}, costo_total[]={0};

        // carga los datos del archivo
        local.cargar_operadores();

        menu_operador.setLayout(null);
        this.getContentPane().add(menu_operador);
        menu_operador.setBackground(Color.lightGray);

        // texto del operador
        JLabel t_ope = new JLabel("operador", SwingConstants.CENTER);
        t_ope.setBounds(((width-120) / 3),
                ((height-30) / 3),
                120,
                30);
        t_ope.setForeground(Color.darkGray); // color del texto
        t_ope.setFont(new Font("Verdana", 0, 22));
        menu_operador.add(t_ope);

        // lista desplegable de los operadores
        String[] lista = {"WOM",
                "Claro",
                "Movistar"};
        JComboBox desplegable = new JComboBox(lista);
        desplegable.setBounds(t_ope.getX(),
                distancia_y(t_ope),
                120,
                40);
        desplegable.setForeground(Color.darkGray); // color del texto
        desplegable.setFont(new Font("Verdana", 3, 14));
        menu_operador.add(desplegable);

        // texto de la cantidad
        JLabel t_can = new JLabel("cantidad", SwingConstants.CENTER);
        t_can.setBounds(distancia_x(t_ope),
                t_ope.getY(),
                120,
                30); // posicion y tamaño
        t_can.setForeground(Color.darkGray);
        t_can.setFont(new Font("Verdana", 0, 22));
        menu_operador.add(t_can);

        // caja de texto para la cantidad
        JTextField caja_cantidad = new JTextField();
        caja_cantidad.setBounds(t_can.getX(),
                distancia_y(t_can),
                120,
                40);
        caja_cantidad.setFont(new Font("Verdana", 1, 16));
        menu_operador.add(caja_cantidad);

        // boton para confirmar
        JButton confirmar = new JButton("Confirmar");
        confirmar.setBounds(caja_cantidad.getX(),
                distancia_y(caja_cantidad),
                120,
                40);
        confirmar.setFont(new Font("Verdana", 1, 14));
        menu_operador.add(confirmar);

        // capturar evento del boton confirmar
        ActionListener pago = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mensaje_error(mensaje_error, confirmar, menu_operador, false);
                try
                {
                    int cantidad = Integer.parseInt(caja_cantidad.getText());

                    // si la cantidad es negativa lanza un error
                    if(cantidad < 1)
                    {
                        throw new NumberFormatException("----- numero negativo -----");
                    }

                    // ciclo para definir el operador
                    for (int i=0; i<local.getLisOper().size(); i++)
                    {
                        String seleccionado = (String) desplegable.getSelectedItem();
                        if(Objects.equals(seleccionado, local.getLisOper().get(i).get_nombre()))
                        {
                            local.usar_opera(i);
                            System.out.println(seleccionado + local.get_opera().get_venta_sim());
                            break;
                        }
                    }

                    // define el total del dinero a pagar
                    local.set_total(2,
                            cantidad);
                    valor_total[0] = local.get_venta();
                    costo_total[0] = local.get_venta();
                    System.out.println(">>>>> VALOR A PAGAR: $" + valor_total[0]);

                    // lleva a la pestaña efectivo con los valores ya asignados
                    panel_efectivo(local, valor_total[0], costo_total[0]);

                    // desaparece la ventana actual
                    caja_cantidad.setText("");
                    menu_operador.setVisible(false);
                    efectivo.setVisible(true);
                }
                catch (NumberFormatException error)
                {
                    System.out.println("----- valor invalido -----");
                    mensaje_error(mensaje_error, confirmar, menu_operador, true);
                }
            }
        };
        mensaje_error(mensaje_error, confirmar, menu_operador, false);
        confirmar.addActionListener(pago);
    }


// funcion para llamar el menu de las fotocopiadoras
    public static void menu_fotocopias(Negocio local, Scanner getDate)
    {
        int tipo_foto;

        // ciclo para listar cada una de las fotocopiadoras registradas
        System.out.println("\n----- lista de fotocopiadoras -----");
        for (int i = 0; i < local.get_fotolista().size() ; i++)
        {
            System.out.println((i+1) + ") " + local.get_fotolista().get(i).get_nombre());
        }

        // pregunta la fotocopiadora a usar
        System.out.print("Ingresa la fotocopiadora a utilizar: ");
        tipo_foto = getDate.nextInt();
        getDate.nextLine();

        local.usar_foto(tipo_foto);
        vender_fotocopia(local, getDate, tipo_foto);
    }


// metodo para llamar el panel del efectivo
    private void panel_efectivo(Negocio local, int dinero_a_pagar, int costo_produccion)
    {
        efectivo.setLayout(null);
        this.getContentPane().add(efectivo);
        efectivo.setBackground(Color.lightGray);

        // texto del efectivo
        JLabel titulo = new JLabel("Efectivo", SwingConstants.CENTER);
        titulo.setBounds(((width-500) / 2),
                ((height-40) / 3),
                500,
                30); // posicion y tamaño
        titulo.setForeground(Color.darkGray); // color del texto
        titulo.setFont(new Font("Verdana", 0, 22));
        efectivo.add(titulo);

        // crear caja de texto
        JTextField caja_efectivo = new JTextField();
        caja_efectivo.setBounds( ((width-120) / 2),
                distancia_y(titulo),
                120,
                40);
        caja_efectivo.setFont(new Font("Verdana", 1, 16));
        efectivo.add(caja_efectivo);

        // boton para ingresar el efectivo ----------
        JButton pago = new JButton("Ingresar");
        pago.setBounds(( (width-120)/2),
                distancia_y(caja_efectivo),
                120,
                40);
        pago.setFont(new Font("Verdana", 1, 16));
        efectivo.add(pago);


        // capturar evento del boton de pago
        ActionListener pagar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evento)
            {
                mensaje_error(mensaje_error, pago, efectivo, false);
                mensaje_error(dinero_insuficiente, pago, efectivo, false);
                try
                {
                    int pagado = Integer.parseInt(caja_efectivo.getText());

                    // si la cantidad es negativa lanza un error
                    if(pagado < 1)
                    {
                        throw new NumberFormatException("----- numero negativo -----");
                    }
                    else if(pagado < dinero_a_pagar)
                    {
                        throw new IllegalAccessException("----- dinero insuficiente -----");
                    }
                    else
                    {
                        System.out.println("----- dinero insuficiente -----");
                    }

                    local.registrar(1, pagado, dinero_a_pagar, costo_produccion);

                    efectivo.setVisible(false);
                    caja_efectivo.setText("");
                    menu_principal.setVisible(true);
                    System.out.println("----- venta realizada correctamente -----");
                }
                catch (NumberFormatException error)
                {
                    System.out.println("----- valor invalido -----");
                    mensaje_error(mensaje_error, pago, efectivo, true);
                }
                catch(IllegalAccessException pobre)
                {
                    System.out.println("----- dinero insuficiente -----");
                    mensaje_error(dinero_insuficiente, pago, efectivo, true);
                }
            }
        };
        pago.addActionListener(pagar);
    }


    // funcion para realizar una venta de fotocopias
    public static void vender_fotocopia(Negocio local, Scanner getDate, int numero_foto)
    {
        // atributos generales
        int tinta, efectivo, valor_total, costo_total;
        // atributos del plotter
        int ancho, largo, area, impresion = 0;

        // imprimir en el plotter
        if(numero_foto == 3)
        {
            // pregunta el tipo de impresion
            System.out.println("\n(1) Afiche publicitario "
                    + "\n(2) Plano arquitectonico");
            System.out.print("Ingrese el tipo de impresion a realizar: ");
            impresion = getDate.nextInt();

            // pregunta las medidas de la impresion
            System.out.print("\nIngrese el ancho en CM: ");
            ancho = getDate.nextInt();
            System.out.print("- Ingrese el largo en CM: ");
            largo = getDate.nextInt();
            area = ancho * largo;
        }
        // imprimir en las otras fotocopiadoras
        else
        {
            // pregunta la cantidad de copias a imprimir
            System.out.print("- Cantidad de copias: ");
            area = getDate.nextInt();
        }

        // pregunta el color de la tinta a utilizar
        System.out.println("\n(1) Color " +
                            "\n(2) Blanco y negro");
        System.out.print("Ingrese el color de la tinta: ");
        tinta = getDate.nextInt();

        // muestra el valor total de la impresion
        local.set_total(local.getFotoP().get_nombre(),
                        tinta,
                        area,
                        impresion);
        valor_total = local.get_venta();
        costo_total = local.get_costo();
        System.out.println(">>>>> VALOR A PAGAR: $" + valor_total);

        // pide el efectivo al cliente
        System.out.print("Ingrese su efectivo: $");
        efectivo = getDate.nextInt();

        // registra el pago en el negocio
        if(local.registrar(2, efectivo, valor_total, costo_total))
        {
            System.out.println("----- venta realizada con exito -----");
            local.escribir_impresora(numero_foto, area, tinta);
        }
        else
        {
            System.out.println("----- dinero insuficiente -----");
        }
    }


    // funcion para mostrar el menu crud de los operadores
    public static void crud_operadores(Negocio local, Scanner getDate)
    {
        String nombre_operador;
        int opcion, opcion_auxiliar, costo_minuto, valor_venta, costo_sim, venta_sim;

        System.out.println("\n1) Crear operador");
        System.out.println("2) Modificar operador");
        System.out.println("3) Eliminar operador");
        System.out.print("Ingresa tu opcion: ");
        opcion = getDate.nextInt();
        getDate.nextLine();


        switch(opcion)
        {
            case 1: // crear nuevo operador
            {
                System.out.print("- Nombre: ");
                nombre_operador = getDate.nextLine();
                System.out.print("- Costo/minuto: ");
                costo_minuto = getDate.nextInt();
                System.out.print("- Venta/minuto: ");
                valor_venta = getDate.nextInt();
                System.out.print("- Costo/SIM: ");
                costo_sim = getDate.nextInt();
                System.out.print("- Venta/SIM: ");
                venta_sim = getDate.nextInt();
                local.crear_operador(nombre_operador, costo_minuto, valor_venta, costo_sim, venta_sim);
            }; break;

            case 2: // modificar nuevo operador
            {
                System.out.println("----- lista de operadores -----");
                for (int i = 0; i < local.getLisOper().size() ; i++)
                {
                    System.out.println((i+1)+") "+local.getLisOper().get(i).get_nombre());
                }

                System.out.print("Ingresa el numero de operador a modificar: ");
                opcion_auxiliar = getDate.nextInt();
                getDate.nextLine();

                System.out.print("- Nombre: ");
                nombre_operador = getDate.nextLine();
                System.out.print("- Costo/minuto: ");
                costo_minuto = getDate.nextInt();
                System.out.print("- Venta/minuto: ");
                valor_venta = getDate.nextInt();
                System.out.print("- Costo/SIM: ");
                costo_sim = getDate.nextInt();
                System.out.print("- Venta/SIM: ");
                venta_sim = getDate.nextInt();

                local.modificar_operador(opcion_auxiliar, nombre_operador, costo_minuto, valor_venta, costo_sim, venta_sim);
            }; break;

            case 3: // eliminar un operador
            {
                System.out.println("----- lista de operadores -----");
                for (int i = 0; i < local.getLisOper().size() ; i++)
                {
                    System.out.println((i+1)+") "+local.getLisOper().get(i).get_nombre());
                }

                System.out.print("Ingresa el numero de operador a eliminar: ");
                opcion_auxiliar = getDate.nextInt();
                getDate.nextLine();

                local.eliminar_operador(opcion_auxiliar);
            }; break;
        }
    }

    // funcion para mostrar el menu crud de las fotocopiadora
    public static void crud_fotocopiadoras(Negocio local, Scanner getDate)
    {
        String tipo;
        int opcion, opcion_auxiliar, costoHBN, costoHC, valorVentaHC, valorVentaHBN;

        System.out.println("\n1) Crear fotocopiadora");
        System.out.println("2) Modificar fotocopiadora");
        System.out.println("3) Eliminar fotocopiadora");
        System.out.print("Ingresa tu opcion: ");
        opcion = getDate.nextInt();
        getDate.nextLine();
        switch(opcion)
        {
            case 1: // crear una nueva fotocopiadora
            {
                System.out.print("- Nuevo tipo de fotocopiadora: ");
                tipo = getDate.nextLine();
                System.out.print("- Nuevo costo de HBN: ");
                costoHBN = getDate.nextInt();
                System.out.print("- Nuevo costo de HC: ");
                costoHC = getDate.nextInt();
                System.out.print("- Nuevo valor de venta HC: ");
                valorVentaHC = getDate.nextInt();
                System.out.print("- Nuevo valor de venta HBN: ");
                valorVentaHBN = getDate.nextInt();

                local.crear_fotocopiadora(tipo, costoHBN, costoHC, valorVentaHC, valorVentaHBN);

            }; break;

            case 2: // modificar una fotocopiadora
            {
                System.out.println("\n----- lista de fotocopias -----");
                for (int i = 0; i < local.get_fotolista().size() ; i++)
                {
                    System.out.println((i+1) + ") " + local.get_fotolista().get(i).get_nombre());
                }

                System.out.print("Ingresa el numero de la fotocopiadora a modificar: ");
                opcion_auxiliar = getDate.nextInt();
                getDate.nextLine();

                System.out.print("- Nuevo tipo de fotocopiadora: ");
                tipo = getDate.nextLine();
                System.out.print("- Nuevo costo de HBN: ");
                costoHBN = getDate.nextInt();
                System.out.print("- Nuevo costo de HC: ");
                costoHC = getDate.nextInt();
                System.out.print("- Nuevo valor de venta HC: ");
                valorVentaHC = getDate.nextInt();
                System.out.print("- Nuevo valor de venta HBN: ");
                valorVentaHBN = getDate.nextInt();

                local.crear_fotocopiadora(tipo, costoHBN, costoHC, valorVentaHC, valorVentaHBN);

            }; break;

            case 3: // eliminar una fotocopiadora
            {
                System.out.println("\n----- lista de fotocopias -----");
                for (int i = 0; i < local.get_fotolista().size() ; i++)
                {
                    System.out.println((i+1) + ") " + local.get_fotolista().get(i).get_nombre());
                }

                System.out.print("Ingresa el numero de la fotocopiadora a modificar: ");
                opcion_auxiliar = getDate.nextInt();
                getDate.nextLine();

                local.eliminar_fotocopiadora(opcion_auxiliar);
            }; break;
        }
    }


    // funcion para calcular calcular el dinero del dia
    public static void cierre_dia(Negocio local)
    {
        int ventas, costo = 0, foto_gana, minu_gana;

        // estadisticas de operador
        ventas = local.get_opera().get_recolectado();
        minu_gana = local.get_opera().get_recolectado() - local.get_opera().get_produccion();
        costo += (local.get_opera().get_produccion());

        // rendimiento de las fotocopias
        ventas += local.getFotoP().get_recolectado();
        foto_gana = local.getFotoP().get_recolectado() - local.getFotoP().get_produccion();
        costo += (local.getFotoP().get_produccion()
                + local.getCosto_mpleado()
                + local.getCosto_energia());

        System.out.println("******************** CIERRE DEL DIA ********************");
        System.out.println("VALOR RECOLECTADO: $" + ventas);
        System.out.println(">>>>> GANANCIAS TOTALES: $" + ((foto_gana+minu_gana) - costo));
        System.out.println("      - OPERADORES: $" + (minu_gana));
        System.out.println("      - FOTOCOPIAS: $" + (foto_gana));
        System.out.println("***** COSTOS DE PRODUCCION: $" + costo);

        // muestra el servicio mas rentable junto a sus ganancias
        if(minu_gana > foto_gana)
        {
            System.out.println("----- servicio mas rentable: MINUTOS ($" + minu_gana + ") -----");
        }
        else if(foto_gana > minu_gana)
        {
            System.out.println("----- servicio mas rentable: FOTOCOPIAS ($" + foto_gana + ") -----");
        }
        else
        {
            System.out.println("----- NINGUNO ha generado ingresos -----");
        }
    }



    // metodo para inicializar el programa
    public static void main(String[] args)
    {
        String opcion;

        Main ventana = new Main();


        //local.cargar_impresoras();


        /* ciclo para mostrar el menu de opciones
        do
        {
            System.out.println("\n\n***** BIENVENIDO A VARIEDADES TECNOLOGICAS *****");
            System.out.println("\n(1) Minutos");
            System.out.println("(2) Fotocopias");
            System.out.println("(3) CRUD minutos");
            System.out.println("(4) CRUD fotocopiadora");
            System.out.println("(5) Cierre Día");
            System.out.println("(6) Salir");

            System.out.print("\nIngresa tu opcion: ");
            opcion = getDate.next();
            getDate.nextLine();


            switch (opcion)
            {
                case "2":
                    menu_fotocopias(local,getDate);
                    break;
                case "3":
                    crud_operadores(local, getDate);
                    break;
                case "4":
                    crud_fotocopiadoras(local, getDate);
                    break;
                case "5":
                    cierre_dia(local);
                    break;
            }
        }
        while(!opcion.equals("5"));
        */
    }
}