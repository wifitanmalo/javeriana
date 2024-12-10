import java.io.*;
import java.util.ArrayList;

public class Negocio {
    private Fotocopiadora fotoP;
    private Operador opera;
    private ArrayList<Operador> lista_operador;

    private int venta;
    private int costo;

    private ArrayList<Fotocopiadora> foto_lista;
    private int constoEnergiaDia;
    private int costoEmpleadoDia;

    // -------------------- constructor del negocio --------------------
    public Negocio(int costoEmpleadoDia, int constoEnergiaDia) {
        // -------------------- operadores --------------------
        lista_operador = new ArrayList<>();

        // -------------------- fotocopiadoras --------------------
        foto_lista = new ArrayList<>();
        // el objeto recibe el valor de la fotocopiadora predeterminada

        // -------------------- costos diarios del negocio --------------------
        this.costoEmpleadoDia = costoEmpleadoDia;
        this.constoEnergiaDia = constoEnergiaDia;
    }

    // ------------------------ objeto de operador ------------------------
    public void set_opera(Operador opera) {
        this.opera = opera;
    }

    public Operador get_opera() {
        return opera;
    }

    public ArrayList<Operador> getLisOper() {
        return lista_operador;
    }

    public void setLisOper(ArrayList<Operador> lisOper) {
        this.lista_operador = lisOper;
    }

