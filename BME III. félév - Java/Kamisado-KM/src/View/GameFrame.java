package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

import GameControl.Game;

public class GameFrame extends JFrame{
	
	private static final long serialVersionUID = 6793953666455755789L;
	
	private int state; //0 - MainMenu; 1 - Game; 2 - SavedGames; 3 - Rules
	private GamePanel gamePanel;
	private MainMenuPanel mainMenuPanel;
	private SavedGamesPanel savedGamesPanel;
	private RulesPanel rulesPanel;
	
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem saveMenuItem;
    private JMenuItem backToMainMenuMenuItem;
    private JMenuItem lastMoveBackMenuItem;
    
    private Game game;
    

	public GameFrame() {
		super("Kamisado");
		this.setPreferredSize(new Dimension(814,864));
		createGameMenu();
		
		mainMenuPanel = new MainMenuPanel(this);
		savedGamesPanel = new SavedGamesPanel(this);
		gamePanel = new GamePanel(this);
		rulesPanel = new RulesPanel();
		
		this.switchToMainMenu();
		
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
	}
	
	// Létrehozza a MenuBart-t
	private void createGameMenu() {
	    menuBar = new JMenuBar();
	    menu = new JMenu("Játék");
	    
	    saveMenuItem = new JMenuItem("Mentés");
	    saveMenuItem.addActionListener(new SaveMenuItemListener());
	    
	    backToMainMenuMenuItem = new JMenuItem("Vissza a főmenübe");
	    backToMainMenuMenuItem.addActionListener(new BackToMainMenuListener());
	    
	    lastMoveBackMenuItem = new JMenuItem("Lépés visszavonása");
	    lastMoveBackMenuItem.addActionListener(new LastMoveBackListener());
	    

	    Font font = new Font("SansSerif", Font.PLAIN, 20);
	    menu.setFont(font);
	    saveMenuItem.setFont(font);
	    backToMainMenuMenuItem.setFont(font);
	    lastMoveBackMenuItem.setFont(font);

	    menu.add(backToMainMenuMenuItem);
	    menu.add(saveMenuItem);
	    menu.add(lastMoveBackMenuItem);
	    menuBar.add(menu);
	    
	    this.setJMenuBar(menuBar);
	}
	
	// A keretben megjelenő panelt átválltja a játék panelére
	public void switchToGamePanel() {
	    saveMenuItem.setVisible(true);
	    backToMainMenuMenuItem.setVisible(true);
	    lastMoveBackMenuItem.setVisible(true);

	    if (this.state == 0) {
	        this.remove(mainMenuPanel);
	        gamePanel = new GamePanel(this);
	    }
	    if (this.state == 1) {
	        this.remove(gamePanel);
	        gamePanel = new GamePanel(this,game);
	    }
	    if (this.state == 2) {
	        this.remove(savedGamesPanel);
	    }
	    this.add(gamePanel, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();

	    state = 1;
	}
	// A keretben megjelenő panelt átválltja a mentett játékok panelére
    public void switchToSavedGames() {
    	saveMenuItem.setVisible(false);
    	backToMainMenuMenuItem.setVisible(true);
    	
        this.remove(mainMenuPanel);
        
        savedGamesPanel = new SavedGamesPanel(this);
        this.add(savedGamesPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        
        state = 2;
    }
    // A keretben megjelenő panelt átválltja a főmenő panelére
    public void switchToMainMenu() {
    	saveMenuItem.setVisible(false);
    	backToMainMenuMenuItem.setVisible(false);
    	lastMoveBackMenuItem.setVisible(false);
    	
    	this.remove(savedGamesPanel);
    	this.remove(gamePanel);
    	this.remove(rulesPanel);
        this.add(mainMenuPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        
        state = 0;
    }
 // A keretben megjelenő panelt átválltja a játékszabályzat panelére
    public void switchToRules() {
    	saveMenuItem.setVisible(false);
    	backToMainMenuMenuItem.setVisible(true);
    	lastMoveBackMenuItem.setVisible(false);
    	
    	this.remove(savedGamesPanel);
    	this.remove(gamePanel);
    	this.remove(mainMenuPanel);
        this.add(rulesPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        
        state = 4;
    }
    
    public int getState() {
    	return this.state;
    }
    
    public Game getGame() {
    	return this.game;
    }
    
    public void setGame(Game game) {
    	this.game = game;
    }
    
    // Új játékot állít be
    public void setNewGame(Game game) {
    	this.game = game;
    	gamePanel = new GamePanel(this, game);
    }
    
    private class SaveMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(getState()==1) {
                String fileName = JOptionPane.showInputDialog("Játék mentés ezen a néven:");
                if (fileName != null) {
                	
                    File directory = new File("saved");
                    if (!directory.exists()) {
                        directory.mkdir();
                    }
                    
                    fileName = "saved/" + fileName;
                    File file = new File(fileName);
                    if (!file.exists()) {
                        try {
                            FileOutputStream fileOut = new FileOutputStream(fileName);
                            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                            out.writeObject(game);
                            out.close();
                            fileOut.close();
                            game.setSaved(true);
                            JOptionPane.showMessageDialog(null, "A mentés SIKERES volt!");
                 
                        } catch (IOException i) {
                             JOptionPane.showMessageDialog(null, "A mentés SIKERTELEN volt!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "A mentés SIKERTELEN volt, mert már létezik ilyen nevű mentés!");
                    }
                }
            }
        }
    }
	
	private class BackToMainMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(getState() == 1) {
				if(!game.isSaved() && !game.isOver() && !game.isPatt()) {
					int response = JOptionPane.showConfirmDialog(null, "Még nem mentetted a játékot! Biztosan kilépsz? ", "Igen",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.YES_OPTION) {  
				    	switchToMainMenu();
				    	return;
				    } else if (response == JOptionPane.NO_OPTION) {
				        	return;
				    }
				} 
			}
			switchToMainMenu();
		}
	}
	private class LastMoveBackListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        if (!game.isFirst()) {
	            Game newGame = game.backOneMove();
	            if (newGame != null) {
	                setGame(newGame);
	                switchToGamePanel();
	            }
	        }
	    }
	}

}

