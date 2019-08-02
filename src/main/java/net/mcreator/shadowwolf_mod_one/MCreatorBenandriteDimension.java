package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.init.Blocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

import java.util.Random;

public class MCreatorBenandriteDimension extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:benandritedimension")
	public static final BiomeGenCustom biome = null;

	public MCreatorBenandriteDimension(shadowwolf_mod_one instance) {
		super(instance);
		instance.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
	}

	static class BiomeGenCustom extends Biome {

		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("benandritedimension").setRainfall(0.2F).setBaseHeight(0.3F).setHeightVariation(1.5F)
					.setTemperature(0.6F));
			setRegistryName("benandritedimension");
			topBlock = Blocks.BLACK_GLAZED_TERRACOTTA.getDefaultState();
			fillerBlock = Blocks.STAINED_HARDENED_CLAY.getDefaultState();
			decorator.generateFalls = false;
			decorator.treesPerChunk = 20;
			decorator.flowersPerChunk = 5;
			decorator.grassPerChunk = 20;
			decorator.deadBushPerChunk = 1;
			decorator.mushroomsPerChunk = 3;
			decorator.bigMushroomsPerChunk = 2;
			decorator.reedsPerChunk = 2;
			decorator.cactiPerChunk = 5;
			decorator.sandPatchesPerChunk = 10;
			decorator.gravelPatchesPerChunk = 5;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
			this.spawnableCreatureList.add(new SpawnListEntry(MCreatorDimensionGuard.EntityCustom.class, 40, 1, 5));
		}

		@Override
		public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
			return new CustomTree();
		}
	}

	static class CustomTree extends WorldGenAbstractTree {

		public CustomTree() {
			super(false);
		}

		@Override
		public boolean generate(World worldIn, Random rand, BlockPos position) {
			int i = rand.nextInt(3) + 20;
			boolean flag = true;
			if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
				for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
					int k = 1;
					if (j == position.getY()) {
						k = 0;
					}
					if (j >= position.getY() + 1 + i - 2) {
						k = 2;
					}
					BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
					for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
						for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
							if (j >= 0 && j < worldIn.getHeight()) {
								if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
									flag = false;
								}
							} else {
								flag = false;
							}
						}
					}
				}
				if (!flag) {
					return false;
				} else {
					Block ground = worldIn.getBlockState(position.add(0, -1, 0)).getBlock();
					Block ground2 = worldIn.getBlockState(position.add(0, -2, 0)).getBlock();
					if (!((ground == Blocks.BLACK_GLAZED_TERRACOTTA.getDefaultState().getBlock() || ground == Blocks.STAINED_HARDENED_CLAY
							.getDefaultState().getBlock()) && (ground2 == Blocks.BLACK_GLAZED_TERRACOTTA.getDefaultState().getBlock() || ground2 == Blocks.STAINED_HARDENED_CLAY
							.getDefaultState().getBlock())))
						return false;
					IBlockState state = worldIn.getBlockState(position.down());
					if (position.getY() < worldIn.getHeight() - i - 1) {
						state.getBlock().onPlantGrow(state, worldIn, position.down(), position);
						int k2 = 3;
						int l2 = 0;
						for (int i3 = position.getY() - 3 + i; i3 <= position.getY() + i; ++i3) {
							int i4 = i3 - (position.getY() + i);
							int j1 = 1 - i4 / 2;
							for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
								int l1 = k1 - position.getX();
								for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
									int j2 = i2 - position.getZ();
									if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
										BlockPos blockpos = new BlockPos(k1, i3, i2);
										state = worldIn.getBlockState(blockpos);
										if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos)
												|| state.getBlock() == Blocks.VINE.getDefaultState().getBlock()
												|| state.getBlock() == MCreatorCharorite.block.getDefaultState().getBlock()) {
											this.setBlockAndNotifyAdequately(worldIn, blockpos, MCreatorCharorite.block.getDefaultState());
										}
									}
								}
							}
						}
						for (int j3 = 0; j3 < i; ++j3) {
							BlockPos upN = position.up(j3);
							state = worldIn.getBlockState(upN);
							if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN)
									|| state.getBlock() == Blocks.VINE.getDefaultState().getBlock()
									|| state.getBlock() == MCreatorCharorite.block.getDefaultState().getBlock()) {
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3), MCreatorBenandrite.block.getDefaultState());
							}
						}
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}

		private void placeFruits(World worldIn, int p_181652_2_, BlockPos pos, EnumFacing side) {
			this.setBlockAndNotifyAdequately(worldIn, pos, MCreatorInsanityOre.block.getDefaultState());
		}

		private void addVine(World worldIn, BlockPos pos) {
			this.setBlockAndNotifyAdequately(worldIn, pos, Blocks.VINE.getDefaultState());
		}

		private void addHangingVine(World worldIn, BlockPos pos) {
			this.addVine(worldIn, pos);
			int i = 4;
			for (BlockPos blockpos = pos.down(); worldIn.isAirBlock(blockpos) && i > 0; --i) {
				this.addVine(worldIn, blockpos);
				blockpos = blockpos.down();
			}
		}

		@Override
		protected boolean canGrowInto(Block blockType) {
			Material material = blockType.getDefaultState().getMaterial();
			return material == Material.AIR || blockType == MCreatorBenandrite.block.getDefaultState().getBlock()
					|| blockType == MCreatorCharorite.block.getDefaultState().getBlock()
					|| blockType == Blocks.BLACK_GLAZED_TERRACOTTA.getDefaultState().getBlock()
					|| blockType == Blocks.STAINED_HARDENED_CLAY.getDefaultState().getBlock();
		}

		@Override
		protected void setDirtAt(World worldIn, BlockPos pos) {
			if (worldIn.getBlockState(pos).getBlock() != Blocks.STAINED_HARDENED_CLAY.getDefaultState().getBlock())
				this.setBlockAndNotifyAdequately(worldIn, pos, Blocks.STAINED_HARDENED_CLAY.getDefaultState());
		}

		@Override
		public boolean isReplaceable(World world, BlockPos pos) {
			net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
			return state.getBlock().isAir(state, world, pos) || canGrowInto(state.getBlock());
		}

		@Override
		public void generateSaplings(World worldIn, Random random, BlockPos pos) {
		}
	}
}
