import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class App {
    private JFrame frame;
    private JPanel mainPanel;
    private Timer timer;

    public static void main(String[] args) throws Exception {
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
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        timer = new Timer();
        System.out.println(SwingUtilities.isEventDispatchThread());
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainPanel.repaint();
                        System.out.println(SwingUtilities.isEventDispatchThread());
                    }
                });
            }
        }, 0, 40);
    }
}
