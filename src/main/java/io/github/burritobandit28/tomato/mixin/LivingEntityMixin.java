package io.github.burritobandit28.tomato.mixin;

import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {


    @Inject(method = "getPreferredEquipmentSlot", at = @At("TAIL"), cancellable = true)
    private void wearCarvedTomato(ItemStack stack, CallbackInfoReturnable<EquipmentSlot> cir) {
        if(stack.getItem() == ItemRegister.carved_tomato_block){
            cir.setReturnValue(EquipmentSlot.HEAD);
        }
    }




}
