package memory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;


public class Main{
	public static JFrame fr;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		MemoryImages[] imgs = MemoryImages.values();		//all the images in an array (has only "im" as a buffered image)

		ArrayList<Cards> crds1 = new ArrayList<Cards>();	//the first images list
		ArrayList<Cards> crds2 = new ArrayList<Cards>();	//their ruplicates list
		for(int i = 0 ; i < imgs.length - 1 ; i++) {
			crds1.add(new Cards(imgs[i]));
			crds2.add(new Cards(imgs[i]));
		}
		crds1.addAll(crds2);
		Collections.shuffle(crds1); 
		
		
		//Header  
		JLabel title = new JLabel("Memory Game",SwingConstants.CENTER);
		title.setFont(new Font("Serif",0,30));
		title.setPreferredSize(new Dimension(title.getWidth() , 50));

		//creating a table and adding cards
		Table t = new Table();
		t.addCards(crds1);
		
		
		fr = new JFrame(); 
		fr.setTitle("Memory Game");
		try {
			fr.setIconImage(ImageIO.read(new File("src/images/GameIcon.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);
		fr.setSize(t.getSize());
		//fr.setPreferredSize(fr.getSize());	//since the size of the frame is the same as the table so we dont need pack() function
		fr.add(t,BorderLayout.CENTER);
		fr.add(title , BorderLayout.NORTH);
		 
		fr.pack();
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Cards x : Table.TABLE_CARDS) {
			x.flip();
		}
		
	}
}