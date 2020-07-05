package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.rmi.UnexpectedException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.ChooseHero1;
import Controller.Controller;
import exceptions.FullHandException;

public class winner extends JFrame implements MouseListener {
	private Clip clip;

	public winner(String n) throws IOException, UnexpectedException {
		super();
		this.setSize(new Dimension(1280, 700));
		this.setContentPane(new JLabel(new ImageIcon("/HS-M2/Images/Winner (2).jpg")));
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				(new ImageIcon("/HS-M2/other/normalCursor.png")).getImage(), new Point(), "Normal Cursor");
		this.setCursor(cursor);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel startGame = new JLabel("Winner is "+n, JLabel.CENTER);
		startGame.setForeground(Color.WHITE);
		startGame.setFont(new Font("Georgia", Font.BOLD, 60));
		File soundFile = new File("/HS-M2/sounds/cheer2.wav");
		try {
			AudioInputStream x = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(x);
			clip.loop(10000);
		} catch (UnsupportedAudioFileException | IOException e) {
		} catch (LineUnavailableException e) {
		}
		this.add(startGame);
		startGame.addMouseListener(this);
		this.setVisible(true);

	}

	public static void main(String[] args) throws IOException {
		winner m = new winner("wd");
	}

	public void playSound(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.setVisible(false);
		try {
			new ChooseHero1();
			// new ChooseHero1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (FullHandException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		playSound("/HS-M2/sounds/Mouse_Click1.wav");
		e.getComponent().setForeground(Color.WHITE);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setForeground(Color.BLUE);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		playSound("/HS-M2/sounds/Mouse_Click1.wav");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
