package nl.knokko.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public final class GameScreen {
	
	private static final int MIN_WIDTH = 1200;
	private static final int MIN_HEIGHT = 700;
	
	private static int scale;
	
	private static int offsetX;
	private static int offsetY;
	
	private static int width;
	private static int height;
	
	public static void open(){
		try {
			DisplayMode mode = Display.getDesktopDisplayMode();
			scale = Math.min(mode.getWidth() / MIN_WIDTH, mode.getHeight() / MIN_HEIGHT);
			width = scale * MIN_WIDTH;
			height = scale * MIN_HEIGHT;
			offsetX = (mode.getWidth() - width) / 2;
			offsetY = (mode.getHeight() - height) / 2;
			Display.setDisplayModeAndFullscreen(mode);
			Display.setTitle("Ghost House");
			Display.create();
			System.out.println("Window settings:");
			System.out.println("scale = " + scale);
			System.out.println("width = " + width);
			System.out.println("height = " + height);
			System.out.println("offsetX = " + offsetX);
			System.out.println("offsetY = " + offsetY);
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
	}
	
	public static boolean requestClose(){
		return Display.isCloseRequested();
	}
	
	public static void update(){
		Display.update();
		Display.sync(60);
	}
	
	public static void close(){
		Display.destroy();
	}
	
	public static int getWidth(){
		return width;
	}
	
	public static int getHeight(){
		return height;
	}
	
	public static int getScreenWidth(){
		return Display.getWidth();
	}
	
	public static int getScreenHeight(){
		return Display.getHeight();
	}
	
	public static int getRenderXOffset(){
		return offsetX;
	}
	
	public static int getRenderYOffset(){
		return offsetY;
	}
	
	public static int getScale(){
		return scale;
	}
}
