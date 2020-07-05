package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;

import Controller.FieldMinion;
import Controller.ImageJPanel;
import Controller.MinionCard;
import Controller.SpellCard;
import engine.*;
import exceptions.*;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import model.heroes.*;

public class View2 extends JFrame implements MouseListener {
//	ImageJPanel background;
	private JPanel MainPanel;
	private JPanel Herofields;

	private JPanel OppField;
	private JPanel CurrField;
	private JPanel CurrHand;
	private JPanel OppHand;

	public JPanel getHerofields() {
		return Herofields;
	}

	public void setHerofields(ImageJPanel herofields) {
		Herofields = herofields;
	}

	private Image icon;
	private OverlapLayout layout;
	OverlapLayout layout1;
	private JPanel CardInfo; 
	private JPanel HeroInfo;
	private JPanel OppInfo1;
    private JPanel ButtonPanel;
	private JPanel CurrInfo1;
	
	JLabel background;

//	private JButton DrawCard;


	public JPanel getOppHand() {
		return OppHand;
	}

	public View2() throws MalformedURLException, IOException {

		background = new JLabel(new ImageIcon("/HS-M2/Images/WoodBackground54342.jpg"));
		background.setLayout(new FlowLayout());
		MainPanel= new JPanel();
		MainPanel.setLayout(new BorderLayout());
		
		this.setContentPane(background);
		this.setBounds(00, 00, 1280, 720);
		MainPanel.setPreferredSize(new Dimension(1280,720));
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setUndecorated(true);
//		this.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		
		
		MainPanel.setOpaque(false);
		// Herofields.setBackground(new Color(213, 134, 145, 123));

		//Herofields.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		CardInfo = new JPanel();
		CardInfo.setPreferredSize(new Dimension(200, this.getHeight()));
		HeroInfo = new JPanel();
		HeroInfo.setPreferredSize(new Dimension(300, this.getHeight()));
		//HeroInfo.setLayout(new GridLayout(5, 1, 0, 0));
		HeroInfo.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		//CardInfo.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		HeroInfo.setOpaque(false);
		
//		ImageIcon x= new ImageIcon("/HS-M2/Images/d0442ce1ce01d8a3a4ff5ef7121ac6c6102ac5142c4c5b165054dfc6253ec82c.png");
//		BufferedImage c3= Trial.resize(x, 200,300);
//		JLabel cardImage= new JLabel(new ImageIcon(c3));
//		JButton click= new JButton("idjiw");
//		CardInfo.add(cardImage);
//		CardInfo.add(click);
		CardInfo.setOpaque(false);
		
		
		OppInfo1 = new JPanel();
		OppInfo1.setOpaque(false);
		//HeroInfo.add(OppInfo1);
		ButtonPanel= new JPanel();
		ButtonPanel.setOpaque(false);
		//HeroInfo.add(ButtonPanel);
        CurrInfo1 = new JPanel();
		CurrInfo1.setOpaque(false);
		//HeroInfo.add(CurrInfo1);

		
		
		
		// OppHand = new JLayeredPane();
       layout = new OverlapLayout(new Point(70, 0));
		layout.setPopupInsets(new Insets(5, 0, 0, 0));
		
		layout1 = new OverlapLayout(new Point(70, 0));
		layout1.setPopupInsets(new Insets(5, 0, 0, 0));
		OppHand = new JPanel(layout);
		OppHand.setBackground(new Color(218, 134, 145, 000));
		CurrHand = new JPanel(layout1);
		CurrHand.setBackground(new Color(218, 134, 145, 000));
//		Trial x1 = new Trial();
		Trial y = new Trial();
//		Trial s = new Trial();
//		Trial a = new Trial();
//		Trial b = new Trial();
//		Trial p = new Trial();
//		Trial w = new Trial();
//		Trial ps = new Trial();
//		Trial k = new Trial();
//		Trial c1 = new Trial();
//		Trial j = new Trial();
//		//OppHand.add(x,100);
//		y.addMouseListener(this);
	//	OppHand.add(y);
//		y.addMouseListener(this);
//		OppHand.add(s);
//		OppHand.add(a);
//		OppHand.add(b);
//		s.addMouseListener(this);
//		a.addMouseListener(this);
//		b.addMouseListener(this);
//		OppHand.add(p);
//		OppHand.add(w);
//		OppHand.add(ps);
//		OppHand.add(k);
//		OppHand.add(c1);
//		
//		p.addMouseListener(this);
//		w.addMouseListener(this);
//		ps.addMouseListener(this);
//		k.addMouseListener(this);
//		c1.addMouseListener(this);
//	j.addMouseListener(this);
    //	OppHand.setOpaque(false);

		OppField = new JPanel();
		OppField.setLayout(new GridLayout(1, 7));
		// OppField.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f)); // this works for
		// transparency
		OppField.setBackground(new Color(213, 134, 145, 123));

		CurrField = new JPanel();
		CurrField.setLayout(new GridLayout(1, 7));
		CurrField.setBackground(new Color(213, 134, 145, 123));
//CurrHand.setLayout(new GridLayout(1, 10));
		
		
		
      //  CurrHand.add(j);
		//MainPanel.add(OppHand, BorderLayout.NORTH);
		//MainPanel.add(CurrHand,BorderLayout.SOUTH);
//		Herofields.add(OppHand);
//		Herofields.add(OppField);
//		Herofields.add(CurrField);
//		Herofields.add(CurrHand);

		
		MainPanel.add(CardInfo, BorderLayout.WEST);
		MainPanel.add(HeroInfo, BorderLayout.EAST);
		
		
        Herofields = new JPanel();
		//Herofields.setPreferredSize(new Dimension(800, this.getHeight()));
		Herofields.setLayout(new GridLayout(4,1));
		Herofields.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
//		
//        GridBagLayout gbl = new GridBagLayout();
//        setLayout(gbl);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill= GridBagConstraints.HORIZONTAL;
//        gbc.insets= new Insets(5,5,5,5);
//        
//        gbc.gridx=0;
//        gbc.gridy=0;
//        gbc.gridwidth=1;
//        gbc.gridheight=1;
//        Herofields.add(OppHand);
//
//        gbc.gridx=0;
//        gbc.gridy=1;
//        gbc.gridwidth=1;
//        gbc.gridheight=1;
//        Herofields.add(OppField);
//        
//        gbc.gridx=0;
//        gbc.gridy=2;
//        gbc.gridwidth=1;
//        gbc.gridheight=1;
//        Herofields.add(CurrField);
//        
//        gbc.gridx=0;
//        gbc.gridy=3;
//        gbc.gridwidth=1;
//        gbc.gridheight=1;
//        Herofields.add(CurrHand);

	    Herofields.add(OppHand);
	    Herofields.add(OppField);
	    Herofields.add(CurrField);
	    Herofields.add(CurrHand);
	  // MainPanel.add(Herofields);
	    
	    MainPanel.add(Herofields, BorderLayout.CENTER);
		
         this.add(MainPanel);
         setVisible(true);
		this.revalidate();
		this.repaint();
	}

