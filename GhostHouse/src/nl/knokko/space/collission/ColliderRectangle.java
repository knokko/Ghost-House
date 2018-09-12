package nl.knokko.space.collission;

import nl.knokko.space.position.Position;

public class ColliderRectangle extends Collider implements Polygon {
	
	final ColliderLine[] lines;
	
	final int minX;
	final int minY;
	final int maxX;
	final int maxY;

	public ColliderRectangle(Position position, int minX, int minY, int maxX, int maxY) {
		super(position);
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		lines = new ColliderLine[]{new ColliderLine(position, minX, minY, maxX, minY), new ColliderLine(position, maxX, minY, maxX, maxY),
				new ColliderLine(position, maxX, maxY, minX, maxY), new ColliderLine(position, minX, maxY, minX, minY)};
	}

	@Override
	public int getRelativeMinX() {
		return minX;
	}

	@Override
	public int getRelativeMinY() {
		return minY;
	}

	@Override
	public int getRelativeMaxX(){
		return maxX;
	}

	@Override
	public int getRelativeMaxY(){
		return maxY;
	}

	public ColliderLine[] getLines() {
		return lines;
	}
}