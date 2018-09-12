package nl.knokko.render.sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;

public class BufferedSprite {
	
	private final ByteBuffer buffer;
	private ByteBuffer[] bufferArray;
	
	private final int width;
	private final int height;
	
	public BufferedSprite(Random random, int width, int height){
		byte[] pixels = new byte[width * height * 3];
		random.nextBytes(pixels);
		this.width = width;
		this.height = height;
		buffer = ByteBuffer.allocateDirect(pixels.length);
		buffer.put(pixels);
		buffer.flip();
		setBufferArray();
	}
	
	public static BufferedSprite fromBufferedImage(BufferedImage image){
		byte[][][] data = new byte[image.getWidth()][image.getHeight()][3];
		for(int x = 0; x < image.getWidth(); x++){
			for(int y = 0; y < image.getHeight(); y++){
				Color color = new Color(image.getRGB(x, y));
				data[x][image.getHeight() - y - 1][0] = (byte) (color.getRed());
				data[x][image.getHeight() - y - 1][1] = (byte) (color.getGreen());
				data[x][image.getHeight() - y - 1][2] = (byte) (color.getBlue());
			}
		}
		return new BufferedSprite(data);
	}

	public BufferedSprite(Texture texture) {
		width = texture.getImageWidth();
		height = texture.getImageHeight();
		byte[] pixels = texture.getTextureData();
		buffer = ByteBuffer.allocateDirect(pixels.length);
		buffer.put(pixels);
		buffer.flip();
		setBufferArray();
	}
	
	public BufferedSprite(byte[][][] data){
		width = data.length;
		height = data[0].length;
		buffer = ByteBuffer.allocateDirect(width * height * 3);
		bufferArray = new ByteBuffer[height];
		for(int y = 0; y < height; y++){
			ByteBuffer buf = ByteBuffer.allocateDirect(width * 3);
			for(int x = 0; x < width; x++){
				buffer.put(data[x][y]);
				buf.put(data[x][y]);
			}
			bufferArray[y] = buf;
		}
		buffer.flip();
		buffer.position(0);
	}
	
	private void setBufferArray(){
		bufferArray = new ByteBuffer[height];
		for(int i = 0; i < bufferArray.length; i++){
			ByteBuffer buf = ByteBuffer.allocateDirect(width * 3);
			byte[] bytes = new byte[width * 3];
			buffer.get(bytes);
			buf.put(bytes);
			buf.flip();
			bufferArray[height - i - 1] = buf;
		}
		buffer.position(0);
	}
	
	public ByteBuffer getBuffer(){
		buffer.position(0);
		return buffer;
	}
	
	public ByteBuffer[] getBufferArray(){
		for(ByteBuffer b : bufferArray)
			b.position(0);
		return bufferArray;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setPixel(int x, int y, byte[] pixel){
		int index = (x + y * width) * 3;
		setPixels(index, pixel);
	}
	
	private void setPixels(int index, byte[] pixels){
		buffer.position(index);
		buffer.put(pixels);
		buffer.position(0);
	}
	
	public BufferedSprite createScaled(int scale){
		byte[][][] data = new byte[width * scale][height * scale][3];
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				int index = (x + y * width) * 3;
				buffer.position(index);
				byte[] pixel = {buffer.get(), buffer.get(), buffer.get()};
				for(int i = 0; i < scale; i++){
					for(int j = 0; j < scale; j++){
						data[x * scale + i][y * scale + j] = pixel;
					}
				}
			}
		}
		return new BufferedSprite(data);
	}
}
