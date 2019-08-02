package net.mcreator.shadowwolf_mod_one;

import net.minecraft.item.ItemStack;

public class MCreatorColorStickFuel extends shadowwolf_mod_one.ModElement {

	public MCreatorColorStickFuel(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public int addFuel(ItemStack fuel) {
		if (fuel.getItem() == new ItemStack(MCreatorColorStick.block, (int) (1)).getItem())
			return 10000;
		return 0;
	}
}
