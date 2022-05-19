import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

    private ArrayList<Tank> tanks = new ArrayList<>();
    private ArrayList<Platform> platforms = new ArrayList<>();

    public MainPanel() {
        tanks.add(new Tank("images\\tnKnks.png", 53, 31, 0, 0));
        for (int i = -250; i < 250; i += 100) {
            platforms.add(new Platform(Math.abs(i+50) / 10, i, i + 100));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Tank player = tanks.get(0);
        g.translate(getWidth() / 2 - player.getCenterX(), getHeight() - 60 - player.getBottomY());
        for (Platform platform : platforms) {
            platform.paintMe(g);
        }
        for (Tank tank : tanks) {
            tank.paintMe(g);
        }
    }
}
