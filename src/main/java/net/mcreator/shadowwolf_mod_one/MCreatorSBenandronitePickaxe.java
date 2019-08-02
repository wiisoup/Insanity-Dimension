package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import java.util.Set;
import java.util.HashMap;

public class MCreatorSBenandronitePickaxe extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:sbenandronitepickaxe")
	public static final Item block = null;

	public MCreatorSBenandronitePickaxe(shadowwolf_mod_one instance) {
		super(instance);
		instance.items.add(() -> new ItemPickaxe(EnumHelper.addToolMaterial("SBENANDRONITEPICKAXE", 12, 1000, 20f, 30f, 24)) {

			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("pickaxe", 12);
				return ret.keySet();
			}
		}.setUnlocalizedName("sbenandronitepickaxe").setRegistryName("sbenandronitepickaxe").setCreativeTab(MCreatorCharoriteTools.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("shadowwolf_mod_one:sbenandronitepickaxe", "inventory"));
	}
}
