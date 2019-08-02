package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

public class MCreatorDarkOreSmelt extends shadowwolf_mod_one.ModElement {

	public MCreatorDarkOreSmelt(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(MCreatorDarkOre.block, (int) (1)), new ItemStack(MCreatorDarkGem.block, (int) (1)), 1F);
	}
}
