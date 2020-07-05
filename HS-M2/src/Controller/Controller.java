package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//import javax.swing.text.View;
import View.*;
import engine.*;
import exceptions.*;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import model.heroes.*;

public class Controller  implements ActionListener, GameListener, MouseListener {
	private Game game;
	private View2 gameview;
	private ChooseHero1 hero1;
	private ChooseHeroPanel2 hero2;
	static boolean player = false;
	private boolean isitMage = false;
	private boolean iSiTPriest = false;
	private boolean HeroTargetSpell = false;
	private boolean MinionTargetSpell = false;
	private boolean LeechingSpell = false;
	private Minion attacker;
	private HeroTargetSpell HeroTargetSpellCard;
	private MinionTargetSpell MinionTargetSpellCard;
	private LeechingSpell LeechingSpellCard;
	private JButton EndTurn;
	private JButton UseHeroPower;
	private JButton HeroPower;
	private String CurrentHero;
	private String OpponentHero;

	public Controller() throws IOException, CloneNotSupportedException, FullHandException, InterruptedException {
		HeroTargetSpellCard = null;
		MinionTargetSpellCard = null;
		
	    hero1=new ChooseHero1();
		Thread.sleep(1000);
        hero2=new ChooseHeroPanel2();
        Thread.sleep(1000);
		//hero2.setVisible(false);
		gameview = new View2();
		//gameview.setVisible(false);
		try {
//			System.out.println(hero1.getName());
//			System.out.println(hero2.getName());
			Hero h1 = null;
			Hero h2 = null;
			Object[] possibilities = { "Hunter", "Mage", "Paladin", "Warlock", "Priest" };
			Object[] possibilities1 = { "Hunter", "Mage", "Paladin", "Warlock", "Priest" };
//			 CurrentHero = (String) JOptionPane.showInputDialog(gameview, "Please choose a Hero type:",
//					"Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Mage");
			CurrentHero = hero1.getName1();
			
			//CurrentHero = "Warlock";
			HeroGame x2 = null;
			if (CurrentHero.equals("Hunter")) {
				h1 = new Hunter();

			} else if (CurrentHero.equals("Mage")) {
				h1 = new Mage();

			} else if (CurrentHero.equals("Paladin")) {
				h1 = new Paladin();

			} else if (CurrentHero.equals("Priest")) {
				h1 = new Priest();

			} else if (CurrentHero.equals("Warlock")) {
				h1 = new Warlock();

			}
			x2 = new HeroGame(h1);

//		    gameview.getCurrInfo1().add(x2);
//			gameview.getHeroInfo().add(gameview.getCurrInfo1().add(x2));

			UseHeroPower = new JButton("Use Hero Power");
			EndTurn = new JButton("End Turn");
			EndTurn.setOpaque(false);
			EndTurn.setFont(new Font("Georgia", Font.BOLD, 18));
			UseHeroPower.setFont(new Font("Georgia", Font.BOLD, 18));

			HeroGame x = null;
//			OpponentHero = (String) JOptionPane.showInputDialog(gameview, "Please choose a Hero type:",
//					"Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities1, "Mage");
//			OpponentHero = hero2.getName2();
     		OpponentHero = hero2.getName2();
			if (OpponentHero.equals("Hunter")) {
				h2 = new Hunter();

			} else if (OpponentHero.equals("Mage")) {
				h2 = new Mage();

			} else if (OpponentHero.equals("Paladin")) {
				h2 = new Paladin();

			} else if (OpponentHero.equals("Priest")) {
				h2 = new Priest();

			} else if (OpponentHero.equals("Warlock")) {
				h2 = new Warlock();

			}
			x = new HeroGame(h2);

			x.addMouseListener(this);// x is for opponent while x2 for current
			x2.addMouseListener(this);
			game = new Game(h1, h2);

			game.setListener(this);

			gameview.getCurrHand().setVisible(true);
			gameview.getOppHand().setVisible(true);
			String q = "<html>" + game.getCurrentHero().getName() + "<br/>" + "HP: "
					+ game.getCurrentHero().getCurrentHP() + "<br/> Hand Cards: "
					+ game.getCurrentHero().getHand().size() + "<br/> Cards left: "
					+ game.getCurrentHero().getDeck().size() + "<html>";

			String qOpp = "<html>" + game.getOpponent().getName() + "<br/>" + "HP: " + game.getOpponent().getCurrentHP()
					+ "<br/>" + "Hand Cards: " + game.getOpponent().getHand().size() + "<br/>Cards left: "
					+ game.getOpponent().getDeck().size() + "<html>";

			JLabel OppInfo = new JLabel(qOpp);
			OppInfo.setForeground(Color.white);
			gameview.getOppInfo1().add(x2);
			gameview.getOppInfo1().add(OppInfo);
			gameview.getHeroInfo().add(gameview.getOppInfo1());

			EndTurn.addActionListener(this);
			EndTurn.setFont(new Font("Georgia", Font.BOLD, 30));
			EndTurn.setIcon(new ImageIcon("/HS-M2/Images/buttonNew1.png"));
			UseHeroPower.setIcon(new ImageIcon("/HS-M2/Images/CardInfoButton.png"));
			EndTurn.setHorizontalTextPosition(JButton.CENTER);
			EndTurn.setVerticalTextPosition(JButton.CENTER);
			UseHeroPower.setHorizontalTextPosition(JButton.CENTER);
			UseHeroPower.setVerticalTextPosition(JButton.CENTER);
			UseHeroPower.addActionListener(this);
			UseHeroPower.setFont(new Font("Georgia", Font.BOLD, 15));
			UseHeroPower.setPreferredSize(new Dimension(180, 90));
			HeroPower = new JButton("Hero Power");
			HeroPower.addActionListener(this);
			HeroPower.setPreferredSize(new Dimension(270, 100));
			EndTurn.setPreferredSize(new Dimension(270, 100));
			HeroPower.setFont(new Font("Georgia", Font.BOLD, 30));
			HeroPower.setIcon(new ImageIcon("/HS-M2/Images/buttonNew1.png"));
			HeroPower.setHorizontalTextPosition(JButton.CENTER);
			HeroPower.setVerticalTextPosition(JButton.CENTER);
			gameview.getHeroInfo().add(HeroPower);
			gameview.getHeroInfo().add(EndTurn);

			JLabel currentInfo = new JLabel(q);
			currentInfo.setForeground(Color.white);
			gameview.getCurrInfo1().add(x);
			gameview.getCurrInfo1().add(currentInfo);
			gameview.getHeroInfo().add(gameview.getCurrInfo1());

		} catch (IOException w) {
			JOptionPane.showMessageDialog(gameview, w.getMessage());
		} catch (CloneNotSupportedException w) {
			JOptionPane.showMessageDialog(gameview, w.getMessage());
		} catch (FullHandException w) {
			JOptionPane.showMessageDialog(gameview, w.getMessage());
		}

		gameview.getCurrInfo1().setFont(new Font("Georgia", Font.PLAIN, 13));
		gameview.getOppInfo1().setFont(new Font("Georgia", Font.PLAIN, 13));

		if (player) {
			gameview.getOppInfo1().setBackground(Color.GREEN);
			gameview.getCurrInfo1().setBackground(Color.WHITE);

		} else {
			gameview.getCurrInfo1().setBackground(Color.GREEN);
			gameview.getOppInfo1().setBackground(Color.WHITE);
		}

		for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {

			JTextArea q = new JTextArea();
			q.setFont(new Font("Georgia", Font.PLAIN, 13));
			if (game.getCurrentHero().getHand().get(i) instanceof Minion) {
				Minion a = (Minion) game.getCurrentHero().getHand().get(i);
				MinionCard a1 = new MinionCard(a);
				a1.addMouseListener(this);
				(gameview).getCurrHand().add(a1);

			} else {
				JPanel a1 = new JPanel();
				Spell b = (Spell) game.getCurrentHero().getHand().get(i);
				SpellCard b1 = new SpellCard(b);
				b1.addMouseListener(this);
				(gameview).getCurrHand().add(b1);
			}

		}
		gameview.revalidate();
		gameview.repaint();

		for (int i = 0; i < game.getOpponent().getHand().size(); i++) {

			ImageIcon x = new ImageIcon("/HS-M2/Images/Card Templates Minions/Back Card.png");
			JLabel s = new JLabel(x);
			gameview.getOppHand().add(s);
		}
		File soundFile = new File("/YuGiOh/resources/others/Hearthstone Game.wav");
		try {
			AudioInputStream x = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			 clip.open(x);
				clip.loop(10000);
		} catch (UnsupportedAudioFileException | IOException e) {
		}
	     catch (LineUnavailableException e) {
		}
		gameview.revalidate();
		gameview.repaint();
	}

