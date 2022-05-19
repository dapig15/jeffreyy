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
        String[] givens = {"Target's x-displacement","Target's y-displacement","Initial |S|peed"};
        String[] values = {"1000 m","0 m","5 m/s"};
        mainPanel.newProblem("Fire angle",givens,values);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainPanel.update();
                        mainPanel.repaint();
                    }
                });
            }
        }, 0, 40);
    }
}