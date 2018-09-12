package nl.knokko.gui;

import java.nio.ByteBuffer;

import nl.knokko.gui.button.GuiButton;
import nl.knokko.gui.texture.GuiTexture;

public interface Gui {
	
	void init();
	
	void click(int x, int y, int button);
	
	GuiTexture[] getTextures();
	
	GuiButton[] getButtons();

	ByteBuffer getBackground();
}
