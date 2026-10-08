package io.github.burritobandit28.tomato.entities;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.goals.HarvestPlantGoal;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;

public class TomatoGolemEntity extends GolemEntity implements RangedAttackMob {


    private BlockPos anchorPlantPos = null;


    protected TomatoGolemEntity(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    //@Override
    //public boolean isPersistent() {
    //    return true;
    //}

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack items = player.getStackInHand(hand);
        if (items.getItem() instanceof BoneMealItem && this.getHealth() < this.getMaxHealth()) {
            // bonemeal sound effect + particles
            items.decrement(1);
            this.heal(this.random.nextBetween(2,5));
            ParticleUtil.spawnParticlesAround(this.getWorld(), this.getBlockPos(), 12, ParticleTypes.HAPPY_VILLAGER);
            return ActionResult.SUCCESS;
        }
        if (( items.getItem() instanceof ArmorItem armorItem && armorItem.getSlotType() == EquipmentSlot.HEAD)  || items.getItem() == Items.AIR) {

            // instanceofinstanceofinstanceofinstanceofinstanceofinstanceofinstanceofinstanceofinstanceofinstanceofinstanceof
            if (items.getItem() instanceof ArmorItem helmet) {
                playSound(helmet.getEquipSound().value());
            }
            ItemStack current_helmet = this.getEquippedStack(EquipmentSlot.HEAD);
            equipStack(EquipmentSlot.HEAD, items);
            player.setStackInHand(hand, current_helmet);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    protected void initGoals() {
    //  this.goalSelector.add(0, new FindAndOrbitTomatoPlantGoal(this, 10));
        this.goalSelector.add(1, new ProjectileAttackGoal(this, (double)1.25F, 20, 10.0F));
        this.goalSelector.add(0, new FleeEntityGoal(this, PigEntity.class, 6.0F,1,1.2));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new HarvestPlantGoal(this, 9));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 4));
        this.goalSelector.add(2, new LookAtEntityGoal(this, CatEntity.class, 4));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, MobEntity.class, 10, true, false, (entity) -> ((MobEntity)entity).getType().isIn(Tomato.TOMATO_GOLEM_TARGETS)));
    }

    public static DefaultAttributeContainer.Builder createTomatoGolemAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }

    public void setAnchorPlantPos(BlockPos anchorPlantPos) {
        this.anchorPlantPos = anchorPlantPos;
    }

    public BlockPos getAnchorPlantPos() {
        return anchorPlantPos;
    }

    // stolen from snow golem lol
    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        ThrownTomatoEntity tomatoEntity = new ThrownTomatoEntity(this.getWorld(), this);
        double d = target.getEyeY() - (double)1.1F;
        double e = target.getX() - this.getX();
        double f = d - tomatoEntity.getY();
        double g = target.getZ() - this.getZ();
        double h = Math.sqrt(e * e + g * g) * (double)0.2F;
        tomatoEntity.setVelocity(e, f + h, g, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(tomatoEntity);
    }

    @Override
    public boolean canTarget(EntityType<?> type) {
        return type.isIn(Tomato.TOMATO_GOLEM_TARGETS);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        //                                                          string comparisons because ica anymore
        return super.isInvulnerableTo(damageSource) || damageSource.getName().equals("tomato_hurt");
    }

    @Override
    protected float getDropChance(EquipmentSlot slot) {
        return 1.0F;
    }
}
