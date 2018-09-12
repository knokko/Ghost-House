package java2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.knokko.resources.Resources;

public class Test {
	
	private static JFrame frame;
	private static Panel panel;
	private static BufferedImage image;
	
	private static long renderTime;
	private static int renderTicks;

	public static void main(String[] args) {
		open();
		while(shouldContinue()){
			render();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
				frame.dispose();
			}
		}
		close();
		frame.dispose();
	}
	
	private static void open(){
		panel = new Panel();
		frame = new JFrame();
		frame.setSize(1600, 900);
		frame.setVisible(true);
		frame.add(panel);
		image = Resources.loadBufferedImage("guis/main menu.png");
		Graphics2D g = image.createGraphics();
		g.drawImage(Resources.loadBufferedImage("guis/buttons/main play.png"), 600, 540, null);
		g.drawImage(Resources.loadBufferedImage("guis/buttons/main quit.png"), 600, 160, null);
		g.dispose();
	}
	
	private static boolean shouldContinue(){
		return frame.isDisplayable() && frame.isActive();
	}
	
	private static void render(){
		panel.repaint();
	}
	
	private static void close(){
		System.out.println("Average render time is " + (renderTime / renderTicks) / 1000000.0 + " ms.");
	}
	
	private static class Panel extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 4764044504379388502L;

		@Override
		public void paint(Graphics g){
			long startTime = System.nanoTime();
			g.drawImage(image, 200, 100, null);
			long endTime = System.nanoTime();
			renderTime += endTime - startTime;
			renderTicks++;
		}
	}
}
