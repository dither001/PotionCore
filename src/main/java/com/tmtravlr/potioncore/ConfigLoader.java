package com.tmtravlr.potioncore;

import net.minecraftforge.common.config.Configuration;

import com.tmtravlr.potioncore.PotionCoreEffects.PotionData;
import com.tmtravlr.potioncore.effects.PotionArchery;
import com.tmtravlr.potioncore.effects.PotionExplosion;
import com.tmtravlr.potioncore.effects.PotionExplosionSelf;
import com.tmtravlr.potioncore.effects.PotionFire;
import com.tmtravlr.potioncore.effects.PotionKlutz;
import com.tmtravlr.potioncore.effects.PotionLaunch;
import com.tmtravlr.potioncore.effects.PotionLevitate;
import com.tmtravlr.potioncore.effects.PotionRecoil;
import com.tmtravlr.potioncore.effects.PotionRepair;
import com.tmtravlr.potioncore.effects.PotionRevival;
import com.tmtravlr.potioncore.effects.PotionRust;
import com.tmtravlr.potioncore.effects.PotionSlowfall;
import com.tmtravlr.potioncore.effects.PotionSpin;
import com.tmtravlr.potioncore.effects.PotionTeleport;
import com.tmtravlr.potioncore.effects.PotionTeleportSpawn;
import com.tmtravlr.potioncore.effects.PotionVulnerable;
import com.tmtravlr.potioncore.effects.PotionWeight;

public class ConfigLoader {

	public static Configuration config;
	
	public static boolean fixInvisibility;
	public static boolean fixBlindness;
	
	public static void load() {
		config.load();
		
		loadPotionConfigs();
		
		fixInvisibility = config.getBoolean("Fix Invisibiliby", "_options", true, "Fixes Invisibiliby so mobs can't see you as close while you are invisible.\nThey can see you at 1-12 blocks away depending on how much armor you\nhave on and if you are holding a item or not.");
		fixBlindness = config.getBoolean("Fix Blindness", "_options", true, "Fixes Blindness so mobs can't see things to attack unless they are really\nclose.");
		
		for(String name : PotionCoreEffects.potionMap.keySet()) {
			PotionData data = PotionCoreEffects.potionMap.get(name);
			
			data.enabled = config.getBoolean("Enabled", name, data.enabled, "Is the " + name + " potion enabled?");
			//data.id = config.getInt("Id", name, data.id, 0, 255, "Id of the " + name + " potion.");
		}
		
		config.save();
	}

