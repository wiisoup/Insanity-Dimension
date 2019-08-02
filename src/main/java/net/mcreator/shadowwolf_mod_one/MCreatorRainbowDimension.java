package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.WorldType;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.World;
import net.minecraft.world.Teleporter;
import net.minecraft.world.DimensionType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ReportedException;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.init.Biomes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.Entity;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.CrashReport;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.List;
import java.util.HashMap;

import com.google.common.cache.LoadingCache;

public class MCreatorRainbowDimension extends shadowwolf_mod_one.ModElement {

	public static int DIMID = 5;
	public static final boolean NETHER_TYPE = false;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:rainbowdimension")
	public static final Item block = null;
	@GameRegistry.ObjectHolder("shadowwolf_mod_one:rainbowdimension_portal")
	public static final BlockCustomPortal portal = null;
	public static DimensionType dtype;

	public MCreatorRainbowDimension(shadowwolf_mod_one instance) {
		super(instance);
		instance.blocks.add(() -> new BlockCustomPortal());
		instance.items.add(() -> new ItemBlock(portal).setRegistryName(portal.getRegistryName()));
		instance.items.add(() -> new ModTrigger().setUnlocalizedName("rainbowdimension").setRegistryName("rainbowdimension"));
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if (DimensionManager.isDimensionRegistered(DIMID)) {
			DIMID = DimensionManager.getNextFreeDimId();
			System.err.println("Dimension ID for dimension rainbowdimension is already registered. Falling back to ID: " + DIMID);
		}
		dtype = DimensionType.register("rainbowdimension", "_rainbowdimension", DIMID, WorldProviderMod.class, true);
		DimensionManager.registerDimension(DIMID, dtype);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("shadowwolf_mod_one:rainbowdimension", "inventory"));
	}

	public static class WorldProviderMod extends WorldProvider {

		@Override
		public void init() {
			this.biomeProvider = new BiomeProviderCustom(this.world.getSeed());
			this.nether = NETHER_TYPE;
		}

		@Override
		public DimensionType getDimensionType() {
			return dtype;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Vec3d getFogColor(float par1, float par2) {
			return new Vec3d(1, 1, 1);
		}

		@Override
		public IChunkGenerator createChunkGenerator() {
			return new ChunkProviderModded(this.world, this.world.getSeed() - DIMID);
		}

		@Override
		public boolean isSurfaceWorld() {
			return false;
		}

		@Override
		public boolean canRespawnHere() {
			return false;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public boolean doesXZShowFog(int par1, int par2) {
			return false;
		}

		@Override
		protected void generateLightBrightnessTable() {
			float f = 0.5F;
			for (int i = 0; i <= 15; ++i) {
				float f1 = 1.0F - (float) i / 15.0F;
				this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
			}
		}

		@Override
		public void onPlayerAdded(EntityPlayerMP entity) {
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				MCreatorRainbowDimensionAchievement1.executeProcedure($_dependencies);
			}
		}
	}

	public static class TeleporterDimensionMod extends Teleporter {

		private Vec3d lastPortalVec;
		private EnumFacing teleportDirection;

		public TeleporterDimensionMod(WorldServer worldServer, Vec3d lastPortalVec, EnumFacing teleportDirection) {
			super(worldServer);
			this.lastPortalVec = lastPortalVec;
			this.teleportDirection = teleportDirection;
		}

		@Override
		public void placeInPortal(Entity entityIn, float rotationYaw) {
			if (this.world.provider.getDimensionType().getId() != 1) {
				if (!this.placeInExistingPortal(entityIn, rotationYaw)) {
					this.makePortal(entityIn);
					this.placeInExistingPortal(entityIn, rotationYaw);
				}
			} else {
				int i = MathHelper.floor(entityIn.posX);
				int j = MathHelper.floor(entityIn.posY) - 1;
				int k = MathHelper.floor(entityIn.posZ);
				int l = 1;
				int i1 = 0;
				for (int j1 = -2; j1 <= 2; ++j1) {
					for (int k1 = -2; k1 <= 2; ++k1) {
						for (int l1 = -1; l1 < 3; ++l1) {
							int i2 = i + k1;
							int j2 = j + l1;
							int k2 = k - j1;
							boolean flag = l1 < 0;
							this.world.setBlockState(new BlockPos(i2, j2, k2),
									flag ? MCreatorRainbowBlock.block.getDefaultState() : Blocks.AIR.getDefaultState());
						}
					}
				}
				entityIn.setLocationAndAngles((double) i, (double) j, (double) k, entityIn.rotationYaw, 0.0F);
				entityIn.motionX = 0.0D;
				entityIn.motionY = 0.0D;
				entityIn.motionZ = 0.0D;
			}
		}

		@Override
		public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
			int i = 128;
			double d0 = -1.0D;
			int j = MathHelper.floor(entityIn.posX);
			int k = MathHelper.floor(entityIn.posZ);
			boolean flag = true;
			BlockPos blockpos = BlockPos.ORIGIN;
			long l = ChunkPos.asLong(j, k);
			if (this.destinationCoordinateCache.containsKey(l)) {
				Teleporter.PortalPosition teleporter$portalposition = (Teleporter.PortalPosition) this.destinationCoordinateCache.get(l);
				d0 = 0.0D;
				blockpos = teleporter$portalposition;
				teleporter$portalposition.lastUpdateTime = this.world.getTotalWorldTime();
				flag = false;
			} else {
				BlockPos blockpos3 = new BlockPos(entityIn);
				for (int i1 = -128; i1 <= 128; ++i1) {
					BlockPos blockpos2;
					for (int j1 = -128; j1 <= 128; ++j1) {
						for (BlockPos blockpos1 = blockpos3.add(i1, this.world.getActualHeight() - 1 - blockpos3.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
							blockpos2 = blockpos1.down();
							if (this.world.getBlockState(blockpos1).getBlock() == portal) {
								for (blockpos2 = blockpos1.down(); this.world.getBlockState(blockpos2).getBlock() == portal; blockpos2 = blockpos2
										.down()) {
									blockpos1 = blockpos2;
								}
								double d1 = blockpos1.distanceSq(blockpos3);
								if (d0 < 0.0D || d1 < d0) {
									d0 = d1;
									blockpos = blockpos1;
								}
							}
						}
					}
				}
			}
			if (d0 >= 0.0D) {
				if (flag) {
					this.destinationCoordinateCache.put(l, new Teleporter.PortalPosition(blockpos, this.world.getTotalWorldTime()));
				}
				double d5 = (double) blockpos.getX() + 0.5D;
				double d7 = (double) blockpos.getZ() + 0.5D;
				BlockPattern.PatternHelper blockpattern$patternhelper = portal.createPatternHelper(this.world, blockpos);
				boolean flag1 = blockpattern$patternhelper.getForwards().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE;
				double d2 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? (double) blockpattern$patternhelper
						.getFrontTopLeft().getZ() : (double) blockpattern$patternhelper.getFrontTopLeft().getX();
				double d6 = (double) (blockpattern$patternhelper.getFrontTopLeft().getY() + 1) - lastPortalVec.y
						* (double) blockpattern$patternhelper.getHeight();
				if (flag1) {
					++d2;
				}
				if (blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X) {
					d7 = d2 + (1.0D - lastPortalVec.x) * blockpattern$patternhelper.getWidth()
							* blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
				} else {
					d5 = d2 + (1.0D - lastPortalVec.x) * blockpattern$patternhelper.getWidth()
							* blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
				}
				float f = 0.0F;
				float f1 = 0.0F;
				float f2 = 0.0F;
				float f3 = 0.0F;
				if (blockpattern$patternhelper.getForwards().getOpposite() == teleportDirection) {
					f = 1.0F;
					f1 = 1.0F;
				} else if (blockpattern$patternhelper.getForwards().getOpposite() == teleportDirection.getOpposite()) {
					f = -1.0F;
					f1 = -1.0F;
				} else if (blockpattern$patternhelper.getForwards().getOpposite() == teleportDirection.rotateY()) {
					f2 = 1.0F;
					f3 = -1.0F;
				} else {
					f2 = -1.0F;
					f3 = 1.0F;
				}
				double d3 = entityIn.motionX;
				double d4 = entityIn.motionZ;
				entityIn.motionX = d3 * (double) f + d4 * (double) f3;
				entityIn.motionZ = d3 * (double) f2 + d4 * (double) f1;
				entityIn.rotationYaw = rotationYaw - (float) (teleportDirection.getOpposite().getHorizontalIndex() * 90)
						+ (float) (blockpattern$patternhelper.getForwards().getHorizontalIndex() * 90);
				if (entityIn instanceof EntityPlayerMP) {
					((EntityPlayerMP) entityIn).connection.setPlayerLocation(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
				} else {
					entityIn.setLocationAndAngles(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
				}
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean makePortal(Entity entityIn) {
			int i = 16;
			double d0 = -1.0D;
			int j = MathHelper.floor(entityIn.posX);
			int k = MathHelper.floor(entityIn.posY);
			int l = MathHelper.floor(entityIn.posZ);
			int i1 = j;
			int j1 = k;
			int k1 = l;
			int l1 = 0;
			int i2 = this.random.nextInt(4);
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			for (int j2 = j - 16; j2 <= j + 16; ++j2) {
				double d1 = (double) j2 + 0.5D - entityIn.posX;
				for (int l2 = l - 16; l2 <= l + 16; ++l2) {
					double d2 = (double) l2 + 0.5D - entityIn.posZ;
					label293 : for (int j3 = this.world.getActualHeight() - 1; j3 >= 0; --j3) {
						if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3, l2))) {
							while (j3 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3 - 1, l2))) {
								--j3;
							}
							for (int k3 = i2; k3 < i2 + 4; ++k3) {
								int l3 = k3 % 2;
								int i4 = 1 - l3;
								if (k3 % 4 >= 2) {
									l3 = -l3;
									i4 = -i4;
								}
								for (int j4 = 0; j4 < 3; ++j4) {
									for (int k4 = 0; k4 < 4; ++k4) {
										for (int l4 = -1; l4 < 4; ++l4) {
											int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
											int j5 = j3 + l4;
											int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
											blockpos$mutableblockpos.setPos(i5, j5, k5);
											if (l4 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || l4 >= 0
													&& !this.world.isAirBlock(blockpos$mutableblockpos)) {
												continue label293;
											}
										}
									}
								}
								double d5 = (double) j3 + 0.5D - entityIn.posY;
								double d7 = d1 * d1 + d5 * d5 + d2 * d2;
								if (d0 < 0.0D || d7 < d0) {
									d0 = d7;
									i1 = j2;
									j1 = j3;
									k1 = l2;
									l1 = k3 % 4;
								}
							}
						}
					}
				}
			}
			if (d0 < 0.0D) {
				for (int l5 = j - 16; l5 <= j + 16; ++l5) {
					double d3 = (double) l5 + 0.5D - entityIn.posX;
					for (int j6 = l - 16; j6 <= l + 16; ++j6) {
						double d4 = (double) j6 + 0.5D - entityIn.posZ;
						label231 : for (int i7 = this.world.getActualHeight() - 1; i7 >= 0; --i7) {
							if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7, j6))) {
								while (i7 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7 - 1, j6))) {
									--i7;
								}
								for (int k7 = i2; k7 < i2 + 2; ++k7) {
									int j8 = k7 % 2;
									int j9 = 1 - j8;
									for (int j10 = 0; j10 < 4; ++j10) {
										for (int j11 = -1; j11 < 4; ++j11) {
											int j12 = l5 + (j10 - 1) * j8;
											int i13 = i7 + j11;
											int j13 = j6 + (j10 - 1) * j9;
											blockpos$mutableblockpos.setPos(j12, i13, j13);
											if (j11 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || j11 >= 0
													&& !this.world.isAirBlock(blockpos$mutableblockpos)) {
												continue label231;
											}
										}
									}
									double d6 = (double) i7 + 0.5D - entityIn.posY;
									double d8 = d3 * d3 + d6 * d6 + d4 * d4;
									if (d0 < 0.0D || d8 < d0) {
										d0 = d8;
										i1 = l5;
										j1 = i7;
										k1 = j6;
										l1 = k7 % 2;
									}
								}
							}
						}
					}
				}
			}
			int i6 = i1;
			int k2 = j1;
			int k6 = k1;
			int l6 = l1 % 2;
			int i3 = 1 - l6;
			if (l1 % 4 >= 2) {
				l6 = -l6;
				i3 = -i3;
			}
			if (d0 < 0.0D) {
				j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
				k2 = j1;
				for (int j7 = -1; j7 <= 1; ++j7) {
					for (int l7 = 1; l7 < 3; ++l7) {
						for (int k8 = -1; k8 < 3; ++k8) {
							this.world.setBlockState(new BlockPos(i6 + (l7 - 1) * l6 + j7 * i3, k2 + k8, k6 + (l7 - 1) * i3 - j7 * l6), (k8 < 0)
									? MCreatorRainbowBlock.block.getDefaultState()
									: Blocks.AIR.getDefaultState());
						}
					}
				}
			}
			IBlockState iblockstate = portal.getDefaultState().withProperty(BlockPortal.AXIS, l6 == 0 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
			for (int i8 = 0; i8 < 4; ++i8) {
				for (int l8 = 0; l8 < 4; ++l8) {
					for (int l9 = -1; l9 < 4; ++l9) {
						this.world.setBlockState(new BlockPos(i6 + (l8 - 1) * l6, k2 + l9, k6 + (l8 - 1) * i3),
								(l8 == 0 || l8 == 3 || l9 == -1 || l9 == 3) ? MCreatorRainbowBlock.block.getDefaultState() : iblockstate, 2);
					}
				}
				for (int i9 = 0; i9 < 4; ++i9) {
					for (int i10 = -1; i10 < 4; ++i10) {
						BlockPos blockpos = new BlockPos(i6 + (i9 - 1) * l6, k2 + i10, k6 + (i9 - 1) * i3);
						this.world.notifyNeighborsOfStateChange(blockpos, this.world.getBlockState(blockpos).getBlock(), false);
					}
				}
			}
			return true;
		}
	}

	public static class BlockCustomPortal extends BlockPortal {

		public BlockCustomPortal() {
			setHardness(-1.0F);
			setUnlocalizedName("rainbowdimension_portal");
			setRegistryName("rainbowdimension_portal");
			setLightLevel(0F);
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		}

		@Override
		public boolean trySpawnPortal(World worldIn, BlockPos pos) {
			BlockCustomPortal.Size blockportal$size = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.X);
			if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
				blockportal$size.placePortalBlocks();
				return true;
			} else {
				BlockCustomPortal.Size blockportal$size1 = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
				if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0) {
					blockportal$size1.placePortalBlocks();
					return true;
				} else {
					return false;
				}
			}
		}

		@Override
		public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
			EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis) state.getValue(AXIS);
			if (enumfacing$axis == EnumFacing.Axis.X) {
				BlockCustomPortal.Size blockportal$size = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.X);
				if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
					portalDestroyed(worldIn, pos);
				}
			} else if (enumfacing$axis == EnumFacing.Axis.Z) {
				BlockCustomPortal.Size blockportal$size1 = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
				if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height) {
					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
					portalDestroyed(worldIn, pos);
				}
			}
		}

		private void portalDestroyed(World world, BlockPos pos) {
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
			if (rand.nextInt(100) == 0)
				world.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D,
						(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation(("weather.rain"))),
						SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
			for (int i = 0; i < 4; ++i) {
				double d0 = (double) ((float) pos.getX() + rand.nextFloat());
				double d1 = (double) ((float) pos.getY() + rand.nextFloat());
				double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
				double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
				double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
				double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
				int j = rand.nextInt(2) * 2 - 1;
				if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
					d0 = pos.getX() + 0.5D + 0.25D * (double) j;
					d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
				} else {
					d2 = pos.getZ() + 0.5D + 0.25D * (double) j;
					d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
				}
				world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d0, d1, d2, d3, d4, d5);
			}
		}

		public BlockPattern.PatternHelper createPatternHelper(World worldIn, BlockPos pos) {
			EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
			BlockCustomPortal.Size blockportal$size = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.X);
			LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
			if (!blockportal$size.isValid()) {
				enumfacing$axis = EnumFacing.Axis.X;
				blockportal$size = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
			}
			if (!blockportal$size.isValid()) {
				return new BlockPattern.PatternHelper(pos, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
			} else {
				int[] aint = new int[EnumFacing.AxisDirection.values().length];
				EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
				BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);
				for (EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values()) {
					BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(
							enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir,
									blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis),
							EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
					for (int i = 0; i < blockportal$size.getWidth(); ++i) {
						for (int j = 0; j < blockportal$size.getHeight(); ++j) {
							BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);
							if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR) {
								++aint[enumfacing$axisdirection.ordinal()];
							}
						}
					}
				}
				EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;
				for (EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values()) {
					if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
						enumfacing$axisdirection1 = enumfacing$axisdirection2;
					}
				}
				return new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(
						blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection1,
						enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
			}
		}

		public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
			if (!worldIn.isRemote && !entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn instanceof EntityPlayerMP) {
				EntityPlayerMP thePlayer = (EntityPlayerMP) entityIn;
				if (thePlayer.timeUntilPortal > 0) {
					thePlayer.timeUntilPortal = 10;
				} else if (thePlayer.dimension != DIMID) {
					thePlayer.timeUntilPortal = 10;
					ReflectionHelper.setPrivateValue(EntityPlayerMP.class, thePlayer, true, "invulnerableDimensionChange", "field_184851_cj");
					thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, DIMID, getTeleporterForDimension(thePlayer, pos, DIMID));
				} else {
					thePlayer.timeUntilPortal = 10;
					ReflectionHelper.setPrivateValue(EntityPlayerMP.class, thePlayer, true, "invulnerableDimensionChange", "field_184851_cj");
					thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, 0, getTeleporterForDimension(thePlayer, pos, 0));
				}
			}
		}

		private TeleporterDimensionMod getTeleporterForDimension(Entity entity, BlockPos pos, int dimid) {
			BlockPattern.PatternHelper blockpattern$patternhelper = portal.createPatternHelper(entity.world, new BlockPos(pos));
			double d0 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? (double) blockpattern$patternhelper
					.getFrontTopLeft().getZ() : (double) blockpattern$patternhelper.getFrontTopLeft().getX();
			double d1 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? entity.posZ : entity.posX;
			d1 = Math.abs(MathHelper.pct(d1
					- (double) (blockpattern$patternhelper.getForwards().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0),
					d0, d0 - (double) blockpattern$patternhelper.getWidth()));
			double d2 = MathHelper.pct(entity.posY - 1.0D, (double) blockpattern$patternhelper.getFrontTopLeft().getY(),
					(double) (blockpattern$patternhelper.getFrontTopLeft().getY() - blockpattern$patternhelper.getHeight()));
			return new TeleporterDimensionMod(entity.getServer().getWorld(dimid), new Vec3d(d1, d2, 0.0D), blockpattern$patternhelper.getForwards());
		}

		public static class Size {

			private final World world;
			private final EnumFacing.Axis axis;
			private final EnumFacing rightDir;
			private final EnumFacing leftDir;
			private int portalBlockCount;
			private BlockPos bottomLeft;
			private int height;
			private int width;

			public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
				this.world = worldIn;
				this.axis = p_i45694_3_;
				if (p_i45694_3_ == EnumFacing.Axis.X) {
					this.leftDir = EnumFacing.EAST;
					this.rightDir = EnumFacing.WEST;
				} else {
					this.leftDir = EnumFacing.NORTH;
					this.rightDir = EnumFacing.SOUTH;
				}
				for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0
						&& this.isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down()) {
					;
				}
				int i = this.getDistanceUntilEdge(p_i45694_2_, this.leftDir) - 1;
				if (i >= 0) {
					this.bottomLeft = p_i45694_2_.offset(this.leftDir, i);
					this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
					if (this.width < 2 || this.width > 21) {
						this.bottomLeft = null;
						this.width = 0;
					}
				}
				if (this.bottomLeft != null) {
					this.height = this.calculatePortalHeight();
				}
			}

			protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
				int i;
				for (i = 0; i < 22; ++i) {
					BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
					if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock())
							|| this.world.getBlockState(blockpos.down()).getBlock() != MCreatorRainbowBlock.block.getDefaultState().getBlock()) {
						break;
					}
				}
				Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
				return block == MCreatorRainbowBlock.block.getDefaultState().getBlock() ? i : 0;
			}

			public int getHeight() {
				return this.height;
			}

			public int getWidth() {
				return this.width;
			}

			protected int calculatePortalHeight() {
				label56 : for (this.height = 0; this.height < 21; ++this.height) {
					for (int i = 0; i < this.width; ++i) {
						BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
						Block block = this.world.getBlockState(blockpos).getBlock();
						if (!this.isEmptyBlock(block)) {
							break label56;
						}
						if (block == portal) {
							++this.portalBlockCount;
						}
						if (i == 0) {
							block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
							if (block != MCreatorRainbowBlock.block.getDefaultState().getBlock()) {
								break label56;
							}
						} else if (i == this.width - 1) {
							block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
							if (block != MCreatorRainbowBlock.block.getDefaultState().getBlock()) {
								break label56;
							}
						}
					}
				}
				for (int j = 0; j < this.width; ++j) {
					if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != MCreatorRainbowBlock.block
							.getDefaultState().getBlock()) {
						this.height = 0;
						break;
					}
				}
				if (this.height <= 21 && this.height >= 3) {
					return this.height;
				} else {
					this.bottomLeft = null;
					this.width = 0;
					this.height = 0;
					return 0;
				}
			}

			protected boolean isEmptyBlock(Block blockIn) {
				return blockIn.getDefaultState().getMaterial() == Material.AIR || blockIn == Blocks.FIRE || blockIn == portal;
			}

			public boolean isValid() {
				return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
			}

			public void placePortalBlocks() {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
					for (int j = 0; j < this.height; ++j) {
						this.world.setBlockState(blockpos.up(j), portal.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
					}
				}
			}
		}
	}

	public static class ModTrigger extends Item {

		public ModTrigger() {
			super();
			this.maxStackSize = 1;
			setMaxDamage(64);
			setCreativeTab(MCreatorCharoriteTools.tab);
		}

		@Override
		public EnumActionResult onItemUse(EntityPlayer entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY,
				float hitZ) {
			pos = pos.offset(facing);
			ItemStack itemstack = entity.getHeldItem(hand);
			if (!entity.canPlayerEdit(pos, facing, itemstack)) {
				return EnumActionResult.FAIL;
			} else {
				if (world.isAirBlock(pos)) {
					portal.trySpawnPortal(world, pos);
				}
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					MCreatorEnterAllDimensions3.executeProcedure($_dependencies);
				}
				itemstack.damageItem(1, entity);
				return EnumActionResult.SUCCESS;
			}
		}
	}

	public static class GenLayerFix {

		public static GenLayer[] makeTheWorld(long seed) {
			GenLayer biomes = new GenLayerBiomesCustom(1L);
			biomes = new GenLayerZoom(1000L, biomes);
			biomes = new GenLayerZoom(1001L, biomes);
			biomes = new GenLayerZoom(1002L, biomes);
			biomes = new GenLayerZoom(1003L, biomes);
			biomes = new GenLayerZoom(1004L, biomes);
			biomes = new GenLayerZoom(1005L, biomes);
			GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
			biomes.initWorldGenSeed(seed);
			genlayervoronoizoom.initWorldGenSeed(seed);
			return new GenLayer[]{biomes, genlayervoronoizoom};
		}
	}

	public static class GenLayerBiomesCustom extends GenLayer {

		private Biome[] allowedBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("shadowwolf_mod_one:woodandoredimension")),
				Biome.REGISTRY.getObject(new ResourceLocation("shadowwolf_mod_one:benandritedimension")),
				Biome.REGISTRY.getObject(new ResourceLocation("extreme_hills")), Biome.REGISTRY.getObject(new ResourceLocation("ocean")),
				Biome.REGISTRY.getObject(new ResourceLocation("river")),
				Biome.REGISTRY.getObject(new ResourceLocation("shadowwolf_mod_one:rainbowbiome")),
				Biome.REGISTRY.getObject(new ResourceLocation("jungle")), Biome.REGISTRY.getObject(new ResourceLocation("jungle_hills")),
				Biome.REGISTRY.getObject(new ResourceLocation("shadowwolf_mod_one:unoplusfourbiome")),
				Biome.REGISTRY.getObject(new ResourceLocation("extreme_hills")),};

		public GenLayerBiomesCustom(long seed) {
			super(seed);
		}

		@Override
		public int[] getInts(int x, int z, int width, int depth) {
			int[] dest = IntCache.getIntCache(width * depth);
			for (int dz = 0; dz < depth; dz++) {
				for (int dx = 0; dx < width; dx++) {
					this.initChunkSeed(dx + x, dz + z);
					dest[(dx + dz * width)] = Biome.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
				}
			}
			return dest;
		}
	}

	public static class BiomeProviderCustom extends BiomeProvider {

		private GenLayer genBiomes;
		private GenLayer biomeIndexLayer;
		private BiomeCache biomeCache;

		public BiomeProviderCustom() {
			this.biomeCache = new BiomeCache(this);
		}

		public BiomeProviderCustom(long seed) {
			this();
			GenLayer[] agenlayer = GenLayerFix.makeTheWorld(seed);
			this.genBiomes = agenlayer[0];
			this.biomeIndexLayer = agenlayer[1];
		}

		public BiomeProviderCustom(World world) {
			this(world.getSeed());
		}

		@Override
		public Biome getBiome(BlockPos pos) {
			return this.getBiome(pos, null);
		}

		@Override
		public Biome getBiome(BlockPos pos, Biome defaultBiome) {
			return this.biomeCache.getBiome(pos.getX(), pos.getZ(), defaultBiome);
		}

		@Override
		public Biome[] getBiomesForGeneration(Biome[] par1ArrayOfBiome, int par2, int par3, int par4, int par5) {
			IntCache.resetIntCache();
			if (par1ArrayOfBiome == null || par1ArrayOfBiome.length < par4 * par5) {
				par1ArrayOfBiome = new Biome[par4 * par5];
			}
			int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);
			try {
				for (int i = 0; i < par4 * par5; ++i) {
					par1ArrayOfBiome[i] = Biome.getBiome(aint[i]);
				}
				return par1ArrayOfBiome;
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
				crashreportcategory.addCrashSection("biomes[] size", par1ArrayOfBiome.length);
				crashreportcategory.addCrashSection("x", par2);
				crashreportcategory.addCrashSection("z", par3);
				crashreportcategory.addCrashSection("w", par4);
				crashreportcategory.addCrashSection("h", par5);
				throw new ReportedException(crashreport);
			}
		}

		@Override
		public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int depth) {
			return this.getBiomes(oldBiomeList, x, z, width, depth, true);
		}

		@Override
		public Biome[] getBiomes(Biome[] listToReuse, int x, int y, int width, int length, boolean cacheFlag) {
			IntCache.resetIntCache();
			if (listToReuse == null || listToReuse.length < width * length) {
				listToReuse = new Biome[width * length];
			}
			if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (y & 15) == 0) {
				Biome[] aBiome1 = this.biomeCache.getCachedBiomes(x, y);
				System.arraycopy(aBiome1, 0, listToReuse, 0, width * length);
				return listToReuse;
			} else {
				int[] aint = this.biomeIndexLayer.getInts(x, y, width, length);
				for (int i = 0; i < width * length; ++i) {
					listToReuse[i] = Biome.getBiome(aint[i]);
				}
				return listToReuse;
			}
		}

		@Override
		public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
			IntCache.resetIntCache();
			int i = x - radius >> 2;
			int j = z - radius >> 2;
			int k = x + radius >> 2;
			int l = z + radius >> 2;
			int i1 = k - i + 1;
			int j1 = l - j + 1;
			int[] aint = this.genBiomes.getInts(i, j, i1, j1);
			try {
				for (int k1 = 0; k1 < i1 * j1; ++k1) {
					Biome biome = Biome.getBiome(aint[k1]);
					if (!allowed.contains(biome)) {
						return false;
					}
				}
				return true;
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
				crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
				crashreportcategory.addCrashSection("x", x);
				crashreportcategory.addCrashSection("z", z);
				crashreportcategory.addCrashSection("radius", radius);
				crashreportcategory.addCrashSection("allowed", allowed);
				throw new ReportedException(crashreport);
			}
		}

		@Override
		@SuppressWarnings("rawtypes")
		public BlockPos findBiomePosition(int x, int z, int range, List biomes, Random random) {
			IntCache.resetIntCache();
			int l = x - range >> 2;
			int i1 = z - range >> 2;
			int j1 = x + range >> 2;
			int k1 = z + range >> 2;
			int l1 = j1 - l + 1;
			int i2 = k1 - i1 + 1;
			int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
			BlockPos blockpos = null;
			int j2 = 0;
			for (int k2 = 0; k2 < l1 * i2; ++k2) {
				int l2 = l + k2 % l1 << 2;
				int i3 = i1 + k2 / l1 << 2;
				Biome biome = Biome.getBiome(aint[k2]);
				if (biomes.contains(biome) && (blockpos == null || random.nextInt(j2 + 1) == 0)) {
					blockpos = new BlockPos(l2, 0, i3);
					++j2;
				}
			}
			return blockpos;
		}

		@Override
		public void cleanupCache() {
			this.biomeCache.cleanupCache();
		}
	}

	public static class ChunkProviderModded implements IChunkGenerator {

		protected static final IBlockState STONE = MCreatorRainbowBlock.block.getDefaultState();
		protected static final IBlockState GRAVEL = MCreatorRainbowBlock.block.getDefaultState();
		protected static final IBlockState WATER = Blocks.LAVA.getDefaultState();
		protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
		protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
		private final Random rand;
		private NoiseGeneratorOctaves field_185991_j;
		private NoiseGeneratorOctaves field_185992_k;
		private NoiseGeneratorOctaves field_185993_l;
		private NoiseGeneratorPerlin field_185994_m;
		public NoiseGeneratorOctaves field_185983_b;
		public NoiseGeneratorOctaves field_185984_c;
		public NoiseGeneratorOctaves field_185985_d;
		private final World world;
		private final WorldType terrainType;
		private final double[] heightMap;
		private final float[] field_185999_r;
		private ChunkGeneratorSettings settings;
		private double[] field_186002_u = new double[256];
		private MapGenBase caveGenerator;
		private MapGenBase ravineGenerator;
		private Biome[] biomesForGeneration;
		double[] field_185986_e;
		double[] field_185987_f;
		double[] field_185988_g;
		double[] field_185989_h;

		public ChunkProviderModded(World worldIn, long seed) {
			caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(new MapGenCaves() {

				@Override
				protected boolean canReplaceBlock(IBlockState a, IBlockState b) {
					if (a.getBlock() == STONE.getBlock())
						return true;
					return super.canReplaceBlock(a, b);
				}
			}, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
			ravineGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(new MapGenRavine() {

				@Override
				protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
					net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
					IBlockState state = data.getBlockState(x, y, z);
					if (state.getBlock() == STONE.getBlock() || state.getBlock() == biome.topBlock.getBlock()
							|| state.getBlock() == biome.fillerBlock.getBlock()) {
						if (y - 1 < 10) {
							data.setBlockState(x, y, z, FLOWING_LAVA);
						} else {
							data.setBlockState(x, y, z, AIR);
							if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == biome.fillerBlock.getBlock()) {
								data.setBlockState(x, y - 1, z, biome.topBlock.getBlock().getDefaultState());
							}
						}
					}
				}
			}, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE);
			this.world = worldIn;
			this.terrainType = worldIn.getWorldInfo().getTerrainType();
			this.rand = new Random(seed);
			this.field_185991_j = new NoiseGeneratorOctaves(this.rand, 16);
			this.field_185992_k = new NoiseGeneratorOctaves(this.rand, 16);
			this.field_185993_l = new NoiseGeneratorOctaves(this.rand, 8);
			this.field_185994_m = new NoiseGeneratorPerlin(this.rand, 4);
			this.field_185983_b = new NoiseGeneratorOctaves(this.rand, 10);
			this.field_185984_c = new NoiseGeneratorOctaves(this.rand, 16);
			this.field_185985_d = new NoiseGeneratorOctaves(this.rand, 8);
			this.heightMap = new double[825];
			this.field_185999_r = new float[25];
			for (int i = -2; i <= 2; ++i) {
				for (int j = -2; j <= 2; ++j) {
					float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
					this.field_185999_r[i + 2 + (j + 2) * 5] = f;
				}
			}
			this.settings = new ChunkGeneratorSettings.Factory().build();
			net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx = new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(
					field_185991_j, field_185992_k, field_185993_l, field_185994_m, field_185983_b, field_185984_c, field_185985_d);
			ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
			this.field_185991_j = ctx.getLPerlin1();
			this.field_185992_k = ctx.getLPerlin2();
			this.field_185993_l = ctx.getPerlin();
			this.field_185994_m = ctx.getHeight();
			this.field_185983_b = ctx.getScale();
			this.field_185984_c = ctx.getDepth();
			this.field_185985_d = ctx.getForest();
		}

		@Override
		public Chunk generateChunk(int x, int z) {
			this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
			ChunkPrimer chunkprimer = new ChunkPrimer();
			this.setBlocksInChunk(x, z, chunkprimer);
			this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 16, z * 16, 16, 16);
			this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
			this.caveGenerator.generate(this.world, x, z, chunkprimer);
			this.ravineGenerator.generate(this.world, x, z, chunkprimer);
			Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
			byte[] abyte = chunk.getBiomeArray();
			for (int i = 0; i < abyte.length; ++i) {
				abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
			}
			chunk.generateSkylightMap();
			return chunk;
		}

		@Override
		public void populate(int x, int z) {
			BlockFalling.fallInstantly = true;
			int i = x * 16;
			int j = z * 16;
			BlockPos blockpos = new BlockPos(i, 0, j);
			Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
			this.rand.setSeed(this.world.getSeed());
			long k = this.rand.nextLong() / 2L * 2L + 1L;
			long l = this.rand.nextLong() / 2L * 2L + 1L;
			this.rand.setSeed((long) x * k + (long) z * l ^ this.world.getSeed());
			net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
			if (biome != Biomes.DESERT && biome != Biomes.DESERT_HILLS && this.settings.useWaterLakes
					&& this.rand.nextInt(this.settings.waterLakeChance) == 0)
				if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
						net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE)) {
					int i1 = this.rand.nextInt(16) + 8;
					int j1 = this.rand.nextInt(256);
					int k1 = this.rand.nextInt(16) + 8;
					(new WorldGenLakes(WATER.getBlock())).generate(this.world, this.rand, blockpos.add(i1, j1, k1));
				}
			if (this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes)
				if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
						net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA)) {
					int i2 = this.rand.nextInt(16) + 8;
					int l2 = this.rand.nextInt(this.rand.nextInt(248) + 8);
					int k3 = this.rand.nextInt(16) + 8;
					if (l2 < this.world.getSeaLevel() || this.rand.nextInt(this.settings.lavaLakeChance / 8) == 0) {
						(new WorldGenLakes(WATER.getBlock())).generate(this.world, this.rand, blockpos.add(i2, l2, k3));
					}
				}
			if (this.settings.useDungeons)
				if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
						net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON)) {
					for (int j2 = 0; j2 < this.settings.dungeonChance; ++j2) {
						int i3 = this.rand.nextInt(16) + 8;
						int l3 = this.rand.nextInt(256);
						int l1 = this.rand.nextInt(16) + 8;
						(new WorldGenDungeons()).generate(this.world, this.rand, blockpos.add(i3, l3, l1));
					}
				}
			biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
			if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
					net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
				WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.rand);
			blockpos = blockpos.add(8, 0, 8);
			if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
					net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE)) {
				for (int k2 = 0; k2 < 16; ++k2) {
					for (int j3 = 0; j3 < 16; ++j3) {
						BlockPos blockpos1 = this.world.getPrecipitationHeight(blockpos.add(k2, 0, j3));
						BlockPos blockpos2 = blockpos1.down();
						if (this.world.canBlockFreezeWater(blockpos2)) {
							this.world.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
						}
						if (this.world.canSnowAt(blockpos1, true)) {
							this.world.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
						}
					}
				}
			}
			net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
			BlockFalling.fallInstantly = false;
		}

		@Override
		public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
			Biome biome = this.world.getBiome(pos);
			return biome.getSpawnableList(creatureType);
		}

		@Override
		public void recreateStructures(Chunk chunkIn, int x, int z) {
		}

		@Override
		public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
			return false;
		}

		@Override
		public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
			return null;
		}

		@Override
		public boolean generateStructures(Chunk chunkIn, int x, int z) {
			return false;
		}

		private void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
			this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
			this.generateHeightmap(x * 4, 0, z * 4);
			for (int i = 0; i < 4; ++i) {
				int j = i * 5;
				int k = (i + 1) * 5;
				for (int l = 0; l < 4; ++l) {
					int i1 = (j + l) * 33;
					int j1 = (j + l + 1) * 33;
					int k1 = (k + l) * 33;
					int l1 = (k + l + 1) * 33;
					for (int i2 = 0; i2 < 32; ++i2) {
						double d0 = 0.125D;
						double d1 = this.heightMap[i1 + i2];
						double d2 = this.heightMap[j1 + i2];
						double d3 = this.heightMap[k1 + i2];
						double d4 = this.heightMap[l1 + i2];
						double d5 = (this.heightMap[i1 + i2 + 1] - d1) * d0;
						double d6 = (this.heightMap[j1 + i2 + 1] - d2) * d0;
						double d7 = (this.heightMap[k1 + i2 + 1] - d3) * d0;
						double d8 = (this.heightMap[l1 + i2 + 1] - d4) * d0;
						for (int j2 = 0; j2 < 8; ++j2) {
							double d9 = 0.25D;
							double d10 = d1;
							double d11 = d2;
							double d12 = (d3 - d1) * d9;
							double d13 = (d4 - d2) * d9;
							for (int k2 = 0; k2 < 4; ++k2) {
								double d14 = 0.25D;
								double d16 = (d11 - d10) * d14;
								double lvt_45_1_ = d10 - d16;
								for (int l2 = 0; l2 < 4; ++l2) {
									if ((lvt_45_1_ += d16) > 0.0D) {
										primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
									} else if (i2 * 8 + j2 < this.settings.seaLevel) {
										primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, WATER);
									}
								}
								d10 += d12;
								d11 += d13;
							}
							d1 += d5;
							d2 += d6;
							d3 += d7;
							d4 += d8;
						}
					}
				}
			}
		}

		private void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
			if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world))
				return;
			double d0 = 0.03125D;
			this.field_186002_u = this.field_185994_m.getRegion(this.field_186002_u, (double) (x * 16), (double) (z * 16), 16, 16, d0 * 2.0D,
					d0 * 2.0D, 1.0D);
			for (int i = 0; i < 16; ++i) {
				for (int j = 0; j < 16; ++j) {
					Biome Biome = biomesIn[j + i * 16];
					generateBiomeTerrain(this.world, this.rand, primer, x * 16 + i, z * 16 + j, this.field_186002_u[j + i * 16], Biome);
				}
			}
		}

		private void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal, Biome biome) {
			int i = worldIn.getSeaLevel();
			IBlockState iblockstate = biome.topBlock;
			IBlockState iblockstate1 = biome.fillerBlock;
			int j = -1;
			int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
			int l = x & 15;
			int i1 = z & 15;
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			for (int j1 = 255; j1 >= 0; --j1) {
				if (j1 <= rand.nextInt(5)) {
					chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
				} else {
					IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
					if (iblockstate2.getMaterial() == Material.AIR) {
						j = -1;
					} else if (iblockstate2.getBlock() == STONE.getBlock()) {
						if (j == -1) {
							if (k <= 0) {
								iblockstate = AIR;
								iblockstate1 = STONE;
							} else if (j1 >= i - 4 && j1 <= i + 1) {
								iblockstate = biome.topBlock;
								iblockstate1 = biome.fillerBlock;
							}
							if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
								iblockstate1 = WATER;
							}
							j = k;
							if (j1 >= i - 1) {
								chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
							} else if (j1 < i - 7 - k) {
								iblockstate = AIR;
								iblockstate1 = STONE;
								chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
							} else {
								chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
							}
						} else if (j > 0) {
							--j;
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
							if (j == 0 && iblockstate1.getBlock() == Blocks.SAND) {
								j = rand.nextInt(4) + Math.max(0, j1 - 63);
								iblockstate1 = STONE;
							}
						}
					}
				}
			}
		}

		private void generateHeightmap(int p_185978_1_, int p_185978_2_, int p_185978_3_) {
			this.field_185989_h = this.field_185984_c.generateNoiseOctaves(this.field_185989_h, p_185978_1_, p_185978_3_, 5, 5,
					(double) this.settings.depthNoiseScaleX, (double) this.settings.depthNoiseScaleZ, (double) this.settings.depthNoiseScaleExponent);
			float f = this.settings.coordinateScale;
			float f1 = this.settings.heightScale;
			this.field_185986_e = this.field_185993_l.generateNoiseOctaves(this.field_185986_e, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5,
					(double) (f / this.settings.mainNoiseScaleX), (double) (f1 / this.settings.mainNoiseScaleY),
					(double) (f / this.settings.mainNoiseScaleZ));
			this.field_185987_f = this.field_185991_j.generateNoiseOctaves(this.field_185987_f, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5,
					(double) f, (double) f1, (double) f);
			this.field_185988_g = this.field_185992_k.generateNoiseOctaves(this.field_185988_g, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5,
					(double) f, (double) f1, (double) f);
			int i = 0;
			int j = 0;
			for (int k = 0; k < 5; ++k) {
				for (int l = 0; l < 5; ++l) {
					float f2 = 0.0F;
					float f3 = 0.0F;
					float f4 = 0.0F;
					int i1 = 2;
					Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];
					for (int j1 = -i1; j1 <= i1; ++j1) {
						for (int k1 = -i1; k1 <= i1; ++k1) {
							Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
							float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
							float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;
							if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
								f5 = 1.0F + f5 * 2.0F;
								f6 = 1.0F + f6 * 4.0F;
							}
							float f7 = this.field_185999_r[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);
							if (biome1.getBaseHeight() > biome.getBaseHeight()) {
								f7 /= 2.0F;
							}
							f2 += f6 * f7;
							f3 += f5 * f7;
							f4 += f7;
						}
					}
					f2 = f2 / f4;
					f3 = f3 / f4;
					f2 = f2 * 0.9F + 0.1F;
					f3 = (f3 * 4.0F - 1.0F) / 8.0F;
					double d7 = this.field_185989_h[j] / 8000.0D;
					if (d7 < 0.0D) {
						d7 = -d7 * 0.3D;
					}
					d7 = d7 * 3.0D - 2.0D;
					if (d7 < 0.0D) {
						d7 = d7 / 2.0D;
						if (d7 < -1.0D) {
							d7 = -1.0D;
						}
						d7 = d7 / 1.4D;
						d7 = d7 / 2.0D;
					} else {
						if (d7 > 1.0D) {
							d7 = 1.0D;
						}
						d7 = d7 / 8.0D;
					}
					++j;
					double d8 = (double) f3;
					double d9 = (double) f2;
					d8 = d8 + d7 * 0.2D;
					d8 = d8 * (double) this.settings.baseSize / 8.0D;
					double d0 = (double) this.settings.baseSize + d8 * 4.0D;
					for (int l1 = 0; l1 < 33; ++l1) {
						double d1 = ((double) l1 - d0) * this.settings.stretchY * 128.0D / 256.0D / d9;
						if (d1 < 0.0D) {
							d1 *= 4.0D;
						}
						double d2 = this.field_185987_f[i] / (double) this.settings.lowerLimitScale;
						double d3 = this.field_185988_g[i] / (double) this.settings.upperLimitScale;
						double d4 = (this.field_185986_e[i] / 10.0D + 1.0D) / 2.0D;
						double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;
						if (l1 > 29) {
							double d6 = (double) ((float) (l1 - 29) / 3.0F);
							d5 = d5 * (1.0D - d6) + -10.0D * d6;
						}
						this.heightMap[i] = d5;
						++i;
					}
				}
			}
		}
	}
}
