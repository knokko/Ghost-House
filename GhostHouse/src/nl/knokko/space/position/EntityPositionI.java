package nl.knokko.space.position;

import nl.knokko.space.tiles.Tile;

public class EntityPositionI implements EntityPosition {
	
	private int x;
	private int y;
	private int d;

	public EntityPositionI(int startX, int startY, int startDimension){
		x = startX;
		y = startY;
		d = startDimension;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getTileX() {
		return x / Tile.SIZE;
	}

	public int getTileY() {
		return y / Tile.SIZE;
	}

	public int getD() {
		return d;
	}

	public float getFX() {
		return x;
	}

	public float getFY() {
		return y;
	}

	public double getDX() {
		return x;
	}

	public double getDY() {
		return y;
	}

	public void moveDirect(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public void moveDirect(float dx, float dy) {
		x += dx;
		y += dy;
	}

	public void moveDirect(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public void setDimension(int d) {
		this.d = d;
	}
}
