package net.mcreator.shadowwolf_mod_one;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class MCreatorEnterAllDimensions7 extends shadowwolf_mod_one.ModElement {

	public MCreatorEnterAllDimensions7(shadowwolf_mod_one instance) {
		super(instance);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorEnterAllDimensions7!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		boolean InsanityDimension1 = false;
		boolean BenandroniteDimension1 = false;
		boolean RainbowDimension1 = false;
		boolean DarkDimension1 = false;
		boolean UnoDimension1 = false;
		boolean PrankdDimension1 = false;
		if (((shadowwolf_mod_oneVariables.InsanityDimension1) == (true))) {
			if (((shadowwolf_mod_oneVariables.BenandroniteDimension1) == (true))) {
				if (((shadowwolf_mod_oneVariables.RainbowDimension1) == (true))) {
					if (((shadowwolf_mod_oneVariables.DarkDimension1) == (true))) {
						if (((shadowwolf_mod_oneVariables.UnoDimension1) == (true))) {
							if (((shadowwolf_mod_oneVariables.PrankdDimension1) == (true))) {
								if (((shadowwolf_mod_oneVariables.OverworldEnd1) == (true))) {
									if (entity instanceof EntityPlayerMP)
										MCreatorEnterAllDimensions.trigger.triggerAdvancement((EntityPlayerMP) entity);
								}
							}
						}
					}
				}
			}
		}
	}
}
