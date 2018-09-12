package nl.knokko.gui.button;

import nl.knokko.gui.texture.GuiTexture;
import nl.knokko.render.sprite.BufferedSprite;

public abstract class ButtonBase extends GuiTexture implements GuiButton {

	public ButtonBase(BufferedSprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public void click(int x, int y, int button) {
		if(x >= getMinX() && x <= getMaxX() && y >= getMinY() && y <= getMaxY())
			click(button);
	}
	
	public abstract void click(int button);
}
