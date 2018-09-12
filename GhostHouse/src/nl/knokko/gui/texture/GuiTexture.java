package nl.knokko.gui.texture;

import nl.knokko.render.sprite.BufferedSprite;

public class GuiTexture {
	
	private final BufferedSprite sprite;
	
	private final int x;
	private final int y;

	public GuiTexture(BufferedSprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x - sprite.getWidth() / 2;
		this.y = y - sprite.getHeight() / 2;
	}
	
	public BufferedSprite getSprite(){
		return sprite;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getMinX(){
		return x;
	}
	
	public int getMinY(){
		return y;
	}
	
	public int getMaxX(){
		return x + sprite.getWidth();
	}
	
	public int getMaxY(){
		return y + sprite.getHeight();
	}
}
