package net.mcreator.shadowwolf_mod_one;

import net.minecraft.item.ItemStack;

public class MCreatorCharoriteShardFuel extends shadowwolf_mod_one.ModElement {

	public MCreatorCharoriteShardFuel(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public int addFuel(ItemStack fuel) {
		if (fuel.getItem() == new ItemStack(MCreatorCharoriteShard.block, (int) (1)).getItem())
			return 3200;
		return 0;
	}
}
