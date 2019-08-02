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

public class MCreatorDarkArmor extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:darkarmorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:darkarmorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:darkarmorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:darkarmorboots")
	public static final Item boots = null;

	public MCreatorDarkArmor(shadowwolf_mod_one instance) {
		super(instance);
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("DARKARMOR", "shadowwolf_mod_one:darkarmor", 25, new int[]{50, 50, 50, 50}, 20,
				null, 2.5f);
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("darkarmorhelmet")
				.setRegistryName("darkarmorhelmet").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("darkarmorbody")
				.setRegistryName("darkarmorbody").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("darkarmorlegs")
				.setRegistryName("darkarmorlegs").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("darkarmorboots")
				.setRegistryName("darkarmorboots").setCreativeTab(MCreatorCharoriteTools.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("shadowwolf_mod_one:darkarmorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("shadowwolf_mod_one:darkarmorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("shadowwolf_mod_one:darkarmorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("shadowwolf_mod_one:darkarmorboots", "inventory"));
	}
}
