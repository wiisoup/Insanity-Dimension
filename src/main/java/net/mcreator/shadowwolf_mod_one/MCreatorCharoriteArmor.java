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

public class MCreatorCharoriteArmor extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:charoritearmorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:charoritearmorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:charoritearmorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:charoritearmorboots")
	public static final Item boots = null;

	public MCreatorCharoriteArmor(shadowwolf_mod_one instance) {
		super(instance);
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("CHARORITEARMOR", "shadowwolf_mod_one:charoritearmor", 50, new int[]{30, 33, 34,
				30}, 9, null, 1.4000000000000001f);
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("charoritearmorhelmet")
				.setRegistryName("charoritearmorhelmet").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("charoritearmorbody")
				.setRegistryName("charoritearmorbody").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("charoritearmorlegs")
				.setRegistryName("charoritearmorlegs").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("charoritearmorboots")
				.setRegistryName("charoritearmorboots").setCreativeTab(MCreatorCharoriteTools.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("shadowwolf_mod_one:charoritearmorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("shadowwolf_mod_one:charoritearmorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("shadowwolf_mod_one:charoritearmorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("shadowwolf_mod_one:charoritearmorboots", "inventory"));
	}
}
