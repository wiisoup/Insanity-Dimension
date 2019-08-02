package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

import java.util.Random;

public class MCreatorRainbowBiome extends shadowwolf_mod_one.ModElement {

	@GameRegistry.ObjectHolder("shadowwolf_mod_one:rainbowbiome")
	public static final BiomeGenCustom biome = null;

	public MCreatorRainbowBiome(shadowwolf_mod_one instance) {
		super(instance);
		instance.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
	}

	static class BiomeGenCustom extends Biome {

		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("rainbowbiome").setRainfall(0.9F).setBaseHeight(1F).setHeightVariation(2F).setTemperature(0.6F));
			setRegistryName("rainbowbiome");
			topBlock = MCreatorInsanityOre.block.getDefaultState();
			fillerBlock = MCreatorRainbowBlock.block.getDefaultState();
			decorator.generateFalls = true;
			decorator.treesPerChunk = 7;
			decorator.flowersPerChunk = 12;
			decorator.grassPerChunk = 15;
			decorator.deadBushPerChunk = 0;
			decorator.mushroomsPerChunk = 5;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 3;
			decorator.sandPatchesPerChunk = 2;
			decorator.gravelPatchesPerChunk = 2;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
			this.spawnableCreatureList.add(new SpawnListEntry(MCreatorRainbowCreeper.EntityCustom.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(MCreatorSmileyTheManiac.EntityCustom.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(MCreatorRainbowZombie.EntityCustom.class, 40, 1, 5));
		}

		@Override
		public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
			return new CustomTree();
		}
	}

	static class CustomTree extends WorldGenAbstractTree {

		CustomTree() {
			super(false);
		}

		@Override
		public boolean generate(World world, Random par2Random, BlockPos pos) {
			if (world.isRemote)
				return false;
			Template template = ((WorldServer) world).getStructureTemplateManager().getTemplate(world.getMinecraftServer(),
					new ResourceLocation("shadowwolf_mod_one", "small_mountain"));
			if (template == null)
				return false;
			Block ground = world.getBlockState(pos).getBlock();
			Block ground2 = world.getBlockState(pos.add(0, -1, 0)).getBlock();
			if (!(ground == MCreatorInsanityOre.block.getDefaultState().getBlock()
					|| ground == MCreatorRainbowBlock.block.getDefaultState().getBlock()
					|| ground2 == MCreatorInsanityOre.block.getDefaultState().getBlock() || ground2 == MCreatorRainbowBlock.block.getDefaultState()
					.getBlock()))
				return false;
			Rotation rotation = Rotation.NONE;
			int rot = par2Random.nextInt(3);
			if (rot == 0)
				rotation = Rotation.NONE;
			else if (rot == 1)
				rotation = Rotation.CLOCKWISE_90;
			else if (rot == 2)
				rotation = Rotation.CLOCKWISE_180;
			else if (rot == 3)
				rotation = Rotation.COUNTERCLOCKWISE_90;
			Mirror mirror = Mirror.NONE;
			int mir = par2Random.nextInt(2);
			if (mir == 0)
				mirror = Mirror.NONE;
			else if (mir == 1)
				mirror = Mirror.LEFT_RIGHT;
			else if (mir == 2)
				mirror = Mirror.FRONT_BACK;
			BlockPos placeTo = pos.add(template.getSize().getX() / -2, 0, template.getSize().getZ() / -2);
			IBlockState iblockstate = world.getBlockState(placeTo);
			world.notifyBlockUpdate(placeTo, iblockstate, iblockstate, 3);
			template.addBlocksToWorldChunk(world, placeTo, new PlacementSettings().setRandom(par2Random).setRotation(rotation).setMirror(mirror)
					.setChunk((ChunkPos) null).setReplacedBlock((Block) null).setIgnoreStructureBlock(false).setIgnoreEntities(false));
			return true;
		}

		@Override
		protected boolean canGrowInto(Block blockType) {
			Material material = blockType.getDefaultState().getMaterial();
			return material == Material.AIR || blockType == MCreatorInsanityOre.block.getDefaultState().getBlock()
					|| blockType == MCreatorRainbowBlock.block.getDefaultState().getBlock();
		}

		@Override
		protected void setDirtAt(World worldIn, BlockPos pos) {
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
