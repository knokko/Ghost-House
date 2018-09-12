package nl.knokko.space.collission;

import nl.knokko.space.position.Position;

/**
 * This class represents a line with the formula y = rc*x + b
 */
public class ColliderLine extends Collider {
	
	int x1;
	int y1;
	int x2;
	int y2;
	
	public ColliderLine(Position position, int x1, int y1, int x2, int y2){
		super(position);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public int getRelativeMinX() {
		return Math.min(x1, x2);
	}

	@Override
	public int getRelativeMinY() {
		return Math.min(y1, y2);
	}

	@Override
	public int getRelativeMaxX() {
		return Math.max(x1, x2);
	}

	@Override
	public int getRelativeMaxY() {
		return Math.max(y1, y2);
	}
	
	double getValue(int x){
		if(x1 == x2)
			throw new IllegalStateException("Vertical line");//(6,3) en (10, 2) en x = 7
		return (x - x1 - position.getX() * (y2 - y1)) / (double) (x2 - x1) + y1 + position.getY();
		//(x - x1 - position.getX) * (y2 - y1) / (x2 - x1) + y1 = (xy2 - xy1 - x1y2 + x1y1) / (x2 - x1) + y1
	}
	
	int getIntValue(int x){
		if(x1 == x2)
			throw new IllegalStateException("Vertical line");
		return (x - x1 - position.getX()) * (y2 - y1) / (x2 - x1) + y1 + position.getY();
	}
}
