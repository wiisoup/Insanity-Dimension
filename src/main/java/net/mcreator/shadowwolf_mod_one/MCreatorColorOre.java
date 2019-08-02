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
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.NonNullList;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import java.util.Random;

public class MCreatorColorOre extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:colorore")
	public static final Block block = null;

	public MCreatorColorOre(shadowwolf_mod_one instance) {
		super(instance);
		instance.blocks.add(() -> new BlockCustom());
		instance.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("shadowwolf_mod_one:colorore",
				"inventory"));
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		if (dimID == MCreatorUno.DIMID)
			dimensionCriteria = true;
		if (dimID == MCreatorOverworldEnd.DIMID)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return;
		for (int i = 0; i < 7; i++) {
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(118) + 10;
			int z = chunkZ + random.nextInt(16);
			(new WorldGenMinable(block.getDefaultState(), 13, new com.google.common.base.Predicate<IBlockState>() {

				public boolean apply(IBlockState blockAt) {
					boolean blockCriteria = false;
					IBlockState require;
					if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorUnoBlankCardBlock.block.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorSBenandroniteBlock.block.getDefaultState().getBlock())
						blockCriteria = true;
					if (blockAt.getBlock() == MCreatorSBenandroniteDirt.block.getDefaultState().getBlock())
						blockCriteria = true;
					return blockCriteria;
				}
			})).generate(world, random, new BlockPos(x, y, z));
		}
	}

	public static class BlockCustom extends Block {

		public BlockCustom() {
			super(Material.ROCK);
			setRegistryName("colorore");
			setUnlocalizedName("colorore");
			setSoundType(SoundType.STONE);
			setHarvestLevel("pickaxe", 4);
			setHardness(2.25F);
			setResistance(12.5F);
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
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(MCreatorColorGem.block, (int) (1)));
		}
	}
}
