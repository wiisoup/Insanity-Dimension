package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.BiomeManager;
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

public class MCreatorUnoPlusFourBiome extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:unoplusfourbiome")
	public static final BiomeGenCustom biome = null;

	public MCreatorUnoPlusFourBiome(shadowwolf_mod_one instance) {
		super(instance);
		instance.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(biome, 10));
	}

	static class BiomeGenCustom extends Biome {

		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("unoplusfourbiome").setRainfall(0F).setBaseHeight(1F).setWaterColor(-16711936).setHeightVariation(0.2F)
					.setTemperature(0.1F));
			setRegistryName("unoplusfourbiome");
			topBlock = MCreatorUnoPlusFourOre.block.getDefaultState();
			fillerBlock = MCreatorGoo.block.getDefaultState();
			decorator.generateFalls = false;
			decorator.treesPerChunk = 5;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.deadBushPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getGrassColorAtPos(BlockPos pos) {
			return -16776961;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getFoliageColorAtPos(BlockPos pos) {
			return -16776961;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getSkyColorByTemp(float currentTemperature) {
			return -65485;
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
			int i = rand.nextInt(3) + 2;
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
					if (!((ground == MCreatorUnoPlusFourOre.block.getDefaultState().getBlock() || ground == MCreatorGoo.block.getDefaultState()
							.getBlock()) && (ground2 == MCreatorUnoPlusFourOre.block.getDefaultState().getBlock() || ground2 == MCreatorGoo.block
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
												|| state.getBlock() == Blocks.SPONGE.getDefaultState().getBlock()
												|| state.getBlock() == MCreatorUnoReverseCardOre.block.getDefaultState().getBlock()) {
											this.setBlockAndNotifyAdequately(worldIn, blockpos, MCreatorUnoReverseCardOre.block.getDefaultState());
										}
									}
								}
							}
						}
						for (int j3 = 0; j3 < i; ++j3) {
							BlockPos upN = position.up(j3);
							state = worldIn.getBlockState(upN);
							if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN)
									|| state.getBlock() == Blocks.SPONGE.getDefaultState().getBlock()
									|| state.getBlock() == MCreatorUnoReverseCardOre.block.getDefaultState().getBlock()) {
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3), MCreatorUnoPlusFourOre.block.getDefaultState());
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
			this.setBlockAndNotifyAdequately(worldIn, pos, MCreatorUnoBlankCard.block.getDefaultState());
		}

		private void addVine(World worldIn, BlockPos pos) {
			this.setBlockAndNotifyAdequately(worldIn, pos, Blocks.SPONGE.getDefaultState());
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
			return material == Material.AIR || blockType == MCreatorUnoPlusFourOre.block.getDefaultState().getBlock()
					|| blockType == MCreatorUnoReverseCardOre.block.getDefaultState().getBlock()
					|| blockType == MCreatorUnoPlusFourOre.block.getDefaultState().getBlock()
					|| blockType == MCreatorGoo.block.getDefaultState().getBlock();
		}

		@Override
		protected void setDirtAt(World worldIn, BlockPos pos) {
			if (worldIn.getBlockState(pos).getBlock() != MCreatorGoo.block.getDefaultState().getBlock())
				this.setBlockAndNotifyAdequately(worldIn, pos, MCreatorGoo.block.getDefaultState());
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
