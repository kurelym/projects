package View;
import javax.swing.*;

import GameControl.Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class SavedGamesPanel extends JPanel {
	
    private static final long serialVersionUID = -3578984157296074901L;
    
    private final GameFrame gameFrame;
    
    private JComboBox<String> comboBox;
    private Map<String, Game> savedGames = new HashMap<>();
    

    public SavedGamesPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setPreferredSize(new Dimension(814,864));
        setLayout(new GridLayout(7, 0));

        add(new JLabel(""));

        JLabel kamisado = new JLabel("KAMISADO", SwingConstants.CENTER);
        kamisado.setFont(new Font("SansSerif", Font.BOLD, 72));
        add(kamisado);

        add(new JLabel(""));

        this.add(this.createComboBox());

        add(new JLabel(""));

        JLabel mentesek = new JLabel("MENTÉSEK", SwingConstants.CENTER);
        mentesek.setFont(new Font("SansSerif", Font.BOLD, 72));
        add(mentesek);

        add(new JLabel(""));
    }
    
    public void update() {
    	this.comboBox=this.createComboBox();
    	repaint();
    }

    private class ComboBoxActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedName = (String) comboBox.getSelectedItem();
            if(selectedName.equals("Válassz egyet a listából!")) return;
            
            gameFrame.setNewGame(savedGames.get(selectedName));
            gameFrame.switchToGamePanel();
        }
    }

    public Game loadGame(String fileName) {
        Game loadedGame = null;
        try {
            FileInputStream fileIn = new FileInputStream("saved/" + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadedGame = (Game) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
        return loadedGame;
    }
    
    public JComboBox<String> createComboBox() {
        File folder = new File("saved");
        
        if (!folder.exists()) {
            folder.mkdir();
        }

        File[] listOfFiles = folder.listFiles();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("Válassz egyet a listából!");

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Game newest = loadGame(file.getName());
                    if(newest != null) {
                        comboBoxModel.addElement(file.getName());
                        savedGames.put(file.getName(), newest);
                    }
                }
            }
        }
        this.comboBox = new JComboBox<>(comboBoxModel);
        this.comboBox.setBackground(new Color(173, 216, 230));
        this.comboBox.setFont(new Font("Serif", Font.BOLD, 40));
        this.comboBox.addActionListener(new ComboBoxActionHandler());

        return this.comboBox;
    }
}