package Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Grafikus felhasználói felület osztály, mely felelős a menü megjelenítéséért és a felhasználói interakciók kezeléséért.
 * Tartalmaz két belső osztályt a START és ADDPLAYER gombok működésének kezelésére.
 */
public class MenuView extends JFrame {

    private JPanel panel;
    private JLabel titleLabel;
    private JLabel playersLabel;
    private JButton startButton;
    private JButton addPlayerButton;
    private JTextField playerField;
    private static JList<String> playersList;
    private JPanel buttonPanel;
    private JScrollPane listScrollPane;
    private DefaultListModel<String> listModel;

    /**
     * Inicializálja a MenuView-t, beállítva a felhasználói felület komponenseit.
     */
    public MenuView() {
        super("vik_szine_java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Panel létrehozása és hátterének beállítása
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);

        GridBagConstraints constraints = new GridBagConstraints();
 
        titleLabel = new JLabel("A Logarléc");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 20, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, constraints);

        //Játékosok
        playersLabel = new JLabel("Játékosok:");
        playersLabel.setFont(new Font("Serif", Font.BOLD, 24));
        playersLabel.setForeground(Color.ORANGE);
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(playersLabel, constraints);

        // Játékosok listája
        listModel = new DefaultListModel<>();
        playersList = new JList<>(listModel);
        playersList.setBackground(Color.LIGHT_GRAY);
        playersList.setFont(new Font("Serif", Font.PLAIN, 16));
        playersList.setEnabled(false);
        listScrollPane = new JScrollPane(playersList);
        listScrollPane.setPreferredSize(new Dimension(200, 100));
        listScrollPane.setBackground(Color.LIGHT_GRAY);
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 10, 10, 10);
        panel.add(listScrollPane, constraints);

        // Új játékos hozzáadása
        playerField = new JTextField(15);
        playerField.setFont(new Font("Serif", Font.PLAIN, 16));
        playerField.setBackground(Color.LIGHT_GRAY);
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(playerField, constraints);


        // Gombok: Start és Add Player
        startButton = new JButton("Start");
        startButton.setForeground(Color.DARK_GRAY);
        startButton.setPreferredSize(new Dimension(120, 40));
        startButton.setFont(new Font("Serif", Font.BOLD, 20));

        addPlayerButton = new JButton("Add Player");
        addPlayerButton.setForeground(Color.DARK_GRAY);
        addPlayerButton.setPreferredSize(new Dimension(120, 40));
        addPlayerButton.setFont(new Font("Serif", Font.BOLD, 20));

        // ActionListener-ek hozzáadása
        addPlayerButton.addActionListener(new AddButtonListener());
        startButton.addActionListener(new StartButtonListener());

        buttonPanel = new JPanel(new GridLayout(1, 2, 100, 0));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(startButton);
        buttonPanel.add(addPlayerButton);
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(buttonPanel, constraints);

        // Kész panel elhelyezése a frame-ben
        getContentPane().add(panel, BorderLayout.CENTER);
        setSize(500, 400); // Keret mérete
        setVisible(true);
    }
    
    /**
     * Ellenőrzi, hogy egy adott névvel rendelkező játékos már létezik-e.
     *
     * @param playerName Az ellenőrizendő játékos neve.
     * @return True, ha a játékos már létezik, különben false.
    */
    private boolean isPlayerExists(String playerName) {
        for (int i = 0; i < listModel.size(); i++) {
            if (listModel.getElementAt(i).equals(playerName)) {
                return true; // A játékos már létezik
            }
        }
        return false; // A játékos nem létezik még
    }

    /**
     * Hozzáad egy új játékost a játékosok listájához.
    */
    private void addPlayer() {
        String playerName = playerField.getText().trim();
        if (!playerName.isEmpty()) {
        	if(listModel.size()>=5) {
        		JOptionPane.showMessageDialog(this, "Több játékost már nem lehet hozzáadni a játékhoz!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
        	}
        	else {
        		if (!isPlayerExists(playerName)) {
                    listModel.addElement(playerName);
                    playerField.setText("");
                } 
                else {
                    JOptionPane.showMessageDialog(this, "Ezzel a névvel már létezik játékos!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
                }
        	}
        }
    }
    /**
     * Visszaadja a játékosok neveit String tömb formájában.
     *
     * @return A játékosok neveit tartalmazó String tömb.
    */
    public static String[] getPlayerNames() {
        DefaultListModel<String> listModel = (DefaultListModel<String>) playersList.getModel();
        String[] playerArray = new String[listModel.size()];
        for (int i = 0; i < listModel.size(); i++) {
            playerArray[i] = listModel.get(i);
        }
        return playerArray;
    }
    private void warning() {
    	JOptionPane.showMessageDialog(this, "Nem adtál meg játékosokat!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    }
    /**
     * ActionListener az Add Player gomb eseménykezeléséhez.
     */
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addPlayer();
        }
    }
    /**
     * ActionListener a Start gomb eseménykezeléséhez.
     */
    private class StartButtonListener implements ActionListener {
        @Override
        
        public void actionPerformed(ActionEvent e) {
            if(MenuView.getPlayerNames().length==0){
                warning();
            }
            else{
                GameViewController gvc = new GameViewController(MenuView.getPlayerNames());
            }
            /*GameViewController gvc = new GameViewController();
            ArrayList nodes = new ArrayList<>();
            HashMap<String,ArrayList<String>> connections = new HashMap<String,ArrayList<String>>();
            nodes.add("Room_1");
            nodes.add("Room_2");
            nodes.add("Room_3");
            ArrayList connections1 = new ArrayList<>();
            
            connections1.add("Room_2");
            ArrayList connections2 = new ArrayList<>();
            ArrayList connections3 = new ArrayList<>();
            ArrayList connections4 = new ArrayList<>();
            connections.put("Room_1", connections1);
            connections.put("Room_2", connections2);
            connections.put("Room_3", connections3);
            String[] names = getPlayerNames();
            ArrayList<String> inventory = new ArrayList<String>();
            inventory.add("SlideRule_1");
            inventory.add("CamambertCheese_1");
            inventory.add("TVSZ_1");
            inventory.add("Transistor_1");
            inventory.add("Transistor_2");
            int round = 4;
            ArrayList items = new ArrayList<>();
            items.add("Mask_1");
            items.add("Mask_2");
            items.add("TVSZ_2");
            items.add("HolyBeer_1");
            items.add("WunderBaum_1");
            items.add("DirtyRag_1");
            ArrayList peoples = new ArrayList<>();
            peoples.add("Teacher_1");
            peoples.add("Cleaner_1");
            peoples.add("Student_1");
            ViewData testData = new ViewData(names,"Room_1",round,inventory,items,peoples,nodes,connections );
            GameView gv = new GameView(testData,gvc);
            gvc.addView(gv);
            gv.setVisible(true);*/
        
        }
    }
}