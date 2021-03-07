package memory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Cards extends JLabel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	///static variables that are same for all objects
	static int count = 0;	/// shown pictures counts
	static ArrayList<Cards> SHOWN_CARDS = new ArrayList<Cards>();
	static int success_match = 0; 
	static int f_counts = 0;
	
	public ImageIcon img;
	private boolean showed = false;/// Current card shown
	private boolean pause = false; /// game is over
	
	
	
	
	public Cards(MemoryImages crd) {
		this.img = new ImageIcon(crd.im);
		
		this.setSize(crd.im.getWidth(null), crd.im.getHeight(null)); 		//Even setting the JLabel to a fixed size will not make the extras of the Image disappear
		this.setPreferredSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setMinimumSize(this.getSize());
		
		this.setIcon(this.img);
		this.setVisible(true);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		
		this.addMouseListener(this);
		System.out.println("JCard has been created with size: " + this.getSize().toString());
		
		this.setFocusable(false);
	}
	
	public void flip() {
		this.setIcon(new ImageIcon(MemoryImages.COVER.im));
	}
	
	public boolean isCardShown() {
		return showed;	//if the card is faced up or not
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!(this.isCardShown()) && count < 2 && !pause) {
			this.setIcon(img);
			this.showed = true; 
			SHOWN_CARDS.add(this);
			count++;
			System.out.println("Icon setted... " + this.getIcon().toString());
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
		if(count == 2) {
			System.out.println("comparing... ");
			boolean match = this.equals();
			if(match) {
				success_match = success_match + 2;
				count = 0;//just pass on to the next cards
				Cards.SHOWN_CARDS.clear();
				System.out.println(success_match + " " + Table.TABLE_CARDS.size() );
				if(success_match == Table.TABLE_CARDS.size()) {
					JOptionPane.showMessageDialog(Main.fr, "We Have a winner !!!", "Win" , JOptionPane.INFORMATION_MESSAGE);
					//System.out.println(Table.WIN);
				}
			}else {
				
				f_counts++;
				
				if(f_counts >= 3) {
					JOptionPane.showMessageDialog(Main.fr, "You Lost and you have a fly's memory -_-", "Lost" , JOptionPane.ERROR_MESSAGE);
					pause = true;
					return;
				}
				//adding a Thread.sleep(); 		not affecting the rendering of the icon..
				try { 
					System.out.print("Sleeping...");
					pause = true;
					Thread.sleep(500);
					pause = false;
					System.out.println("Done");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Cards.SHOWN_CARDS.get(0).setIcon(new ImageIcon(MemoryImages.COVER.im));
				Cards.SHOWN_CARDS.get(0).showed = false;
				Cards.SHOWN_CARDS.get(1).setIcon(new ImageIcon(MemoryImages.COVER.im));
				Cards.SHOWN_CARDS.get(1).showed = false;
				count = 0;
				Cards.SHOWN_CARDS.clear();
			} 
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	 @Override
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 /*g.setColor(Color.BLUE);
		 g.drawLine(0, 0, 700, 700);*/
		 //g.drawImage (MemoryImages.BLACKHOLE.im, 10, 10, 100 , 100,  null);
	 }

	 
	 public boolean equals() { 
		 
		//BufferedImage im1 = new BufferedImage(Cards.SHOWN_CARDS.get(0).img.getIconHeight(), Cards.SHOWN_CARDS.get(0).img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		//im1 = (BufferedImage) Cards.SHOWN_CARDS.get(0).img.getImage();
		
		 
		 /*
		  * 1: create an image with same width and hieight as the cards icon
		  * 2: get the graphics buffer of the image
		  * 3: paint the icon to the graphics buffer
		  * */
		BufferedImage im1 = new BufferedImage(Cards.SHOWN_CARDS.get(0).img.getIconWidth(),Cards.SHOWN_CARDS.get(0).img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = im1.createGraphics();
		// paint the Icon to the BufferedImage.
		Cards.SHOWN_CARDS.get(0).img.paintIcon(null, g, 0,0);
		g.dispose();
		
		
		//BufferedImage im2 = new BufferedImage(Cards.SHOWN_CARDS.get(1).img.getIconHeight(), Cards.SHOWN_CARDS.get(1).img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		//im2 = (BufferedImage) Cards.SHOWN_CARDS.get(1).img.getImage();
		
		BufferedImage im2 = new BufferedImage(Cards.SHOWN_CARDS.get(1).img.getIconWidth(),Cards.SHOWN_CARDS.get(1).img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g1 = im2.createGraphics();
		// paint the Icon to the BufferedImage.
		Cards.SHOWN_CARDS.get(1).img.paintIcon(null, g1, 0,0);
		g1.dispose();
		
		long difference = 0;
        for (int y = 0; y < Cards.SHOWN_CARDS.get(0).img.getIconHeight(); y++) 
        { 
             for (int x = 0; x <  Cards.SHOWN_CARDS.get(0).img.getIconWidth(); x++) 
             { 
            	 
                 int rgbA = im1.getRGB(x, y); 
                 int rgbB = im2.getRGB(x, y); 
                 int redA = (rgbA >> 16) & 0xff; 
                 int greenA = (rgbA >> 8) & 0xff; 
                 int blueA = (rgbA) & 0xff; 
                 int redB = (rgbB >> 16) & 0xff; 
                 int greenB = (rgbB >> 8) & 0xff; 
                 int blueB = (rgbB) & 0xff; 
                 difference += Math.abs(redA - redB); 
                 difference += Math.abs(greenA - greenB); 
                 difference += Math.abs(blueA - blueB); 
             } 
        }
		return difference == 0;
	 }
}
