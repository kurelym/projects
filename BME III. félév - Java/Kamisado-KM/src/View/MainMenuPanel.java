package View;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenuPanel  extends JPanel{
	
	private final GameFrame gameFrame;
	private JButton startButton;
	
	private static final long serialVersionUID = 1L;

	public MainMenuPanel(GameFrame gameFrame) {
		
		this.gameFrame = gameFrame;
	    this.setLayout(new GridLayout(9, 0));
	    
	    this.add(new JLabel(""));
	    
	    JLabel kamisado = new JLabel("KAMISADO", SwingConstants.CENTER );
	    kamisado.setFont(new Font("SansSerif", Font.BOLD, 72));
	    this.add(kamisado);
	    
	    this.add(new JLabel(""));
	    
	    this.startButton = new JButton("Új Játék");
	    startButton.setFont(new Font("SansSerif", Font.BOLD, 40));
	    startButton.addActionListener(new StartButtonListener());
	    this.add(startButton);
	    
	    JButton loadButton = new JButton("Mentett Játék Folytatása");
	    loadButton.setFont(new Font("SansSerif", Font.BOLD, 40));
	    loadButton.addActionListener(new LoadButtonListener());
	    this.add(loadButton);
	    
	    JButton rulesButton = new JButton("Játékszabályzat");
	    rulesButton.setFont(new Font("SansSerif", Font.BOLD, 40));
	    rulesButton.addActionListener(new RulesButtonListener());
	    this.add(rulesButton);
	    
	    this.add(new JLabel(""));
	    
	    JLabel jatek = new JLabel("TÁRSASJÁTÉK", SwingConstants.CENTER);
	    jatek.setFont(new Font("SansSerif", Font.BOLD, 72));
	    this.add(jatek);
	    
	    JLabel copyright = new JLabel(" © Kurely Mózes ©   ", SwingConstants.RIGHT);
	    copyright.setFont(new Font("SansSerif", Font.BOLD, 20));
	    this.add(copyright);
	}
	
	private class StartButtonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	        gameFrame.switchToGamePanel();
	    }
	}
	
	private class LoadButtonListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 gameFrame.switchToSavedGames();
		 }
	}
	
	private class RulesButtonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	        gameFrame.switchToRules();
	    }
	}
	

}