package View;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class RulesPanel  extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public RulesPanel() {
		
	    this.setLayout(new GridLayout(8, 0));
	    
	    String cim = "KAMISADO" + "\n" + "JÁTÉKSZABÁLYZAT";
	    JLabel cimLabel = new JLabel("<html><div style='text-align: center;'>" + cim.replace("\n", "<br>") + "</div></html>", SwingConstants.CENTER);
	    cimLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
	    this.add(cimLabel);
	    
	    int fontSize = 25;
	    String fontType = "SansSerif";

	    JLabel label1 = new JLabel("<html><body style='width: 600px; text-align: justify'>" + "- A játék két játékos között zajlik, akik fehér vagy fekete tornyokat irányítanak." + "</body></html>");
	    label1.setFont(new Font(fontType, Font.PLAIN, fontSize));
	    label1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    this.add(label1);

	    JLabel label2 = new JLabel("<html><body style='width: 600px; text-align: justify'>" + "- A tornyok tetejei különböző színűek, amelyek megegyeznek a tábla mezőinek nyolc színével." + "</body></html>");
	    label2.setFont(new Font(fontType, Font.PLAIN, fontSize));
	    label2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    this.add(label2);

	    JLabel label3 = new JLabel("<html><body style='width: 600px; text-align: justify'>" + "- A játékosok felváltva lépnek tornyaikkal, de csak olyan toronnyal, amelynek színe megegyezik annak a mezőnek a színével ahová az előző játékos a bábujával lépett." + "</body></html>");
	    label3.setFont(new Font(fontType, Font.PLAIN, fontSize));
	    label3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    this.add(label3);

	    JLabel label4 = new JLabel("<html><body style='width: 600px; text-align: justify'>" + "- A tornyok csak egyenes vonalban mozoghatnak előre vagy átlósan előre, és nem ugorhatnak át más tornyokat." + "</body></html>");
	    label4.setFont(new Font(fontType, Font.PLAIN, fontSize));
	    label4.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    this.add(label4);

	    JLabel label5 = new JLabel("<html><body style='width: 600px; text-align: justify'>" + "- A játék akkor ér véget, amikor valaki eljuttat egy tornyot az ellenfél alapvonalára, vagy patthelyzet alakul ki." + "</body></html>");
	    label5.setFont(new Font(fontType, Font.PLAIN, fontSize));
	    label5.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    this.add(label5);
	    
	    JLabel label6 = new JLabel("<html><body style='width: 600px; text-align: justify'>" + "- Patthelyzet esetén az a játékos veszít aki utoljára lépett, mivel ő okozta a patthelyzetet." + "</body></html>");
	    label6.setFont(new Font(fontType, Font.PLAIN, fontSize));
	    label6.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	    this.add(label6);
	    
	}
}