package launcher;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {
	public Test(){
		try {
			ImageIO.read(this.getClass().getResource("Launcher.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Test();
	}
}
