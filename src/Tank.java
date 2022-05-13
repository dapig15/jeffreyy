import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank {
    private BufferedImage tankBody;
    private int width, height;
    private int centerX, bottomY;

    public Tank(String fileName, int width, int height, int centerX, int bottomY) {
        /*
         * try {
         * tankBody = ImageIO.read(new File(fileName));
         * } catch (IOException ioe) {
         * ioe.printStackTrace();
         * }
         */
        this.width = width;
        this.height = height;
        this.centerX = centerX;
        this.bottomY = bottomY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getBottomY() {
        return bottomY;
    }

    public void setBottomY(int bottomY) {
        this.bottomY = bottomY;
    }

    public void paintMe(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(centerX - width / 2, bottomY - height, width, height);
        // g.drawImage(tankBody, centerX - width / 2, bottomY - height, width, height,
        // null);
    }

}
