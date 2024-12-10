public class Operador
{
// atributos del operador
    private  String nombre;
    private  int costo;
    private  int valor_venta;
    private int costo_sim;
    private int venta_sim;


// atributos de rendimiento
    private int recolectado;
    private int produccion;


// -------------------- constructor del operador --------------------
    public Operador(String nombre, int costo, int valor_venta, int costo_sim, int venta_sim)
    {
        this.nombre = nombre;
        this.costo = costo;
        this.valor_venta = valor_venta;
        this.costo_sim = costo_sim;
        this.venta_sim = venta_sim;
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

    public void set_costo(int costo)
    {
        this.costo = costo;
    }
    public int get_costo()
    {
        return costo;
    }

    public void set_venta(int valor)
    {
        this.valor_venta = valor;
    }
    public int get_venta()
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
}
