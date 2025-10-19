import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class GUI extends JFrame {
    // UI Components
    private JLabel playersLabel;
    private JLabel hostLabel;
    private JLabel phraseLabel;
    private JButton startGameButton;
    private JTextArea messageArea;
    private JCheckBox saveMessagesCheckBox;

    // Game State
    private List<Player> players = new ArrayList<>();
    private Host host;
    private Game game;

    public GUI() { 
        setTitle("Word Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout(10, 10)); 
        
        // Setup Menus
        setupMenuBar();

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel statusPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        
        playersLabel = new JLabel("Players: None");
        hostLabel = new JLabel("Host: None");
        phraseLabel = new JLabel("Current Phrase: ______");
        
        playersLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        hostLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        phraseLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        phraseLabel.setForeground(new Color(0, 100, 0));
        
        statusPanel.add(playersLabel);
        statusPanel.add(hostLabel);
        statusPanel.add(phraseLabel);

        // Start Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startGameButton = new JButton("Start Game");
        startGameButton.setEnabled(false); 
        startGameButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        startGameButton.addActionListener(e -> startGame());
        buttonPanel.add(startGameButton);

        centerPanel.add(statusPanel);
        centerPanel.add(Box.createVerticalStrut(30)); 
        centerPanel.add(buttonPanel);
        
        add(centerPanel, BorderLayout.CENTER);

        // South Panel
        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        southPanel.setBorder(BorderFactory.createTitledBorder("Game Log"));
        
        // Message Area
        messageArea = new JTextArea(8, 40); 
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        
        saveMessagesCheckBox = new JCheckBox("Save Messages", true);
        saveMessagesCheckBox.setToolTipText("If checked, messages accumulate. If unchecked, each new message overwrites the previous one."); 
        
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkPanel.add(saveMessagesCheckBox);
        
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.add(checkPanel, BorderLayout.SOUTH);
        
        add(southPanel, BorderLayout.SOUTH);

        pack(); 
        setLocationRelativeTo(null); 
        setVisible(true);
        
        appendMessage("Welcome to Word Game! Use the 'Game' menu (Alt-G) to add players and set the host to begin.");
    }
    
    // Menu Bar Setup
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Game Menu 
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        
        JMenuItem addPlayerItem = new JMenuItem("Add Player"); 
        addPlayerItem.addActionListener(e -> addPlayer());
        
        JMenuItem setHostItem = new JMenuItem("Set Host & Phrase"); 
        setHostItem.addActionListener(e -> setHost());
        
        JMenuItem newGameItem = new JMenuItem("New Game (Reset All)");
        newGameItem.addActionListener(e -> resetGameSetup());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        gameMenu.add(addPlayerItem);
        gameMenu.add(setHostItem);
        gameMenu.addSeparator();
        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        // About Menu
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        
        JMenuItem aboutAppItem = new JMenuItem("About App");
        aboutAppItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, 
                "Word Game Game\nVersion 1.1\nFeaturing enhanced GUI and logging.", 
                "About Application", 
                JOptionPane.INFORMATION_MESSAGE));
        
        JMenuItem layoutItem = new JMenuItem("Layout Explanation");
        layoutItem.addActionListener(e -> showLayoutExplanation());
        
        aboutMenu.add(aboutAppItem);
        aboutMenu.add(layoutItem);

        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);
        
        setJMenuBar(menuBar); 
    }
    
    // Layout
    private void showLayoutExplanation() {
        String explanation = 
            "I chose a border layout for the main frame because it has good structure, with the game controls in the center and the message log in the south.\n";
            
        JOptionPane.showMessageDialog(this, explanation, "Layout Manager Choice", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void appendMessage(String message) {
        String timeStamp = new java.text.SimpleDateFormat("HH:mm:ss").format(new Date());
        String fullMessage = "[" + timeStamp + "] " + message + "\n";
        
        if (SwingUtilities.isEventDispatchThread()) {
            performMessageUpdate(fullMessage);
        } else {
            SwingUtilities.invokeLater(() -> performMessageUpdate(fullMessage));
        }
    }
    
    private void performMessageUpdate(String fullMessage) {
        if (saveMessagesCheckBox.isSelected()) {
            messageArea.append(fullMessage);
        } else {
            messageArea.setText(fullMessage.trim()); 
        }
        
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }

    private void clearMessages() {
        messageArea.setText("");
        appendMessage("--- New Game/Round Setup Initiated ---");
    }


    private void resetGameSetup() {
        players.clear();
        host = null;
        game = null;
        
        updatePlayersLabel();
        hostLabel.setText("Host: None");
        phraseLabel.setText("Current Phrase: ______");
        startGameButton.setEnabled(false);
        
        clearMessages(); 
        appendMessage("The game has been fully reset. Use the 'Game' menu to set up a new round.");
    }

    private void addPlayer() {
        String name = JOptionPane.showInputDialog(this, "Enter player name:");
        if (name != null && !name.trim().isEmpty()) {
            String trimName = name.trim();
            for (Player p : players) {
                if (p.getName().equalsIgnoreCase(trimName)) {
                    appendMessage("Error: Player '" + trimName + "' already exists!");
                    return;
                }
            }
            players.add(new Player(trimName));
            updatePlayersLabel();
            if (game != null) {
                startGameButton.setEnabled(true);
            }
            appendMessage("Player '" + trimName + "' added.");
        }
    }

    private void updatePlayersLabel() {
        if (players.isEmpty()) {
            playersLabel.setText("Players: None");
        } else {
            StringBuilder sb = new StringBuilder("Players: ");
            for (Player p : players) {
                sb.append(p.getName()).append(" ($").append(p.getMoney()).append("), ");
            }
            playersLabel.setText(sb.substring(0, sb.length() - 2));
        }
    }

    private void setHost() {
        String name = JOptionPane.showInputDialog(this, "Enter host name:");
        if (name == null || name.trim().isEmpty()) return;

        String gamePhrase = JOptionPane.showInputDialog(this, "Enter game phrase:");
        if (gamePhrase == null || gamePhrase.trim().isEmpty() || !gamePhrase.matches(".*[a-zA-Z].*")) {
             appendMessage("Error: Invalid phrase. Please enter a phrase containing letters.");
             return;
        }

        host = new Host(name.trim(), gamePhrase.trim());
        game = new Game(host, players);
        
        if (players.isEmpty()) {
             appendMessage("Host '" + host.getName() + "' set. Add players now to enable Start Game.");
        } else {
             startGameButton.setEnabled(true);
             appendMessage("Host '" + host.getName() + "' set, and game phrase is ready: " + game.getDisplayedPhrase());
        }
        
        phraseLabel.setText("Current Phrase: " + game.getDisplayedPhrase());
        hostLabel.setText("Host: " + host.getName());
    }

    private void startGame() {
        if (game == null) {
            appendMessage("Error: Set a host and phrase first!");
            return;
        }
        if (players.isEmpty()) {
            appendMessage("Error: Add at least one player first!");
            return;
        }

        // Disable main game button
        startGameButton.setEnabled(false);

        new Thread(() -> {
            boolean gameOver = false;
            if (game.isWon()) {
                game.reset(); 
            }
            
            SwingUtilities.invokeLater(() -> {
                phraseLabel.setText("Current Phrase: " + game.getDisplayedPhrase());
                clearMessages();
                appendMessage("Game started! Phrase to guess: " + game.getDisplayedPhrase());
            });

            while (!gameOver) {
                for (Player p : players) {
                    
                    String input = null;
                    char guess = ' ';
                    boolean inputValid = false;
                    
                    while (!inputValid && !gameOver) {
                        input = JOptionPane.showInputDialog(this, p.getName() + " ($" + p.getMoney() + 
                            "), enter a letter to guess or hit CANCEL to end the round:", 
                            "Player Turn: " + p.getName());
                        
                        if (input == null) {
                            int confirm = JOptionPane.showConfirmDialog(this, 
                                "Are you sure you want to stop the current round?", "End Round", 
                                JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                gameOver = true;
                            }
                            inputValid = true; 
                        } else if (input.trim().length() == 1 && Character.isLetter(input.trim().charAt(0))) {
                            guess = input.toUpperCase().charAt(0);
                            inputValid = true;
                        } else {
                            appendMessage("Input Error: " + p.getName() + " entered invalid input. Must be one letter.");
                        }
                    }
                    
                    if (gameOver) break;

                    // guessLetter now returns the number of letters revealed
                    int lettersFound = game.guessLetter(p, guess);
                    
                    SwingUtilities.invokeLater(() -> {
                        phraseLabel.setText("Current Phrase: " + game.getDisplayedPhrase());
                        updatePlayersLabel();
                    });
                    
                    if (lettersFound > 0) {
                        appendMessage(p.getName() + " guessed '" + guess + "' correctly! Revealed " + lettersFound + " letters. Money: $" + p.getMoney());
                    } else {
                        appendMessage(p.getName() + " guessed '" + guess + "' wrong. Turn passes.");
                    }

                    if (game.isWon()) {
                        appendMessage("CONGRATULATIONS! " + p.getName() + " has won the game! Final Money: $" + p.getMoney());
                        
                        int playAgain = JOptionPane.showConfirmDialog(this, "Do you want to play a new round?", "Game Over",
                                JOptionPane.YES_NO_OPTION);
                        
                        if (playAgain == JOptionPane.YES_OPTION) {
                             SwingUtilities.invokeLater(() -> setHost());
                        } else {
                            gameOver = true;
                        }
                        break; 
                    }
                }
                if (gameOver) break;
            }
            SwingUtilities.invokeLater(() -> {
                startGameButton.setEnabled(true);
                appendMessage("Game round completed.");
            });
        }).start();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(GUI::new);
    }
}
