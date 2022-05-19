import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainPanel extends JPanel {

    private ArrayList<Tank> tanks = new ArrayList<>();
    private ArrayList<Platform> platforms = new ArrayList<>();
    private int framesAlive = 0;
    private BufferedImage cloudBkgd, topBkgd;

    public MainPanel() {
        tanks.add(new Tank("images\\tnKnks.png", 53, 31, 0, 0));
        for (int i = -250; i < 250; i += 100) {
            platforms.add(new Platform(Math.abs(i + 50) / 10, i, i + 100));
        }
        try {
            cloudBkgd = ImageIO.read(new File("images/cloud_day.png"));
            topBkgd = ImageIO.read(new File("images/top_day.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        framesAlive++;
    }

    @Override
    public void paintComponent(Graphics g) {
        Tank player = tanks.get(0);
        int xChange = getWidth() / 2 - player.getCenterX();
        int yChange = getHeight() - 60 - player.getBottomY();
        g.translate(getWidth() / 2 - player.getCenterX(), getHeight() - 60 - player.getBottomY());
        int pos = framesAlive % cloudBkgd.getWidth() - cloudBkgd.getWidth();
        while (pos < getWidth()) {
            g.drawImage(cloudBkgd, -xChange + pos, -yChange + getHeight() - cloudBkgd.getHeight(), null);
            pos += cloudBkgd.getWidth();
        }
        g.drawImage(topBkgd, -xChange, -yChange, getWidth(), getHeight() - cloudBkgd.getHeight(), null);
        for (Platform platform : platforms) {
            platform.paintMe(g);
        }
        for (Tank tank : tanks) {
            tank.paintMe(g);
        }
    }
}
