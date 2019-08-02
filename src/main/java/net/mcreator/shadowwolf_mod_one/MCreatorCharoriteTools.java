package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

public class MCreatorCharoriteTools extends shadowwolf_mod_one.ModElement {

	public MCreatorCharoriteTools(shadowwolf_mod_one instance) {
		super(instance);
	}

	public static CreativeTabs tab = new CreativeTabs("tabcharoritetools") {

		@SideOnly(Side.CLIENT)
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(MCreatorInsanityIngot.block, (int) (1));
		}

		@SideOnly(Side.CLIENT)
		public boolean hasSearchBar() {
			return false;
		}
	};
}
