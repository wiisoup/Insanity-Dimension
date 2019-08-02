package net.mcreator.shadowwolf_mod_one;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.common.DungeonHooks;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.model.ModelBiped;

public class MCreatorSmiley extends shadowwolf_mod_one.ModElement {

	public static final int ENTITYID = 5;
	public static final int ENTITYID_RANGED = 6;

	public MCreatorSmiley(shadowwolf_mod_one instance) {
		super(instance);
		instance.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class)
				.id(new ResourceLocation("shadowwolf_mod_one", "smiley"), ENTITYID).name("smiley").tracker(64, 1, true).egg(-16777216, -1).build());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		Biome[] spawnBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("plains")), Biome.REGISTRY.getObject(new ResourceLocation("desert")),
				Biome.REGISTRY.getObject(new ResourceLocation("forest")),
				Biome.REGISTRY.getObject(new ResourceLocation("shadowwolf_mod_one:woodandoredimension")),
				Biome.REGISTRY.getObject(new ResourceLocation("shadowwolf_mod_one:benandritedimension")),};
		EntityRegistry.addSpawn(EntityCustom.class, 20, 1, 1, EnumCreatureType.MONSTER, spawnBiomes);
		DungeonHooks.addDungeonMob(new ResourceLocation("shadowwolf_mod_one:smiley"), 180);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			RenderBiped customRender = new RenderBiped(renderManager, new ModelBiped(), 0.5f) {

				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("shadowwolf_mod_one:textures/smiley.png");
				}
			};
			customRender.addLayer(new net.minecraft.client.renderer.entity.layers.LayerHeldItem(customRender));
			customRender.addLayer(new net.minecraft.client.renderer.entity.layers.LayerBipedArmor(customRender) {

				protected void initArmor() {
					this.modelLeggings = new ModelBiped();
					this.modelArmor = new ModelBiped();
				}
			});
			return customRender;
		});
	}

	public static class EntityCustom extends EntityMob {

		public EntityCustom(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 1000;
			this.isImmuneToFire = false;
			setNoAI(!true);
			this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true, true));
			this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.5, true));
			this.tasks.addTask(3, new EntityAIWander(this, 0.3));
			this.tasks.addTask(4, new EntityAILookIdle(this));
			this.tasks.addTask(5, new EntityAISwimming(this));
			this.tasks.addTask(6, new EntityAILeapAtTarget(this, (float) 0.8));
			this.tasks.addTask(7, new EntityAIPanic(this, 0.5));
			this.targetTasks.addTask(8, new EntityAIHurtByTarget(this, true));
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(MCreatorEnhancedBenandriteSword.block, (int) (1)));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(MCreatorInsanityArmor.helmet, (int) (1)));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(MCreatorInsanityArmor.body, (int) (1)));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(MCreatorInsanityArmor.legs, (int) (1)));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(MCreatorInsanityArmor.boots, (int) (1)));
		}

		@Override
		public EnumCreatureAttribute getCreatureAttribute() {
			return EnumCreatureAttribute.UNDEFINED;
		}

		@Override
		protected Item getDropItem() {
			return new ItemStack(MCreatorEnhancedBenandriteSword.block, (int) (1)).getItem();
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation(
					"entity.parrot.imitate.enderman"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.silverfish.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.witch.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			if (this.getEntityAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15D);
		}

		protected void dropRareDrop(int par1) {
			this.dropItem(new ItemStack(MCreatorBlindroniteOnAStick.block, (int) (1)).getItem(), 1);
		}
	}
}
