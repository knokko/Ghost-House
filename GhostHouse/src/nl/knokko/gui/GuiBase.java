package nl.knokko.gui;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import nl.knokko.gui.button.ButtonBase;
import nl.knokko.gui.button.GuiButton;
import nl.knokko.gui.texture.GuiTexture;
import nl.knokko.main.Game;
import nl.knokko.render.sprite.BufferedSprite;
import nl.knokko.resources.Resources;

public class GuiBase implements Gui {
	
	public static final GuiBase MAIN_MENU = new GuiBase("main menu", new ButtonBase(Resources.loadButtonSprite("main play"), 600, 540){

		@Override
		public void click(int button) {
			
		}}, new ButtonBase(Resources.loadButtonSprite("main quit"), 600, 160){

			@Override
			public void click(int button) {
				Game.stop();
			}
		});
	
	private final GuiTexture[] textures;
	private final GuiButton[] buttons;
	
	private final BufferedSprite backGround;

	public GuiBase(String guiName, Object... buttonAndTextures) {
		backGround = Resources.loadGuiSprite(guiName);
		List<GuiTexture> textureList = new ArrayList<GuiTexture>();
		List<GuiButton> buttonList = new ArrayList<GuiButton>();
		for(Object object : buttonAndTextures){
			if(object instanceof GuiTexture)
				textureList.add((GuiTexture) object);
			if(object instanceof GuiButton)
				buttonList.add((GuiButton) object);
		}
		textures = textureList.toArray(new GuiTexture[textureList.size()]);
		buttons = buttonList.toArray(new GuiButton[buttonList.size()]);
	}
	
	@Override
	public boolean equals(Object other){
		if(other == this)
			return true;
		if(other.getClass() == GuiBase.class){
			GuiBase gb = (GuiBase) other;
			for(int i = 0; i < textures.length; i++)
				if(!textures[i].equals(gb.textures[i]))
					return false;
			for(int i = 0; i < buttons.length; i++)
				if(!buttons[i].equals(gb.buttons[i]))
					return false;
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return textures.length * 5 + buttons.length;
	}

	public void init() {}

	public void click(int x, int y, int button) {
		for(GuiButton gbutton : buttons)
			gbutton.click(x, y, button);
	}

	public GuiTexture[] getTextures() {
		return textures;
	}

	public GuiButton[] getButtons() {
		return buttons;
	}

	public ByteBuffer getBackground() {
		return backGround.getBuffer();
	}
}
