//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends JFrame
{
    // medidas de la ventana
    int width = 500, height = 500;

    // objeto del negocio
    private Negocio local;

    // mensaje de error
    JLabel mensaje_error = new JLabel("Ingrese un numero positivo", SwingConstants.CENTER);
    JLabel dinero_insuficiente = new JLabel("Dinero insuficiente", SwingConstants.CENTER);


    JTabbedPane impresoras = new JTabbedPane( );

    // crea los paneles a utilizar
    private JPanel menu_principal = new JPanel();
    private JPanel efectivo = new JPanel();
    private JPanel menu_operador = new JPanel();
    private JPanel foto_menu = new JPanel();


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
        return (objeto.getX()+objeto.getWidth()) + 20;
    }
    public int distancia_x(JButton objeto)
    {
        return (objeto.getX()+objeto.getWidth() + 20);
    }
    public int distancia_x(JRadioButton objeto)
    {
        return (objeto.getX()+objeto.getWidth() + 20);
    }

    public int distancia_x(JTextField objeto)
    {
        return (objeto.getX()+objeto.getWidth()) + 20;
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
    public int distancia_y(JRadioButton objeto)
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
        // carga los archivos para cargar sus datos en listas
        local.cargar_operadores();
        local.cargar_impresoras();

        menu_principal.setLayout(null);
        this.getContentPane().add(menu_principal);
        menu_principal.setBackground(Color.lightGray);

        // nombre del negocio
        JLabel nombre = new JLabel("Variedades tecnologicas", SwingConstants.CENTER);
        nombre.setBounds((width-400)/2,
                ((height-40) / 8),
                400,
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

        // evento de fotocopias
        ActionListener foto = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menu_principal.setVisible(false);
                menu_impresoras();
                impresoras.setVisible(true);
            }
        };
        fotocopias.addActionListener(foto);

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
        menu_operador.setLayout(null);
        this.getContentPane().add(menu_operador);
        menu_operador.setBackground(Color.lightGray);

        // botones de seleccion
        JRadioButton llamar = radio_boton("Llamar",
                                    ((width-130) / 2),
                                     ((height-30) / 4),
                                    menu_operador.getBackground());
        menu_operador.add(llamar);
        JRadioButton sim = radio_boton("SIM Card",
                                        llamar.getX(),
                                        distancia_y(llamar),
                                        menu_operador.getBackground());
        menu_operador.add(sim);

        // añade los botones a un grupo de botones
        ButtonGroup grupo_botones = new ButtonGroup();
        grupo_botones.add(llamar);
        grupo_botones.add(sim);

        // texto del operador
        JLabel t_ope = new JLabel("operador", SwingConstants.CENTER);
        t_ope.setBounds(((width-120) / 3),
                distancia_y(sim),
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
                mensaje_error(mensaje_error,
                            confirmar,
                            menu_operador,
                            false);
                try
                {
                    int cantidad = Integer.parseInt(caja_cantidad.getText());

                    // si la cantidad es negativa lanza un error
                    if(cantidad < 1)
                    {
                        throw new NumberFormatException("----- numero negativo -----");
                    }

                    String seleccionado = (String) desplegable.getSelectedItem();

                    // define el total del dinero a pagar
                    if(llamar.isSelected())
                    {
                        // llama al panel efectivo
                        panel_efectivo(local, 1, 1, cantidad, seleccionado);
                    }
                    else
                    {
                        // llama al panel efectivo
                        panel_efectivo(local, 1, 2, cantidad, seleccionado);
                    }

                    // desaparece la ventana actual
                    caja_cantidad.setText("");
                    menu_operador.setVisible(false);
                    efectivo.setVisible(true);
                }
                catch (NumberFormatException error)
                {
                    System.out.println("----- valor invalido -----");
                    mensaje_error(mensaje_error,
                            confirmar,
                            menu_operador,
                            true);
                }
            }
        };
        mensaje_error(mensaje_error,
                        confirmar,
                        menu_operador,
                        false);
        confirmar.addActionListener(pago);
    }


    // metodo para llamar el panel de operador
    public void menu_impresoras()
    {
        limpiar_pestana(impresoras);

        int contador = 0;

        // Lista con elementos para generar pestañas
        ArrayList<String> elementos = new ArrayList<>();

        for (int i=0; i<local.get_fotolista().size(); i++)
        {
            String nombre = local.get_fotolista().get(i).get_nombre();
            elementos.add(nombre);
        }

        // Crear pestañas dinámicamente basadas en la lista
        for (String elemento : elementos)
        {
            contador++;
            JPanel panel = new JPanel();
            panel.setLayout(null);

            // define los valores de la impresora a usar
            local.usar_foto(contador);

            // carga los archivos de las imagenes
            ImageIcon imagen = new ImageIcon("logo.png");
            ImageIcon b = new ImageIcon("black.png");
            ImageIcon y = new ImageIcon("yellow.png");
            ImageIcon c = new ImageIcon("cyan.png");
            ImageIcon m = new ImageIcon("magenta.png");

            JLabel imagen1 = cargar_imagen(imagen,
                    ((width - 204) / 2),
                    ((height - 202) / 8),
                    204,
                    200);

            // añade nombre de la impresora en el menu
            JLabel nombre = new JLabel(elemento, SwingConstants.CENTER);
            nombre.setBounds(imagen1.getX(),
                    distancia_y(imagen1),
                    200,
                    30);
            nombre.setForeground(Color.darkGray);
            nombre.setFont(new Font("Verdana", 1, 20));

            // añade los recuadros de color
            JLabel negro = cargar_imagen(b,
                    (width-66)/5,
                    distancia_y(nombre),
                    66,
                    40);
            JLabel amarillo = cargar_imagen(y,
                    distancia_x(negro),
                    negro.getY(),
                    66,
                    40);
            JLabel cian = cargar_imagen(c,
                    distancia_x(amarillo),
                    negro.getY(),
                    66,
                    40);
            JLabel magenta = cargar_imagen(m,
                    distancia_x(cian),
                    negro.getY(),
                    66,
                    40);

            // añade los porcentajes de cada tinta
            JLabel por_1 = porcentaje(
                    negro.getX(),
                    negro.getY(),
                    Color.WHITE,
                    local.getFotoP().get_negro());
            JLabel por_2 = porcentaje(
                    amarillo.getX(),
                    amarillo.getY(),
                    Color.darkGray,
                    local.getFotoP().get_amarillo());
            JLabel por_3 = porcentaje(
                    cian.getX(),
                    cian.getY(),
                    Color.darkGray,
                    local.getFotoP().get_cian());
            JLabel por_4 = porcentaje(
                    magenta.getX(),
                    magenta.getY(),
                    Color.darkGray,
                    local.getFotoP().get_magenta());

            // botones de seleccion
            JRadioButton tinta_negra = radio_boton("Blanco y negro",
                    ((width-192) / 3),
                    distancia_y(negro),
                    panel.getBackground());
            JRadioButton tinta_color = radio_boton("Color",
                    distancia_x(tinta_negra),
                    tinta_negra.getY(),
                    panel.getBackground());

            // grupo para agrupar los botones de seleccion
            ButtonGroup grupo_tinta = new ButtonGroup();
            grupo_tinta.add(tinta_negra);
            grupo_tinta.add(tinta_color);

            // boton para usar la impresora
            JButton usar = new JButton("Usar");
            usar.setBounds( (width-150)/2,
                    distancia_y(tinta_negra),
                    150,
                    40);
            usar.setFont(new Font("Verdana", 1, 16));


            // evento de la impresion
            ActionListener uso = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int indice = impresoras.getSelectedIndex();
                    String nombre = impresoras.getTitleAt(indice);

                    if(Objects.equals(local.get_fotolista().get(indice).get_nombre(), "Plotter"))
                    {
                        vender_impresion(local, nombre);
                    }
                    else
                    {
                        // define el total del dinero a pagar
                        if(tinta_negra.isSelected())
                        {
                            // llama al panel efectivo
                            vender_impresion(local, nombre, tinta_negra.getText());
                        }
                        else
                        {
                            // llama al panel efectivo
                            vender_impresion(local, nombre, tinta_color.getText());
                        }
                    }

                    System.out.println("Impresora seleccionada: " + nombre);

                    impresoras.setVisible(false);
                    foto_menu.setVisible(true);
                }
            };
            usar.addActionListener(uso);

            // agrega el logo y el nombre de la impresora al panel
            panel.add(imagen1);
            panel.add(nombre);

            // agrega los porcentajes de cada tinta
            panel.add(por_1);
            panel.add(por_2);
            panel.add(por_3);
            panel.add(por_4);

            // agrega los recuadros de cada tinta
            panel.add(negro);
            panel.add(amarillo);
            panel.add(cian);
            panel.add(magenta);

            // agrega los botones de seleccion
            panel.add(tinta_negra);
            panel.add(tinta_color);

            // agrega el boton de usar
            panel.add(usar);

            impresoras.addTab(elemento, panel);
        }

        getContentPane().add(impresoras);
    }


    // metodo para vender una impresion normal
    public void vender_impresion(Negocio local, String nombre_impresora, String opcion)
    {
        limpiar_pestana(foto_menu);

        foto_menu.setLayout(null);
        this.getContentPane().add(foto_menu);
        foto_menu.setBackground(Color.lightGray);

        // texto del efectivo
        JLabel t_can = new JLabel("Cantidad", SwingConstants.CENTER);
        t_can.setBounds(((width-500) / 2),
                ((height-40) / 3),
                500,
                30); // posicion y tamaño
        t_can.setForeground(Color.darkGray); // color del texto
        t_can.setFont(new Font("Verdana", 0, 22));
        foto_menu.add(t_can);

        // crear caja de texto
        JTextField caja_cantidad = new JTextField();
        caja_cantidad.setBounds( ((width-120) / 2),
                distancia_y(t_can),
                120,
                40);
        caja_cantidad.setFont(new Font("Verdana", 1, 16));
        foto_menu.add(caja_cantidad);

        // boton para confirmar la cantidad
        JButton pago = new JButton("Ingresar");
        pago.setBounds(( (width-120)/2),
                distancia_y(caja_cantidad),
                120,
                40);
        pago.setFont(new Font("Verdana", 1, 16));
        foto_menu.add(pago);

        // capturar evento del boton confirmar
        ActionListener pagar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mensaje_error(mensaje_error,
                        pago,
                        menu_operador,
                        false);
                try
                {
                    int cantidad = Integer.parseInt(caja_cantidad.getText());

                    // si la cantidad es negativa lanza un error
                    if(cantidad < 1)
                    {
                        throw new NumberFormatException("----- numero negativo -----");
                    }

                    int numero = 0;

                    // desaparece la ventana actual
                    for(int i=0; i<local.get_fotolista().size(); i++)
                    {
                        if(Objects.equals(nombre_impresora, local.get_fotolista().get(i).get_nombre()))
                        {
                            numero = i;
                            break;
                        }
                    }

                    if(Objects.equals(opcion, "Blanco y negro"))
                    {
                        panel_efectivo(local, 2, 1, cantidad, nombre_impresora);
                        local.escribir_impresora(numero, cantidad, 1);
                    }
                    else
                    {
                        panel_efectivo(local, 2, 2, cantidad, nombre_impresora);
                        local.escribir_impresora(numero, cantidad, 2);
                    }
                    foto_menu.setVisible(false);
                    efectivo.setVisible(true);
                }
                catch (NumberFormatException error)
                {
                    System.out.println("----- valor invalido -----");
                    mensaje_error(mensaje_error,
                            pago,
                            menu_operador,
                            true);
                }
                caja_cantidad.setText("");
            }
        };
        mensaje_error(mensaje_error,
                pago,
                menu_operador,
                false);
        pago.addActionListener(pagar);
    }

    // metodo para vender una impresion de plotter
    public void vender_impresion(Negocio local, String nombre_impresora)
    {
        limpiar_pestana(foto_menu);

        foto_menu.setLayout(null);
        this.getContentPane().add(foto_menu);
        foto_menu.setBackground(Color.lightGray);

        // botones de seleccion
        JRadioButton afiche = radio_boton("Afiche",
                ((width-192) / 3),
                ((height-40) / 5),
                foto_menu.getBackground());
        foto_menu.add(afiche);
        JRadioButton plano = radio_boton("Plano",
                distancia_x(afiche),
                afiche.getY(),
                foto_menu.getBackground());
        foto_menu.add(plano);

        // grupo para agrupar los botones de seleccion
        ButtonGroup grupo_tipo = new ButtonGroup();
        grupo_tipo.add(afiche);
        grupo_tipo.add(plano);

        // texto del efectivo
        JLabel ancho = new JLabel("Ancho", SwingConstants.CENTER);
        ancho.setBounds(((width-120) / 2),
                distancia_y(afiche),
                120,
                40); // posicion y tamaño
        ancho.setForeground(Color.darkGray); // color del texto
        ancho.setFont(new Font("Verdana", 0, 22));
        foto_menu.add(ancho);

        // crear caja de texto del ancho
        JTextField caja_ancho = new JTextField();
        caja_ancho.setBounds(((width-120) / 2),
                distancia_y(ancho),
                120,
                40);
        caja_ancho.setFont(new Font("Verdana", 1, 16));
        foto_menu.add(caja_ancho);

        // crear caja de texto del ancho
        JLabel largo = new JLabel("Largo", SwingConstants.CENTER);
        largo.setBounds(ancho.getX(),
                distancia_y(caja_ancho),
                120,
                30); // posicion y tamaño
        largo.setForeground(Color.darkGray); // color del texto
        largo.setFont(new Font("Verdana", 0, 22));
        foto_menu.add(largo);

        // crear caja de texto
        JTextField caja_largo = new JTextField();
        caja_largo.setBounds( ancho.getX(),
                distancia_y(largo),
                120,
                40);
        caja_largo.setFont(new Font("Verdana", 1, 16));
        foto_menu.add(caja_largo);


        // boton para confirmar la cantidad
        JButton pago = new JButton("Ingresar");
        pago.setBounds(ancho.getX(),
                distancia_y(caja_largo),
                120,
                40);
        pago.setFont(new Font("Verdana", 1, 16));
        foto_menu.add(pago);

        // capturar evento del boton confirmar
        ActionListener accion = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mensaje_error(mensaje_error,
                        pago,
                        foto_menu,
                        false);
                try
                {
                    int an = Integer.parseInt(caja_ancho.getText());
                    int lar = Integer.parseInt(caja_largo.getText());

                    // si la cantidad es negativa lanza un error
                    if(an < 1 || lar < 1)
                    {
                        throw new NumberFormatException("----- numero negativo -----");
                    }

                    int numero = 0;

                    // desaparece la ventana actual
                    for(int i=0; i<local.get_fotolista().size(); i++)
                    {
                        if(nombre_impresora == local.get_fotolista().get(i).get_nombre())
                        {
                            numero = i;
                        }
                    }


                    // define el total del dinero a pagar
                    if(afiche.isSelected())
                    {
                        // llama al panel efectivo
                        panel_efectivo(local,
                                2,
                                1,
                                an*lar,
                                nombre_impresora);
                        local.escribir_impresora(numero, an*lar, 1);
                    }
                    else
                    {
                        // llama al panel efectivo
                        panel_efectivo(local,
                                2,
                                2,
                                an*lar,
                                nombre_impresora);
                        local.escribir_impresora(numero, an*lar, 2);
                    }

                    // desaparece la ventana actual
                    foto_menu.setVisible(false);
                    efectivo.setVisible(true);
                }
                catch (NumberFormatException error)
                {
                    System.out.println("----- valor invalido -----");
                    mensaje_error(mensaje_error,
                            pago,
                            foto_menu,
                            true);
                }
                caja_ancho.setText("");
                caja_largo.setText("");
            }
        };
        mensaje_error(mensaje_error,
                pago,
                menu_operador,
                false);
        pago.addActionListener(accion);
    }


    // metodo para llamar el panel del efectivo
    private void panel_efectivo(Negocio local,
                                int servicio,
                                int opcion,
                                int cantidad,
                                String nombre)
    {
        if(servicio == 1)
        {
            local.set_total(opcion, cantidad, nombre);
        }
        else
        {
            local.set_total(nombre, cantidad, opcion);
        }
        System.out.println("VALOR A PAGAR: $" + local.get_venta());
        System.out.println("- Costo de produccion: $" + local.get_costo());

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
                mensaje_error(mensaje_error,
                                pago,
                                efectivo,
                                false);
                mensaje_error(dinero_insuficiente,
                                pago,
                                efectivo,
                                false);
                try
                {
                    int pagado = Integer.parseInt(caja_efectivo.getText());

                    // si la cantidad es negativa lanza un error
                    if(pagado != local.get_venta())
                    {
                        throw new NumberFormatException("----- dinero insuficiente -----");
                    }
                    else
                    {
                        efectivo.setVisible(false);
                        menu_principal.setVisible(true);

                        System.out.println("----- venta realizada correctamente -----");
                    }
                }
                catch (NumberFormatException error)
                {
                    System.out.println("----- dinero no coincidente -----");
                    mensaje_error(dinero_insuficiente,
                                    pago,
                                    efectivo,
                                    true);
                }
                caja_efectivo.setText("");
            }
        };
        pago.addActionListener(pagar);
    }


    // metodo para calcular el dinero del dia
    public static void cierre_dia(Negocio local)
    {
        int minutos=0, sim_card=0, ventas=0, costo =0, foto_gana;
        int sim_gana=0, sim_costo=0;

        // estadisticas de operador
        local.set_venta_minutos();
        local.set_venta_sim();

        // estadisticas de minutos
        int minu_venta = local.get_venta_minutos();
        int minu_costo = local.get_costo_minutos();

        // estadisticas de sim card
        sim_gana = local.get_venta_sim();
        sim_costo = local.get_costo_sim();

        // valores totales de operador
        int total_venta_operador = sim_gana + minu_venta;
        int total_costo_operador = sim_costo + minu_costo;


        for(int i=0; i<local.getLisOper().size(); i++)
        {
            System.out.println("Operador: $" + local.getLisOper().get(i).get_nombre());
            System.out.println("- Minutos: " + local.getLisOper().get(i).get_cantidad_minutos());
            System.out.println("- SIM Card: " + local.getLisOper().get(i).get_cantidad_sim());
            sim_card += local.getLisOper().get(i).get_cantidad_sim();
            minutos += local.getLisOper().get(i).get_cantidad_minutos();
        }

        // rendimiento de las fotocopias
        ventas += local.getFotoP().get_recolectado();
        foto_gana = local.getFotoP().get_recolectado() - local.getFotoP().get_produccion();
        costo += (local.getFotoP().get_produccion()
                + local.getCosto_mpleado()
                + local.getCosto_energia());

        System.out.println("******************** CIERRE DEL DIA ********************");
        System.out.println("VALOR RECOLECTADO: $" + ventas);
        System.out.println(">>>>> GANANCIAS TOTALES: $" + ((foto_gana+ minu_venta) - costo));
        System.out.println("      - OPERADORES: $" + total_venta_operador);
        System.out.println("      - Minutos totales: " + minutos);
        System.out.println("      - SIM Card: " + sim_card);
        System.out.println("      - FOTOCOPIAS: $" + foto_gana);
        System.out.println("***** COSTOS DE PRODUCCION: $" + costo);

        // muestra el servicio mas rentable junto a sus ganancias
        if(minu_venta > foto_gana)
        {
            System.out.println("----- servicio mas rentable: MINUTOS ($" + minu_venta + ") -----");
        }
        else if(foto_gana > minu_venta)
        {
            System.out.println("----- servicio mas rentable: FOTOCOPIAS ($" + foto_gana + ") -----");
        }
        else
        {
            System.out.println("----- NINGUNO ha generado ingresos -----");
        }
    }


    // metodo para para llamar las imagenes
    private JLabel cargar_imagen(ImageIcon imagen,
                                 int x,
                                 int y,
                                 int width,
                                 int height)
    {
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        return label;
    }


// metodo para llamar los porcentajes de las tintas
    private JLabel porcentaje(int x, int y, Color color, Double cantidad)
    {
        JLabel label = new JLabel(cantidad.toString()+"%", SwingConstants.CENTER);
        label.setBounds(x,
                y,
                60,
                40);
        label.setForeground(color);
        label.setFont(new Font("Verdana",
                1,
                10));
        return label;
    }

// metodo para llamar los botones de seleccion
    private JRadioButton radio_boton(String mensaje, int x, int y, Color color)
    {
        JRadioButton opcion = new JRadioButton(mensaje, true);
        opcion.setBounds(x,
                y,
                192,
                30);
        opcion.setForeground(Color.darkGray);
        opcion.setBackground(color);
        opcion.setFont(new Font("Verdana",
                0,
                22));
        return opcion;
    }

    public void limpiar_pestana(JTabbedPane tabulado)
    {
        // Limpia las pestañas existentes
        tabulado.removeAll();
        tabulado.revalidate();
        tabulado.repaint();
    }
    public void limpiar_pestana(JPanel panel)
    {
        // Limpia las pestañas existentes
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }



    // metodo para inicializar el programa
    public static void main(String[] args)
    {
        String opcion;
        Main ventana = new Main();
    }
}