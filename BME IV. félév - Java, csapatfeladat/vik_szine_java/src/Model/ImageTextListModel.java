package Model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.*;

public class ImageTextListModel extends AbstractListModel<ImageTextItem> {
		private ArrayList<ImageTextItem> listOfPictures;
		public ImageTextListModel(ArrayList<String> startList) {
			listOfPictures = new ArrayList<ImageTextItem>();
				matchPicture(startList);
		}
		private void matchPicture(ArrayList<String> listWithIDs)  {
			try {
				if(!listOfPictures.isEmpty()){
					listOfPictures.clear();
				}
				for(String nextPicture: listWithIDs) {
						String trimmedName = nextPicture.replaceAll("[_1234567890]", "");
						BufferedImage image = ImageIO.read(new File("Model/imgs/"+trimmedName+".png"));
						String text = nextPicture;
						listOfPictures.add(new ImageTextItem(image,text));
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		public 	ArrayList<ImageTextItem> getList(){
			return listOfPictures;
		}
		public void changeList(ArrayList<String> newList) {
				matchPicture(newList);
		}
        @Override
        public int getSize() {
            return listOfPictures.size();
        }
        @Override
        public ImageTextItem getElementAt(int index) {
        	return listOfPictures.get(index);
        }
}
