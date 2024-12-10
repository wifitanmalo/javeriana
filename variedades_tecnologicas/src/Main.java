import java.util.Scanner;

public class Main
{
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


// funcion para mostrar el menu de los operadores
    public static void menu_minutos(Negocio local,Scanner getDate)
    {
        int op, opcion, cantidad, valor_total, costo_total, efectivo;

        // pregunta el tipo de movimiento a realizar
        System.out.println("\n(1) Llamar operador "
                + "\n(2) Comprar SIM Card");
        System.out.print("Ingrese una opcion: ");
        op = getDate.nextInt();

        // ciclo para mostrar la lista de operadores
        System.out.println("\n----- lista de operadores -----");
        for (int i = 0; i < local.getLisOper().size() ; i++)
        {
            System.out.println((i+1) + ") " + local.getLisOper().get(i).get_nombre());
        }
        System.out.print("Ingrese el operador: ");
        opcion = getDate.nextInt();
        local.usar_opera(opcion);

        if(op == 1)
        {
            System.out.print("- Cantidad de minutos: ");
        }
        else
        {
            System.out.print("- Cantidad de SIM Cards: ");
        }

        // pide la cantidad de minutos/SIM Card a comprar
        cantidad = getDate.nextInt();

        // muestra el valor total de los minutos
        local.set_total(op, cantidad);
        valor_total = local.get_venta();
        costo_total = local.get_costo();
        System.out.println(">>>>> VALOR A PAGAR: $" + valor_total);

        // pide el efectivo al cliente
        System.out.print("Ingrese su efectivo: $");
        efectivo = getDate.nextInt();

        if(local.registrar(1, efectivo, valor_total, costo_total))
        {
            System.out.println("----- venta realizada con exito -----");
        }
        else
        {
            System.out.println("----- dinero insuficiente -----");
        }

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

    // metodo para inicializar el programa
    public static void main(String[] args)
    {
        String opcion;

        Ventana ventana = new Ventana();

        // se hace visible la ventana
        ventana.setVisible(true);
        ventana.setResizable(false);

        // objeto de la clase Negocio
        Negocio local = new Negocio(35000,12500);
        Scanner getDate = new Scanner(System.in);

        // carga los datos de los archivos
        local.cargar_operadores();
        local.cargar_impresoras();

        // ciclo para mostrar el menu de opciones
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
                case "1":
                    menu_minutos(local,getDate);
                    break;
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
    }
}