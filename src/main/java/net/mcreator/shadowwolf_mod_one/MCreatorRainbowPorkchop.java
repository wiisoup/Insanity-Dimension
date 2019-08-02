package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFood;
import net.minecraft.item.Item;
import net.minecraft.item.EnumAction;
import net.minecraft.init.MobEffects;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class MCreatorRainbowPorkchop extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:rainbowporkchop")
	public static final Item block = null;

	public MCreatorRainbowPorkchop(shadowwolf_mod_one instance) {
		super(instance);
		instance.items.add(() -> new ItemFoodCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("shadowwolf_mod_one:rainbowporkchop", "inventory"));
	}

	public static class ItemFoodCustom extends ItemFood {

		public ItemFoodCustom() {
			super(3, 0.3F, true);
			setUnlocalizedName("rainbowporkchop");
			setRegistryName("rainbowporkchop");
			setPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 0), 1.0F);
			setAlwaysEdible();
			setCreativeTab(MCreatorCharoriteTools.tab);
			setMaxStackSize(64);
		}

		@Override
		public EnumAction getItemUseAction(ItemStack par1ItemStack) {
			return EnumAction.EAT;
		}
	}
}
