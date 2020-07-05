package Controller;

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

public class FieldMinion extends JPanel {
public Minion min;
	public FieldMinion(Minion a) throws MalformedURLException, IOException {
		min=a;
		setLayout(new BorderLayout());
		this.setBounds(00, 00, 500, 501);

		String name = a.getName();
		System.out.println(name);

		String imagename = getImageName(a.getName());
		ImageIcon x = new ImageIcon(imagename);
		System.out.println(x.getIconHeight());
		int Manacost = a.getManaCost();
		int Attackpoints = a.getAttack();
		int Health = a.getCurrentHP();
		boolean Sleeping = a.isSleeping();
		boolean Taunt = a.isTaunt();
		BufferedImage image1 = resize(x, 120, 130, Manacost, Attackpoints, Health, Sleeping);
		JLabel background = new JLabel(new ImageIcon(image1));
		background.setLayout(new BorderLayout());
		setOpaque(false);
		add(background);
		this.setVisible(true);
		this.validate();
		this.repaint();
	}
	
public static String getImageName(String name) {
	String imagename="";
	if (name.equals("Argent Commander"))
		imagename = "/HS-M2/Images/Card Templates Minions/Argent Commander.png";
	if (name.equals("Bloodfen Raptor"))
		imagename = "/HS-M2/Images/Card Templates Minions/Bloodfen Raptor.png";
	if (name.equals("Chilwind Yeti"))
		imagename = "/HS-M2/Images/Card Templates Minions/Chillwind Yeti.png";
	if (name.equals("Chromaggus"))
		imagename = "/HS-M2/Images/Card Templates Minions/Chromaggus.png";
	if (name.equals("Frostwolf Grunt"))
		imagename = "/HS-M2/Images/Card Templates Minions/Frostwolf Grunt.png";
	if (name.equals("Core Hound"))
		imagename = "/HS-M2/Images/Card Templates Minions/Core Hound.png";
	if (name.equals("Icehowl"))
		imagename = "/HS-M2/Images/Card Templates Minions/icehowl.png";
	if (name.equals("Stonetusk Boar"))
		imagename = "/HS-M2/Images/Card Templates Minions/Stonetusk Boar.png";
	if (name.equals("Sunwalker"))
		imagename = "/HS-M2/Images/Card Templates Minions/Sunwalker.png";
	if (name.equals("The LichKing"))
		imagename = "/HS-M2/Images/Card Templates Minions/The LichKing.png";
	if (name.equals("Colossus of the Moon"))
		imagename = "/HS-M2/Images/Card Templates Minions/Colossus of the Moon.png";
	if (name.equals("Wolfrider"))
		imagename = "/HS-M2/Images/Card Templates Minions/Wolfrider.png";
	if (name.equals("Boulderfist Ogre"))
		imagename = "/HS-M2/Images/Card Templates Minions/Boulderfist Ogre.png";
	if (name.equals("Goldshire Footman"))
		imagename = "/HS-M2/Images/Card Templates Minions/Goldshire Footman.png";
	if (name.equals("King Krush"))
		imagename = "/HS-M2/Images/Card Templates Minions/King Krush.png";
	if (name.equals("Kalcgos"))
		imagename = "/HS-M2/Images/Card Templates Minions/Kalecgos.png";
	if (name.equals("Silver Hand Recruit"))
		imagename = "/HS-M2/Images/Card Templates Minions/Silver Hand Recruit.png";
	if (name.equals("Wilfred Fizzlebang"))
		imagename = "/HS-M2/Images/Card Templates Minions/Wilfred Fizzlebang.png";
	if (name.equals("Prophet Velen"))
		imagename = "/HS-M2/Images/Card Templates Minions/Prophet Velen.png";
	if (name.equals("Tirion Fordring"))
		imagename = "/HS-M2/Images/Card Templates Minions/Wilfred Fizzlebang.png";
	return imagename;
	
}
	public static BufferedImage resize(ImageIcon x, int newW, int newH, int mana, int attack, int health,
			boolean sleep) {

		BufferedImage image = new BufferedImage(375, 518, BufferedImage.TYPE_INT_ARGB_PRE);

		Graphics g = image.createGraphics();

		x.paintIcon(null, g, 0, 0);
		Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();

		g2d.drawImage(tmp, 0, 0, null);
		g2d.setFont(g.getFont().deriveFont(20f));
		if(newW>150 && newH>150) {
			 g2d.drawString(String.valueOf(mana), 15, 50);
		     g2d.drawString(String.valueOf(attack), 18, 285);
		     g2d.drawString(String.valueOf(health), 167, 285);
		}
		else {
		g2d.drawString(String.valueOf(mana), 10, 30);
		g2d.drawString(String.valueOf(attack), 8, 125);
		g2d.drawString(String.valueOf(health), 95, 125);}
		// g2d.drawImage(dimg, 50, 20, 100, 100,null,null);
		g2d.dispose();

		return dimg;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		new FieldMinion(new Icehowl());
	}
}
