package nl.knokko.space.position;

import nl.knokko.space.tiles.Tile;

public class EntityPositionD implements EntityPosition {
	
	private double x;
	private double y;
	private int d;

	public EntityPositionD(double startX, double startY, int startDimension) {
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
		return (float) x;
	}

	public float getFY() {
		return (float) y;
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
