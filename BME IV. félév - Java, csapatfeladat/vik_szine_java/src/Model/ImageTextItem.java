package Model;
import java.awt.image.BufferedImage;

public class ImageTextItem {
	private BufferedImage image;
    private String text;

    public ImageTextItem(BufferedImage image, String text) {
        this.image = image;
        this.text = text;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}