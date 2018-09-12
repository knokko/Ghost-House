package nl.knokko.space.collission;

import nl.knokko.space.position.Position;

public abstract class Collider {
	
	public static boolean doesIntersect(Collider c1, Collider c2){
		if(c1.position.getD() != c2.position.getD())
			return false;
		if(!(c1.getMinX() <= c2.getMaxX() && c2.getMinX() <= c1.getMaxX() && c1.getMinY() <= c2.getMaxY() && c2.getMinY() <= c1.getMaxY()))
			return false;
		if(c1 instanceof ColliderRectangle){
			//ColliderRectangle r1 = (ColliderRectangle) c1;
			if(c2 instanceof ColliderRectangle)
				return true;
		}
		if(c1 instanceof Polygon){
			Polygon p1 = (Polygon) c1;
			if(c2 instanceof Polygon){
				Polygon p2 = (Polygon) c2;
				ColliderLine[] lines1 = p1.getLines();
				ColliderLine[] lines2 = p2.getLines();
				for(ColliderLine line1 : lines1)
					for(ColliderLine line2 : lines2)
						if(lineIntersection(line1, line2))
							return true;
				return false;
			}
		}
		throw new IllegalArgumentException("Can not check for collission between " + c1.getClass() + " and " + c2.getClass() + "!");
	}
	
	private static boolean lineIntersection(ColliderLine line1, ColliderLine line2){
		if(line1.getMinX() > line2.getMaxX() || line2.getMinX() > line1.getMaxX() || line1.getMinY() > line2.getMaxY() || line2.getMinY() > line1.getMaxY())
			return false;
		if(line1.x1 == line1.x2){
			if(line2.x1 == line2.x2)
				return true;
			if(line2.y1 == line2.y2)
				return true;
			int y = line2.getIntValue(line1.getMinX());
			return y >= line1.getMinY() && y <= line1.getMaxY();
		}
		if(line2.x1 == line2.x2){
			int y = line1.getIntValue(line2.getMinX());
			return y >= line2.getMinY() && y <= line2.getMaxY();
		}
		//line1.getValue() = line2.getValue()
		//(x - l1x1 - px1) * (l1y2 - l1y1) / (l1x2 - l1x1) + l1y1 + py1 = (x - l2x2 - px2) * (l2y2 - l2y1) / (l2x2 - l2x1) + l2y1 + py2
		//(x - l1x1 - px1) * (l1y2 - l1y1) / (l1x2 - l1x1) - (x - l2x2 - px2) * (l2y2 - l2y1) / (l2x2 - l2x1) = -l1y1 - py1 + l2y1 + py2
		//(x - l1x1 - px1) * rc1 - (x - l2x2 - px2) * rc2 = -l1y1 - py1 + l2y1 + py2
		//(x * rc1 - l1x1 * rc1 - px1 * rc1 - x * rc2 + l2x2 * rc2 + px2 * rc2 = -l1y1 - py1 + l2y1 + py2
		//x * rc1 - x * rc2 = l1x1 * rc1 + px1 * rc1 - l2x2 * rc2 - px2 * rc2 - l1y1 - py1 + l2y1 + py2
		//x(rc1 - rc2) = l1x1 * rc1 + px1 * rc1 - l2x2 * rc2 - px2 * rc2 - l1y1 - py1 + l2y1 + py2
		//x = (l1x1 * rc1 + px1 * rc1 - l2x2 * rc2 - px2 * rc2 - l1y1 - py1 + l2y1 + py2) / (rc1 - rc2)
		double rc1 = (line1.y2 - line1.y1) / (line1.x2 - line1.x1);
		double rc2 = (line2.y2 - line2.y1) / (line2.x2 - line2.x1);
		if(rc1 == rc2){
			int dx = line2.x1 + line2.position.getX() - line1.x1 - line1.position.getX();
			int dy = line2.y1 + line2.position.getY() - line1.y1 - line1.position.getY();
			if(dx == 0)
				return false;
			return (double)dy / dx == rc1;
		}
		double x = (line1.x1 * rc1 + line1.position.getX() * rc1 - line2.x2 * rc2 - line2.position.getX() * rc2 - line1.y1 - line1.position.getY() + line2.y1 + line2.position.getY()) / (rc1 - rc2);
		return x >= line1.getMinX() && x >= line2.getMinX() && x <= line1.getMaxX() && x <= line2.getMaxX();
	}
	
	final Position position;

	public Collider(Position position) {
		this.position = position;
	}
	
	public final Position getPosition(){
		return position;
	}
	
	/**
	 * @return the relative minimum X coordinate of this collider
	 */
	public abstract int getRelativeMinX();
	
	/**
	 * @return the relative minimum Y coordinate of this collider
	 */
	public abstract int getRelativeMinY();
	
	/**
	 * @return the relative maximum X coordinate of this collider
	 */
	public abstract int getRelativeMaxX();
	
	/**
	 * @return the relative maximum Y coordinate of this collider
	 */
	public abstract int getRelativeMaxY();
	
	public final int getMinX(){
		return position.getX() + getRelativeMinX();
	}
	
	public final int getMinY(){
		return position.getY() + getRelativeMinY();
	}
	
	public final int getMaxX(){
		return position.getX() + getRelativeMaxX();
	}
	
	public final int getMaxY(){
		return position.getY() + getRelativeMaxY();
	}
}
