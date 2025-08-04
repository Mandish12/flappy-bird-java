import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBirdHomePage extends JFrame {

    public FlappyBirdHomePage() {
        setTitle("Flappy Bird");
        setSize(600, 800);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        HomePanel panel = new HomePanel();
        add(panel);

        setVisible(true);
    }

    class HomePanel extends JPanel implements ActionListener {
        private Image background;
        private Image bird;
        private JButton startButton;
        private Timer timer;
        private int bgX = 0;
        private int scrollSpeed = 1;

        // Bird float animation variables
        private int birdBaseY = 250;
        private int birdY = birdBaseY;
        private int floatDirection = 1;
        private int floatSpeed = 1;
        private int floatRange = 20;

        public HomePanel() {
            setLayout(null);

            background = new ImageIcon("flappybirdbg.png").getImage();
            bird = new ImageIcon("flappybird.png").getImage();

            // Button setup
            startButton = new JButton("Start Game");
            startButton.setBounds(200, 600, 200, 50);
            startButton.setFocusPainted(false);
            startButton.setFont(new Font("Arial", Font.BOLD, 20));
            startButton.setBackground(new Color(255, 204, 0));
            startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            add(startButton);

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close homepage
                    FlappyBirdGameLauncher.main(null); // Launch the actual game
                }
            });

            timer = new Timer(1000 / 60, this); // ~60 FPS
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int bgWidth = background.getWidth(this);
            int bgHeight = background.getHeight(this);

            // Repeat background horizontally
            int x = bgX;
            while (x < getWidth()) {
                g2d.drawImage(background, x, 0, bgWidth, getHeight(), this);
                x += bgWidth;
            }

            // Reset scroll loop
            if (bgX <= -bgWidth) {
                bgX = 0;
            }

            // Title text
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            g2d.setColor(Color.WHITE);
            g2d.drawString("Flappy Bird", 170, 150);

            // Bird image (scaled and centered)
            int birdDrawWidth = 68;
            int birdDrawHeight = 48;
            int birdX = (getWidth() - birdDrawWidth) / 2;
            g2d.drawImage(bird, birdX, birdY, birdDrawWidth, birdDrawHeight, this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            bgX -= scrollSpeed;

            // Bird float animation
            birdY += floatDirection * floatSpeed;
            if (birdY > birdBaseY + floatRange || birdY < birdBaseY - floatRange) {
                floatDirection *= -1;
            }

            repaint();
        }
    }

    public static void main(String[] args) {
        new FlappyBirdHomePage();
    }
}