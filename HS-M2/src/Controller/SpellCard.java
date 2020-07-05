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

public class SpellCard extends JPanel {
public Spell min;
	public SpellCard(Spell a) throws MalformedURLException, IOException {
		min=a;
		setLayout(new BorderLayout());
		this.setBounds(00, 00, 500, 501);

		String name = min.getName();
		System.out.println(name);

		String imagename = getImageName(name);
		System.out.println(imagename);
		ImageIcon x = new ImageIcon(imagename);
		System.out.println(x.getIconHeight());
		int Manacost = a.getManaCost();
		
		BufferedImage image1 = resize1(x, 120, 130, Manacost);
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
	if (name.equals("Curse of Weakness"))
		imagename = "/HS-M2/Images/Spells cards/CurseOfWeakness.java";
	if (name.equals("Divine Spirit"))
		imagename = "/HS-M2/Images/Spells cards/Divine_Spirit.png";
	if (name.equals("Flamestrike"))
		imagename = "/HS-M2/Images/Spells cards/Flamestrike.png";
	if (name.equals("Holy Nova"))
		imagename = "/HS-M2/Images/Spells cards/Holy Nova.png";
	if (name.equals("Kill Command"))
		imagename = "/HS-M2/Images/Spells cards/Kill Command.png";
	if (name.equals("Level Up!"))
		imagename = "/HS-M2/Images/Spells cards/Level Up!.png";
	if (name.equals("Multi-Shot"))
		imagename = "/HS-M2/Images/Spells cards/Multi-Shot.png";
	if (name.equals("Polymorph"))
		imagename = "/HS-M2/Images/Spells cards/Polymorph.png";
	if (name.equals("Pyroblast"))
		imagename = "/HS-M2/Images/Spells cards/Pyroblast.png";
	if (name.equals("Seal of Champions"))
		imagename = "/HS-M2/Images/Spells cards/Seal of Champions.png";
	if (name.equals("Shadow Word: Death"))
		imagename = "/HS-M2/Images/Spells cards/Shadow Word Death.png";
	if (name.equals("Siphon Soul"))
		imagename = "/HS-M2/Images/Spells cards/Siphon_Soul.png";
	if (name.equals("Twisting Nethter"))
		imagename = "/HS-M2/Images/Spells cards/Twisting Nether.png";
	
	return imagename;
	
}
	public static BufferedImage resize1(ImageIcon x, int newW, int newH, int mana) {

		BufferedImage image = new BufferedImage(286, 395, BufferedImage.TYPE_INT_ARGB_PRE);

		Graphics g = image.createGraphics();

		x.paintIcon(null, g, 0, 0);
		Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();

		g2d.drawImage(tmp, 0, 0, null);
		g2d.setFont(g.getFont().deriveFont(20f));
//		if(newW>150 && newH>150) {
//			 g2d.drawString(String.valueOf(mana), 15, 50);
//		}
//		else {
//		g2d.drawString(String.valueOf(mana), 6, 30);
//		}
		
		g2d.dispose();

		return dimg;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		new SpellCard(new KillCommand());
	}
}
