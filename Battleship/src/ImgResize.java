import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImgResize {

	public ImageIcon resize(BufferedImage img, int x, int y) throws IOException {
		
		ImageIcon resized = new ImageIcon(img);
		Image icon = resized.getImage();
		Image change = icon.getScaledInstance(x,  y,  java.awt.Image.SCALE_SMOOTH);
		resized = new ImageIcon(change);
		return resized;
	}
}
