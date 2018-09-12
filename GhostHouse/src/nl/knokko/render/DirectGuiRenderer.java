package nl.knokko.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import nl.knokko.gui.Gui;
import nl.knokko.gui.texture.GuiTexture;
import nl.knokko.main.GameScreen;

public class DirectGuiRenderer implements GuiRenderer {

	public DirectGuiRenderer() {}

	public void renderFullGui(Gui gui) {
		GL11.glClearColor(0, 0, 0, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL14.glWindowPos2i(GameScreen.getRenderXOffset(), GameScreen.getRenderYOffset());
		GL11.glDrawPixels(GameScreen.getWidth(), GameScreen.getHeight(), GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, gui.getBackground());
		for(GuiTexture texture : gui.getTextures()){
			GL14.glWindowPos2i(texture.getMinX() + GameScreen.getRenderXOffset(), texture.getMinY() + GameScreen.getRenderYOffset());
			GL11.glDrawPixels(texture.getSprite().getWidth(), texture.getSprite().getHeight(), GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, texture.getSprite().getBuffer());
		}
	}

}
