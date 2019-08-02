package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.potion.Potion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;

import java.util.function.Supplier;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@Mod(modid = shadowwolf_mod_one.MODID, version = shadowwolf_mod_one.VERSION, dependencies = "required-after:forge@[14.23.5.2768]")
public class shadowwolf_mod_one implements IFuelHandler, IWorldGenerator {

	public static final String MODID = "shadowwolf_mod_one";
	public static final String VERSION = "2.6.0";
	@SidedProxy(clientSide = "net.mcreator.shadowwolf_mod_one.ClientProxyshadowwolf_mod_one", serverSide = "net.mcreator.shadowwolf_mod_one.CommonProxyshadowwolf_mod_one")
	public static CommonProxyshadowwolf_mod_one proxy;
	@Instance(MODID)
	public static shadowwolf_mod_one instance;
	public final List<ModElement> elements = new ArrayList<>();
	public final List<Supplier<Block>> blocks = new ArrayList<>();
	public final List<Supplier<Item>> items = new ArrayList<>();
	public final List<Supplier<Biome>> biomes = new ArrayList<>();
	public final List<Supplier<EntityEntry>> entities = new ArrayList<>();
	public final List<Supplier<Potion>> potions = new ArrayList<>();

	public shadowwolf_mod_one() {
		FluidRegistry.enableUniversalBucket();
		elements.add(new MCreatorCharorite(this));
		elements.add(new MCreatorWoodAndOre(this));
		elements.add(new MCreatorCharoriteSword(this));
		elements.add(new MCreatorCharoriteTools(this));
		elements.add(new MCreatorBenandrite(this));
		elements.add(new MCreatorBenandriteItem(this));
		elements.add(new MCreatorCharoriteIngot(this));
		elements.add(new MCreatorCharoriteIngotSmelting(this));
		elements.add(new MCreatorCharoritePickaxe(this));
		elements.add(new MCreatorDimensionGuard(this));
		elements.add(new MCreatorWoodAndOreDimension(this));
		elements.add(new MCreatorRainbowPortalIgniterRecipe(this));
		elements.add(new MCreatorCharoriteArmor(this));
		elements.add(new MCreatorCharoriteSword1(this));
		elements.add(new MCreatorInsanityOre(this));
		elements.add(new MCreatorBenandritePickaxe(this));
		elements.add(new MCreatorBenandriteSword(this));
		elements.add(new MCreatorBenandriteArmor(this));
		elements.add(new MCreatorCharoriteHelmet(this));
		elements.add(new MCreatorCharoriteChest(this));
		elements.add(new MCreatorCharoriteLeggings(this));
		elements.add(new MCreatorCharoriteBoots(this));
		elements.add(new MCreatorBenandriteHelmet(this));
		elements.add(new MCreatorBenandriteChest(this));
		elements.add(new MCreatorBenandriteLeggings(this));
		elements.add(new MCreatorBenandriteBoots(this));
		elements.add(new MCreatorBenandriteAxe(this));
		elements.add(new MCreatorBenandriteShovel(this));
		elements.add(new MCreatorBenandriteHoe(this));
		elements.add(new MCreatorCharoriteSword2(this));
		elements.add(new MCreatorBenandritePickaxe1(this));
		elements.add(new MCreatorBenandriteSword1(this));
		elements.add(new MCreatorBenandriteAxe1(this));
		elements.add(new MCreatorBenandriteShovel1(this));
		elements.add(new MCreatorBenandriteHoe1(this));
		elements.add(new MCreatorSmallMountain(this));
		elements.add(new MCreatorInsanityArmor(this));
		elements.add(new MCreatorColorStick(this));
		elements.add(new MCreatorInsanityIngot(this));
		elements.add(new MCreatorInsanityIngotCrafting(this));
		elements.add(new MCreatorInsanityHelmet(this));
		elements.add(new MCreatorInsanityChest(this));
		elements.add(new MCreatorInsanityLeggings(this));
		elements.add(new MCreatorInsanityBoots(this));
		elements.add(new MCreatorWoolOreHouse(this));
		elements.add(new MCreatorColorStickBenandriteAxe(this));
		elements.add(new MCreatorEnhancedBenandriteAxe(this));
		elements.add(new MCreatorEnhancedBenandriteHoe(this));
		elements.add(new MCreatorEnhancedBenandritePickaxe(this));
		elements.add(new MCreatorEnhancedBenandriteHoeRecipe(this));
		elements.add(new MCreatorEnhancedBenandritePickaxeRecipe(this));
		elements.add(new MCreatorEnhancedBenandriteShovel(this));
		elements.add(new MCreatorEnhancedBenandriteShovelRecipe(this));
		elements.add(new MCreatorEnhancedBenandriteSword(this));
		elements.add(new MCreatorEnhancedBenandriteSwordRecipe(this));
		elements.add(new MCreatorUselessCharoritePickaxe(this));
		elements.add(new MCreatorUselessCharoritePickaxeRecipe(this));
		elements.add(new MCreatorUselessCharoriteSword(this));
		elements.add(new MCreatorUselessCharoriteSwordRecipe(this));
		elements.add(new MCreatorBenandriteDimension(this));
		elements.add(new MCreatorBenandroniteBlock(this));
		elements.add(new MCreatorBenandroniteBlockRecipe(this));
		elements.add(new MCreatorCharoriteShard(this));
		elements.add(new MCreatorBlindronite(this));
		elements.add(new MCreatorBenandroniteGravelBlockDestroyedByPlayer(this));
		elements.add(new MCreatorBenandroniteGravel(this));
		elements.add(new MCreatorBenandroniteDimension(this));
		elements.add(new MCreatorCharoriteOre(this));
		elements.add(new MCreatorBenandroniteAndBlindoniteRecipe(this));
		elements.add(new MCreatorBenandroniteBlockIntoBenandronite(this));
		elements.add(new MCreatorSmileyTheManiac(this));
		elements.add(new MCreatorRainbowDust(this));
		elements.add(new MCreatorColorStickRecipe(this));
		elements.add(new MCreatorSmileyTheManiacMobDies(this));
		elements.add(new MCreatorColorStickFuel(this));
		elements.add(new MCreatorBlindroniteOnAStick(this));
		elements.add(new MCreatorBlindroniteOnAStickRecipe(this));
		elements.add(new MCreatorSmiley(this));
		elements.add(new MCreatorRainbowCreeper(this));
		elements.add(new MCreatorRainbowBlock(this));
		elements.add(new MCreatorRainbowDimension(this));
		elements.add(new MCreatorRainbowBiome(this));
		elements.add(new MCreatorRainbowZombie(this));
		elements.add(new MCreatorRainbowBow(this));
		elements.add(new MCreatorRainbowArrow(this));
		elements.add(new MCreatorRainbowBlockRecipe(this));
		elements.add(new MCreatorRainbowOre(this));
		elements.add(new MCreatorRainbowArrowRecipe(this));
		elements.add(new MCreatorRainbowString(this));
		elements.add(new MCreatorRainbowBowRecipe(this));
		elements.add(new MCreatorRainbowPig(this));
		elements.add(new MCreatorRainbowBlockRecipe1(this));
		elements.add(new MCreatorRainbowPorkchop(this));
		elements.add(new MCreatorCookedRainbowPorkchop(this));
		elements.add(new MCreatorRainbowCookedPorkchopRecipe(this));
		elements.add(new MCreatorRainbowDimensionRecipe(this));
		elements.add(new MCreatorInsanityOreSmelt(this));
		elements.add(new MCreatorDarkStone(this));
		elements.add(new MCreatorDarkDirt(this));
		elements.add(new MCreatorDarkDirtMaterial(this));
		elements.add(new MCreatorDarkDimension(this));
		elements.add(new MCreatorDarkBiome(this));
		elements.add(new MCreatorDarkPig(this));
		elements.add(new MCreatorDarkDirtFlint(this));
		elements.add(new MCreatorDarkDirtFlintRecipe(this));
		elements.add(new MCreatorDarkDimensionIgniter(this));
		elements.add(new MCreatorRainbowGunpowder(this));
		elements.add(new MCreatorAprilfoof(this));
		elements.add(new MCreatorGoo(this));
		elements.add(new MCreatorDarkPickaxe(this));
		elements.add(new MCreatorBlindroniteOnAStickRainbow(this));
		elements.add(new MCreatorBlindroniteOnAStickRainbowRecipe(this));
		elements.add(new MCreatorDarkArmor(this));
		elements.add(new MCreatorDarkGem(this));
		elements.add(new MCreatorDarkPickaxeRecipe(this));
		elements.add(new MCreatorDarkHelmet(this));
		elements.add(new MCreatorDarkChest(this));
		elements.add(new MCreatorDarkLeggings(this));
		elements.add(new MCreatorDarkBoots(this));
		elements.add(new MCreatorDarkOre(this));
		elements.add(new MCreatorDarkSword(this));
		elements.add(new MCreatorDarkShovel(this));
		elements.add(new MCreatorDarkAxe(this));
		elements.add(new MCreatorDarkHoe(this));
		elements.add(new MCreatorDarkCreeper(this));
		elements.add(new MCreatorStairs(this));
		elements.add(new MCreatorLapis(this));
		elements.add(new MCreatorUnoPlusFourOre(this));
		elements.add(new MCreatorUnoPlusFour(this));
		elements.add(new MCreatorUnoBlankCard(this));
		elements.add(new MCreatorUnoBlankCard1(this));
		elements.add(new MCreatorUnoBlankCardBlock(this));
		elements.add(new MCreatorUnoBlankCardBlockRecipe(this));
		elements.add(new MCreatorUno1Card(this));
		elements.add(new MCreatorUnoReverseCard(this));
		elements.add(new MCreatorUno1Ore(this));
		elements.add(new MCreatorUnoReverseCardOre(this));
		elements.add(new MCreatorUno(this));
		elements.add(new MCreatorUnoIgniter(this));
		elements.add(new MCreatorUnoArmor(this));
		elements.add(new MCreatorUnoHelmet(this));
		elements.add(new MCreatorUnoChest(this));
		elements.add(new MCreatorUnoLeggings(this));
		elements.add(new MCreatorUnoBoots(this));
		elements.add(new MCreatorUnoPickaxe(this));
		elements.add(new MCreatorUnoSword(this));
		elements.add(new MCreatorUnoShovel(this));
		elements.add(new MCreatorUnoAxe(this));
		elements.add(new MCreatorUnoPlusFourBiome(this));
		elements.add(new MCreatorInsaneDimensionGuard(this));
		elements.add(new MCreatorInsaneDust(this));
		elements.add(new MCreatorInsanePickaxe(this));
		elements.add(new MCreatorInsaneSword(this));
		elements.add(new MCreatorInsaneShovel(this));
		elements.add(new MCreatorInsaneAxe(this));
		elements.add(new MCreatorInsanePickaxeRecipe(this));
		elements.add(new MCreatorInsaneSwordRecipe(this));
		elements.add(new MCreatorInsaneShovelRecipe(this));
		elements.add(new MCreatorInsaneAxeRecipe(this));
		elements.add(new MCreatorMoistGrayDirt(this));
		elements.add(new MCreatorBenandroniteSmooth(this));
		elements.add(new MCreatorBenandroniteSmoothed(this));
		elements.add(new MCreatorSBenandronitePickaxe(this));
		elements.add(new MCreatorSBenandroniteSword(this));
		elements.add(new MCreatorSBenandroniteAxe(this));
		elements.add(new MCreatorSBenandroniteShovel(this));
		elements.add(new MCreatorSBenandroniteSmelt2(this));
		elements.add(new MCreatorUnoPickaxeRecipe(this));
		elements.add(new MCreatorUnoSwordRecipe(this));
		elements.add(new MCreatorUnoShovelRecipe(this));
		elements.add(new MCreatorUnoAxeRecipe(this));
		elements.add(new MCreatorSBenandronitePickaxeRecipe(this));
		elements.add(new MCreatorSBenandroniteSwordRecipe(this));
		elements.add(new MCreatorSBenandroniteAxeRecipe(this));
		elements.add(new MCreatorSBenandroniteShovelRecipe(this));
		elements.add(new MCreatorDarkSwordRecipe(this));
		elements.add(new MCreatorDarkShovelRecipe(this));
		elements.add(new MCreatorDarkAxeRecipe(this));
		elements.add(new MCreatorDarkHoeRecipe(this));
		elements.add(new MCreatorColorGem(this));
		elements.add(new MCreatorColorOre(this));
		elements.add(new MCreatorColorOreSmelting(this));
		elements.add(new MCreatorColorBlock(this));
		elements.add(new MCreatorColorBlockRecipe(this));
		elements.add(new MCreatorSBenandroniteBlock(this));
		elements.add(new MCreatorSBenandroniteBlockRecipe(this));
		elements.add(new MCreatorCharoriteShardFuel(this));
		elements.add(new MCreatorSBenandroniteDirt(this));
		elements.add(new MCreatorSBenandroniteBlockUpdateTick(this));
		elements.add(new MCreatorSBenandroniteDirtUpdateTick(this));
		elements.add(new MCreatorRainbowOreSmelt(this));
		elements.add(new MCreatorDarkOreSmelt(this));
		elements.add(new MCreatorUnoPlusFourSmelt(this));
		elements.add(new MCreatorUnoBlankCardSmelt(this));
		elements.add(new MCreatorUnoOneCardSmelt(this));
		elements.add(new MCreatorUnoReverseCardSmelt(this));
		elements.add(new MCreatorPrankdSmelt(this));
		elements.add(new MCreatorBlockOfSBenandronite(this));
		elements.add(new MCreatorSBenandroniteDirtRecipe(this));
		elements.add(new MCreatorOddBrownOre(this));
		elements.add(new MCreatorOddBrownGem(this));
		elements.add(new MCreatorSmeltBrownOre(this));
		elements.add(new MCreatorKernel(this));
		elements.add(new MCreatorPrankdBiome(this));
		elements.add(new MCreatorPrankdDimension(this));
		elements.add(new MCreatorPrankdIgniter(this));
		elements.add(new MCreatorColorGemArmor(this));
		elements.add(new MCreatorColorGemArmorHelmet(this));
		elements.add(new MCreatorColorGemArmorChest(this));
		elements.add(new MCreatorColorGemArmorLeggings(this));
		elements.add(new MCreatorColorGemArmorBoots(this));
		elements.add(new MCreatorInsanityDimension(this));
		elements.add(new MCreatorInsanityDimensionEnter(this));
		elements.add(new MCreatorInsanityDimensionPortal(this));
		elements.add(new MCreatorInsanityDimensionPortalProcedure(this));
		elements.add(new MCreatorCharoritePickaxeAchievement(this));
		elements.add(new MCreatorCharoritePickaxeProcedure(this));
		elements.add(new MCreatorUselessCharoritePickaxeAchievement(this));
		elements.add(new MCreatorUselessCharoritePickaxeProcedure(this));
		elements.add(new MCreatorRewardDimensionAchievement(this));
		elements.add(new MCreatorRewardDimensionProcedure(this));
		elements.add(new MCreatorOddBrownGemArmor(this));
		elements.add(new MCreatorOddBrownHelmet(this));
		elements.add(new MCreatorOddBrownChest(this));
		elements.add(new MCreatorOddBrownLeggings(this));
		elements.add(new MCreatorOddBrownBoots(this));
		elements.add(new MCreatorEnterAllDimensions(this));
		elements.add(new MCreatorRainbowDimensionAchievement(this));
		elements.add(new MCreatorRainbowDimensionAchievement1(this));
		elements.add(new MCreatorEnterAllDimensions1(this));
		elements.add(new MCreatorEnterAllDimensions2(this));
		elements.add(new MCreatorEnterAllDimensions3(this));
		elements.add(new MCreatorEnterAllDimensions4(this));
		elements.add(new MCreatorEnterAllDimensions5(this));
		elements.add(new MCreatorEnterAllDimensions6(this));
		elements.add(new MCreatorEnterAllDimensions7(this));
		elements.add(new MCreatorDarkDimensionAchievement(this));
		elements.add(new MCreatorDarkDimensionAchievementProcedure(this));
		elements.add(new MCreatorUnoDimensionAchievement(this));
		elements.add(new MCreatorUnoDimensionAchievementProcedure(this));
		elements.add(new MCreatorPrankdDimensionAchievement(this));
		elements.add(new MCreatorPrankdDimensionAchievementProcedure(this));
		elements.add(new MCreatorUnoBlankCardBlockReverseRecipe(this));
		elements.add(new MCreatorOddBrownGemBlock(this));
		elements.add(new MCreatorBrownGemAchievement(this));
		elements.add(new MCreatorBrownGemProcedure(this));
		elements.add(new MCreatorOddBrownGemBlockRecipe(this));
		elements.add(new MCreatorDontDrinkItAchievement(this));
		elements.add(new MCreatorDontDrinkItProcedure(this));
		elements.add(new MCreatorWhyDidIMakeThisArmor(this));
		elements.add(new MCreatorWhyDidIMakeThisArmorProcedure(this));
		elements.add(new MCreatorChest1(this));
		elements.add(new MCreatorChest2(this));
		elements.add(new MCreatorChest3(this));
		elements.add(new MCreatorChest4(this));
		elements.add(new MCreatorChest5(this));
		elements.add(new MCreatorChest6(this));
		elements.add(new MCreatorChest7(this));
		elements.add(new MCreatorChest8(this));
		elements.add(new MCreatorChest9(this));
		elements.add(new MCreatorChest10(this));
		elements.add(new MCreatorChest11(this));
		elements.add(new MCreatorChest12(this));
		elements.add(new MCreatorChest13(this));
		elements.add(new MCreatorChest14(this));
		elements.add(new MCreatorDimensionSpawn(this));
		elements.add(new MCreatorDimensionSpawnSpawning(this));
		elements.add(new MCreatorTheDimensionSpawn(this));
		elements.add(new MCreatorDimensionSpawnRuby(this));
		elements.add(new MCreatorTheDimensionSpawnProcedure(this));
		elements.add(new MCreatorDimensionSpawnSpawner(this));
		elements.add(new MCreatorDimensionSpawnSpawnerRecipe(this));
		elements.add(new MCreatorDimensionSpawnSpawnerSpawning(this));
		elements.add(new MCreatorSpawner1(this));
		elements.add(new MCreatorSpawner2(this));
		elements.add(new MCreatorSpawner3(this));
		elements.add(new MCreatorSpawner4(this));
		elements.add(new MCreatorSpawner5(this));
		elements.add(new MCreatorSpawner6(this));
		elements.add(new MCreatorSpawner7(this));
		elements.add(new MCreatorDimensionSpawnAxe(this));
		elements.add(new MCreatorDimensionSpawnHoe(this));
		elements.add(new MCreatorDimensionSpawnPickaxe(this));
		elements.add(new MCreatorDimensionSpawnShovel(this));
		elements.add(new MCreatorDimensionSpawnSword(this));
		elements.add(new MCreatorDimensionSpawnRubyBlock(this));
		elements.add(new MCreatorDimensionSpawnRubyBlockRecipe(this));
		elements.add(new MCreatorDimensionSpawnRubyStick(this));
		elements.add(new MCreatorDimensionSpawnRubyStickRecipe(this));
		elements.add(new MCreatorDimensionSpawnAxeRecipe(this));
		elements.add(new MCreatorDimensionSpawnHoeRecipe(this));
		elements.add(new MCreatorDimensionSpawnPickaxeRecipe(this));
		elements.add(new MCreatorDimensionSpawnShovelRecipe(this));
		elements.add(new MCreatorDimensionSpawnSwordRecipe(this));
		elements.add(new MCreatorWhyAchievement(this));
		elements.add(new MCreatorWhyAchievementProcedure(this));
		elements.add(new MCreatorBestWeaponAchievement(this));
		elements.add(new MCreatorBestWeaponAchievementProcedure(this));
		elements.add(new MCreatorInsaneHoe(this));
		elements.add(new MCreatorSBenandroniteHoe(this));
		elements.add(new MCreatorHeSaidNoMoreHoes(this));
		elements.add(new MCreatorHeSaidNoMoreHoes1(this));
		elements.add(new MCreatorInsaneHoeRecipe(this));
		elements.add(new MCreatorSBenandroniteHoeRecipe(this));
		elements.add(new MCreatorUnoHoe(this));
		elements.add(new MCreatorKernelArmor(this));
		elements.add(new MCreatorGlitchBlock2(this));
		elements.add(new MCreatorKernelIngot(this));
		elements.add(new MCreatorKernelSmelt(this));
		elements.add(new MCreatorGlitchBlock2Uncraft(this));
		elements.add(new MCreatorKernelHelmet(this));
		elements.add(new MCreatorKernelChest(this));
		elements.add(new MCreatorKernelLeggings(this));
		elements.add(new MCreatorKernelBoots(this));
		elements.add(new MCreatorError514AchievementFailedToLoad(this));
		elements.add(new MCreatorError514Achievement(this));
		elements.add(new MCreatorUnoHoeRecipe(this));
		elements.add(new MCreatorDarkPickaxeAchievement(this));
		elements.add(new MCreatorDarkPickaxeProcedure(this));
		elements.add(new MCreatorOverworldEnd(this));
		elements.add(new MCreatorOverworldEndIngiterRecipe(this));
		elements.add(new MCreatorOverworldEndAchievement(this));
		elements.add(new MCreatorOverworldEndAchievementProcedure(this));
		elements.add(new MCreatorPotionoftoughnessPotionStartedapplied(this));
		elements.add(new MCreatorPotionoftoughnessPotionExpires(this));
		elements.add(new MCreatorPotionoftoughness(this));
		elements.add(new MCreatorPotionoftoughnessOnPotionActiveTick(this));
		elements.add(new MCreatorPotionoftoughnessPotionStartedapplied2(this));
		elements.add(new MCreatorPotionofscrawnyPotionStartedapplied(this));
		elements.add(new MCreatorPotionofscrawnyPotionExpires(this));
		elements.add(new MCreatorPotionofscrawny(this));
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		for (ModElement element : elements) {
			int ret = element.addFuel(fuel);
			if (ret != 0)
				return ret;
		}
		return 0;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator cg, IChunkProvider cp) {
		elements.forEach(element -> element.generateWorld(random, chunkX * 16, chunkZ * 16, world, world.provider.getDimension(), cg, cp));
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(blocks.stream().map(Supplier::get).toArray(Block[]::new));
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(items.stream().map(Supplier::get).toArray(Item[]::new));
	}

