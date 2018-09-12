package nl.knokko.main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import nl.knokko.gamestate.GameState;
import nl.knokko.gamestate.StateMainMenu;
import nl.knokko.render.*;
import nl.knokko.resources.Natives;

public class Game {
	
	private static GameState state;
	
	private static Renderer renderer;
	private static GuiRenderer guiRenderer;
	
	private static int ticks;
	
	private static long renderTime;
	private static long updateTime;
	
	private static boolean isStopping;

	public static void main(String[] args) {
		prepare();
		open();
		init();
		while(shouldContinue()){
			update();
			render();
			updateScreen();
		}
		finish();
		close();
	}
	
	private static void prepare(){
		Natives.prepare();
	}
	
	private static void open(){
		GameScreen.open();
	}
	
	private static void init(){
		renderer = new BufferedRenderer();
		guiRenderer = (GuiRenderer) renderer;
		state = new StateMainMenu();
		state.open();
	}
	
	private static boolean shouldContinue(){
		return !GameScreen.requestClose() && !isStopping;
	}
	
	private static void update(){
		long startTime = System.nanoTime();
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState())
				state.press(Keyboard.getEventKey(), Keyboard.getEventCharacter());
			else
				state.release(Keyboard.getEventKey());
		}
		while(Mouse.next()){
			if(Mouse.getEventButton() >= 0 && Mouse.getEventButtonState())
				state.click(Mouse.getEventX() - GameScreen.getRenderXOffset(), Mouse.getEventY() - GameScreen.getRenderYOffset(), Mouse.getEventButton());
		}
		state.update();
		long endTime = System.nanoTime();
		updateTime += endTime - startTime;
	}
	
	private static void render(){
		long startTime = System.nanoTime();
		state.render();
		long endTime = System.nanoTime();
		renderTime += endTime - startTime;
		ticks++;
	}
	
	private static void updateScreen(){
		GameScreen.update();
	}
	
	private static void finish(){
		System.out.println("average update time is " + (updateTime / ticks) / 1000000.0 + " ms");
		System.out.println("average render time is " + (renderTime / ticks) / 1000000.0 + " ms");
	}
	
	private static void close(){
		GameScreen.close();
		System.exit(0);
	}
	
	public static void stop(){
		isStopping = true;
	}
	
	public static int getMouseX(){
		return Mouse.getX();
	}
	
	public static int getMouseY(){
		return Mouse.getY();
	}
	
	public static Renderer getRenderer(){
		return renderer;
	}
	
	public static GuiRenderer getGuiRenderer(){
		return guiRenderer;
	}
	
	public static boolean isKeyDown(int key){
		return Keyboard.isKeyDown(key);
	}
	
	public static boolean isMouseDown(int button){
		return Mouse.isButtonDown(button);
	}
	
	public static void setGameState(GameState gameState){
		state.close();
		state = gameState;
		state.open();
	}
	
	public static GameState getCurrentState(){
		return state;
	}
}
