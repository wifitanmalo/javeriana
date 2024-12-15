import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Negocio {
    // objetos de los servicios
    private Fotocopiadora fotoP;
    private Operador opera;


    // listas de los servicios
    private ArrayList<Operador> lista_operador;
    private ArrayList<Fotocopiadora> foto_lista;


    // rendimiento de ganancia/produccion
    private int venta;
    private int costo;


    // costos diarios de produccion
    private int costo_energia;
    private int costo_mpleado;

    // atributos de rendimiento
    private int venta_minutos;
    private int costo_minutos;
    private int venta_sim;
    private int costo_sim;


    // -------------------- constructor del negocio --------------------
    public Negocio(int costo_mpleado, int costo_energia) {
        // lista de los operadores
        lista_operador = new ArrayList<>();

        // lista de las fotocopiadoras
        foto_lista = new ArrayList<>();
        // el objeto recibe el valor de la fotocopiadora predeterminada

        // costos diarios del negocio
        this.costo_mpleado = costo_mpleado;
        this.costo_energia = costo_energia;
    }


    // ------------------------ objeto de operador ------------------------
    public void set_opera(Operador opera) {
        this.opera = opera;
    }

    public Operador get_opera() {
        return opera;
    }

    public void setLisOper(ArrayList<Operador> lisOper) {
        this.lista_operador = lisOper;
    }

    public ArrayList<Operador> getLisOper() {
        return lista_operador;
    }

    // metodo para usar el objeto del operador
    public void usar_opera(int posicion) {
        this.opera = lista_operador.get(posicion);
    }


    // ------------------------ objeto de fotocopiadora ------------------------
    public void setFotoP(Fotocopiadora fotoP) {
        this.fotoP = fotoP;
    }

    public Fotocopiadora getFotoP() {
        return fotoP;
    }

    public void set_fotolista(ArrayList<Fotocopiadora> lista_fotocopiadora) {
        this.foto_lista = lista_fotocopiadora;
    }

    public ArrayList<Fotocopiadora> get_fotolista() {
        return foto_lista;
    }

    // metodo para utilizar el objeto de la fotocopiadora
    public void usar_foto(int posicion) {
        fotoP = foto_lista.get(posicion - 1);
    }


    // valor del costo total
    public void set_total(int opcion, int cantidad, String nombre) {
        int posicion = 0;

        for (int i = 0; i < getLisOper().size(); i++) {
            if (Objects.equals(getLisOper().get(i).get_nombre(), nombre)) {
                posicion = i;
                break;
            }
        }

        if (opcion == 1) // llamar
        {
            venta = cantidad * getLisOper().get(posicion).get_venta_minuto();
            costo = cantidad * getLisOper().get(posicion).get_costo_minuto();
            getLisOper().get(posicion).set_cantidad_minutos(cantidad);
        } else // sim card
        {
            venta = cantidad * getLisOper().get(posicion).get_venta_sim();
            costo = cantidad * getLisOper().get(posicion).get_costo_sim();
            getLisOper().get(posicion).set_cantidad_sim(cantidad);
        }
    }

    // total de las fotocopias
    public void set_total(String nombre, int area_can, int impresion) {
        int posicion = 0;

        for (int i = 0; i < get_fotolista().size(); i++) {
            if (Objects.equals(get_fotolista().get(i).get_nombre(), nombre)) {
                posicion = i;
                break;
            }
        }

        // formula = cantidad * venta de hoja
        if (impresion == 1) // color
        {
            venta = area_can * get_fotolista().get(posicion).get_valor_venta_c();
            costo = area_can * get_fotolista().get(posicion).get_costo_c();
        } else // blanco y negro
        {
            venta = area_can * get_fotolista().get(posicion).get_valor_venta_bn();
            costo = area_can * get_fotolista().get(posicion).get_costo_bn();
        }
    }

    public int get_venta() {
        return venta;
    }

    public int get_costo() {
        return costo;
    }


    // metodos para definir y obtener la deuda diaria del negocio
    public void setCosto_energia(int costo_energia) {
        this.costo_energia = costo_energia;
    }

    public int getCosto_energia() {
        return costo_energia;
    }

    public void setCosto_mpleado(int costo_mpleado) {
        this.costo_mpleado = costo_mpleado;
    }

    public int getCosto_mpleado() {
        return costo_mpleado;
    }


    // -------------------- funcion para cargar los operadores --------------------
    public void cargar_operadores() {
        try {
            BufferedReader leer = new BufferedReader(new FileReader("operadores.txt"));
            String line;
            while ((line = leer.readLine()) != null) {
                // separa los datos por comas
                String[] datos = line.split(",");

                String nombre = datos[0];
                int costo = Integer.parseInt(datos[1]);
                int valor_venta = Integer.parseInt(datos[2]);
                int costo_sim = Integer.parseInt(datos[3]);
                int valor_sim = Integer.parseInt(datos[4]);

                Operador nuevo = new Operador(nombre, costo, valor_venta, costo_sim, valor_sim);
                this.lista_operador.add(nuevo); // agrega el nuevo operador a la lista
            }
            leer.close();
            opera = lista_operador.get(0);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }


    // funcion para cargar las impresoras
    public void cargar_impresoras() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("impresoras.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // separa cada linea por comas
                String[] datos = line.split(",");

                String nombre = datos[0];
                int costoHBN = Integer.parseInt(datos[1]);
                int costoHC = Integer.parseInt(datos[2]);
                int valorVentaHC = Integer.parseInt(datos[3]);
                int valorVentaHBN = Integer.parseInt(datos[4]);

                double negro = Double.parseDouble(datos[5]);
                double amarillo = Double.parseDouble(datos[6]);
                double cian = Double.parseDouble(datos[7]);
                double magenta = Double.parseDouble(datos[8]);

                double consumo_negro = Double.parseDouble(datos[9]);
                double consumo_ci_am = Double.parseDouble(datos[10]);
                double consumo_magenta = Double.parseDouble(datos[11]);

                Fotocopiadora nueva = new Fotocopiadora(nombre,
                        costoHBN,
                        costoHC,
                        valorVentaHC,
                        valorVentaHBN,
                        negro, amarillo,
                        cian,
                        magenta,
                        consumo_negro,
                        consumo_ci_am,
                        consumo_magenta);
                this.foto_lista.add(nueva);  // agrega la nueva fotocopiadora a la lista
            }
            reader.close();
            fotoP = foto_lista.get(0);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }


    // metodo para escribir en el archivo de las impresoras
    public void escribir_impresora(int impresora, int cantidad, int tinta) {
        String archivo = "impresoras.txt";
        String linea;
        ArrayList<String> lineas = new ArrayList<>();

        try {
            // guardar cada linea en una lista
            System.out.println("\nContenido del archivo:");
            for (int posicion = 0; posicion < foto_lista.size(); posicion++) {
                linea = (foto_lista.get(posicion).get_nombre()
                        + ","
                        + foto_lista.get(posicion).get_costo_bn()
                        + ","
                        + foto_lista.get(posicion).get_valor_venta_bn()
                        + ","
                        + foto_lista.get(posicion).get_valor_venta_c()
                        + ","
                        + foto_lista.get(posicion).get_costo_c()
                        + ",");

                // disminuye el porcentaje de la tinta a utilizar
                if (posicion == impresora) {
                    if (tinta == 1) {
                        linea += (foto_lista.get(posicion).get_negro())
                                + ","
                                + (foto_lista.get(posicion).get_amarillo() - (cantidad * foto_lista.get(posicion).get_consumo_ci_am()))
                                + ","
                                + (foto_lista.get(posicion).get_cian() - (cantidad * foto_lista.get(posicion).get_consumo_ci_am()))
                                + ","
                                + (foto_lista.get(posicion).get_magenta() - (cantidad * foto_lista.get(posicion).get_consumo_magenta()))
                                + ",";
                    } else {
                        linea += (foto_lista.get(posicion).get_negro() - (cantidad * foto_lista.get(posicion).get_consumo_negro()))
                                + ","
                                + (foto_lista.get(posicion).get_amarillo())
                                + ","
                                + (foto_lista.get(posicion).get_cian())
                                + ","
                                + (foto_lista.get(posicion).get_magenta())
                                + ",";
                    }
                } else {
                    linea += (foto_lista.get(posicion).get_negro()
                            + ","
                            + foto_lista.get(posicion).get_amarillo()
                            + ","
                            + foto_lista.get(posicion).get_cian()
                            + ","
                            + foto_lista.get(posicion).get_magenta()
                            + ",");
                }

                // consumo de cada tinta por impresion
                linea += (foto_lista.get(posicion).get_consumo_negro()
                        + ","
                        + foto_lista.get(posicion).get_consumo_ci_am()
                        + ","
                        + foto_lista.get(posicion).get_consumo_magenta());
                lineas.add(linea);
            }

            // escribe las líneas de vuelta al archivo
            try (BufferedWriter escribir = new BufferedWriter(new FileWriter(archivo))) {
                for (String l : lineas) {
                    escribir.write(l);
                    escribir.newLine();
                }
            }
            System.out.println("----- la línea ha sido modificada exitosamente -----");
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }


    // metodo para redondear numeros en dos decimales
    public double redondear(double numero) {
        return Math.round(numero * 100) / 100;
    }


// metodos para calcular el costo y venta total de los minutos
    public void set_venta_minutos()
    {
        for (int i = 0; i < getLisOper().size(); i++)
        {
            // cantidad de minutos por valor de venta
            venta_minutos += getLisOper().get(i).get_cantidad_minutos() * getLisOper().get(i).get_venta_minuto();
            costo_minutos += getLisOper().get(i).get_cantidad_minutos() * getLisOper().get(i).get_venta_minuto();
        }
    }
    public int get_venta_minutos()
    {
        return venta_minutos;
    }
    public int get_costo_minutos()
    {
        return costo_minutos;
    }


// metodos para calcular el costo y venta total de los minutos
    public void set_venta_sim()
    {
        for (int i = 0; i < getLisOper().size(); i++)
        {
            // cantidad de minutos por valor de venta
            venta_sim += getLisOper().get(i).get_cantidad_sim() * getLisOper().get(i).get_venta_minuto();
            costo_sim += getLisOper().get(i).get_cantidad_sim() * getLisOper().get(i).get_costo_minuto();
        }
    }
    public int get_venta_sim()
    {
        return venta_minutos;
    }
    public int get_costo_sim()
    {
        return costo_minutos;
    }

}