	@SubscribeEvent
	public void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(biomes.stream().map(Supplier::get).toArray(Biome[]::new));
	}

	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().registerAll(entities.stream().map(Supplier::get).toArray(EntityEntry[]::new));
	}

	@SubscribeEvent
	public void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(potions.stream().map(Supplier::get).toArray(Potion[]::new));
	}

	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		elements.forEach(element -> element.registerModels(event));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		GameRegistry.registerFuelHandler(this);
		GameRegistry.registerWorldGenerator(this, 5);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		elements.forEach(element -> element.preInit(event));
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		elements.forEach(element -> element.init(event));
		proxy.init(event);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		elements.forEach(element -> element.serverLoad(event));
	}

	public static class GuiHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}

		@Override
		public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}
	}

	public static class ModElement {

		public static shadowwolf_mod_one instance;

		public ModElement(shadowwolf_mod_one instance) {
			this.instance = instance;
		}

		public void init(FMLInitializationEvent event) {
		}

		public void preInit(FMLPreInitializationEvent event) {
		}

		public void generateWorld(Random random, int posX, int posZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		}

		public void serverLoad(FMLServerStartingEvent event) {
		}

		public void registerModels(ModelRegistryEvent event) {
		}

		public int addFuel(ItemStack fuel) {
			return 0;
		}
	}
}
