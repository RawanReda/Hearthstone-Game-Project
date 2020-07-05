package Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.cards.*;

public class ActionButton extends JButton{
	
	private Card card;
	
	public ActionButton(Card card) {
		super("Play Card");
		this.setIcon(new ImageIcon("/HS-M2/Images/CardInfoButton.png"));
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
		this.setFont(new Font("Georgia", Font.BOLD, 15));
		this.setPreferredSize(new Dimension(150, 59));
	//	minion = m;
		this.card = card;
//		this.setBackground(Color.ORANGE);
//		this.setFont(new Font("Georgia", Font.BOLD, 13));
	}
	
	public Card getCard() {
		return card;
	}
	
	public void setCard(Card c) {
		card = c;
	}
}
