package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import java.util.Random;

public class MCreatorMoistGrayDirt extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:moistgraydirt")
	public static final Block block = null;

	public MCreatorMoistGrayDirt(shadowwolf_mod_one instance) {
		super(instance);
		instance.blocks.add(() -> new BlockCustom());
		instance.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("shadowwolf_mod_one:moistgraydirt",
				"inventory"));
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		if (dimID == MCreatorUno.DIMID)
			dimensionCriteria = true;
		if (dimID == MCreatorDarkDimension.DIMID)
			dimensionCriteria = true;
		if (dimID == MCreatorBenandroniteDimension.DIMID)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return;
		for (int i = 0; i < 25; i++) {
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(155) + 100;
			int z = chunkZ + random.nextInt(16);
			(new WorldGenMinable(block.getDefaultState(), 30, new com.google.common.base.Predicate<IBlockState>() {

				public boolean apply(IBlockState blockAt) {
					boolean blockCriteria = false;
					IBlockState require;
					if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == Blocks.PLANKS.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorUnoBlankCardBlock.block.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorUnoReverseCardOre.block.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorRainbowBlock.block.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorUno1Ore.block.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorUnoBlankCard.block.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorUnoPlusFourOre.block.getDefaultState().getBlock())
						blockCriteria = true;
					return blockCriteria;
				}
			})).generate(world, random, new BlockPos(x, y, z));
		}
	}

	public static class BlockCustom extends Block {

		public BlockCustom() {
			super(Material.GROUND);
			setRegistryName("moistgraydirt");
			setUnlocalizedName("moistgraydirt");
			setSoundType(SoundType.GROUND);
			setHarvestLevel("shovel", 0);
			setHardness(0.5F);
			setResistance(2.5F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(MCreatorCharoriteTools.tab);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.SOLID;
		}

		@Override
		public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
			return false;
		}
	}
}
