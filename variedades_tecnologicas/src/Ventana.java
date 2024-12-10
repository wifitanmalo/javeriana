import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Ventana extends JFrame
{
    // ---------- medidas de la ventana ----------
    int width = 500, height = 500;

    // ---------- crea los paneles a utilizar ----------
    private JPanel menu_principal = new JPanel();
    private JPanel efectivo = new JPanel();
    private JPanel menu_operador = new JPanel();

    // ---------- constructor de la ventana ----------
    public Ventana()
    {
        setTitle("Variedades tecnológicas");
        setBounds(0, 0, width, height);
        setLocationRelativeTo(null);
        iniciar_componentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // -------------------- metodo para llamar a los componentes de la ventana --------------------
    private void iniciar_componentes()
    {
        panel_inicio();
    }

// -------------------- metodo para llamar el panel inicial --------------------
    private void panel_inicio()
    {
        menu_principal.setLayout(null);
        this.getContentPane().add(menu_principal);
        menu_principal.setBackground(Color.lightGray);

        // ---------- carga el logo del negocio ----------
        ImageIcon imagen = new ImageIcon("logo.png");
        JLabel logo = new JLabel();
        logo.setBounds(((width-204) / 2),
                        ((height-202) / 8),
                    204,
                    200);
        logo.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH)));
        menu_principal.add(logo);

    // ---------- nombre del negocio ----------
        JLabel nombre = new JLabel("Variedades tecnologicas", SwingConstants.CENTER);
        nombre.setBounds(0,
                        distancia_y(logo),
                        500,
                        40);
        nombre.setForeground(Color.darkGray);
        nombre.setFont(new Font("Verdana", 3, 25));
        menu_principal.add(nombre);

// ---------- boton para el menu de los minutos ----------
        JButton minutos = new JButton("Operadores");
        minutos.setBounds(( (width-150)/2),
                            distancia_y(nombre),
                            150,
                            40);
        minutos.setFont(new Font("Verdana", 1, 16));
        menu_principal.add(minutos);

// ---------- boton para el menu de las fotocopiadoras ----------
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

    // ---------- boton el cierre del dia ----------
        JButton cierre = new JButton("Cerrar");
        cierre.setBounds(( (width-150)/2),
                            distancia_y(fotocopias),
                            150,
                            40);
        cierre.setFont(new Font("Verdana", 1, 16));
        menu_principal.add(cierre);

    // ---------- evento de operador ----------
        ActionListener opera = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menu_principal.setVisible(false);
                panel_operador();
                menu_operador.setVisible(true);
            }
        };
        minutos.addActionListener(opera);

    // ---------- evento de cierre ----------
        ActionListener cerrar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menu_principal.setVisible(false);
                panel_efectivo();
                efectivo.setVisible(true);
            }
        };
        cierre.addActionListener(cerrar);

   // ---------- evento al presionar el mouse ----------
        KeyListener teclado = new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }
            @Override
            public void keyPressed(KeyEvent e)
            {
                minutos.setVisible(false);
                fotocopias.setVisible(false);
            }
            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        };
    }


// -------------------- metodo para llamar el panel del efectivo --------------------
    private void panel_efectivo()
    {
        efectivo.setLayout(null);
        this.getContentPane().add(efectivo);
        efectivo.setBackground(Color.lightGray);

    // ---------- texto del efectivo ----------
        JLabel titulo = new JLabel("Efectivo", SwingConstants.CENTER);
        titulo.setBounds(((width-500) / 2),
                        ((height-40) / 3),
                        500,
                        30); // posicion y tamaño
        titulo.setForeground(Color.darkGray); // color del texto
        titulo.setFont(new Font("Verdana", 0, 22));
        efectivo.add(titulo);

    // ---------- crear cajas de texto ----------
        JTextField caja_efectivo = new JTextField();
        caja_efectivo.setBounds( ((width-120) / 2),
                                distancia_y(titulo),
                                120,
                                40);
        caja_efectivo.setFont(new Font("Verdana", 1, 16));
        efectivo.add(caja_efectivo);

    // ---------- boton para ingresar el efectivo ----------
        JButton pago = new JButton("Ingresar");
        pago.setBounds(( (width-120)/2), distancia_y(caja_efectivo), 120, 40);
        pago.setFont(new Font("Verdana", 1, 16));
        efectivo.add(pago);

    // -------------------- evento al presionar el boton --------------------
        ActionListener pagar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                efectivo.setVisible(false);

                // limpia la caja de texto
                caja_efectivo.setText("");
                menu_principal.setVisible(true);
            }
        };
        pago.addActionListener(pagar);
    }


// -------------------- metodo para llamar el panel de operador --------------------
    private void panel_operador()
    {
        menu_operador.setLayout(null);
        this.getContentPane().add(menu_operador);
        menu_operador.setBackground(Color.lightGray);

    // ---------- texto del operador ----------
        JLabel t_ope = new JLabel("operador", SwingConstants.CENTER);
        t_ope.setBounds(((width-120) / 3),
                ((height-30) / 3),
                120,
                30);
        t_ope.setForeground(Color.darkGray); // color del texto
        t_ope.setFont(new Font("Verdana", 0, 22));
        menu_operador.add(t_ope);

    // ---------- lista desplegable de los operadores ----------
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

    // ---------- texto de la cantidad ----------
        JLabel t_can = new JLabel("cantidad", SwingConstants.CENTER);
        t_can.setBounds(distancia_x(t_ope),
                t_ope.getY(),
                120,
                30); // posicion y tamaño
        t_can.setForeground(Color.darkGray);
        t_can.setFont(new Font("Verdana", 0, 22));
        menu_operador.add(t_can);

    // ---------- caja de texto para la cantidad ----------
        JTextField caja_cantidad = new JTextField();
        caja_cantidad.setBounds(t_can.getX(),
                                distancia_y(t_can),
                                120,
                                40);
        caja_cantidad.setFont(new Font("Verdana", 1, 16));
        menu_operador.add(caja_cantidad);

    // ---------- boton para confirmar ----------
        JButton confirmar = new JButton("Confirmar");
        confirmar.setBounds(caja_cantidad.getX(),
                            distancia_y(caja_cantidad),
                            120,
                            40);
        confirmar.setFont(new Font("Verdana", 1, 14));
        menu_operador.add(confirmar);

    // ---------- evento al presionar el boton ----------
        ActionListener pago = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menu_operador.setVisible(false);
                // limpia la caja de texto
                caja_cantidad.setText("");
                panel_efectivo();
                efectivo.setVisible(true);
            }
        };
        confirmar.addActionListener(pago);
    }






    // metodo para conocer la distancia x de una etiqueta
    public int distancia_y(JLabel objeto)
    {
        return (objeto.getY()+objeto.getHeight()) + 10;
    }
    // metodo para conocer la distancia en y de un boton
    public int distancia_y(JButton objeto)
    {
        return (objeto.getY()+objeto.getHeight()) + 10;
    }
    // metodo para conocer la distancia en y de una caja de texto
    public int distancia_y(JTextField objeto)
    {
        return (objeto.getY()+objeto.getHeight()) + 10;
    }

    // distancia y de una etiqueta
    public int distancia_x(JLabel objeto)
    {
        return (objeto.getX()+objeto.getWidth()) + 10;
    }
    // metodo para conocer la distancia en x de un boton
    public int distancia_x(JButton objeto)
    {
        return (objeto.getX()+objeto.getWidth() + 10);
    }
    // metodo para conocer la distancia en x de una caja de texto
    public int distancia_x(JTextField objeto)
    {
        return (objeto.getX()+objeto.getWidth()) + 10;
    }

}

