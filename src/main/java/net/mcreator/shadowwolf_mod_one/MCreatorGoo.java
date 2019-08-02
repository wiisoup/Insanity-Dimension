package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.HashMap;

public class MCreatorGoo extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:goo")
	public static final Block block = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:goo")
	public static final Item item = null;
	private Fluid fluid;

	public MCreatorGoo(shadowwolf_mod_one instance) {
		super(instance);
		fluid = new Fluid("goo", new ResourceLocation("shadowwolf_mod_one:blocks/goo"), new ResourceLocation("shadowwolf_mod_one:blocks/goo"))
				.setLuminosity(0).setDensity(2500).setViscosity(1500).setGaseous(false);
		FluidRegistry.registerFluid(fluid);
		instance.blocks.add(() -> new BlockFluidClassic(fluid, Material.WATER) {

			@Override
			public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
				super.onEntityCollidedWithBlock(world, pos, state, entity);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				Block block = this;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					MCreatorDontDrinkItProcedure.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("goo").setRegistryName("goo"));
		instance.items.add(() -> new ItemBlock(block).setRegistryName("goo"));
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		FluidRegistry.addBucketForFluid(fluid);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		ModelBakery.registerItemVariants(item);
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("shadowwolf_mod_one:goo", "goo");
			}
		});
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("shadowwolf_mod_one:goo", "goo");
			}
		});
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		if (dimID == MCreatorRainbowDimension.DIMID)
			dimensionCriteria = true;
		if (dimID == MCreatorDarkDimension.DIMID)
			dimensionCriteria = true;
		if (dimID == MCreatorWoodAndOre.DIMID)
			dimensionCriteria = true;
		if (dimID == MCreatorBenandroniteDimension.DIMID)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return;
		int i = chunkX + random.nextInt(16) + 8;
		int j = random.nextInt(256);
		int k = chunkZ + random.nextInt(16) + 8;
		new WorldGenLakes(block).generate(world, random, new BlockPos(i, j, k));
	}
}