	public String getCurrentHero() {
		return CurrentHero;
	}

	public void setCurrentHero(String currentHero) {
		CurrentHero = currentHero;
	}

	public String getOpponentHero() {
		return OpponentHero;
	}

	public void setOpponentHero(String opponentHero) {
		OpponentHero = opponentHero;
	}

	@Override
//	
	public void onGameOver() {
		// TODO Auto-generated method stub
		String winner = null;
		if (game.getCurrentHero().getCurrentHP() != 0)
			winner = game.getCurrentHero().getName();
		else
			winner = game.getOpponent().getName();
		//JOptionPane.showMessageDialog(gameview, "GAME OVER! The Winner Is " + winner);
		try {
			new winner(winner);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameview.setVisible(false);
		return;
	}

	public void updateFromHandtoField(Minion minion) throws MalformedURLException, IOException {
		JPanel cardOnField = new JPanel();
		JTextArea cardInfo = new JTextArea();
		cardInfo.setFont(new Font("Georgia", Font.PLAIN, 13));
		String state = (minion).isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
		state += (minion).isDivine() ? "\nMinion is Divine!" : "";
		state += (minion).isTaunt() ? "\nMinion has Taunt!" : "";
		cardInfo.setText("Name: " + (minion).getName() + "\n" + "Mana Cost: " + (minion).getManaCost() + "    "
				+ "AttackPoints: " + (minion).getAttack() + "\nHP: " + (minion).getCurrentHP() + "\n" + state);
		cardInfo.setEditable(false);
		cardOnField.add(cardInfo, BorderLayout.NORTH);
		AttackButton Attack = new AttackButton(minion);
		Attack.addActionListener(this);

		cardOnField.add(Attack, BorderLayout.SOUTH);
		FieldMinion x1 = new FieldMinion(minion);
		x1.addMouseListener(this);
		if (player) {
			gameview.getOppHand().removeAll();
			// gameview.getOppField().add(cardOnField);
			gameview.getOppField().add(x1);
			gameview.getOppField().revalidate();
			gameview.getOppField().repaint();

		} else {
			gameview.getCurrHand().removeAll();
			// gameview.getCurrField().add(cardOnField);
			gameview.getCurrField().add(x1);
			gameview.getCurrField().revalidate();
			gameview.getCurrField().repaint();
		}
		this.removeHandCard();
		gameview.revalidate();
		gameview.repaint();
	}

	public void removeHandCard() throws MalformedURLException, IOException {
		if (player)
			gameview.getOppHand().removeAll();
		else
			gameview.getCurrHand().removeAll();

		for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
			JPanel a1 = new JPanel();
			JTextArea q = new JTextArea();
			q.setEditable(false);
			q.setFont(new Font("Georgia", Font.PLAIN, 13));
			if (game.getCurrentHero().getHand().get(i) instanceof Minion) {
				Minion a = (Minion) game.getCurrentHero().getHand().get(i);
				String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
				state += a.isDivine() ? "\nMinion is Divine!" : "";
				state += a.isTaunt() ? "\nMinion has Taunt!" : "";
				q.setText("Name: " + a.getName() + "\n" + "Mana Cost: " + a.getManaCost() + "\n" + "AttackPoints: "
						+ a.getAttack() + "\nHP: " + a.getCurrentHP() + "\n" + state);
				ActionButton l = new ActionButton(a);
				l.addActionListener(this);
				a1.add(q, BorderLayout.NORTH);
				a1.add(l, BorderLayout.SOUTH);

				MinionCard x1 = new MinionCard(a);
				x1.addMouseListener(this);
				if (player)
					gameview.getOppHand().add(x1);
				else
					gameview.getCurrHand().add(x1);
			} else {
				Spell b = (Spell) game.getCurrentHero().getHand().get(i);
				q.setText("Name: " + b.getName() + "\n" + "\n" + "Rarity: " + b.getRarity() + "\n" + "\n" + "ManaCost: "
						+ b.getManaCost() + "\n");
				CastSpellButton l = new CastSpellButton(b);
				l.addActionListener(this);
				a1.add(q, BorderLayout.NORTH);
				a1.add(l, BorderLayout.SOUTH);
				SpellCard x1 = new SpellCard(b);
				x1.addMouseListener(this);

				if (player)
					gameview.getOppHand().add(x1);
				else
					gameview.getCurrHand().add(x1);
			}

		}
		gameview.revalidate();
		gameview.repaint();

	}

