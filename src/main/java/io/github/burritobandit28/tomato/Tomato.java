package io.github.burritobandit28.tomato;

import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.effect.SplattedEffect;
import io.github.burritobandit28.tomato.entities.EntityRegister;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.village.*;
import net.minecraft.world.WanderingTraderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tomato implements ModInitializer {

    public static final String MOD_ID = "tomato";

    public static Identifier ID(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static final RegistryKey<DamageType> TOMATO_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "tomato"));

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final SoundEvent TOMATO_SPLAT = of("tomato_splat");

    static SoundEvent of(String path) {
        var obj = SoundEvent.of(Identifier.of(MOD_ID, path));
        return Registry.register(Registries.SOUND_EVENT, obj.getId(), obj);
    }

    public static final SimpleParticleType TOMATO_SPLAT_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType GOLDEN_TOMATO_SPLAT_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType SPLATTED_EFFECT_PARTICLE = FabricParticleTypes.simple();

    @Override
    public void onInitialize() {

        LOGGER.info("Loading the Tomato Mod!");
        Registry.register(Registries.PARTICLE_TYPE, ID("tomato_splat_particle"), TOMATO_SPLAT_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, ID("golden_tomato_splat_particle"), GOLDEN_TOMATO_SPLAT_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, ID("tomato_splat_effect_particle"), SPLATTED_EFFECT_PARTICLE);


        ItemRegister.init();
        BlockRegister.init();
        SplattedEffect.init();
        EntityRegister.init();

        DispenserBlock.registerProjectileBehavior(ItemRegister.tomato);
        DispenserBlock.registerProjectileBehavior(ItemRegister.golden_tomato);
        DispenserBlock.registerBehavior(BlockRegister.CARVED_TOMATO_BLOCK, new FallibleItemDispenserBehavior() {
            @Override
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {

                this.setSuccess(ArmorItem.dispenseArmor(pointer, stack));

                return stack;
            }
        });


        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato_seeds, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato_mega_seeds, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato_block, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.carved_tomato_block, 0.65f);

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 1), new ItemStack(ItemRegister.tomato_seeds, 16), 2, 5, 0.05F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(ItemRegister.tomato, 16), new ItemStack( Items.EMERALD), 5, 5, 0.05F));

        });


        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(ItemRegister.tomato_block, 6), new ItemStack(Items.EMERALD, 1 ), 12, 5, 0.05F));
        });
    }


    public static DamageSource getTomatoDamage(ServerWorld world, Entity attacler) {

        return new DamageSource(
                world.getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(TOMATO_DAMAGE), attacler);

    }

    public static Integer posOrNeg(double val) {
        if (val >0) {
            return 1;
        }
        if (val < 0) {
            return -1;
        }
        return 0;
    }

}
