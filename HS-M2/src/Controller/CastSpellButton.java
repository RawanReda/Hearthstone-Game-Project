package Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.cards.spells.Spell;

public class CastSpellButton extends JButton{

	private Spell spell;
	
	public CastSpellButton(Spell s) {
		spell = s;
		this.setText("Cast Spell");
		this.setIcon(new ImageIcon("/HS-M2/Images/CardInfoButton.png"));
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
		this.setFont(new Font("Georgia", Font.BOLD, 15));
		this.setPreferredSize(new Dimension(150, 50));
//		this.setBackground(Color.MAGENTA);
//		this.setFont(new Font("Georgia", Font.BOLD, 13));
	}
	
	public Spell getCard() {
		return spell;
	}
}
