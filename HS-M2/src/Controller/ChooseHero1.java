package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.FullHandException;
import model.heroes.*;

public class ChooseHero1 extends JDialog implements MouseListener {
	JPanel Heros;
	public String  name1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public ChooseHero1() throws MalformedURLException, IOException, CloneNotSupportedException {
		super();
		name1 = "Paladin";
		this.setSize(new Dimension(1280, 700));
		this.setContentPane(new JLabel(new ImageIcon("/HS-M2/Images/Winner.jpg")));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
		Heros = new JPanel();
		Heros.setLayout(new GridLayout());
		ImageIcon image = new ImageIcon("/HS-M2/Images/Warlock.png");
		JPanel player1 = new JPanel();
		JLabel player = new JLabel("PLayer 1: Please choose a Hero: ");
		player.setForeground(Color.WHITE);
		player.setFont(new Font("Georgia", Font.BOLD, 50));
		player1.add(player);
		player1.setOpaque(false);
		Heros.setOpaque(false);
		JLabel background = new JLabel(image);

		ImageIcon hunterImage = new ImageIcon("/HS-M2/Images/Warlock.png");
		JLabel hunterLabel = new JLabel(hunterImage);
		ChooseHeroPanel Hunter = new ChooseHeroPanel(new Hunter());

		ImageIcon mageImage = new ImageIcon("/HS-M2/Images/Warlock.png");
		JLabel mageLabel = new JLabel(mageImage);
		ChooseHeroPanel Mage = new ChooseHeroPanel(new Mage());

		ImageIcon priestImage = new ImageIcon("/HS-M2/Images/Warlock.png");
		JLabel priestLabel = new JLabel(priestImage);
		ChooseHeroPanel Priest = new ChooseHeroPanel(new Priest());

		ImageIcon paladinImage = new ImageIcon("/HS-M2/Images/Warlock.png");
		JLabel paladinLabel = new JLabel(priestImage);
		ChooseHeroPanel Paladin = new ChooseHeroPanel(new Paladin());

		ImageIcon warlockImage = new ImageIcon("/HS-M2/Images/Warlock.png");
		JLabel warlockLabel = new JLabel(priestImage);
		ChooseHeroPanel Warlock = new ChooseHeroPanel(new Warlock());

		Hunter.addMouseListener(this);
		Mage.addMouseListener(this);
		Priest.addMouseListener(this);
		Warlock.addMouseListener(this);
		Paladin.addMouseListener(this);
//       Hunter.add(hunterLabel);
//       Mage.add(mageLabel);
//       Priest.add(priestLabel);
//       Paladin.add(paladinLabel);
//       Warlock.add(warlockLabel);

		Heros.add(Hunter);
		Heros.add(Mage);
		Heros.add(Priest);
		Heros.add(Paladin);
		Heros.add(Warlock);
		
		

		this.add(player1);
		this.add(Heros);
		
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
//	}
}
	public static void main(String[] args) throws MalformedURLException, IOException, CloneNotSupportedException {
		new ChooseHero1();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.setVisible(false);
//		try {
//		//	new Controller();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (FullHandException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (CloneNotSupportedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof ChooseHeroPanel) {
			ChooseHeroPanel k = (ChooseHeroPanel) e.getSource();
			Hero a = k.hero;
			// gamec= new Controller();
			String name = "";
			if (a instanceof Hunter)
				name = "Hunter";
			else if (a instanceof Mage)
				name = "Mage";
			else if (a instanceof Priest)
				name = "Priest";
			else if (a instanceof Paladin)
				name = "Paladin";
			else if (a instanceof Warlock)
				name = "Warlock";
			else
				name = "Warlock";
			name1 = name;
			System.out.println(name1+"the nameeeeee");
//			try {
//				new ChooseHeroPanel2();
//			} catch (IOException | CloneNotSupportedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