    // metodo para usar el objeto del operador
    public void usar_opera(int posicion) {
        opera = lista_operador.get(posicion - 1);
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

    // metodo para usar el objeto de la fotocopiadora
    public void usar_foto(int posicion) {
        fotoP = foto_lista.get(posicion - 1);
    }


// ------------------------ CRUD DE OPERADOR ------------------------

    // funcion para CREAR un operador
    public void crear_operador(String nombre, int costo_minuto, int valor_venta, int costo_sim, int venta_sim) {
        lista_operador.add(new Operador(nombre, costo_minuto, valor_venta, costo_sim, venta_sim));
    }

    // funcion para MODIFICAR un operador
    public void modificar_operador(int posicion, String nombre, int costo_minuto, int valor_venta, int costo_sim, int venta_sim) {
        Operador operador_modificado = lista_operador.get(posicion - 1);
        operador_modificado.set_nombre(nombre);
        operador_modificado.set_costo(costo_minuto);
        operador_modificado.set_venta(valor_venta);
        operador_modificado.set_costo_sim(costo_sim);
        operador_modificado.set_venta_sim(venta_sim);
    }

    // funcion para ELIMINAR un operador
    public void eliminar_operador(int posicion) {
        foto_lista.remove(posicion - 1);
    }

// ------------------------ CRUD DE FOTOCOPIADORA ------------------------

    // funcion para CREAR una fotocopiadora
    public void crear_fotocopiadora(String tipo,
                                    int costoHBN,
                                    int costoHC,
                                    int valorVentaHC,
                                    int valorVentaHBN)
    {
        foto_lista.add(new Fotocopiadora(tipo, costoHBN, costoHC, valorVentaHC, valorVentaHBN, 100, 100, 100, 100, 0,0,0));
    }

    // funcion para MODIFICAR una fotocopiadora
    public void modificar_fotocopiadora(int posicion,
                                        String nombre,
                                        int costoHBN,
                                        int costoHC,
                                        int valorVentaHC,
                                        int valorVentaHBN,
                                        int negro,
                                        int amarillo,
                                        int cian,
                                        int magenta)
    {
        Fotocopiadora modificada = foto_lista.get(posicion - 1);
        modificada.set_nombre(nombre);
        modificada.setCostoHBN(costoHBN);
        modificada.setCostoHC(costoHC);
        modificada.setValorVentaHC(valorVentaHC);
        modificada.setValorVentaHBN(valorVentaHBN);
        modificada.set_negro(negro);
        modificada.set_amarillo(amarillo);
        modificada.set_cian(cian);
        modificada.set_magenta(magenta);
    }

    // funcion para ELIMINAR una fotocopiadora
    public void eliminar_fotocopiadora(int posicion) {
        foto_lista.remove(posicion - 1);
    }

    // registrar la ventas en el negocio
    public boolean registrar(int servicio, int efectivo, int valor_total, int costo) {
        if (servicio == 1) {
            if (valor_total == efectivo) {
                this.opera.set_recolectado(efectivo);
                this.opera.set_produccion(costo);
            } else {
                return false;
            }
        } else {
            if (valor_total == efectivo) {
                this.fotoP.set_recolectado(efectivo);
                this.fotoP.set_produccion(costo);
            } else {
                return false;
            }
        }
        return true;
    }

// ---------- funcion para conocer y obtener los valores totales ----------

    // total de los minutos
    public void set_total(int opcion, int cantidad) {
        if (opcion == 1) {
            venta = cantidad * this.opera.get_venta();
            costo = cantidad * this.opera.get_costo();
        } else {
            venta = cantidad * this.opera.get_venta_sim();
            costo = cantidad * this.opera.get_costo_sim();
        }
    }

    // total de las fotocopias
    public void set_total(int numero_foto, int tinta, int area_can, int impresion) {
        if (numero_foto == 3) {
            // formula = (area * venta por centimetro) + (area * venta por impresion)
            if (impresion == 1) // afiche
            {
                venta = (area_can * this.fotoP.get_venta_cm_afiche()) + (area_can * this.fotoP.get_venta_afiche());
                costo = (area_can * this.fotoP.get_costo_cm_afiche()) + (area_can * this.fotoP.get_costo_afiche());
            } else if (impresion == 2) // plano
            {
                venta = (area_can * this.fotoP.get_venta_cm_plano()) + (area_can * this.fotoP.get_venta_plano());
                costo = (area_can * this.fotoP.get_costo_cm_plano()) + (area_can * this.fotoP.get_costo_plano());
            }
        } else {
            // formula = cantidad * venta de hoja (color/blanco y negro)
            if (tinta == 1) // color
            {
                venta = area_can * this.fotoP.getValorVentaHC();
                costo = area_can * this.fotoP.getCostoHC();
            } else if (tinta == 2) // blanco y negro
            {
                venta = area_can * this.fotoP.getValorVentaHBN();
                costo = area_can * this.fotoP.getCostoHBN();
            }
        }
    }

    public int get_venta() {
        return venta;
    }

    public int get_costo() {
        return costo;
    }

    // -------------------- metodos para definir y obtener la deuda diaria del negocio --------------------
    public void setConstoEnergiaDia(int constoEnergiaDia) {
        this.constoEnergiaDia = constoEnergiaDia;
    }

    public int getConstoEnergiaDia() {
        return constoEnergiaDia;
    }

    public void setCostoEmpleadoDia(int costoEmpleadoDia) {
        this.costoEmpleadoDia = costoEmpleadoDia;
    }

    public int getCostoEmpleadoDia() {
        return costoEmpleadoDia;
    }


    // -------------------- funcion para cargar los operadores --------------------
    public void cargar_operadores() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("operadores.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");  // Suponiendo que los datos están separados por comas
                String nombre = datos[0];
                int costo = Integer.parseInt(datos[1]);
                int valor_venta = Integer.parseInt(datos[2]);
                int costo_sim = Integer.parseInt(datos[3]);
                int valor_sim = Integer.parseInt(datos[4]);

                Operador nuevo = new Operador(nombre, costo, valor_venta, costo_sim, valor_sim);
                this.lista_operador.add(nuevo);  // Agrega la nueva fotocopiadora a la lista
            }
            reader.close();
            opera = lista_operador.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // -------------------- funcion para cargar las impresoras --------------------
    public void cargar_impresoras() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("impresoras.txt"));
            String line;
            while ((line = reader.readLine()) != null)
            {
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
                this.foto_lista.add(nueva);  // Agrega la nueva fotocopiadora a la lista
            }
            reader.close();
            fotoP = foto_lista.get(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

// -------------------- metodo para escribir en el archivo de las impresoras --------------------
    public void escribir_impresora(int impresora, int cantidad, int tinta)
    {
        String archivo = "impresoras.txt";
        String linea;
        ArrayList<String> lineas = new ArrayList<>();

        try
        {
            // guardar cada linea en una lista
            System.out.println("\nContenido del archivo:");
            for(int posicion=0; posicion<foto_lista.size(); posicion++)
            {
                linea = (foto_lista.get(posicion).get_nombre()
                        + ","
                        + foto_lista.get(posicion).getCostoHBN()
                        + ","
                        + foto_lista.get(posicion).getValorVentaHBN()
                        + ","
                        + foto_lista.get(posicion).getValorVentaHC()
                        + ","
                        + foto_lista.get(posicion).getCostoHC()
                        + ",");

            // disminuye el porcentaje de la tinta a utilizar
                if( (posicion+1) == impresora)
                {
                    if(tinta == 1)
                    {
                        linea += (foto_lista.get(posicion).get_negro())
                                + ","
                                + ( foto_lista.get(posicion).get_amarillo() - (cantidad*foto_lista.get(posicion).get_consumo_ci_am()) )
                                + ","
                                + ( foto_lista.get(posicion).get_cian() - (cantidad*foto_lista.get(posicion).get_consumo_ci_am()) )
                                + ","
                                + ( foto_lista.get(posicion).get_magenta() - (cantidad*foto_lista.get(posicion).get_consumo_magenta()) )
                                + ",";
                    }
                    else
                    {
                        linea += ( foto_lista.get(posicion).get_negro() - (cantidad*foto_lista.get(posicion).get_consumo_negro()) )
                                + ","
                                + ( foto_lista.get(posicion).get_amarillo())
                                + ","
                                + ( foto_lista.get(posicion).get_cian())
                                + ","
                                + ( foto_lista.get(posicion).get_magenta())
                                + ",";
                    }
                }
                else
                {
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
                System.out.println("\n MAMASITA RICA" + linea);
            }

            // Escribir las líneas de vuelta al archivo
            try (BufferedWriter escribir = new BufferedWriter(new FileWriter(archivo)))
            {
                for (String l : lineas)
                {
                    escribir.write(l);
                    escribir.newLine();
                }
            }
            System.out.println("----- la línea ha sido modificada exitosamente -----");
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    // metodo para redondear numeros en dos decimales
    public double redondear(double numero)
    {
        return Math.round(numero*100) / 100;
    }
}