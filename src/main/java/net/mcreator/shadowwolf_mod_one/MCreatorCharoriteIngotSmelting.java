package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

public class MCreatorCharoriteIngotSmelting extends shadowwolf_mod_one.ModElement {

	public MCreatorCharoriteIngotSmelting(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(MCreatorCharoriteShard.block, (int) (1)), new ItemStack(MCreatorCharoriteIngot.block, (int) (1)), 1F);
	}
}
