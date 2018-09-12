package nl.knokko.render;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.util.Color;

import nl.knokko.gui.Gui;
import nl.knokko.gui.texture.GuiTexture;
import nl.knokko.main.GameScreen;
import nl.knokko.render.sprite.BufferedSprite;

public class BufferedRenderer implements Renderer, GuiRenderer {
	
	private ByteBuffer buffer;
	private ByteBuffer backGround;
	
	private Map<Gui,ByteBuffer> guiTextureMap;
	
	private byte backRed;
	private byte backGreen;
	private byte backBlue;
	
	private int width;
	private int height;

	public BufferedRenderer() {
		width = GameScreen.getWidth();
		height = GameScreen.getHeight();
		buffer = ByteBuffer.allocateDirect(width * height * 3);
		backGround = ByteBuffer.allocateDirect(width * height * 3);
		guiTextureMap = new HashMap<Gui, ByteBuffer>();
	}

	public void start(Color color){
		if(backRed != color.getRedByte() || backGreen != color.getGreenByte() || backBlue != color.getBlueByte()){
			System.out.println("initialise background");
			backRed = color.getRedByte();
			backGreen = color.getGreenByte();
			backBlue = color.getBlueByte();
			byte[] pixel = {(byte) (color.getRed() - 128), (byte) (color.getGreen() - 128), (byte) (color.getBlue() - 128)};
			while(backGround.hasRemaining())
				backGround.put(pixel);
			backGround.position(0);
		}
		buffer.position(0);
		backGround.position(0);
		buffer.put(backGround);
	}

	public void renderSprite(int x, int y, BufferedSprite sprite) {
		renderSprite(x, y, sprite, buffer);
	}
	
	private void renderSprite(int x, int y, BufferedSprite sprite, ByteBuffer buffer){
		ByteBuffer[] buffers = sprite.getBufferArray();
		x *= GameScreen.getScale();
		y *= GameScreen.getScale();
		for(int i = 0; i < buffers.length; i++){
			if((y + i) < height && x >= 0 && y >= 0){
				if(x + (buffers[i].remaining() / 3) <= width){
					buffer.position((x + (y + i) * width) * 3);
					buffer.put(buffers[i]);
				}
				else
					System.out.println("max x too far");
			}
			else
				System.out.println("y too far");
		}
	}
	
	public void renderPixel(int x, int y, ByteBuffer pixel){
		if(x >= 0 && y >= 0 && x < width && y < height){
			buffer.position((x + y * width) * 3);
			buffer.put(pixel);
		}
	}
	
	public void renderFullGui(Gui gui) {
		ByteBuffer guiTexture = guiTextureMap.get(gui);
		if(guiTexture == null){
			System.out.println("creating new gui texture");
			guiTexture = gui.getBackground();
			for(GuiTexture texture : gui.getTextures())
				renderSprite(texture.getX(), texture.getY(), texture.getSprite(), guiTexture);
			guiTextureMap.put(gui, guiTexture);
		}
		guiTexture.position(0);
		GL14.glWindowPos2i(GameScreen.getRenderXOffset(), GameScreen.getRenderYOffset());
		GL11.glDrawPixels(width, height, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, guiTexture);
	}

	public void stop() {
		buffer.position(0);
		GL14.glWindowPos2i(GameScreen.getRenderXOffset(), GameScreen.getRenderYOffset());
		GL11.glDrawPixels(width, height, GL11.GL_RGB, GL11.GL_BYTE, buffer);
	}
}
