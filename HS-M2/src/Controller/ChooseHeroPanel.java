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

import engine.*;
import exceptions.*;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import model.heroes.*;

public class ChooseHeroPanel extends JPanel {
	public Hero hero;

	public ChooseHeroPanel(Hero a) throws MalformedURLException, IOException {
		hero = a;
		setLayout(new BorderLayout());
		this.setBounds(00, 00, 500, 501);

		String name = a.getName();
		// System.out.println(name);
		System.out.println("dwdwd" + name);
		String imagename = getImageName(a.getName());
		ImageIcon x = new ImageIcon(imagename);
		System.out.println(x.getIconHeight());
		int Manacost = a.getCurrentManaCrystals();
		int TManacost = a.getTotalManaCrystals();
		int Health = a.getCurrentHP();
		BufferedImage image1 = resize(x);
		JLabel background = new JLabel(new ImageIcon(image1));
		background.setLayout(new BorderLayout());
		setOpaque(false);
		add(background);
		this.setVisible(true);
		
		this.validate();
		this.repaint();
	}

	public static String getImageName(String name) {

		String imagename = "";
		if (name.equals("Rexxar"))
			imagename = "/HS-M2/Images/Choose Hero/Hunter.png";
		else if (name.equals("Jaina Proudmoore"))
			imagename = "/HS-M2/Images/Choose Hero/Mage.png";
		else if (name.equals("Uther Lightbringer"))
			imagename = "/HS-M2/Images/Choose Hero/Paladin.png";
		else if (name.equals("Anduin Wrynn"))
			imagename = "/HS-M2/Images/Choose Hero/Priest.png";
		else if (name.equals("Gul'dan"))
			imagename = "/HS-M2/Images/Choose Hero/Warlock.png";
		else
			imagename = "/HS-M2/Images/Warlock.png";
		System.out.println("dwd898798690wd" + imagename);
		return imagename;

	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public static BufferedImage resize(ImageIcon x) {

		BufferedImage image = new BufferedImage(200, 230, BufferedImage.TYPE_INT_ARGB_PRE);

		Graphics g = image.createGraphics();

		x.paintIcon(null, g, 0, 0);
		Image tmp = image.getScaledInstance(200, 230, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(200, 230, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();

		g2d.drawImage(tmp, 0, 0, null);

		// g2d.drawImage(dimg, 50, 20, 100, 100,null,null);
		g2d.dispose();

		return dimg;
	}

	public static void main(String[] args) throws MalformedURLException, IOException, CloneNotSupportedException {
		new ChooseHeroPanel(new Mage());
	}
}
