package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

public class MCreatorBenandroniteSmoothed extends shadowwolf_mod_one.ModElement {

	public MCreatorBenandroniteSmoothed(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(MCreatorBenandriteItem.block, (int) (1)), new ItemStack(MCreatorBenandroniteSmooth.block, (int) (1)),
				1F);
	}
}
