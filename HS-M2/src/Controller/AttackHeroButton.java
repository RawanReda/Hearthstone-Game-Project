package Controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import model.heroes.*;

public class AttackHeroButton extends JButton{

	
	private Hero hero;
	
	public AttackHeroButton(Hero h) {
		super (h.getName());
		hero = h;
		this.setBackground(Color.BLUE);
		this.setFont(new Font("Georgia", Font.BOLD, 13));
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public void setHero(Hero h) {
		hero = h;
	}
}

