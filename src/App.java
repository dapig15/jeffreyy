import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class App {
    private JFrame frame;
    private MainPanel mainPanel;
    private Timer timer;

    public static void main(String[] args) throws Exception {
        FontGenerator.prime();
        App app = new App();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                app.run();
            }
        });
    }

    public void run() {
        frame = new JFrame();
        frame.setSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new MainPanel();
        boolean[] isVariable = new boolean[] { false, false, true, true, true, true };
        float[] givenValues = new float[] { 1000, 0, 100, 0.25f, 0, -10f };
        mainPanel.newProblem(isVariable, givenValues);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (frame.isFocused()) {
                            mainPanel.requestFocus();
                            mainPanel.update();
                        }
                        mainPanel.repaint();
                    }
                });
            }
        }, 0, 40);
    }
}