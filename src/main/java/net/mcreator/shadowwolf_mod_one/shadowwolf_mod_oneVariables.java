package net.mcreator.shadowwolf_mod_one;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;

public class shadowwolf_mod_oneVariables {

	public static boolean InsanityDimension1 = false;
	public static boolean BenandroniteDimension1 = false;
	public static boolean RainbowDimension1 = false;
	public static boolean DarkDimension1 = false;
	public static boolean UnoDimension1 = false;
	public static boolean PrankdDimension1 = false;
	public static boolean OverworldEnd1 = false;

	public static class MapVariables extends WorldSavedData {

		private static final String DATA_NAME = "shadowwolf_mod_one_mapvars";

		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			return nbt;
		}

		public static MapVariables get(World world) {
			MapVariables instance = (MapVariables) world.getMapStorage().getOrLoadData(MapVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new MapVariables();
				world.getMapStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldVariables extends WorldSavedData {

		private static final String DATA_NAME = "shadowwolf_mod_one_worldvars";

		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			return nbt;
		}

		public static WorldVariables get(World world) {
			WorldVariables instance = (WorldVariables) world.getMapStorage().getOrLoadData(WorldVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new WorldVariables();
				world.getMapStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}
}
