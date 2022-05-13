import java.awt.Color;
import java.awt.Graphics;

public class Platform {
    private int height;
    private int minX, maxX;

    public Platform(int height, int minX, int maxX) {
        this.height = height;
        this.minX = minX;
        this.maxX = maxX;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void paintMe(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(minX, -height, maxX - minX, 10);
        g.setColor(new Color(135, 42, 42));
        g.fillRect(minX, -height + 10, maxX - minX, 1000);
    }

}
