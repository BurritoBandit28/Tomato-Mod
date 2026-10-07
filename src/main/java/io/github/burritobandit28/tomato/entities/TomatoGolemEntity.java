package io.github.burritobandit28.tomato.entities;

import io.github.burritobandit28.tomato.goals.HarvestPlantGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;

public class TomatoGolemEntity extends PathAwareEntity {


    private BlockPos anchorPlantPos = null;


    protected TomatoGolemEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        Arrays.fill(this.armorDropChances, 1.0F);
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack items = player.getStackInHand(hand);
        if (items.getItem() instanceof BoneMealItem && this.getHealth() < this.getMaxHealth()) {
            // bonemeal sound effect + particles
            items.decrement(1);
            this.heal(this.random.nextBetween(2,5));
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
        this.goalSelector.add(0, new FleeEntityGoal(this, PigEntity.class, 6.0F,1,1.2));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new HarvestPlantGoal(this, 9));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 4));
        this.goalSelector.add(2, new LookAtEntityGoal(this, CatEntity.class, 4));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));
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
}
