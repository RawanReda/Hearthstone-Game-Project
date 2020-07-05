package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.ImageJPanel;
import engine.*;
import exceptions.*;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import model.heroes.*;

public class Trial extends JPanel{
	

	public Trial() throws MalformedURLException, IOException {
		setLayout(new BorderLayout());
		this.setBounds(00, 00, 500, 501);
		ImageIcon x = new ImageIcon(
				"/HS-M2/Images/Warlock.png");

		BufferedImage image1 = resize(x, 200, 200);

		JLabel background = new JLabel(new ImageIcon(image1));

		background.setLayout(new BorderLayout());
add(background);
		JButton c= new JButton("Trials?");
	//	c.setPreferredSize(new Dimension(5, 5));add(c);
		
	///setOpaque(false);
		
		this.setVisible(true);
		this.validate();
		this.repaint();
	}

	public static BufferedImage resize(ImageIcon x, int newW, int newH) {

		final BufferedImage image = new BufferedImage(x.getIconWidth(), x.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics g = image.createGraphics();
		
      x.paintIcon(null, g, 0, 0);
		Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		
		g2d.drawImage(tmp, 0, 0, null); 
		g2d.setFont(g.getFont().deriveFont(20f));
	
	    // g2d.drawImage(dimg, 50, 20, 100, 100,null,null);
		 g2d.dispose();

		return dimg;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		new Trial();
	}
}
