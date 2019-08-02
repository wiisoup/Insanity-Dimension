package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

public class MCreatorRainbowOreSmelt extends shadowwolf_mod_one.ModElement {

	public MCreatorRainbowOreSmelt(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(MCreatorRainbowOre.block, (int) (1)), new ItemStack(MCreatorRainbowDust.block, (int) (1)), 1F);
	}
}
