package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

public class MCreatorPrankdSmelt extends shadowwolf_mod_one.ModElement {

	public MCreatorPrankdSmelt(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(MCreatorAprilfoof.block, (int) (1)), new ItemStack(MCreatorMoistGrayDirt.block, (int) (64)), 1F);
	}
}
