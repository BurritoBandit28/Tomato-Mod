package io.github.burritobandit28.tomato;

import eu.pb4.polymer.core.api.other.PolymerSoundEvent;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.impl.object.builder.TradeOfferInternals;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;

public class Tomato implements ModInitializer {

    public static final String MOD_ID = "tomato";

    public static final SoundEvent TOMATO_SPLAT = of("tomato_splat");

    public static final RegistryKey<DamageType> TOMATOED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID,"tomatoed"));


    public static DamageSource YgetTomatoedDamageSource(ServerWorld world) {

        return new DamageSource(world.getRegistryManager().getEntryOrThrow(TOMATOED));

    }

    static SoundEvent of(String path) {
        var obj = SoundEvent.of(Identifier.of(MOD_ID, path));
        PolymerSoundEvent.registerOverlay(obj);
        return Registry.register(Registries.SOUND_EVENT, obj.id(), obj);
    }
    static RegistryEntry<SoundEvent> ofEntry(String path) {
        var obj = SoundEvent.of(Identifier.of(MOD_ID, path));
        PolymerSoundEvent.registerOverlay(obj);
        return Registry.registerReference(Registries.SOUND_EVENT, obj.id(), obj);
    }

    @Override
    public void onInitialize() {
        BlockRegister.init();

        ItemRegister.init();
        PolymerResourcePackUtils.addModAssets(MOD_ID);


        var tfactory = new TradeOffers.SellItemFactory(ItemRegister.tomato_seeds, 1, 1, 1, 20);

        TradeOfferHelper.registerWanderingTraderOffers(factory -> {
            factory.addAll(Identifier.of(MOD_ID,"tomato_seeds"), tfactory);
        });
    }
}
