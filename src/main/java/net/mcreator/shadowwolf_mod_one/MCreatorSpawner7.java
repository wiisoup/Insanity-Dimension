package net.mcreator.shadowwolf_mod_one;

import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.Block;

import java.util.Random;

public class MCreatorSpawner7 extends shadowwolf_mod_one.ModElement {

	public MCreatorSpawner7(shadowwolf_mod_one instance) {
		super(instance);
	}

	@Override
	public void generateWorld(Random random, int i2, int k2, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		boolean isNetherType = false;
		if (dimID == MCreatorWoodAndOre.DIMID) {
			dimensionCriteria = true;
			isNetherType = MCreatorWoodAndOre.NETHER_TYPE;
		}
		if (dimID == MCreatorBenandroniteDimension.DIMID) {
			dimensionCriteria = true;
			isNetherType = MCreatorBenandroniteDimension.NETHER_TYPE;
		}
		if (dimID == MCreatorRainbowDimension.DIMID) {
			dimensionCriteria = true;
			isNetherType = MCreatorRainbowDimension.NETHER_TYPE;
		}
		if (!dimensionCriteria)
			return;
		if ((random.nextInt(1000000) + 1) <= 1000) {
			int i = i2 + random.nextInt(16) + 8;
			int k = k2 + random.nextInt(16) + 8;
			int height = 255;
			if (isNetherType) {
				boolean notpassed = true;
				while (height > 0) {
					if (notpassed && world.isAirBlock(new BlockPos(i, height, k)))
						notpassed = false;
					else if (!notpassed && !world.isAirBlock(new BlockPos(i, height, k)))
						break;
					height--;
				}
			} else {
				while (height > 0) {
					if (!world.isAirBlock(new BlockPos(i, height, k)))
						break;
					height--;
				}
			}
			int j = height - 1;
			if (world.isRemote)
				return;
			Template template = ((WorldServer) world).getStructureTemplateManager().getTemplate(world.getMinecraftServer(),
					new ResourceLocation("shadowwolf_mod_one", "spawner7"));
			if (template == null)
				return;
			Rotation rotation = Rotation.NONE;
			Mirror mirror = Mirror.NONE;
			int rot = random.nextInt(3);
			if (rot == 0)
				rotation = Rotation.NONE;
			else if (rot == 1)
				rotation = Rotation.CLOCKWISE_90;
			else if (rot == 2)
				rotation = Rotation.CLOCKWISE_180;
			else if (rot == 3)
				rotation = Rotation.COUNTERCLOCKWISE_90;
			int mir = random.nextInt(2);
			if (mir == 0)
				mirror = Mirror.NONE;
			else if (mir == 1)
				mirror = Mirror.LEFT_RIGHT;
			else if (mir == 2)
				mirror = Mirror.FRONT_BACK;
			BlockPos spawnTo = new BlockPos(i, j + 0, k);
			IBlockState iblockstate = world.getBlockState(spawnTo);
			world.notifyBlockUpdate(spawnTo, iblockstate, iblockstate, 3);
			template.addBlocksToWorldChunk(world, spawnTo, new PlacementSettings().setRotation(rotation).setMirror(mirror).setChunk((ChunkPos) null)
					.setReplacedBlock((Block) null).setIgnoreStructureBlock(false).setIgnoreEntities(false));
		}
	}
}
