package nl.knokko.space.collission;

import java.awt.Point;

import nl.knokko.space.position.Position;

public class ColliderPolygon extends Collider implements Polygon {
	
	final ColliderLine[] lines;
	
	int minX;
	int minY;
	
	int maxX;
	int maxY;

	public ColliderPolygon(Position position, Point... points) {
		super(position);
		minX = points[0].x;
		minY = points[0].y;
		maxX = points[0].x;
		maxY = points[0].y;
		for(Point p : points){
			if(p.x < minX)
				minX = p.x;
			if(p.y < minY)
				minY = p.y;
			if(p.x > maxX)
				maxX = p.x;
			if(p.y > maxY)
				maxY = p.y;
		}
		lines = new ColliderLine[points.length - 1];
		for(int i = 0; i < lines.length; i++)
			lines[i] = new ColliderLine(position, points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
	}

	public ColliderLine[] getLines() {
		return lines;
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
	public int getRelativeMaxX() {
		return maxX;
	}

	@Override
	public int getRelativeMaxY() {
		return maxY;
	}

}
