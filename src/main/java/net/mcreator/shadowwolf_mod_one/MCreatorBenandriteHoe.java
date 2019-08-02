package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import java.util.Set;
import java.util.HashMap;

public class MCreatorBenandriteHoe extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:benandritehoe")
	public static final Item block = null;

	public MCreatorBenandriteHoe(shadowwolf_mod_one instance) {
		super(instance);
		instance.items.add(() -> new ItemHoe(EnumHelper.addToolMaterial("BENANDRITEHOE", 1, 500, 4f, 5f, 2)) {

			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("hoe", 1);
				return ret.keySet();
			}
		}.setUnlocalizedName("benandritehoe").setRegistryName("benandritehoe").setCreativeTab(MCreatorCharoriteTools.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("shadowwolf_mod_one:benandritehoe", "inventory"));
	}
}