	public static String getState(Minion a) {
		String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
		state += a.isDivine() ? "<br/> Minion is Divine!" : "";
		state += a.isTaunt() ? "<br/> Minion has Taunt!" : "";
		return state;

	}

	public void updateField1() throws MalformedURLException, IOException {
		(gameview).getOppField().removeAll();
		(gameview).getOppField().repaint();
		(gameview).getOppField().revalidate();
		(gameview).getCurrField().removeAll();
		(gameview).getCurrField().repaint();
		(gameview).getCurrField().revalidate();
		if (player) {
			for (int i = 0; i < game.getCurrentHero().getField().size(); i++) {
				JPanel a1 = new JPanel();
				JTextArea q = new JTextArea();
				q.setFont(new Font("Georgia", Font.PLAIN, 13));
				q.setEditable(false);
				Minion a = game.getCurrentHero().getField().get(i);
				String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
				state += a.isDivine() ? "\nMinion is Divine!" : "";
				state += a.isTaunt() ? "\nMinion has Taunt!" : "";
				q.setText("Name: " + a.getName() + "\n" + "Mana Cost: " + a.getManaCost() + "\n " + "Attack Points: "
						+ a.getAttack() + "\nHP: " + a.getCurrentHP() + "\n" + state);
				AttackButton l = new AttackButton(a);
				l.addActionListener(this);
				a1.add(q, BorderLayout.NORTH);
				a1.add(l, BorderLayout.SOUTH);

				FieldMinion x1 = new FieldMinion(a);
				x1.addMouseListener(this);
				(gameview).getOppField().add(x1);
			}
			for (int i = 0; i < game.getOpponent().getField().size(); i++) {
				JPanel a1 = new JPanel();
				JTextArea q = new JTextArea();
				q.setFont(new Font("Georgia", Font.PLAIN, 13));
				q.setEditable(false);
				Minion a = game.getOpponent().getField().get(i);
				String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
				state += a.isDivine() ? "\nMinion is Divine!" : "";
				state += a.isTaunt() ? "\nMinion has Taunt!" : "";
				q.setText("Name: " + a.getName() + "\n" + "Mana Cost: " + a.getManaCost() + "\n " + "Attack Points: "
						+ a.getAttack() + "\nHP: " + a.getCurrentHP() + "\n" + state);
				AttackButton l = new AttackButton(a);
				l.addActionListener(this);
				a1.add(q, BorderLayout.NORTH);
				a1.add(l, BorderLayout.SOUTH);

				FieldMinion x1 = new FieldMinion(a);
				x1.addMouseListener(this);
				(gameview).getCurrField().add(x1);
			}
		} else {
			for (int i = 0; i < game.getCurrentHero().getField().size(); i++) {
				JPanel a1 = new JPanel();
				JTextArea q = new JTextArea();
				q.setFont(new Font("Georgia", Font.PLAIN, 13));
				q.setEditable(false);
				// a1.setLayout(new GridLayout(2, 1));
				Minion a = game.getCurrentHero().getField().get(i);
				String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
				state += a.isDivine() ? "\nMinion is Divine!" : "";
				state += a.isTaunt() ? "\nMinion has Taunt!" : "";
				q.setText("Name: " + a.getName() + "\n" + "Mana Cost: " + a.getManaCost() + "\n " + "Attack Points: "
						+ a.getAttack() + "\nHP: " + a.getCurrentHP() + "\n" + state);
				AttackButton l = new AttackButton(a);
				l.addActionListener(this);
				a1.add(q, BorderLayout.NORTH);
				a1.add(l, BorderLayout.SOUTH);
				FieldMinion x1 = new FieldMinion(a);
				x1.addMouseListener(this);
				(gameview).getCurrField().add(x1);
				System.out.println(game.getCurrentHero().getField().get(i) + "");
			}
			for (int i = 0; i < game.getOpponent().getField().size(); i++) {
				JPanel a1 = new JPanel();
				JTextArea q = new JTextArea();
				q.setFont(new Font("Georgia", Font.PLAIN, 13));
				q.setEditable(false);
				Minion a = game.getOpponent().getField().get(i);
				String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
				state += a.isDivine() ? "\nMinion is Divine!" : "";
				state += a.isTaunt() ? "\nMinion has Taunt!" : "";
				q.setText("Name: " + a.getName() + "\n" + "Mana Cost: " + a.getManaCost() + "\n " + "Attack Points: "
						+ a.getAttack() + "\nHP: " + a.getCurrentHP() + "\n" + state);
				AttackButton l = new AttackButton(a);
				l.addActionListener(this);
				a1.add(q, BorderLayout.NORTH);
				a1.add(l, BorderLayout.SOUTH);
				FieldMinion x1 = new FieldMinion(a);
				x1.addMouseListener(this);
				(gameview).getOppField().add(x1);
				// (gameview).getOppField().add(a1);
				System.out.println(game.getOpponent().getField().get(i) + "");
			}

		}
		(gameview).getOppField().repaint();
		(gameview).getOppField().revalidate();
		// (gameview).getCurrField().removeAll();
		(gameview).getCurrField().repaint();
		(gameview).getCurrField().revalidate();
	}

