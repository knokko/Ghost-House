package nl.knokko.helper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageHelper {

	public static void main(String[] args) {
		createMainBackground();
	}
	
	static void createMainBackground(){
		BufferedImage image = new BufferedImage(1200, 700, BufferedImage.TYPE_INT_RGB);
		Random random = new Random();
		Graphics2D g = image.createGraphics();
		g.setColor(new Color(50, 0, 150));
		g.fillRect(30, 30, 1140, 640);
		for(int x = 0; x < 1200; x++){
			int height = height(x);
			for(int y = 0; y < height; y++){
				image.setRGB(x, y, randomFrame(random).getRGB());
				image.setRGB(x, image.getHeight() - y - 1, randomFrame(random).getRGB());
			}
			image.setRGB(x, height, new Color(5, 5, 15).getRGB());
			image.setRGB(x, image.getHeight() - height - 1, new Color(5, 5, 15).getRGB());
		}
		for(int y = 0; y < 700; y++){
			int width = height(y);
			for(int x = 0; x < width; x++){
				image.setRGB(x, y, randomFrame(random).getRGB());
				image.setRGB(image.getWidth() - x - 1, y, randomFrame(random).getRGB());
			}
			image.setRGB(width, y, randomFrame(random).getRGB());
			image.setRGB(image.getWidth() - width - 1, y, randomFrame(random).getRGB());
		}
		try {
			ImageIO.write(image, "PNG", new File("main menu.png"));
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	static Color randomFrame(Random random){
		return new Color(10 + random.nextInt(30), 10 + random.nextInt(30), 30 + random.nextInt(50));
	}
	
	static int height(int x){
		return 60 + (int) (Math.sin(x / 2.0) * 15);
	}
}
