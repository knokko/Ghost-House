package nl.knokko.render;

import java.nio.ByteBuffer;

import nl.knokko.render.sprite.BufferedSprite;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.util.Color;

public class DirectRenderer implements Renderer {
	
	public void start(Color color){
		GL11.glClearColor(color.getRed() / 255f, color.getGreen() / 225f, color.getBlue() / 255f, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void renderSprite(int x, int y, BufferedSprite texture){
		GL14.glWindowPos2i(x, y);
		GL11.glDrawPixels(texture.getWidth(), texture.getHeight(), GL11.GL_RGB, GL11.GL_BYTE, texture.getBuffer());
	}
	
	public void renderPixel(int x, int y, ByteBuffer pixel){
		GL14.glWindowPos2i(x, y);
		GL11.glDrawPixels(1, 1, GL11.GL_RGB, GL11.GL_BYTE, pixel);
	}
	
	public void stop(){}
}
