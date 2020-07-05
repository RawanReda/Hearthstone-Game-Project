package Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.cards.minions.Minion;

public class AttackButton extends JButton{

	private Minion minion;
	
	public AttackButton(Minion m) {
		super("Attack!");
		this.setIcon(new ImageIcon("/HS-M2/Images/CardInfoButton.png"));
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
//		this.addActionListener(this);
		this.setFont(new Font("Georgia", Font.BOLD, 15));
		this.setPreferredSize(new Dimension(150, 90));
		minion = m;
//		this.setBackground(Color.GREEN);
//		this.setFont(new Font("Georgia", Font.BOLD, 13));
	}
	
	public Minion getMinion() {
		return minion;
	}
	
	public void setMinion(Minion m) {
		minion = m;
	}
}
