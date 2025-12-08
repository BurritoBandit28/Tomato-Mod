package io.github.burritobandit28.tomato.mixin;

import com.google.common.collect.ImmutableList;
import io.github.burritobandit28.tomato.block.BlockRegister;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Debug(export = true)
@Mixin(StructureProcessorLists.class)
public class StructureProcessorListsMixin {

    @Shadow
    @Final
    public static RegistryKey<StructureProcessorList> FARM_SAVANNA;


    @Inject(
            method = "register(Lnet/minecraft/registry/Registerable;Lnet/minecraft/registry/RegistryKey;Ljava/util/List;)V",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private static void register$addCrops(Registerable<StructureProcessorList> processorListRegisterable, RegistryKey<StructureProcessorList> key, List<StructureProcessor> processors, CallbackInfo ci) {

        System.out.println("\n\nHELP\n\n");
        if (key == FARM_SAVANNA) {
            processorListRegisterable.register(key, new StructureProcessorList(ImmutableList.of(
                    new RuleStructureProcessor(
                            ImmutableList.of(
                                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()),
                                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 1.0F), AlwaysTrueRuleTest.INSTANCE, BlockRegister.SMALL_TOMATO_PLANT.getDefaultState())
                            )
                    )
            )));
        }
        else {
            processorListRegisterable.register(key, new StructureProcessorList(processors));
        }
        ci.cancel();

    }
}
