package memory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Table extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<Cards> TABLE_CARDS;
	
	Table(){
		//this.setPreferredSize(new Dimension(400, 400));
		//this.setSize(400, 400);
		//this.setMaximumSize(new Dimension(100, 100));
		//this.setMinimumSize(new Dimension(400, 400));
		//this.setBounds(0, 0, 400, 400);
		this.setBackground(Color.DARK_GRAY);
	}
	
	
	 @Override
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 //g.drawImage (MemoryImages.BLACKHOLE.im, 10, 10, 100 , 100,  null);
	 }
	 
	 ///called after creating the table... 
	 public void addCards(List<Cards> cards) {
		 TABLE_CARDS = cards;
		 for(JLabel x: cards) {
			 this.add(x);
		 }
		 
		this.setSize(MemoryImages.BLACKHOLE.im.getWidth(null) * 4, MemoryImages.BLACKHOLE.im.getWidth(null) * 4);
		this.setPreferredSize(this.getSize());
		this.setMaximumSize(new Dimension(400, 400));
		GridLayout griding = new GridLayout( (cards.size()/4) , (cards.size()/4) );
		griding.setHgap(0);
		griding.setVgap(0);
		this.setLayout(griding);
		this.repaint();
		
	 }

}
