package nl.knokko.gamestate;

public interface GameState {
	
	void open();
	
	void update();
	
	void render();
	
	void close();
	
	void click(int x, int y, int button);
	
	void press(int key, char character);
	
	void release(int key);
}