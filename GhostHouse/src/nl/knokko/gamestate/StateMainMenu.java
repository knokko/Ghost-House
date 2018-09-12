package nl.knokko.gamestate;

import org.lwjgl.input.Keyboard;

import nl.knokko.gui.Gui;
import nl.knokko.gui.GuiBase;
import nl.knokko.main.Game;

public class StateMainMenu implements GameState {
	
	private Gui gui;

	public StateMainMenu() {
		// TODO Auto-generated constructor stub
	}

	public void open() {
		gui = GuiBase.MAIN_MENU;
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void render() {
		Game.getGuiRenderer().renderFullGui(gui);
	}

	public void close() {
		gui = null;
	}

	public void click(int x, int y, int button) {
		gui.click(x, y, button);
	}

	public void press(int key, char character) {
		if(key == Keyboard.KEY_ESCAPE)
			Game.stop();
	}

	public void release(int key) {
		// TODO Auto-generated method stub

	}

}
