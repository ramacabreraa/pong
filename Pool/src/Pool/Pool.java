package Pool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pool extends JPanel implements ActionListener, MouseMotionListener {

    private int paloX = 250;
    private int paloY = 300;
    private int pelotaX1 = 50;
    private int pelotaY1 = 50;
    private int pelotaDX1 = 2;
    private int pelotaDY1 = 2;
    private int pelotaX2 = 150;
    private int pelotaY2 = 150;
    private int pelotaDX2 = 2;
    private int pelotaDY2 = -2;

    public Pool() {
        Timer timer = new Timer(10, this);
        timer.start();

        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cambié los colores
        setBackground(Color.GREEN);

        // Dibujar el palo (color madera)
        g.setColor(new Color(139, 69, 19)); // Color madera (RGB)
        g.fillRect(paloX, paloY, 100, 20);

        // Dibujar la primera pelota (color blanco)
        g.setColor(Color.WHITE);
        g.fillOval(pelotaX1, pelotaY1, 30, 30);

        // Dibujar la segunda pelota (color negro)
        g.setColor(Color.BLACK);
        g.fillOval(pelotaX2, pelotaY2, 30, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mover la primera pelota
        pelotaX1 += pelotaDX1;
        pelotaY1 += pelotaDY1;

        // Mover la segunda pelota
        pelotaX2 += pelotaDX2;
        pelotaY2 += pelotaDY2;

        // Verificar colisiones con el marco de la ventana para la primera pelota
        if (pelotaX1 <= 0 || pelotaX1 >= getWidth() - 30) {
            pelotaDX1 = -pelotaDX1;
        }
        if (pelotaY1 <= 0 || pelotaY1 >= getHeight() - 30) {
            pelotaDY1 = -pelotaDY1;
        }

        // Verificar colisiones con el marco de la ventana para la segunda pelota
        if (pelotaX2 <= 0 || pelotaX2 >= getWidth() - 30) {
            pelotaDX2 = -pelotaDX2;
        }
        if (pelotaY2 <= 0 || pelotaY2 >= getHeight() - 30) {
            pelotaDY2 = -pelotaDY2;
        }

        // Verificar colisiones del palo con las pelotas
        if ((paloX + 100 >= pelotaX1 && paloX <= pelotaX1 + 30) &&
            (paloY + 20 >= pelotaY1 && paloY <= pelotaY1 + 30)) {
            pelotaDY1 = -pelotaDY1;
        }

        if ((paloX + 100 >= pelotaX2 && paloX <= pelotaX2 + 30) &&
            (paloY + 20 >= pelotaY2 && paloY <= pelotaY2 + 30)) {
            pelotaDY2 = -pelotaDY2;
        }

        // Verificar colisiones entre las pelotas
        if (Math.abs(pelotaX1 - pelotaX2) <= 30 && Math.abs(pelotaY1 - pelotaY2) <= 30) {
            int tempDX = pelotaDX1;
            int tempDY = pelotaDY1;
            pelotaDX1 = pelotaDX2;
            pelotaDY1 = pelotaDY2;
            pelotaDX2 = tempDX;
            pelotaDY2 = tempDY;
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paloX = e.getX() - 50;
        paloY = e.getY() - 10;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Método requerido por la interfaz MouseMotionListener
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pool Game");
        Pool pool = new Pool();
        frame.add(pool);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setFocusable(true);
    }
}