	public JPanel getCardInfo() {
		return CardInfo;
	}

	public void setCardInfo(JPanel cardInfo) {
		CardInfo = cardInfo;
	}

	public void mousePressed(MouseEvent e) {
    Component c = e.getComponent();
    CardInfo.removeAll();
  //add your elements
  
 //   String j= k.getName();
    if(e.getSource() instanceof MinionCard) {
    	MinionCard k= (MinionCard) c;
    	Minion x= k.min;
    	String name= k.getImageName(x.getName());
    	ImageIcon image = new ImageIcon(name);
    	BufferedImage image1= k.resize(image, 200, 300, x.getManaCost(), x.getAttack(), x.getCurrentHP(), x.isSleeping());
    	JLabel background = new JLabel(new ImageIcon(image1));
		background.setLayout(new BorderLayout());
		
		CardInfo.add(background);}
    
    if(e.getSource() instanceof FieldMinion) {
    	FieldMinion k= (FieldMinion) c;
    	Minion x= k.min;
    	String name= k.getImageName(x.getName());
    	ImageIcon image = new ImageIcon(name);
    	BufferedImage image1= k.resize(image, 200, 300, x.getManaCost(), x.getAttack(), x.getCurrentHP(), x.isSleeping());
    	JLabel background = new JLabel(new ImageIcon(image1));
		background.setLayout(new BorderLayout());
		
		CardInfo.add(background);}  
    
    if(e.getSource() instanceof SpellCard) {
    	SpellCard k= (SpellCard) c;
    	Spell x= k.min;
    	String name= k.getImageName(x.getName());
    	ImageIcon image = new ImageIcon(name);
    	BufferedImage image1= k.resize1(image, 200, 300, x.getManaCost());
    	JLabel background = new JLabel(new ImageIcon(image1));
		background.setLayout(new BorderLayout());
		
		CardInfo.add(background);}
    
   
//	CardInfo.add(click);
    revalidate();
  repaint();
	}



	public JPanel getButtonPanel() {
		return ButtonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		ButtonPanel = buttonPanel;
	}

	public void setOppHand(JPanel oppHand) {
		OppHand = oppHand;
	}

	public void setHeroInfo(JPanel heroInfo) {
		HeroInfo = heroInfo;
	}

	public void setOppField(JPanel oppField) {
		OppField = oppField;
	}

	public void setCurrField(JPanel currField) {
		CurrField = currField;
	}

	public void setCurrHand(JPanel currHand) {
		CurrHand = currHand;
	}

	public JPanel getHeroInfo() {
		return HeroInfo;
	}

	public JPanel getOppField() {
		return OppField;
	}

	public JPanel getCurrField() {
		return CurrField;
	}

	public JPanel getCurrHand() {
		return CurrHand;
	}

	public JPanel getOppInfo1() {
		return OppInfo1;
	}

	public void setOppInfo1(JPanel oppInfo1) {
		OppInfo1 = oppInfo1;
	}

	public JPanel getCurrInfo1() {
		return CurrInfo1;
	}

	public void setCurrInfo1(JPanel currInfo1) {
		CurrInfo1 = currInfo1;
	}

//	public JButton getDrawCard() {
//		return DrawCard;
//	}

//	public JButton getEndTurn() {
//		return EndTurn;
//	}
//
//	public JButton getUseHeroPower() {
//		return UseHeroPower;
//	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		new View2();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Component c = e.getComponent();
		Boolean constraint = layout.getConstraints(c);
		//Boolean constraint1 = layout1.getConstraints(c);
		if (constraint == null || constraint == OverlapLayout.POP_DOWN) {
			layout.addLayoutComponent(c, OverlapLayout.POP_UP);
		//   layout1.addLayoutComponent(c, OverlapLayout.POP_UP);
			}
		else {
			layout.addLayoutComponent(c, OverlapLayout.POP_DOWN);
	     // 	layout1.addLayoutComponent(c, OverlapLayout.POP_DOWN);
			}
		c.getParent().invalidate();
		c.getParent().validate();
		this.revalidate();
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}