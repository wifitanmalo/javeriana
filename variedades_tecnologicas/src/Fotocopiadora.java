public class Fotocopiadora
{

// atributos predeterminadods
    private String nombre;
    private int costoHBN;
    private int costoHC;
    private int valorVentaHC;
    private int valorVentaHBN;

// atributos de tinta
    private double negro;
    private double amarillo;
    private double cian;
    private double magenta;
    private double consumo_negro;
    private double consumo_ci_am;
    private double consumo_magenta;
    private int recarga;

// atributos de la fotocopiadora Plotter
    private int costo_cm_plano;
    private int costo_cm_afiche;
    private int costo_afiche;
    private int costo_plano;
    private int venta_cm_plano;
    private int venta_cm_afiche;
    private int venta_afiche;
    private int venta_plano;

// atributos de rendimiento
    private int recolectado;
    private int produccion;


// -------------------- constructor predeterminado --------------------
    public Fotocopiadora(String nombre,
                         int costoHBN,
                         int costoHC,
                         int valorVentaHC,
                         int valorVentaHBN,
                         double negro,
                         double amarillo,
                         double cian,
                         double magenta,
                         double consumo_negro,
                         double consumo_ci_am,
                         double consumo_magenta)
    {
        this.nombre = nombre;
        this.costoHBN = costoHBN;
        this.costoHC = costoHC;
        this.valorVentaHC = valorVentaHC;
        this.valorVentaHBN = valorVentaHBN;
        this.recolectado = 0;

        this.negro = negro;
        this.amarillo = amarillo;
        this.cian = cian;
        this.magenta = magenta;

        this.consumo_negro = consumo_negro;
        this.consumo_ci_am = consumo_ci_am;
        this.consumo_magenta = consumo_magenta;
    }

// -------------------- constructor para la impresora de planos --------------------
    public Fotocopiadora(String nombre,
                         int costo_cm_plano,
                         int costo_cm_afiche,
                         int venta_cm_plano,
                         int venta_cm_afiche,
                         int costo_plano,
                         int costo_afiche,
                         int venta_plano,
                         int venta_afiche,
                         double blanco_negro,
                         double amarillo,
                         double cian,
                         double magenta,
                         double consumo_negro,
                         double consumo_ci_am,
                         double consumo_magenta)
    {
        this.nombre = nombre;
        this.costo_cm_plano = costo_cm_plano;
        this.costo_cm_afiche = costo_cm_afiche;
        this.venta_cm_plano = venta_cm_plano;
        this.venta_cm_afiche = venta_cm_afiche;
        this.costo_plano = costo_plano;
        this.costo_afiche = costo_afiche;
        this.venta_plano = venta_plano;
        this.venta_afiche = venta_afiche;

        this.negro = blanco_negro;
        this.amarillo = amarillo;
        this.cian = cian;
        this.magenta = magenta;

        this.consumo_negro = consumo_negro;
        this.consumo_ci_am = consumo_ci_am;
        this.consumo_magenta = consumo_magenta;
    }


// -------------------- metodos predeterminados de las fotocopiadoras --------------------
    public void set_nombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String get_nombre()
    {
        return nombre;
    }

    public void setCostoHBN(int costoHBN)
    {
        this.costoHBN = costoHBN;
    }
    public int getCostoHBN()
    {
        return costoHBN;
    }

    public void setCostoHC(int costoHC)
    {
        this.costoHC = costoHC;
    }
    public int getCostoHC()
    {
        return costoHC;
    }

    public void setValorVentaHC(int valorVentaHC)
    {
        this.valorVentaHC = valorVentaHC;
    }
    public int getValorVentaHC()
    {
        return valorVentaHC;
    }

    public void setValorVentaHBN(int valorVentaHBN)
    {
        this.valorVentaHBN = valorVentaHBN;
    }
    public int getValorVentaHBN()
    {
        return valorVentaHBN;
    }

// -------------------- metodos para definir y obtener los costos por centimetro --------------------
    public void set_costo_cm_plano(int costo_cm_plano) {
        this.costo_cm_plano = costo_cm_plano;
    }
    public int get_costo_cm_plano() {
        return costo_cm_plano;
    }

    public void set_costo_cm_afiche(int costo_cm_afiche) {
        this.costo_cm_afiche = costo_cm_afiche;
    }
    public int get_costo_cm_afiche() {
        return costo_cm_afiche;
    }

// -------------------- metodos para definir y obtener los valores de venta por centimetro --------------------
    public void set_venta_cm_plano(int venta_cm_plano) {
        this.venta_cm_plano = venta_cm_plano;
    }
    public int get_venta_cm_plano() {
        return venta_cm_plano;
    }

    public void set_venta_cm_afiche(int costo_cm_afiche) {
        this.venta_cm_afiche = costo_cm_afiche;
    }
    public int get_venta_cm_afiche() {
        return venta_cm_afiche;
    }

// -------------------- metodos para definir y obtener los costos por tipo de impresion --------------------
    public void set_costo_afiche(int costo_afiche) {
        this.costo_afiche = costo_afiche;
    }
    public int get_costo_afiche() {
        return costo_afiche;
    }

    public void set_costo_plano(int costo_plano) {
        this.costo_plano = costo_plano;
    }
    public int get_costo_plano() {
        return costo_plano;
    }

// -------------------- metodos para definir y obtener los valores de venta por tipo de impresion --------------------
    public void set_venta_afiche(int venta_afiche) {
        this.venta_afiche = venta_afiche;
    }
    public int get_venta_afiche() {
        return venta_afiche;
    }

    public void set_venta_plano(int venta_plano) {
        this.venta_plano = venta_plano;
    }
    public int get_venta_plano() {
        return venta_plano;
    }

// -------------------- metodos para adicionar el valor recolectado del dia --------------------
    public void set_recolectado(int efectivo)
    {
        this.recolectado = this.recolectado + efectivo;
    }
    public int get_recolectado()
    {
        return recolectado;
    }

// -------------------- metodos para adicionar el costo de produccion del dia --------------------
    public void set_produccion(int costo)
    {
        this.produccion = this.produccion + costo;
    }
    public int get_produccion()
    {
        return produccion;
    }


// -------------------- metodos para definir y obtener las tintas --------------------
    public void set_negro(double blanco_negro)
    {
        this.negro = blanco_negro;
    }
        public double get_negro()
        {
            return negro;
        }

    public void set_amarillo(double amarillo)
    {
        this.amarillo = amarillo;
    }
    public double get_amarillo()
    {
        return amarillo;
    }

    public void set_cian(double cian) {
        this.cian = cian;
    }
    public double get_cian() {
        return cian;
    }


    public void set_magenta(double magenta)
    {
        this.magenta = magenta;
    }
    public double get_magenta()
    {
        return magenta;
    }


// -------------------- metodos para definir y obtener los consumos de tinta ----------

    public void set_consumo_negro(double consumo_negro)
    {
        this.consumo_negro = consumo_negro;
    }
    public double get_consumo_negro()
    {
        return consumo_negro;
    }

    public void set_consumo_ci_am(double consumo_color)
    {
        this.consumo_ci_am = consumo_color;
    }
    public double get_consumo_ci_am()
    {
        return consumo_ci_am;
    }

    public void set_consumo_magenta(double consumo_color)
    {
        this.consumo_magenta = consumo_color;
    }
    public double get_consumo_magenta()
    {
        return consumo_magenta;
    }


    public void set_recarga(int recarga)
    {
        this.recarga = recarga;
    }
    public int get_recarga()
    {
        return recarga;
    }
}
