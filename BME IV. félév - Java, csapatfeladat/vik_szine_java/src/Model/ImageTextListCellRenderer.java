package Model;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ImageTextListCellRenderer extends JLabel implements ListCellRenderer<ImageTextItem>{
	 @Override
     public Component getListCellRendererComponent(JList<? extends ImageTextItem> list,
    		 									   ImageTextItem value,
                                                   int index,
                                                   boolean isSelected,
                                                   boolean cellHasFocus) {                                           
		 if (value != null) {
			 ImageIcon imageIcon = new ImageIcon(value.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
             setIcon(imageIcon);
             setHorizontalAlignment(SwingConstants.CENTER);
             if (isSelected) {
                 setBackground(Color.GREEN);
                 setForeground(list.getSelectionForeground());
             }
             else {
                 setBackground(list.getBackground());
                 setForeground(list.getForeground());
             }
             setOpaque(true);
         }
		 return this;
     }
}