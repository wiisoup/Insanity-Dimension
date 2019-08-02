package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

public class MCreatorColorOreSmelting extends shadowwolf_mod_one.ModElement {

	public MCreatorColorOreSmelting(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(MCreatorColorOre.block, (int) (1)), new ItemStack(MCreatorColorGem.block, (int) (1)), 1F);
	}
}
