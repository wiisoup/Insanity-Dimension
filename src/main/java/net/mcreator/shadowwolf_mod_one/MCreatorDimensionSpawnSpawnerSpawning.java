package net.mcreator.shadowwolf_mod_one;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class MCreatorDimensionSpawnSpawnerSpawning extends shadowwolf_mod_one.ModElement {

	public MCreatorDimensionSpawnSpawnerSpawning(shadowwolf_mod_one instance) {
		super(instance);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorDimensionSpawnSpawnerSpawning!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorDimensionSpawnSpawnerSpawning!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorDimensionSpawnSpawnerSpawning!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorDimensionSpawnSpawnerSpawning!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		world.createExplosion(null, (int) x, (int) y, (int) z, (float) 3, true);
		if (!world.isRemote) {
			Entity entityToSpawn = new MCreatorDimensionSpawn.EntityCustom(world);
			if (entityToSpawn != null) {
				entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360F, 0.0F);
				world.spawnEntity(entityToSpawn);
			}
		}
	}
}
