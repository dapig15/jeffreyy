import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.YELLOW);
        g.fillRect(getWidth() / 2 - 25, getHeight() - 100, 50, 20);
        g.setColor(Color.GREEN);
        g.fillRect(0, getHeight() - 80, getWidth(), 10);
        g.setColor(new Color(135, 42, 42));
        g.fillRect(0, getHeight() - 70, getWidth(), 70);
    }
}
