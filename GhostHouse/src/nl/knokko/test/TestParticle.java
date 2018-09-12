package nl.knokko.test;

import java.util.Random;

import nl.knokko.main.Game;
import nl.knokko.main.GameScreen;
import nl.knokko.render.sprite.BufferedSprite;
import nl.knokko.resources.Resources;

public class TestParticle {
	
	private static final int WIDTH = GameScreen.getWidth();
	private static final int HEIGHT = GameScreen.getHeight();
	
	//private static final ByteBuffer pixel = (ByteBuffer) ByteBuffer.allocateDirect(3).put(new byte[]{0, 0, 0}).flip();
	private static final BufferedSprite sprite = Resources.loadBufferedSprite("black.png");
	
	private static final boolean[][][] parcour = new boolean[WIDTH][HEIGHT][2];
	
	static {
		setBehaviour(new ParticleBehaviour(){
			
			private Random random = new Random();

			public boolean[] value(int x, int y) {
				return new boolean[]{random.nextInt(x + 1) < 100, random.nextInt(y + 1) < 200};
			}
			
		});
	}
	
	public static void setBehaviour(ParticleBehaviour pb){
		long startTime = System.nanoTime();
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++){
				parcour[x][y] = pb.value(x, y);
			}
		for(int y = 0; y < HEIGHT; y++){
			parcour[0][y][0] = true;
			parcour[WIDTH - 1][y][0] = false;
		}
		for(int x = 0; x < WIDTH; x++){
			parcour[x][0][1] = true;
			parcour[x][HEIGHT - 1][1] = false;
		}
		System.out.println("initialising move map took " + (System.nanoTime() - startTime) / 1000 + " microseconds.");
	}
	
	private Random random;
	
	private int x,y;

	public TestParticle(Random rand) {
		random = new Random(rand.nextLong());
		x = random.nextInt(WIDTH);
		y = random.nextInt(HEIGHT);
	}
	
	public void update(){
		if(parcour[x][y][0])
			x++;
		else
			x--;
		if(parcour[x][y][1])
			y++;
		else
			y--;
	}
	
	public void render(){
		//pixel.position(0);
		Game.getRenderer().renderSprite(x, y, sprite);
	}
}
