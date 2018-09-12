package nl.knokko.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import nl.knokko.main.GameScreen;
import nl.knokko.render.sprite.BufferedSprite;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public final class Resources {
	
	public static BufferedSprite loadButtonSprite(String buttonName){
		return loadBufferedSprite("guis/buttons/" + buttonName + ".png");
	}
	
	public static BufferedSprite loadTileSprite(String tileName){
		return loadBufferedSprite("tiles/" + tileName + ".png");
	}
	
	public static BufferedSprite loadGuiSprite(String name){
		return loadBufferedSprite("guis/" + name + ".png");
	}
	
	public static BufferedSprite loadBufferedSprite(String texturePath){
		return BufferedSprite.fromBufferedImage(loadBufferedImage(texturePath)).createScaled(GameScreen.getScale());
	}
	
	public static BufferedImage loadBufferedImage(String texturePath){
		try {
			return ImageIO.read(getStream("sprites/" + texturePath));
		} catch(IOException ex){
			throw new RuntimeException("Can't load resource " + texturePath, ex);
		}
	}
	
	static Texture loadTexture(String texturePath){
		try {
			return TextureLoader.getTexture("PNG", getStream("sprites" + File.separator + texturePath));
		} catch(IOException ex){
			throw new RuntimeException("Can't load resource " + texturePath + ":", ex);
		}
	}
	
	private static InputStream getStream(String resourceName){
		return Resources.class.getClassLoader().getResourceAsStream(resourceName);
	}
}
