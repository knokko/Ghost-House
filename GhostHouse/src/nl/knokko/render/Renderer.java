package nl.knokko.render;

import java.nio.ByteBuffer;

import org.lwjgl.util.Color;

import nl.knokko.render.sprite.BufferedSprite;

public interface Renderer {
	
	void start(Color color);
	
	void renderSprite(int x, int y, BufferedSprite sprite);
	
	void renderPixel(int x, int y, ByteBuffer pixel);
	
	void stop();
}