	/** Loads all the potion-specific config options. */
	private static void loadPotionConfigs() {
		PotionArchery.damageModifier = config.getFloat("Damage Modifier", PotionArchery.NAME, (float) PotionArchery.damageModifier, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The projectile damage modifier applied by the " + PotionArchery.NAME + " potion. Default is " + PotionArchery.damageModifier + " (+" + (PotionArchery.damageModifier * 100.0) + "%).");

		PotionExplosion.explosionSize = config.getFloat(PotionExplosion.NAME, "Explosion Size", 0.0f, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The base explosion size for the " + PotionExplosion.NAME + " potion. Default is " + PotionExplosion.explosionSize + ".");

		PotionExplosionSelf.explosionSize = config.getFloat("Explosion Size", PotionExplosionSelf.NAME, PotionExplosionSelf.explosionSize, 0.0f, Float.POSITIVE_INFINITY,
				"The base explosion size for the " + PotionExplosionSelf.NAME + " potion. Default is " + PotionExplosionSelf.explosionSize + ".");

		PotionFire.fireDuration = config.getFloat("Fire Duration", PotionFire.NAME, PotionFire.fireDuration, 0.0f, Float.POSITIVE_INFINITY,
				"The fire duration applied by the " + PotionFire.NAME + " potion for each amplifier level. Default is " + PotionFire.fireDuration + " seconds.");

		PotionKlutz.damageModifier = config.getFloat("Damage Modifier", PotionKlutz.NAME, (float) PotionKlutz.damageModifier, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The projectile damage modifier applied by the " + PotionKlutz.NAME + " potion. Default is " + PotionKlutz.damageModifier + " (+" + (PotionKlutz.damageModifier * 100.0) + "%).");

		PotionLaunch.launchSpeed = config.getFloat("Launch Speed", PotionLaunch.NAME, (float) PotionLaunch.launchSpeed, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The initial launch speed for the " + PotionLaunch.NAME + " potion. Default is " + PotionLaunch.launchSpeed + " block/tick.");

		PotionLevitate.floatSpeed = config.getFloat("Float Speed", PotionLevitate.NAME, (float) PotionLevitate.floatSpeed, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The float speed for the " + PotionLevitate.NAME + " potion. Default is " + PotionLevitate.floatSpeed + " block/tick.");

		PotionRecoil.reflectDamage = config.getFloat("Damage Reflection", PotionRecoil.NAME, PotionRecoil.reflectDamage, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The amount of damage reflected for the " + PotionRecoil.NAME + " potion. Default is " + PotionRecoil.reflectDamage + " (" + (PotionRecoil.reflectDamage * 100.0) + "%).");

		PotionRepair.repairTime = config.getInt("Repair Time", PotionRepair.NAME, PotionRepair.repairTime, 0, Integer.MAX_VALUE,
				"The ticks between each repair from the " + PotionRepair.NAME + " potion. Default is " + PotionRepair.repairTime + " ticks.");

		PotionRevival.reviveHealth = config.getFloat("Revive Health", PotionRevival.NAME, PotionRevival.reviveHealth, 0.0f, Float.POSITIVE_INFINITY,
				"The amount of health recovered for each amplifier level when reviving from the " + PotionRevival.NAME + " potion. Default is " + PotionRevival.reviveHealth + " half-hearts.");

		PotionRust.damageTime = config.getInt("Damage Time", PotionRust.NAME, PotionRust.damageTime, 0, Integer.MAX_VALUE,
				"The ticks between each durability reduction from the " + PotionRust.NAME + " potion. Default is " + PotionRust.damageTime + " ticks.");

		PotionSlowfall.maxSpeed = config.getFloat("Max Speed", PotionSlowfall.NAME, (float) PotionSlowfall.maxSpeed, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The base maximum fall speed for the " + PotionSlowfall.NAME + " potion. Default is " + PotionSlowfall.maxSpeed + " block/tick.");

		PotionSpin.rotationSpeed = config.getFloat("Rotation Speed", PotionSpin.NAME, PotionSpin.rotationSpeed, 0.0f, Float.POSITIVE_INFINITY,
				"The base maximum random rotation speed change for the " + PotionSpin.NAME + " potion. Default is " + PotionSpin.rotationSpeed + " degrees/tick/tick.");

		PotionTeleport.teleportRange = 2.0 * config.getFloat("Teleport Range", PotionTeleport.NAME, (float) (PotionTeleport.teleportRange / 2.0), 1.0f, Float.POSITIVE_INFINITY,
				"The base maximum teleport radius for the " + PotionTeleport.NAME + " potion. Default is " + (PotionTeleport.teleportRange / 2.0) + " blocks.");

		PotionTeleportSpawn.teleportDelay = config.getInt("Teleport Delay", PotionTeleportSpawn.NAME, PotionTeleportSpawn.teleportDelay, 0, Integer.MAX_VALUE,
				"The number of ticks the player must stay still before teleporting with the " + PotionTeleportSpawn.NAME + " potion. Default is " + PotionTeleportSpawn.teleportDelay + " ticks.");

		PotionVulnerable.damageMultiplier = config.getFloat("Damage Multiplier", PotionVulnerable.NAME, (float) PotionVulnerable.damageMultiplier, 0.0f, Float.POSITIVE_INFINITY,
				"The base damage multiplier for the " + PotionVulnerable.NAME + " potion. Default is " + PotionVulnerable.damageMultiplier + ".");

		PotionWeight.speedReduction = config.getFloat("Speed Reduction", PotionWeight.NAME, PotionWeight.speedReduction, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
				"The base jump speed reduction for the " + PotionWeight.NAME + " potion. Normal jump speed is about 0.4 block/tick. Default is " + PotionWeight.speedReduction + " block/tick.");
	}
	
}