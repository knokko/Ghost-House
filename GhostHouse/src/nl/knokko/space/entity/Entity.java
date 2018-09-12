package nl.knokko.space.entity;

import nl.knokko.fighting.damage.DamageSource;
import nl.knokko.space.position.EntityPosition;

public interface Entity {
	
	EntityPosition getPosition();
	
	boolean teleport(int x, int y);
	
	boolean move(int dx, int dy);
	
	void attack(DamageSource source, int damage);
}