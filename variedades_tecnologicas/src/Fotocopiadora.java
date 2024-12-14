public class Fotocopiadora
{

// atributos predeterminadods
    private String nombre;
    private int costo_bn;
    private int costo_c;
    private int valor_venta_bn;
    private int valor_venta_c;

// atributos de tinta
    private double negro;
    private double amarillo;
    private double cian;
    private double magenta;
    private double consumo_negro;
    private double consumo_ci_am;
    private double consumo_magenta;
    private int recarga;

// atributos de rendimiento
    private int recolectado;
    private int produccion;


// ---------- constructor predeterminado ----------
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
        this.costo_bn = costoHBN;
        this.costo_c = costoHC;
        this.valor_venta_bn = valorVentaHC;
        this.valor_venta_c = valorVentaHBN;
        this.recolectado = 0;

        this.negro = negro;
        this.amarillo = amarillo;
        this.cian = cian;
        this.magenta = magenta;

        this.consumo_negro = consumo_negro;
        this.consumo_ci_am = consumo_ci_am;
        this.consumo_magenta = consumo_magenta;
    }


// metodos predeterminados de las fotocopiadoras
    public void set_nombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String get_nombre()
    {
        return nombre;
    }

    public void set_costo_bn(int costo_bn)
    {
        this.costo_bn = costo_bn;
    }
    public int get_costo_bn()
    {
        return costo_bn;
    }

    public void set_costo_c(int costo_c)
    {
        this.costo_c = costo_c;
    }
    public int get_costo_c()
    {
        return costo_c;
    }

    public void set_valor_venta_bn(int valor_venta_bn)
    {
        this.valor_venta_bn = valor_venta_bn;
    }
    public int get_valor_venta_bn()
    {
        return valor_venta_bn;
    }

    public void set_valor_venta_c(int valor_venta_c)
    {
        this.valor_venta_c = valor_venta_c;
    }
    public int get_valor_venta_c()
    {
        return valor_venta_c;
    }


// metodos para adicionar el valor recolectado del dia
    public void set_recolectado(int efectivo)
    {
        this.recolectado = this.recolectado + efectivo;
    }
    public int get_recolectado()
    {
        return recolectado;
    }


// metodos para adicionar el costo de produccion del dia
    public void set_produccion(int costo)
    {
        this.produccion = this.produccion + costo;
    }
    public int get_produccion()
    {
        return produccion;
    }


// metodos para definir y obtener las tintas
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


// metodos para definir y obtener los consumos de tinta
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


// metodos para definir y obtener el valor de recarga
    public void set_recarga(int recarga)
    {
        this.recarga = recarga;
    }
    public int get_recarga()
    {
        return recarga;
    }
}
