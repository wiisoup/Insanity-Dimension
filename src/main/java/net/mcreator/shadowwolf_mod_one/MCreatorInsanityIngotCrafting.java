package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

public class MCreatorInsanityIngotCrafting extends shadowwolf_mod_one.ModElement {

	public MCreatorInsanityIngotCrafting(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(MCreatorInsanityOre.block, (int) (1)), new ItemStack(MCreatorInsanityIngot.block, (int) (2)), 1F);
	}
}
