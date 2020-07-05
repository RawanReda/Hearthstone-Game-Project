package Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class ImageTextArea extends JTextArea{

	private BufferedImage image;
	
	public ImageTextArea() {}
	public ImageTextArea(String s) {
		super();
		try {
		image = ImageIO.read(new File(s));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (image != null) {
            int x = getWidth() - image.getWidth();
            int y = getHeight() - image.getHeight();
            g2d.drawImage(image, x, y, this);    
        }
        super.paintComponent(g2d);
        g2d.dispose();
    }

}
