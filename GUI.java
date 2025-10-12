import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private JLabel playersLabel;
    private JLabel hostLabel;
    private JLabel phraseLabel;

    private JButton addPlayerButton;
    private JButton setHostButton;
    private JButton startGameButton;

    private List<Player> players = new ArrayList<>();
    private Host host;
    private Game game;

    public MainWindow() {
        setTitle("Wheel of Fortune");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new FlowLayout());

        // Players
        playersLabel = new JLabel("Players: None");
        addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> addPlayer());

        // Host
        hostLabel = new JLabel("Host: None");
        setHostButton = new JButton("Set Host");
        setHostButton.addActionListener(e -> setHost());

        // Phrase
        phraseLabel = new JLabel("Current Phrase: ______");
        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> startGame());

        // Add everything to window
        add(playersLabel);
        add(addPlayerButton);
        add(hostLabel);
        add(setHostButton);
        add(phraseLabel);
        add(startGameButton);

        setVisible(true);
    }

    private void addPlayer() {
        String name = JOptionPane.showInputDialog(this, "Enter player name:");
        if (name != null && !name.trim().isEmpty()) {
            players.add(new Player(name.trim()));
            updatePlayersLabel();
        }
    }

    private void updatePlayersLabel() {
        if (players.isEmpty()) {
            playersLabel.setText("Players: None");
        } else {
            StringBuilder sb = new StringBuilder("Players: ");
            for (Player p : players) {
                sb.append(p.getName()).append(", ");
            }
            playersLabel.setText(sb.substring(0, sb.length() - 2));
        }
    }

    private void setHost() {
        String name = JOptionPane.showInputDialog(this, "Enter host name:");
        if (name == null || name.trim().isEmpty()) return;

        String gamePhrase = JOptionPane.showInputDialog(this, "Enter game phrase:");
        if (gamePhrase == null || gamePhrase.trim().isEmpty()) return;

        host = new Host(name.trim(), gamePhrase.trim());
        game = new Game(host, players);
        phraseLabel.setText("Current Phrase: " + game.getDisplayedPhrase());
        hostLabel.setText("Host: " + host.getName());
    }

    private void startGame() {
        if (game == null) {
            JOptionPane.showMessageDialog(this, "Set a host and phrase first!");
            return;
        }
        if (players.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add at least one player first!");
            return;
        }

        boolean gameOver = false;
        while (!gameOver) {
            for (Player p : players) {
                char guess = JOptionPane.showInputDialog(this,
                        p.getName() + ", enter a letter:").toUpperCase().charAt(0);

                boolean correct = game.guessLetter(p, guess);
                phraseLabel.setText("Current Phrase: " + game.getDisplayedPhrase());

                String msg = p.getName() + (correct ? " guessed correctly!" : " guessed wrong!") +
                        "\nMoney: $" + p.getMoney();
                JOptionPane.showMessageDialog(this, msg);

                if (game.isWon()) {
                    JOptionPane.showMessageDialog(this, p.getName() + " has won the game!");
                    int playAgain = JOptionPane.showConfirmDialog(this, "Play again?", "Play Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (playAgain == JOptionPane.YES_OPTION) {
                        game.reset();
                        phraseLabel.setText("Current Phrase: " + game.getDisplayedPhrase());
                    }
                    gameOver = true;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
