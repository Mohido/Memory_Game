package memory;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum MemoryImages {
	DONKEY(System.getProperty("user.dir") + "/../images/aldokimi.jpg"),
	BLACKHOLE(System.getProperty("user.dir") + "/../images/blackhole.jpg"),
	BLOODMOON(System.getProperty("user.dir") + "/../images/bloodmoon.jpg"),
	EARTH(System.getProperty("user.dir") + "/../images/earth.jpg"),
	RAILWAYS(System.getProperty("user.dir") + "/../images/railways.jpg"),
	SUNSET(System.getProperty("user.dir") + "/../images/sunset.jpg"), 
	TREE(System.getProperty("user.dir") + "/../images/tree.jpg"),
	VIRUS(System.getProperty("user.dir") + "/../images/virus.jpg"),
	COVER(System.getProperty("user.dir") + "/../images/cover.jpg");

	Image im;
	private MemoryImages(String path) {
			try {
				System.out.println("Memory Image being loaded from: " + path);
				im = (ImageIO.read(new File(path))).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}