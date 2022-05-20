import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private String text;
    private boolean[] isVariable = new boolean[6];
    private Color[] colors = new Color[24];
    private float[] currentValues = new float[6];
    private ArrayList<Tank> tanks = new ArrayList<>();
    private ArrayList<Platform> platforms = new ArrayList<>();
    private int framesAlive = 0;
    private BufferedImage cloudBkgd, topBkgd;
    private int currentlyEditing = -1;
    private StringBuilder stringBuilder = new StringBuilder();
    private ProjectileModeling pm;

    private final String[] bases = new String[] {
            "|Target's |X|-displacement: |",
            "|Target's |Y|-displacement: |",
            "|Initial |S|peed: |",
            "|Fire |A|ngle: |",
            "||H|orizontal Acceleration: |",
            "||V|ertical Acceleration: |"
    };
    private final String[] units = new String[] {
            "m", "m", "m/s", "radians x pi", "m/(ss)", "m/(ss)"
    };
    private final int[] targetKeys = new int[] {
            'x', 'y', 's', 'a', 'h', 'v'
    };

    class GameKeyAdapter implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (currentlyEditing == -1) {
                for (int i = 0; i < targetKeys.length; i++) {
                    if (Character.toLowerCase(e.getKeyChar()) == targetKeys[i] && isVariable[i]) {
                        currentlyEditing = i;
                        stringBuilder = new StringBuilder(Float.toString(currentValues[i]));
                        System.out.println("editing mode!");
                    }
                }
            } else {
                if (FontGenerator.getLetterMap().containsKey(e.getKeyChar()) && stringBuilder.length() < 10) {
                    stringBuilder.append(e.getKeyChar());
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (currentlyEditing != -1) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println(stringBuilder.toString());
                    try {
                        float newVal = Float.parseFloat(stringBuilder.toString());
                        newVal = Math.max(-10000f, Math.min(10000f, newVal));
                        currentValues[currentlyEditing] = newVal;
                        currentlyEditing = -1;
                        
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    public int getFramesAlive() {
        return framesAlive;
    }

    public MainPanel() {
        tanks.add(new Tank(53, 31, 0, 0));
        for (int i = -250; i < 250; i += 100) {
            platforms.add(new Platform(Math.abs(i + 50) / 10, i, i + 100));
        }
        try {
            cloudBkgd = ImageIO.read(new File("images/cloud_day.png"));
            topBkgd = ImageIO.read(new File("images/top_day.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addKeyListener(new GameKeyAdapter());
    }

    public void newProblem(boolean[] isVariable, float[] givenValues) {
        this.isVariable = isVariable;
        this.currentValues = givenValues;
        colors = new Color[24];
        generateText();
    }

    public void generateText() {
        for (int i = 0; i < 6; i++) {
            if (i == currentlyEditing) {
                Color newColor = new Color(255, (int) (Math.sin(framesAlive / 10f) * 50) + 100, 0);
                for (int j = i * 4; j < i * 4 + 3; j++) {
                    colors[j] = newColor;
                }
            } else {
                colors[i * 4 + 0] = Color.GRAY;
                colors[i * 4 + 1] = (isVariable[i] ? Color.RED : Color.GRAY);
                colors[i * 4 + 2] = Color.GRAY;
            }
            colors[i * 4 + 3] = Color.BLACK;
        }
        text = "";
        for (int i = 0; i < currentValues.length; i++) {
            text += bases[i] + ((i == currentlyEditing) ? stringBuilder.toString()
                    : currentValues[i] + " " + units[i]) + "\n";
        }
        text += "&If you can change a variable, a letter in its name will be in red.\n" +
                "Type that red letter to edit the value!\n" +
                "When you're done, type ENTER to lock it in.\n" +
                "Make sure you type a valid decimal!\n" +
                "Click the tank to fire!";
        pm = new ProjectileModeling(currentValues[5], (float) (currentValues[2] * Math.cos(currentValues[3])),
                (float) (currentValues[2] * Math.sin(currentValues[3])));
    }

    public void update() {
        framesAlive++;
    }

    @Override
    public void paintComponent(Graphics g) {
        Tank player = tanks.get(0);
        int xChange = getWidth() / 2 - player.getCenterX();
        int yChange = getHeight() / 2 - player.getBottomY();
        g.translate(getWidth() / 2 - player.getCenterX(), getHeight() / 2 - player.getBottomY());
        int pos = framesAlive * 10 % cloudBkgd.getWidth() - cloudBkgd.getWidth();
        while (pos < getWidth()) {
            g.drawImage(cloudBkgd, -xChange + pos, -yChange + getHeight() - cloudBkgd.getHeight(), null);
            pos += cloudBkgd.getWidth();
        }
        g.drawImage(topBkgd, -xChange, -yChange, getWidth(), getHeight() - cloudBkgd.getHeight(), null);
        for (Platform platform : platforms) {
            platform.paintMe(g);
        }
        for (Tank tank : tanks) {
            tank.setAngle((float) (currentValues[3] * Math.PI));
            tank.paintMe(g);
        }
        generateText();
        FontGenerator.writeComplexText(g, text, 20 - xChange, 20 - yChange, colors, new float[] { 2, 1 });
    }
}
// "|Target's x-displacement: |1000 m\n" +
// "|Target's y-displacement: |0 m\n" +
// "|Initial |S|peed: |5 m/s\n" +
// "|Initial |A|ngle: |pi/2 radians\n" +
// "|Accel in Y direction: |-5 m/(ss)\n",
