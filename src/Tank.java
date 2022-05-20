import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank {
    private BufferedImage tankBody;
    private BufferedImage tankNose;
    private int width, height;
    private int centerX, bottomY;
    private float angle = 0;

    public Tank(int width, int height, int centerX, int bottomY) {
        try {
            tankBody = ImageIO.read(new File("images/tank_body.png"));
            tankNose = ImageIO.read(new File("images/tank_nose.png"));
        } catch (IOException ioe) {

        }
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

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        if (angle < 0) {
            angle += (float) (((int) (Math.abs(angle) / ((2 * Math.PI))) + 1) * (2 * Math.PI));
        }
        this.angle = angle % ((float) (2 * Math.PI));
    }

    public void paintMe(Graphics g) {
        final int noseOffset = 12;

        g.setColor(Color.black);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(centerX, bottomY - height + noseOffset);
        g2d.rotate(-angle);
        g.drawImage(tankNose, 0, 0, null);
        g2d.rotate(angle);
        g2d.translate(-centerX, -(bottomY - height + noseOffset));
        if (Math.abs(Math.PI - angle) >= (Math.PI / 2)) {
            g.drawImage(tankBody, centerX - width / 2, bottomY - height, width, height, null);
        } else {
            g.drawImage(tankBody, centerX + width / 2, bottomY - height, -width, height, null);
        }
    }

}
