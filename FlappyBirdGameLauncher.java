import javax.swing.*;

public class FlappyBirdGameLauncher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird gamePanel = new FlappyBird();

        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setResizable(false);
        frame.setVisible(true);
    }
}