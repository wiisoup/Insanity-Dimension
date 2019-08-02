package net.mcreator.shadowwolf_mod_one;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class MCreatorSBenandroniteDirtUpdateTick extends shadowwolf_mod_one.ModElement {

	public MCreatorSBenandroniteDirtUpdateTick(shadowwolf_mod_one instance) {
		super(instance);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorSBenandroniteDirtUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorSBenandroniteDirtUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorSBenandroniteDirtUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorSBenandroniteDirtUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), MCreatorSBenandroniteBlock.block.getDefaultState(), 3);
		}
	}
}
