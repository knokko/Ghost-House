package nl.knokko.fighting.damage;

import nl.knokko.space.entity.Entity;

public class DamageSource {
	
	private final DamageType type;

	public DamageSource(DamageType type) {
		this.type = type;
	}
	
	/**
	 * @return the Entity that directly caused the damage, or null if the damage was not caused by an Entity
	 */
	public Entity getDamagingEntity(){
		return null;
	}
	
	/**
	 * @return the Entity that is somehow responsible for the damage, or null if no Entity is responsible for the damage
	 */
	public Entity getResponsibleEntity(){
		return null;
	}
	
	public DamageType getType(){
		return type;
	}
}