	public static String heroPimage(Hero x) {
		String n = "";
		if (x instanceof Hunter)
			n = "/HS-M2/Images/HeroPower Card/Hunter.png";
		if (x instanceof Mage)
			n = "/HS-M2/Images/HeroPower Card/Mage.png";
		if (x instanceof Paladin)
			n = "/HS-M2/Images/HeroPower Card/Paladin.png";
		if (x instanceof Priest)
			n = "/HS-M2/Images/HeroPower Card/Priest-removebg-preview.png";
		if (x instanceof Warlock)
			n = "/HS-M2/Images/HeroPower Card/Warlock-removebg-preview.png";
		return n;

	}

	@Override
	public void actionPerformed(ActionEvent c) {
		
		if (c.getSource() instanceof AttackButton) {
			System.out.println("AttackButton");
			AttackButton f = (AttackButton) c.getSource();
			Minion a = (Minion) f.getMinion();
			if (MinionTargetSpell) {
				MinionTargetSpell = false;
				try {
					game.getCurrentHero().castSpell(MinionTargetSpellCard, a);
					this.updateField1();
					this.updateInfoPanel();
					// System.out.println("YESMINION");
					this.removeHandCard();
				} catch (NotYourTurnException | NotEnoughManaException e) {
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (InvalidTargetException e) {
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (LeechingSpell) {
				LeechingSpell = false;
				try {
					game.getCurrentHero().castSpell(LeechingSpellCard, a);
					this.updateField1();
					this.updateInfoPanel();
					System.out.println("YESLEECH");
					this.removeHandCard();
				} catch (NotYourTurnException | NotEnoughManaException e) {
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else {
				if (attacker == null)
					attacker = a;
				else {
					try {
						attackMinionWithMinion(attacker, a);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					attacker = null;
					try {
						this.updateField1();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			gameview.revalidate();
			gameview.repaint();
		} else if (c.getSource() instanceof ActionButton) {
			System.out.println("action");
			ActionButton d = (ActionButton) c.getSource();
			if (d.getCard() instanceof Minion) {
				Minion a = (Minion) d.getCard();
				try {
					game.getCurrentHero().playMinion(a);
					this.updateFromHandtoField(a);
					this.updateInfoPanel();
				} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e) {
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			gameview.getCardInfo().removeAll();
			gameview.revalidate();
			gameview.repaint();

		} else if (c.getSource() instanceof AttackHeroButton) {
			AttackHeroButton x = (AttackHeroButton) c.getSource();
			System.out.println("AttackHeroButton");
			Hero a = x.getHero();
			if (isitMage) {
				Mage m = (Mage) game.getCurrentHero();
				isitMage = false;
				try {
					m.useHeroPower(a);
					this.updateInfoPanel();
				} catch (FullHandException e) {
					if (e.getBurned() instanceof Spell)
						JOptionPane.showMessageDialog(gameview,
								e.getMessage() + "\nBURNED CARD: " + e.getBurned().getName() + "\nRarity: "
										+ e.getBurned().getRarity() + "\n" + "ManaCost: "
										+ e.getBurned().getManaCost());
					else {
						Minion a1 = (Minion) e.getBurned();
						String state = a1.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
						state += a1.isDivine() ? "\nMinion is Divine!" : "";
						state += a1.isTaunt() ? "\nMinion has Taunt!" : "";
						JOptionPane.showMessageDialog(gameview,
								e.getMessage() + "\nBURNED CARD: " + a1.getName() + "\n" + "Mana Cost: "
										+ a1.getManaCost() + "\n" + "AttackPoints: " + a1.getAttack() + "\nHP: "
										+ a1.getCurrentHP() + "\n" + state);
					}
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullFieldException | CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (iSiTPriest) {
				System.out.println("iSiTPriest0");
				Priest m = (Priest) game.getCurrentHero();
				iSiTPriest = false;

				try {
					System.out.println("iSiTPriest");
					m.useHeroPower(a);
					this.updateInfoPanel();
					System.out.println("iSiTPriest1");
				} catch (FullHandException e) {
					if (e.getBurned() instanceof Spell)
						JOptionPane.showMessageDialog(gameview,
								e.getMessage() + "\nBURNED CARD: " + e.getBurned().getName() + "\nRarity: "
										+ e.getBurned().getRarity() + "\n" + "ManaCost: "
										+ e.getBurned().getManaCost());
					else {
						Minion a1 = (Minion) e.getBurned();
						String state = a1.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
						state += a1.isDivine() ? "\nMinion is Divine!" : "";
						state += a1.isTaunt() ? "\nMinion has Taunt!" : "";
						JOptionPane.showMessageDialog(gameview,
								e.getMessage() + "\nBURNED CARD: " + a1.getName() + "\n" + "Mana Cost: "
										+ a1.getManaCost() + "\n" + "AttackPoints: " + a1.getAttack() + "\nHP: "
										+ a1.getCurrentHP() + "\n" + state);
					}
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullFieldException | CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (HeroTargetSpell) {

				HeroTargetSpell = false;
				try {
					game.getCurrentHero().castSpell(HeroTargetSpellCard, a);
				} catch (NotYourTurnException | NotEnoughManaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				}
			} else {
				if (attacker != null)
					try {
						attackHeroWithMinion(attacker, x.getHero());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				attacker = null;
				try {
					this.updateInfoPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					this.updateField1();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				this.updateInfoPanel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (c.getSource() instanceof CastSpellButton) {
			System.out.println("YES11");
			CastSpellButton castSpell = (CastSpellButton) c.getSource();
			Card x = castSpell.getCard();
			if (x instanceof AOESpell) { // field update of current and opponent
				try {
					game.getCurrentHero().castSpell((AOESpell) x, game.getOpponent().getField());
					this.updateField1();
					this.updateInfoPanel();
					System.out.println("YESAOE");
					this.removeHandCard();

				} catch (NotYourTurnException | NotEnoughManaException e) {
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (x instanceof FieldSpell) {// field update of current only
				try {
					game.getCurrentHero().castSpell((FieldSpell) x);
					this.updateField1();
					this.updateInfoPanel();
					System.out.println("YESFIELD");
					this.removeHandCard();
				} catch (NotYourTurnException | NotEnoughManaException e) {
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (x instanceof HeroTargetSpell && x instanceof MinionTargetSpell) {
				HeroTargetSpell = true;
				System.out.println("HT1");
				HeroTargetSpellCard = (model.cards.spells.HeroTargetSpell) x;
				MinionTargetSpell = true;
				MinionTargetSpellCard = (model.cards.spells.MinionTargetSpell) x;
				System.out.println("MT1");
			} else if (x instanceof HeroTargetSpell) {
				HeroTargetSpell = true;
				System.out.println("HT1");
				HeroTargetSpellCard = (model.cards.spells.HeroTargetSpell) x;
			} else if (x instanceof LeechingSpell) {// need to know what it does to the minion
				LeechingSpell = true; // I am not sure how to enter another card.
				LeechingSpellCard = (model.cards.spells.LeechingSpell) x;
				System.out.println("LS1");
			} else if (x instanceof MinionTargetSpell) {
				MinionTargetSpell = true;
				MinionTargetSpellCard = (model.cards.spells.MinionTargetSpell) x;
				System.out.println("MT1");
			}
		} else {
			JButton b = (JButton) c.getSource();
			System.out.println("jbutton");

			if (b.getActionCommand().equals("End Turn")) {
				gameview.getCardInfo().removeAll();
				try {
					// System.out.println(game.getCurrentHero().getName());
					game.endTurn();
				} catch (FullHandException e) {
					if (e.getBurned() instanceof Spell)
						JOptionPane.showMessageDialog(gameview,
								e.getMessage() + "\nBURNED CARD: " + e.getBurned().getName() + "\nRarity: "
										+ e.getBurned().getRarity() + "\n" + "Mana Cost: "
										+ e.getBurned().getManaCost());
					else {
						Minion a = (Minion) e.getBurned();
						String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
						state += a.isDivine() ? "\nMinion is Divine!" : "";
						state += a.isTaunt() ? "\nMinion has Taunt!" : "";
						JOptionPane.showMessageDialog(gameview,
								e.getMessage() + "\nBURNED CARD: " + a.getName() + "\n" + "Mana Cost: "
										+ a.getManaCost() + "\n" + "AttackPoints: " + a.getAttack() + "\nHP: "
										+ a.getCurrentHP() + "\n" + state);
					}
				} catch (CloneNotSupportedException e) {
					JOptionPane.showMessageDialog(gameview, e.getMessage());
				}
				if (player) {
					gameview.getCurrHand().setVisible(true);
					// gameview.getOppHand().setVisible(false);
					gameview.getOppHand().removeAll();
					for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
						ImageIcon x = new ImageIcon("/HS-M2/Images/Card Templates Minions/Back Card.png");
						JLabel s = new JLabel(x);
						gameview.getOppHand().add(s);
					}

					player = false;
				} else {
					gameview.getCurrHand().removeAll();
					for (int i = 0; i < game.getOpponent().getHand().size(); i++) {
						ImageIcon x = new ImageIcon("/HS-M2/Images/Card Templates Minions/Back Card.png");
						JLabel s = new JLabel(x);
						gameview.getCurrHand().add(s);
					}
					// gameview.getCurrHand().setVisible(false);
					gameview.getOppHand().setVisible(true);
					player = true;
				}
				try {
					this.updateInfoPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					this.updateHand();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//gameview.getCardInfo().removeAll();
				if (player) {
					gameview.getOppInfo1().setBackground(Color.GREEN);
					gameview.getCurrInfo1().setBackground(Color.WHITE);

				} else {
					gameview.getCurrInfo1().setBackground(Color.GREEN);
					gameview.getOppInfo1().setBackground(Color.WHITE);
				}

				gameview.revalidate();
				gameview.repaint();
			}
			if (b.getActionCommand().equals("Hero Power")) {
				gameview.getCardInfo().removeAll();
				JPanel x1 = new JPanel();
				String x11 = heroPimage(game.getCurrentHero());
				ImageIcon x = new ImageIcon(x11);
				JLabel x2 = new JLabel(x);
				x2.setLayout(new BorderLayout());
				x1.add(x2);
				x1.setOpaque(false);
				gameview.getCardInfo().add(x1);
				// UseHeroPower.addActionListener(this);
				gameview.getCardInfo().add(UseHeroPower);
				gameview.revalidate();
				gameview.repaint();
			}
			if (b.getActionCommand().equals("Use Hero Power")) {
				if (game.getCurrentHero().getName().equals("Rexxar")) {

					try {
						game.getCurrentHero().useHeroPower();

					} catch (FullHandException e) {
						if (e.getBurned() instanceof Spell)
							JOptionPane.showMessageDialog(gameview,
									e.getMessage() + "\nBURNED CARD: " + e.getBurned().getName() + "\nRarity: "
											+ e.getBurned().getRarity() + "\n" + "ManaCost: "
											+ e.getBurned().getManaCost());
						else {
							Minion a = (Minion) e.getBurned();
							String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
							state += a.isDivine() ? "\nMinion is Divine!" : "";
							state += a.isTaunt() ? "\nMinion has Taunt!" : "";
							JOptionPane.showMessageDialog(gameview,
									e.getMessage() + "\nBURNED CARD: " + a.getName() + "\n" + "Mana Cost: "
											+ a.getManaCost() + "\n" + "AttackPoints: " + a.getAttack() + "\nHP: "
											+ a.getCurrentHP() + "\n" + state);
						}
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullFieldException | CloneNotSupportedException e) {

						JOptionPane.showMessageDialog(gameview, e.getMessage());
					}
				}
				if (game.getCurrentHero().getName().equals("Gul'dan")) {
					// System.out.println(game.getCurrentHero().getName().equals("Rexxar"));
					try {
						game.getCurrentHero().useHeroPower();
						this.updateHand();
						// System.out.println(game.getCurrentHero().getCurrentHP());

					} catch (FullHandException e) {
						if (e.getBurned() instanceof Spell)
							JOptionPane.showMessageDialog(gameview,
									e.getMessage() + "\nBURNED CARD: " + e.getBurned().getName() + "\nRarity: "
											+ e.getBurned().getRarity() + "\n" + "ManaCost: "
											+ e.getBurned().getManaCost());
						else {
							Minion a = (Minion) e.getBurned();
							String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
							state += a.isDivine() ? "\nMinion is Divine!" : "";
							state += a.isTaunt() ? "\nMinion has Taunt!" : "";
							JOptionPane.showMessageDialog(gameview,
									e.getMessage() + "\nBURNED CARD: " + a.getName() + "\n" + "Mana Cost: "
											+ a.getManaCost() + "\n" + "AttackPoints: " + a.getAttack() + "\nHP: "
											+ a.getCurrentHP() + "\n" + state);
						}
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullFieldException | CloneNotSupportedException e) {
						JOptionPane.showMessageDialog(gameview, e.getMessage());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (game.getCurrentHero().getName().equals("Jaina Proudmoore")) {
					System.out.print("i ,love ");
					isitMage = true;
					gameview.revalidate();
					gameview.repaint();
				}
				if (game.getCurrentHero().getName().equals("Anduin Wrynn")) {
					iSiTPriest = true;

				}
				if (game.getCurrentHero().getName().equals("Uther Lightbringer")) {
					try {
						game.getCurrentHero().useHeroPower();
						this.updateField1();

					} catch (FullHandException e) {
						if (e.getBurned() instanceof Spell)
							JOptionPane.showMessageDialog(gameview,
									e.getMessage() + "\nBURNED CARD: " + e.getBurned().getName() + "\nRarity: "
											+ e.getBurned().getRarity() + "\n" + "ManaCost: "
											+ e.getBurned().getManaCost());
						else {
							Minion a = (Minion) e.getBurned();
							String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
							state += a.isDivine() ? "\nMinion is Divine!" : "";
							state += a.isTaunt() ? "\nMinion has Taunt!" : "";
							JOptionPane.showMessageDialog(gameview,
									e.getMessage() + "\nBURNED CARD: " + a.getName() + "\n" + "Mana Cost: "
											+ a.getManaCost() + "\n" + "AttackPoints: " + a.getAttack() + "\nHP: "
											+ a.getCurrentHP() + "\n" + state);
						}
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullFieldException | CloneNotSupportedException e) {

						JOptionPane.showMessageDialog(gameview, e.getMessage());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		try {
			this.updateInfoPanel();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.updateField1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameview.revalidate();
		gameview.repaint();

	}

	public void updateHand() throws MalformedURLException, IOException {
		this.removeHandCard();
		Card x = game.getCurrentHero().getHand().get(game.getCurrentHero().getHand().size() - 1);
		JPanel a1 = new JPanel();
		JTextArea q = new JTextArea();
		q.setEditable(false);

		if (x instanceof Minion) {
			Minion a = (Minion) x;
			String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
			state += a.isDivine() ? "\nMinion is Divine!" : "";
			state += a.isTaunt() ? "\nMinion has Taunt!" : "";
			q.setText("Name: " + a.getName() + "\n" + "Mana Cost: " + a.getManaCost() + "\n" + "AttackPoints: "
					+ a.getAttack() + "\nHP: " + a.getCurrentHP() + "\n" + state);
			a1.add(q, BorderLayout.NORTH);
//			ActionButton l = new ActionButton(x);
//			l.addActionListener(this);
//			a1.add(l);
			MinionCard x1 = new MinionCard(a);
			x1.addMouseListener(this);
//			if (game.getCurrentHero().getHand().size() < 10) {
//				if (player) {
//					gameview.getOppHand().add(x1);
//				} else {
//					gameview.getCurrHand().add(x1);
//				}
//			}

		} else {
			Spell n = (Spell) x;
			q.setText("Name: " + n.getName() + "\n" + "\n" + "Rarity: " + n.getRarity() + "\n" + "\n" + "ManaCost: "
					+ n.getManaCost() + "\n");
			a1.add(q, BorderLayout.NORTH);
//			CastSpellButton l = new CastSpellButton(n);
//			l.addActionListener(this);
//			a1.add(l);
			SpellCard x1 = new SpellCard(n);
			x1.addMouseListener(this);
			if (game.getCurrentHero().getHand().size() < 10) {
				if (player) {
					gameview.getOppHand().add(x1);
				} else {
					gameview.getCurrHand().add(x1);
				}
			}
		}

	}

	public void updateInfoPanel() throws MalformedURLException, IOException { // Curr Infor and Opp info ( top and
																				// bottom panels on the right)
		String q = "<html>" + game.getCurrentHero().getName() + "<br/>" + "HP: " + game.getCurrentHero().getCurrentHP()
				+ "<br/> Hand Cards: " + game.getCurrentHero().getHand().size() + "<br/> Cards left: "
				+ game.getCurrentHero().getDeck().size() + "<html>";

		String qOpp = "<html>" + game.getOpponent().getName() + "<br/>" + "HP: " + game.getOpponent().getCurrentHP()
				+ "<br/>" + "Hand Cards: " + game.getOpponent().getHand().size() + "<br/>Cards left: "
				+ game.getOpponent().getDeck().size() + "<html>";

		JLabel oppQ = new JLabel(qOpp);
		oppQ.setForeground(Color.white);
		JLabel Q = new JLabel(q);
		Q.setForeground(Color.white);

		if (player) {
			gameview.getOppInfo1().removeAll();
			HeroGame h2 = new HeroGame(game.getOpponent());
			HeroGame h1 = new HeroGame(game.getCurrentHero());

			h2.addMouseListener(this);
			gameview.getCurrInfo1().removeAll();

			gameview.getCurrInfo1().add(h2);
			gameview.getOppInfo1().add(h1);
			gameview.getOppInfo1().add(Q);
			gameview.getCurrInfo1().add(oppQ);
			h1.addMouseListener(this);

		} else {
			gameview.getOppInfo1().removeAll();
			HeroGame h2 = new HeroGame(game.getOpponent());
			HeroGame h1 = new HeroGame(game.getCurrentHero());

			h2.addMouseListener(this);
			gameview.getCurrInfo1().removeAll();
			gameview.getCurrInfo1().add(h1);
			gameview.getOppInfo1().add(h2);

			gameview.getOppInfo1().add(oppQ);

			gameview.getCurrInfo1().add(Q);
			h1.addMouseListener(this);
		}
	}

	public void updateOpponentHeroInfo1() throws MalformedURLException, IOException { // the panel opp to current hero
		String q = "<html>" + game.getCurrentHero().getName() + "<br/>" + "HP: " + game.getCurrentHero().getCurrentHP()
				+ "<br/> Hand Cards: " + game.getCurrentHero().getHand().size() + "<br/> Cards left: "
				+ game.getCurrentHero().getDeck().size() + "<html>";

		String qOpp = "<html>" + game.getOpponent().getName() + "<br/>" + "HP: " + game.getOpponent().getCurrentHP()
				+ "<br/>" + "Hand Cards: " + game.getOpponent().getHand().size() + "<br/>Cards left: "
				+ game.getOpponent().getDeck().size() + "<html>";

		JLabel oppQ = new JLabel(qOpp);
		oppQ.setForeground(Color.white);

		JLabel Q = new JLabel(q);
		Q.setForeground(Color.white);

		if (player) {
			gameview.getOppInfo1().removeAll();
			HeroGame h2 = new HeroGame(game.getOpponent());
			HeroGame h1 = new HeroGame(game.getCurrentHero());

			h2.addMouseListener(this);
			gameview.getCurrInfo1().removeAll();

			gameview.getCurrInfo1().add(h2);
			gameview.getOppInfo1().add(h1);
			gameview.getOppInfo1().add(Q);
			gameview.getCurrInfo1().add(oppQ);
			h1.addMouseListener(this);
		} else {
			gameview.getOppInfo1().removeAll();
			HeroGame h2 = new HeroGame(game.getOpponent());
			HeroGame h1 = new HeroGame(game.getCurrentHero());

			h2.addMouseListener(this);
			gameview.getCurrInfo1().removeAll();

			gameview.getCurrInfo1().add(h1);
			gameview.getOppInfo1().add(h2);
			gameview.getOppInfo1().add(oppQ);
			gameview.getCurrInfo1().add(Q);
			h1.addMouseListener(this);
		}
	}

	public void updatecurrField() throws MalformedURLException, IOException {
		Minion a = game.getCurrentHero().getField().get(game.getCurrentHero().getField().size() - 1);
		JPanel a1 = new JPanel();
		JTextArea q = new JTextArea();
		q.setFont(new Font("Georgia", Font.PLAIN, 13));
		q.setEditable(false);
		AttackButton l = new AttackButton(a);
		l.addActionListener(this);

		String state = a.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
		state += a.isDivine() ? "\nMinion is Divine!" : "";
		state += a.isTaunt() ? "\nMinion has Taunt!" : "";
		q.setText("Name: " + a.getName() + "\n" + "Mana Cost: " + a.getManaCost() + "/n" + "AttackPoints: "
				+ a.getAttack() + "\nHP: " + a.getCurrentHP() + "\n" + state);
		a1.add(q, BorderLayout.NORTH);
		a1.add(l);

		FieldMinion x1 = new FieldMinion(a);
		x1.addMouseListener(this);
		if (game.getCurrentHero().getHand().size() < 10) {
			if (player) {
				gameview.getOppField().add(x1);
			} else {
				gameview.getCurrField().add(x1);
			}
		}
	}

	// ATTACKING A MINION WITH A MINION
	public void attackMinionWithMinion(Minion a, Minion target) throws MalformedURLException, IOException {
		try {
			game.getCurrentHero().attackWithMinion(attacker, target);
			this.updateField1();
		} catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
				| NotSummonedException e) {
			JOptionPane.showMessageDialog(gameview, e.getMessage());
		}
		gameview.revalidate();
		gameview.repaint();
	}

	// ATTACKING A HERO WITH A MINION
	public void attackHeroWithMinion(Minion a, Hero a2) throws MalformedURLException, IOException {
		try {
			Hero target = a2;
			game.getCurrentHero().attackWithMinion(a, target);
			this.updateField1();
			this.updateInfoPanel();
		} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
				| InvalidTargetException e) {
			JOptionPane.showMessageDialog(gameview, e.getMessage());
		}
	}

	public static void main(String[] args) throws FullHandException, IOException, CloneNotSupportedException, InterruptedException {
		
		new Controller();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof ChooseHeroPanel) {
			ChooseHeroPanel k = (ChooseHeroPanel) e.getSource();
			Hero a = k.hero;
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
				if(CurrentHero.contentEquals("") ) {
				this.setCurrentHero(name);
				 hero2.setVisible(true);
				}
				else this.setOpponentHero(name);}
			else if (e.getSource() instanceof HeroGame) {
			HeroGame k = (HeroGame) e.getSource();
			Hero a = k.hero;
			if (isitMage) {
				Mage m = (Mage) game.getCurrentHero();
				isitMage = false;
				try {
					m.useHeroPower(a);
					gameview.getCardInfo().removeAll();
					this.updateInfoPanel();
				} catch (FullHandException e1) {
					if (e1.getBurned() instanceof Spell)
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + e1.getBurned().getName() + "\nRarity: "
										+ e1.getBurned().getRarity() + "\n" + "ManaCost: "
										+ e1.getBurned().getManaCost());
					else {
						Minion a1 = (Minion) e1.getBurned();
						String state = a1.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
						state += a1.isDivine() ? "\nMinion is Divine!" : "";
						state += a1.isTaunt() ? "\nMinion has Taunt!" : "";
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + a1.getName() + "\n" + "Mana Cost: "
										+ a1.getManaCost() + "\n" + "AttackPoints: " + a1.getAttack() + "\nHP: "
										+ a1.getCurrentHP() + "\n" + state);
					}
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (iSiTPriest) {
				System.out.println("iSiTPriest0");
				Priest m = (Priest) game.getCurrentHero();
				iSiTPriest = false;

				try {
					System.out.println("iSiTPriest");
					m.useHeroPower(a);
					gameview.getCardInfo().removeAll();
					this.updateInfoPanel();
					System.out.println("iSiTPriest1");
				} catch (FullHandException e1) {
					if (e1.getBurned() instanceof Spell)
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + e1.getBurned().getName() + "\nRarity: "
										+ e1.getBurned().getRarity() + "\n" + "ManaCost: "
										+ e1.getBurned().getManaCost());
					else {
						Minion a1 = (Minion) e1.getBurned();
						String state = a1.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
						state += a1.isDivine() ? "\nMinion is Divine!" : "";
						state += a1.isTaunt() ? "\nMinion has Taunt!" : "";
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + a1.getName() + "\n" + "Mana Cost: "
										+ a1.getManaCost() + "\n" + "AttackPoints: " + a1.getAttack() + "\nHP: "
										+ a1.getCurrentHP() + "\n" + state);
					}
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (HeroTargetSpell) {

				HeroTargetSpell = false;
				try {
					game.getCurrentHero().castSpell(HeroTargetSpellCard, a);
					gameview.getCardInfo().removeAll();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				}
			} else {
				if (attacker != null)
					try {
						attackHeroWithMinion(attacker, a);
						gameview.getCardInfo().removeAll();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				attacker = null;
				try {
					gameview.getCardInfo().removeAll();
					this.updateInfoPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					this.updateField1();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			try {
				this.updateInfoPanel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() instanceof MinionCard) {
			gameview.mousePressed(e);
			MinionCard k = (MinionCard) e.getSource();
			Minion x = k.min;

			JLabel info = new JLabel("<html> Name: " + x.getName() + "<br/>" + "Mana Cost: " + x.getManaCost() + "<br/>"
					+ "AttackPoints: " + x.getAttack() + "<br/>" + "Health Points: " + x.getCurrentHP() + "<br/>"
					+ getState(x) + "<html>");
			info.setForeground(Color.white);
			ActionButton l = new ActionButton(x);
			l.addActionListener(this);
			gameview.getCardInfo().add(info);
			gameview.getCardInfo().add(l);
		}

		if (e.getSource() instanceof SpellCard) {
			gameview.mousePressed(e);
			SpellCard k = (SpellCard) e.getSource();
			Spell x = k.min;
			CastSpellButton l = new CastSpellButton(x);
			l.addActionListener(this);
			gameview.getCardInfo().add(l);
		}

		if (e.getSource() instanceof FieldMinion) {
			FieldMinion k = (FieldMinion) e.getSource();
			Minion x = k.min;
			if (isitMage) {
				System.out.println(isitMage);
				Mage m = (Mage) game.getCurrentHero();
				isitMage = false;
				try {
					System.out.println("YES");
					m.useHeroPower(x);
					gameview.getCardInfo().removeAll();
					this.updateField1();
					System.out.println("YES11");
				} catch (FullHandException e1) {
					if (e1.getBurned() instanceof Spell)
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + e1.getBurned().getName() + "\nRarity: "
										+ e1.getBurned().getRarity() + "\n" + "ManaCost: "
										+ e1.getBurned().getManaCost());
					else {
						Minion a1 = (Minion) e1.getBurned();
						String state = a1.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
						state += a1.isDivine() ? "\nMinion is Divine!" : "";
						state += a1.isTaunt() ? "\nMinion has Taunt!" : "";
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + a1.getName() + "\n" + "Mana Cost: "
										+ a1.getManaCost() + "\n" + "AttackPoints: " + a1.getAttack() + "\nHP: "
										+ a1.getCurrentHP() + "\n" + state);
					}
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullFieldException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (iSiTPriest) {
				Priest m = (Priest) game.getCurrentHero();
				iSiTPriest = false;
				try {
					m.useHeroPower(x);
					gameview.getCardInfo().removeAll();
					this.updateField1();
				} catch (FullHandException e1) {
					if (e1.getBurned() instanceof Spell)
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + e1.getBurned().getName() + "\nRarity: "
										+ e1.getBurned().getRarity() + "\n" + "ManaCost: "
										+ e1.getBurned().getManaCost());
					else {
						Minion a1 = (Minion) e1.getBurned();
						String state = a1.isSleeping() ? "Minion is Sleeping!" : "Minion is Charged!";
						state += a1.isDivine() ? "\nMinion is Divine!" : "";
						state += a1.isTaunt() ? "\nMinion has Taunt!" : "";
						JOptionPane.showMessageDialog(gameview,
								e1.getMessage() + "\nBURNED CARD: " + a1.getName() + "\n" + "Mana Cost: "
										+ a1.getManaCost() + "\n" + "AttackPoints: " + a1.getAttack() + "\nHP: "
										+ a1.getCurrentHP() + "\n" + state);
					}
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullFieldException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (MinionTargetSpell) {
				MinionTargetSpell = false;
				try {
					game.getCurrentHero().castSpell(MinionTargetSpellCard, x);
					gameview.getCardInfo().removeAll();
					this.updateField1();
					this.updateInfoPanel();
					System.out.println("YESMINION");
					this.removeHandCard();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				} catch (InvalidTargetException e1) {
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (LeechingSpell) {
				LeechingSpell = false;
				try {
					game.getCurrentHero().castSpell(LeechingSpellCard, x);
					gameview.getCardInfo().removeAll();
					this.updateField1();
					this.updateInfoPanel();
					System.out.println("YESLEECH");
					this.removeHandCard();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(gameview, e1.getMessage());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			else {
				gameview.mousePressed(e);
				JLabel info = new JLabel("<html> Name: " + x.getName() + "<br/>" + "Mana Cost: " + x.getManaCost()
						+ "<br/>" + "AttackPoints: " + x.getAttack() + "<br/>" + "Health Points: " + x.getCurrentHP()
						+ "<br/>" + getState(x) + "<html>");
				info.setForeground(Color.white);
				AttackButton l = new AttackButton(x);
				l.addActionListener(this);
				gameview.getCardInfo().add(info);
				gameview.getCardInfo().add(l);
			}
			gameview.revalidate();
			gameview.repaint();
		}
		if (e.getSource() instanceof FieldMinion && attacker != null) {
			// gameview.mousePressed(e);
			FieldMinion k = (FieldMinion) e.getSource();
			Minion x = k.min;
			try {
				attackMinionWithMinion(attacker, x);
				gameview.getCardInfo().removeAll();
				attacker = null;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		gameview.revalidate();
		gameview.repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
//	gameview.mouseEntered(e);
//	gameview.revalidate();
//	gameview.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
