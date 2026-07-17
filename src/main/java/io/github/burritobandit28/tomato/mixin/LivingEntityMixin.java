package io.github.burritobandit28.tomato.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.burritobandit28.tomato.effect.SplattedEffect;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin extends Entity {




    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getPreferredEquipmentSlot", at = @At("TAIL"), cancellable = true)
    private void wearCarvedTomato(ItemStack stack, CallbackInfoReturnable<EquipmentSlot> cir) {
        if(stack.getItem() == ItemRegister.carved_tomato_block){
            cir.setReturnValue(EquipmentSlot.HEAD);
        }
    }

    @Group(min = 1, max = 1, name = "LivingEntityMixin")
    @WrapOperation(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    private float hijackFriction(Block instance, Operation<Float> original) {
        if (this.hasStatusEffect(SplattedEffect.SPLATTED_EFFECT) && !this.isSneaking()) {
            return 1.2f;
        }
        else {
            return original.call(instance);
        }
    }

    // this is needed for SinytraConnector
    @Group(min = 1, max = 1, name = "LivingEntityMixin")
    @WrapOperation(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getFriction(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)F"))
    private float hijackFriction(BlockState instance, WorldView levelReader, BlockPos blockPos, Entity entity, Operation<Float> original) {
        if (this.hasStatusEffect(SplattedEffect.SPLATTED_EFFECT) && !this.isSneaking()) {
            return 1.2f;
        }
        else {
            return original.call(instance, levelReader, blockPos, entity);
        }
    }



}
