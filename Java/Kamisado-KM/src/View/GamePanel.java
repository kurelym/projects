package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Elements.Board;
import Elements.Field;
import Elements.Player;
import Elements.Tower;
import GameControl.Game;
public class GamePanel extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134473190843310803L;

	private final GameFrame gameFrame;
	private Game game;
	
	public GamePanel(GameFrame frame, Game game) {
		this.gameFrame = frame;
		this.game = game;
		
		this.gameFrame.setGame(this.game);
		this.addMouseListener(this);
	}
	
	
	public GamePanel(GameFrame frame) {
		this.gameFrame = frame;
		this.game = new Game();
		
		this.gameFrame.setGame(this.game);
		this.addMouseListener(this);
	}
	
	public void setNewGame() {
		this.game = new Game();
	}
	
	public Game getGame() {
		return this.game;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 100;
        int y = e.getY() / 100;
        /*
        if(this.game.getAktPlayer().equals(this.game.getWhitePlayer())) {
        	this.game.nextMove(8 - y - 1,8 - x - 1);
        } else {
        	this.game.nextMove(y,x);
        }*/
        
        this.game.nextMove(y,x);
        if(this.game.isOver()) {
        	Player winner = this.game.getWinnerPlayer();
        	String winnerColor = winner.getColor().toString();
        	
        	JOptionPane.showMessageDialog(this, "A játék véget ért! A nyertes játékos a " + winnerColor);
        	//gameFrame.switchToMainMenu();
        }
        if(this.game.isPatt()) {
        	Player winner = this.game.getWinnerPlayer();
        	String winnerColor = winner.getColor().toString();
        	JOptionPane.showMessageDialog(this, "A játék véget ért! Az utolsó lépés miatt PATT helyzet alakult ki. Ezért a " + winnerColor +" játékos vesztett!");
        	//gameFrame.switchToMainMenu();
        }
        repaint();
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
		Board board = this.game.getBoard();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(12));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field field =board.getFieldAtPlace(i, j);
                g2.setColor(field.getColor().getAwtColor());
                g2.fillRect(j * 100, i * 100, 100, 100);
                g2.setStroke(new BasicStroke(4));
                g2.setColor(Color.BLACK);
                g2.drawRect(j * 100, i * 100, 100, 100);
                if (!field.isFree()) {
                    Tower tower = field.getTower();
                    
                    g2.setColor(tower.getOwnerColor().getAwtColor());
                    g2.setStroke(new BasicStroke(15));
                    g2.drawOval(j * 100 + 12, i * 100 + 12, 75, 75);
                    g2.fillOval(j * 100 + 12, i * 100 + 12, 75, 75);
                    
                    g2.setColor(tower.getColor().getAwtColor());
                    g2.setStroke(new BasicStroke(15));
                    g2.drawOval(j * 100 + 30, i * 100 + 30, 40, 40);
                    g2.fillOval(j * 100 + 30, i * 100 + 30, 40, 40);
                }
                if(field.isPossible()) {
                	g2.setColor(Color.WHITE);
                	g2.setStroke(new BasicStroke(5));
                	g2.drawOval(j * 100 + 30, i * 100 + 30, 40, 40);
                	g2.fillOval(j * 100 + 30, i * 100 + 30, 40, 40);
                }
                
                if(field.isSelected()) {
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(10));
                    g2.drawLine(j * 100 + 30, i * 100 + 30, (j + 1) * 100 - 30, (i + 1) * 100 - 30);
                    g2.drawLine(j * 100 + 30, (i + 1) * 100 - 30, (j + 1) * 100 - 30, i * 100 + 30);
                }
            }
        }
        repaint();
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
