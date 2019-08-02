package net.mcreator.shadowwolf_mod_one;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class MCreatorError514Achievement extends shadowwolf_mod_one.ModElement {

	public MCreatorError514Achievement(shadowwolf_mod_one instance) {
		super(instance);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorError514Achievement!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayerMP)
			MCreatorError514AchievementFailedToLoad.trigger.triggerAdvancement((EntityPlayerMP) entity);
	}
}
