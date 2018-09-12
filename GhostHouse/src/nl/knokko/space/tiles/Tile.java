package nl.knokko.space.tiles;

import nl.knokko.space.position.TilePosition;
import nl.knokko.util.Facing;

public abstract class Tile {
	
	public static final int SIZE = 32;
	
	private char id;

	Tile() {}
	
	public abstract boolean canCollide();
	
	public abstract void onEntityInside(TilePosition ownPosition);
	
	public abstract void onCollide(Facing entityFacing);
	
	public final char getID(){
		return id;
	}
	
	final void setID(char id){
		this.id = id;
	}
}