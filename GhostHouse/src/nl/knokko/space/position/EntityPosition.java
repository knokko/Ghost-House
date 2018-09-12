package nl.knokko.space.position;

public interface EntityPosition extends TilePosition {
	
	/**
	 * @return the width coordinate
	 */
	int getX();
	
	/**
	 * @return the height coordinate
	 */
	int getY();
	
	/**
	 * @return the tile X coordinate this position is located within
	 */
	int getTileX();
	
	/**
	 * @return the tile X coordinate this position is located within
	 */
	int getTileY();
	
	/**
	 * @return the dimension coordinate
	 */
	int getD();
	
	/**
	 * @return the width coordinate as float
	 */
	float getFX();
	
	/**
	 * @return the height coordinate as float
	 */
	float getFY();
	
	/**
	 * @return the width coordinate as double
	 */
	double getDX();
	
	/**
	 * @return the height coordinate as double
	 */
	double getDY();
	
	/**
	 * Move this position without any collission detection.
	 * @param dx the delta X
	 * @param dy the delta Y
	 */
	void moveDirect(double dx, double dy);
	
	/**
	 * Move this position without any collission detection.
	 * @param dx the delta X
	 * @param dy the delta Y
	 */
	void moveDirect(float dx, float dy);
	
	/**
	 * Move this position without any collission detection.
	 * @param dx the delta X
	 * @param dy the delta Y
	 */
	void moveDirect(int dx, int dy);
	
	/**
	 * Set the dimension of this position to the specified value.
	 * @param d the new dimension id
	 */
	void setDimension(int d);
}
