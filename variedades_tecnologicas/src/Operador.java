public class Operador
{
// atributos del operador
    private  String nombre;
    private  int costo;
    private  int valor_venta;
    private int costo_sim;
    private int venta_sim;


// atributos de rendimiento
    private int cantidad_minutos;
    private int cantidad_sim;


// -------------------- constructor del operador --------------------
    public Operador(String nombre,
                    int costo,
                    int valor_venta,
                    int costo_sim,
                    int venta_sim)
    {
        this.nombre = nombre;
        this.costo = costo;
        this.valor_venta = valor_venta;
        this.costo_sim = costo_sim;
        this.venta_sim = venta_sim;
        this.cantidad_minutos = 0;
        this.cantidad_sim = 0;
    }


// metodos apara definr y obtener los atributos del operador
    public void set_nombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String get_nombre()
    {
        return nombre;
    }

    public void set_costo_minuto(int costo)
    {
        this.costo = costo;
    }
    public int get_costo_minuto()
    {
        return costo;
    }

    public void set_venta_minuto(int valor)
    {
        this.valor_venta = valor;
    }
    public int get_venta_minuto()
    {
        return valor_venta;
    }

    public void set_costo_sim(int costo_sim)
    {
        this.costo_sim = costo_sim;
    }
    public int get_costo_sim()
    {
        return costo_sim;
    }

    public void set_venta_sim(int venta_sim)
    {
        this.venta_sim = venta_sim;
    }
    public int get_venta_sim()
    {
        return venta_sim;
    }

//  metodos para adicionar el valor recolectado del dia
    public void set_cantidad_minutos(int efectivo)
    {
        this.cantidad_minutos = this.cantidad_minutos + efectivo;
    }
    public int get_cantidad_minutos()
    {
        return cantidad_minutos;
    }

// metodos para adicionar el costo de produccion del dia
    public void set_cantidad_sim(int costo)
    {
        this.cantidad_sim = this.cantidad_sim + costo;
    }
    public int get_cantidad_sim()
    {
        return cantidad_sim;
    }
}

