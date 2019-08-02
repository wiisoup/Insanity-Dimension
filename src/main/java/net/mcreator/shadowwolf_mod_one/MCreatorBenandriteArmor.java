package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class MCreatorBenandriteArmor extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:benandritearmorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:benandritearmorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:benandritearmorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:benandritearmorboots")
	public static final Item boots = null;

	public MCreatorBenandriteArmor(shadowwolf_mod_one instance) {
		super(instance);
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("BENANDRITEARMOR", "shadowwolf_mod_one:benandritearmor", 50, new int[]{30, 33,
				34, 30}, 11, null, 1.8f);
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("benandritearmorhelmet")
				.setRegistryName("benandritearmorhelmet").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("benandritearmorbody")
				.setRegistryName("benandritearmorbody").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("benandritearmorlegs")
				.setRegistryName("benandritearmorlegs").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("benandritearmorboots")
				.setRegistryName("benandritearmorboots").setCreativeTab(MCreatorCharoriteTools.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("shadowwolf_mod_one:benandritearmorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("shadowwolf_mod_one:benandritearmorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("shadowwolf_mod_one:benandritearmorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("shadowwolf_mod_one:benandritearmorboots", "inventory"));
	}
}
