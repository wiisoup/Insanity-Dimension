package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import java.util.HashMap;

public class MCreatorKernelArmor extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:kernelarmorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:kernelarmorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:kernelarmorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:kernelarmorboots")
	public static final Item boots = null;

	public MCreatorKernelArmor(shadowwolf_mod_one instance) {
		super(instance);
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("KERNELARMOR", "shadowwolf_mod_one:kernelarmor", 150, new int[]{50, 50, 50, 50},
				64, null, 2f);
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD) {

			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				super.onArmorTick(world, entity, itemstack);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					MCreatorError514Achievement.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("kernelarmorhelmet").setRegistryName("kernelarmorhelmet").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST) {

			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					MCreatorError514Achievement.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("kernelarmorbody").setRegistryName("kernelarmorbody").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS) {

			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					MCreatorError514Achievement.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("kernelarmorlegs").setRegistryName("kernelarmorlegs").setCreativeTab(MCreatorCharoriteTools.tab));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET) {

			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					MCreatorError514Achievement.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("kernelarmorboots").setRegistryName("kernelarmorboots").setCreativeTab(MCreatorCharoriteTools.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("shadowwolf_mod_one:kernelarmorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("shadowwolf_mod_one:kernelarmorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("shadowwolf_mod_one:kernelarmorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("shadowwolf_mod_one:kernelarmorboots", "inventory"));
	}
}
