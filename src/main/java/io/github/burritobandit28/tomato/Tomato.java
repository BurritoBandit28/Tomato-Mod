package io.github.burritobandit28.tomato;

import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.entities.ThrownTomatoEntity;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
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

    @Override
    public void onInitialize() {

        LOGGER.info("Loading the Tomato Mod!");
        Registry.register(Registries.PARTICLE_TYPE, ID("tomato_splat_particle"), TOMATO_SPLAT_PARTICLE);


        ItemRegister.init();
        BlockRegister.init();

        ThrownTomatoEntity.init();

        DispenserBlock.registerProjectileBehavior(ItemRegister.tomato);
        DispenserBlock.registerBehavior(BlockRegister.CARVED_TOMATO_BLOCK, new FallibleItemDispenserBehavior() {
            @Override
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {

                this.setSuccess(ArmorItem.dispenseArmor(pointer, stack));

                return stack;
            }
        });

        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato_seeds, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato_mega_seeds, 0.4f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.tomato_block, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(ItemRegister.carved_tomato_block, 0.65f);

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
