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
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

import java.util.Random;

public class MCreatorWoodAndOreDimension extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:woodandoredimension")
	public static final BiomeGenCustom biome = null;

	public MCreatorWoodAndOreDimension(shadowwolf_mod_one instance) {
		super(instance);
		instance.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
	}

	static class BiomeGenCustom extends Biome {

		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("woodandoredimension").setRainfall(0.6F).setBaseHeight(0.18F).setHeightVariation(0.4F)
					.setTemperature(0.53F));
			setRegistryName("woodandoredimension");
			topBlock = Blocks.PLANKS.getDefaultState();
			fillerBlock = MCreatorCharorite.block.getDefaultState();
			decorator.generateFalls = false;
			decorator.treesPerChunk = 3;
			decorator.flowersPerChunk = 10;
			decorator.grassPerChunk = 10;
			decorator.deadBushPerChunk = 6;
			decorator.mushroomsPerChunk = 3;
			decorator.bigMushroomsPerChunk = 9;
			decorator.reedsPerChunk = 3;
			decorator.cactiPerChunk = 5;
			decorator.sandPatchesPerChunk = 2;
			decorator.gravelPatchesPerChunk = 2;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
			this.spawnableCreatureList.add(new SpawnListEntry(MCreatorDimensionGuard.EntityCustom.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityBlaze.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntitySlime.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityEnderman.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityVillager.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 40, 1, 5));
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
			int i = rand.nextInt(3) + 30;
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
					if (!((ground == Blocks.PLANKS.getDefaultState().getBlock() || ground == MCreatorCharorite.block.getDefaultState().getBlock()) && (ground2 == Blocks.PLANKS
							.getDefaultState().getBlock() || ground2 == MCreatorCharorite.block.getDefaultState().getBlock())))
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
												|| state.getBlock() == Blocks.RED_MUSHROOM_BLOCK.getDefaultState().getBlock()) {
											this.setBlockAndNotifyAdequately(worldIn, blockpos, Blocks.RED_MUSHROOM_BLOCK.getDefaultState());
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
									|| state.getBlock() == Blocks.RED_MUSHROOM_BLOCK.getDefaultState().getBlock()) {
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3), Blocks.SLIME_BLOCK.getDefaultState());
								if (j3 > 0) {
									if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(-1, j3, 0))) {
										this.addVine(worldIn, position.add(-1, j3, 0));
									}
									if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(1, j3, 0))) {
										this.addVine(worldIn, position.add(1, j3, 0));
									}
									if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(0, j3, -1))) {
										this.addVine(worldIn, position.add(0, j3, -1));
									}
									if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(0, j3, 1))) {
										this.addVine(worldIn, position.add(0, j3, 1));
									}
								}
							}
						}
						for (int k3 = position.getY() - 3 + i; k3 <= position.getY() + i; ++k3) {
							int j4 = k3 - (position.getY() + i);
							int k4 = 2 - j4 / 2;
							BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();
							for (int l4 = position.getX() - k4; l4 <= position.getX() + k4; ++l4) {
								for (int i5 = position.getZ() - k4; i5 <= position.getZ() + k4; ++i5) {
									blockpos$mutableblockpos1.setPos(l4, k3, i5);
									state = worldIn.getBlockState(blockpos$mutableblockpos1);
									if (state.getBlock().isLeaves(state, worldIn, blockpos$mutableblockpos1)
											|| state.getBlock() == Blocks.RED_MUSHROOM_BLOCK.getDefaultState().getBlock()) {
										BlockPos blockpos2 = blockpos$mutableblockpos1.west();
										BlockPos blockpos3 = blockpos$mutableblockpos1.east();
										BlockPos blockpos4 = blockpos$mutableblockpos1.north();
										BlockPos blockpos1 = blockpos$mutableblockpos1.south();
										if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos2))
											this.addHangingVine(worldIn, blockpos2);
										if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos3))
											this.addHangingVine(worldIn, blockpos3);
										if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos4))
											this.addHangingVine(worldIn, blockpos4);
										if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos1))
											this.addHangingVine(worldIn, blockpos1);
									}
								}
							}
						}
						if (rand.nextInt(5) == 0 && i > 5) {
							for (int l3 = 0; l3 < 2; ++l3) {
								for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
									if (rand.nextInt(4 - l3) == 0) {
										EnumFacing enumfacing1 = enumfacing.getOpposite();
										this.placeFruits(worldIn, rand.nextInt(3),
												position.add(enumfacing1.getFrontOffsetX(), i - 5 + l3, enumfacing1.getFrontOffsetZ()), enumfacing);
									}
								}
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
			this.setBlockAndNotifyAdequately(worldIn, pos, Blocks.COCOA.getDefaultState());
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
			return material == Material.AIR || blockType == Blocks.SLIME_BLOCK.getDefaultState().getBlock()
					|| blockType == Blocks.RED_MUSHROOM_BLOCK.getDefaultState().getBlock() || blockType == Blocks.PLANKS.getDefaultState().getBlock()
					|| blockType == MCreatorCharorite.block.getDefaultState().getBlock();
		}

		@Override
		protected void setDirtAt(World worldIn, BlockPos pos) {
			if (worldIn.getBlockState(pos).getBlock() != MCreatorCharorite.block.getDefaultState().getBlock())
				this.setBlockAndNotifyAdequately(worldIn, pos, MCreatorCharorite.block.getDefaultState());
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
