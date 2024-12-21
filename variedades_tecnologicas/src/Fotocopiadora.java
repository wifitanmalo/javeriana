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


    // atributos de cantidades vendidas
    private int cantidad_bn;
    private int cantidad_c;
    private int cantidad_plano;
    private int cantidad_afiche;
    private double tinta_consumida;


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

        this.negro = negro;
        this.amarillo = amarillo;
        this.cian = cian;
        this.magenta = magenta;

        this.consumo_negro = consumo_negro;
        this.consumo_ci_am = consumo_ci_am;
        this.consumo_magenta = consumo_magenta;

        this.cantidad_bn = 0;
        this.cantidad_c = 0;
        this.cantidad_plano = 0;
        this.cantidad_afiche = 0;
        this.tinta_consumida = 0;
    }



// ---------- METODOS QUE DEFINEN LOS ATRIBUTOS DE LA IMPRESORA ----------
    // nombre de la impresora
    public void set_nombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String get_nombre()
    {
        return nombre;
    }

    // costo por hoja en blanco y negro o por area del plano
    public void set_costo_bn(int costo_bn)
    {
        this.costo_bn = costo_bn;
    }
    public int get_costo_bn()
    {
        return costo_bn;
    }

    // costo por hoja a color o por area del afiche
    public void set_costo_c(int costo_c)
    {
        this.costo_c = costo_c;
    }
    public int get_costo_c()
    {
        return costo_c;
    }

    // precio de venta por hoja en blanco y negro o por area del plano
    public void set_valor_venta_bn(int valor_venta_bn)
    {
        this.valor_venta_bn = valor_venta_bn;
    }
    public int get_valor_venta_bn()
    {
        return valor_venta_bn;
    }

    // precio de venta por hoja a color o por area del afiche
    public void set_valor_venta_c(int valor_venta_c)
    {
        this.valor_venta_c = valor_venta_c;
    }
    public int get_valor_venta_c()
    {
        return valor_venta_c;
    }



// ---------- METODOS PARA DEFINIR LA CANTIDAD DE TINTA DISPONIBLE ----------
    // porcentaje de tinta negra
    public void set_negro(double blanco_negro)
    {
        this.negro = blanco_negro;
    }
    public double get_negro()
        {
            return negro;
        }

    // porcentaje de tinta amarilla
    public void set_amarillo(double amarillo)
    {
        this.amarillo = amarillo;
    }
    public double get_amarillo()
    {
        return amarillo;
    }

    // porcentaje de tinta cian
    public void set_cian(double cian) {
        this.cian = cian;
    }
    public double get_cian() {
        return cian;
    }

    // porcentaje de tinta magenta
    public void set_magenta(double magenta)
    {
        this.magenta = magenta;
    }
    public double get_magenta()
    {
        return magenta;
    }



// ---------- METODOS PARA DEFINIR LOS CONSUMOS DE TINTA ----------
    // consumo consumido de tinta negra por impresion
    public void set_consumo_negro(double consumo_negro)
    {
        this.consumo_negro = consumo_negro;
    }
    public double get_consumo_negro()
    {
        return consumo_negro;
    }

    // porcentaje consumido de tinta amarilla y cian por impresion
    public void set_consumo_ci_am(double consumo_color)
    {
        this.consumo_ci_am = consumo_color;
    }
    public double get_consumo_ci_am()
    {
        return consumo_ci_am;
    }

    // porcentaje consumido de tinta magenta por impresion
    public void set_consumo_magenta(double consumo_color)
    {
        this.consumo_magenta = consumo_color;
    }
    public double get_consumo_magenta()
    {
        return consumo_magenta;
    }



// ---------- METODOS PARA CALCULAR LAS CANTIDADES TOTALES ----------
    // cantidad de hojas a blanco y negro
    public void set_cantidad_bn(int cantidad)
    {
        this.cantidad_bn += cantidad;
    }
    public int get_cantidad_bn()
    {
        return cantidad_bn;
    }

    // cantidad de hojas a color
    public void set_cantidad_c(int cantidad)
    {
        this.cantidad_c += cantidad;
    }
    public int get_cantidad_c()
    {
        return cantidad_c;
    }

    // cantidad del area de los planos
    public void set_cantidad_plano(int cantidad)
    {
        this.cantidad_plano += cantidad;
    }
    public int get_cantidad_plano()
    {
        return cantidad_plano;
    }

    // cantidad del area de los afiches
    public void set_cantidad_afiche(int cantidad)
    {
        this.cantidad_afiche += cantidad;
    }
    public int get_cantidad_afiche()
    {
        return cantidad_afiche;
    }



// ---------- METODOS PARA CALCULAR LA CANTIDAD DE TINTA CONSUMIDA ----------
    public void set_tinta_consumida(double porcentaje)
    {
        this.cantidad_c += porcentaje;
    }
    public double get_tinta_consumida()
    {
        return tinta_consumida;
    }
}



