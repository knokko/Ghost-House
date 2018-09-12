package nl.knokko.space.position;

import nl.knokko.space.tiles.Tile;

public class EntityPositionF implements EntityPosition {
	
	private float x;
	private float y;
	private int d;

	public EntityPositionF(float startX, float startY, int startDimension) {
		x = startX;
		y = startY;
		d = startDimension;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getTileX() {
		return (int) (x / Tile.SIZE);
	}

	public int getTileY() {
		return (int) (y / Tile.SIZE);
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
