package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class FlyView extends JFrame implements WindowListener {
  private static final int width = 350;
  private static final int height = 350;

  private final Client client;
  private final FlyPanel flyPanel;
  private final ScorePanel scorePanel;
  private final NotificationPanel notificationPanel;

  public FlyView(Client client) {
    super("Fly Hunting Game");

    Dimension windowSize = new Dimension(width, height);

    this.client = client;
    this.flyPanel = new FlyPanel();
    this.scorePanel = new ScorePanel();
    this.notificationPanel = new NotificationPanel();

    this.setSize(300, 300);
    this.setMinimumSize(windowSize);
    this.setMaximumSize(windowSize);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(this.flyPanel, BorderLayout.CENTER);
    this.getContentPane().add(this.scorePanel, BorderLayout.EAST);
    this.getContentPane().add(this.notificationPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public void setFlyPosition(int x, int y) {
    this.flyPanel.setFlyPosition(x, y);
  }

  public void setPlayerScore(String playerName, int score) {
    this.scorePanel.setScore(playerName, score);
    this.notificationPanel.setHunter(playerName);
  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {

  }

  @Override
  public void windowClosed(WindowEvent e) {
    client.logout();
  }

  @Override
  public void windowIconified(WindowEvent e) {

  }

  @Override
  public void windowDeiconified(WindowEvent e) {

  }

  @Override
  public void windowActivated(WindowEvent e) {

  }

  @Override
  public void windowDeactivated(WindowEvent e) {

  }

  private class NotificationPanel extends JPanel {

    private NotificationPanel() {
      super();
    }

    public void setHunter(String playerName) {
      this.removeAll();
      this.add(new JLabel(String.format("%s hunted the fly", playerName)));
      this.revalidate();
      this.repaint();
    }
  }

  private class FlyPanel extends JPanel {

    private final JLabel flyLabel;

    private final int panelWidth = 300;
    private final int panelHeight = 300;

    private final int flyWidth = 53;
    private final int flyHeight = 53;

    private FlyPanel() {
      super();
      this.setMaximumSize(new Dimension(panelWidth, panelHeight));
      this.setLayout(null);
      this.setBackground(Color.WHITE);
      this.flyLabel = new JLabel(new ImageIcon(getClass().getResource("/fliege.jpg"), "Fly"));
      flyLabel.setSize(flyWidth, flyHeight);
      this.add(flyLabel);

      flyLabel.addMouseListener(new MouseListener() {
        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
          client.onFlyHunted();
        }
      });
    }

    public void setFlyPosition(int x, int y) {
      flyLabel.setLocation(x % (panelWidth - flyWidth), y % (panelHeight - flyHeight));
    }

  }

  private class ScorePanel extends JPanel {

    private final Map<String, Integer> scores = new HashMap<>();

    private ScorePanel() {
      super();
      this.setBackground(Color.WHITE);
      update();
    }

    public void setScore(String playerName, int score) {
      this.scores.put(playerName, score);
      this.update();
    }

    private void update() {
      this.removeAll();
      this.add(new JLabel(String.format("%10s %5s", "Player Name", "Score")));
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      for (Entry<String, Integer> e : scores.entrySet()) {
        this.add(new JLabel(String.format("%-10s %5d", e.getKey(), e.getValue())));
      }
      this.revalidate();
      this.repaint();
    }
  }
}