package net.mcreator.shadowwolf_mod_one;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class MCreatorPrankdDimensionAchievementProcedure extends shadowwolf_mod_one.ModElement {

	public MCreatorPrankdDimensionAchievementProcedure(shadowwolf_mod_one instance) {
		super(instance);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorPrankdDimensionAchievementProcedure!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayerMP)
			MCreatorPrankdDimensionAchievement.trigger.triggerAdvancement((EntityPlayerMP) entity);
	}
}
