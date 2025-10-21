package io.github.burritobandit28.tomato.block;

import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.block.stems.AttachedTomatoStem;
import io.github.burritobandit28.tomato.block.stems.TomatoStem;
import io.github.burritobandit28.tomato.item.PolymerBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Objects;
import java.util.function.Function;

public class BlockRegister {

    public static Block TOMATO_BLOCK = register("tomato_block", TomatoBlock::new, AbstractBlock.Settings.copy(Blocks.PUMPKIN).mapColor(MapColor.BRIGHT_RED), true);

    public static Block CARVED_TOMATO_BLOCK = register("carved_tomato_block", CarvedTomatoBlock::new, AbstractBlock.Settings.copy(Blocks.CARVED_PUMPKIN).mapColor(MapColor.BRIGHT_RED), true);

    public static Block LIT_CARVED_TOMATO_BLOCK = register("lit_carved_tomato_block", LitCarvedTomatoBlock::new, AbstractBlock.Settings.copy(Blocks.JACK_O_LANTERN).mapColor(MapColor.BRIGHT_RED), true);

    public static Block SMALL_TOMATO = register("small_tomato_plant", SmallTomatoPlant::new, AbstractBlock.Settings.copy(Blocks.BEETROOTS).mapColor(MapColor.BRIGHT_RED), false);

    public static RegistryKey<Block> ATTACHED_TOMATO_STEM_KEY= keyOfBlock("attached_tomato_stem");
    public static RegistryKey<Block> TOMATO_STEM_KEY = keyOfBlock("tomato_stem");

    public static Block ATTACHED_TOMATO_STEM;
    public static Block TOMATO_STEM;

    // I'll be completley honest I always take TS stright from fabric wiki ICBA to do this myself
    // After they made it more confusing with Registry Keys yeah its technically better
    // But I am a stubborn fool
    // https://docs.fabricmc.net/develop/blocks/first-block
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`

        // I want the carved one to be wearable
        if (Objects.equals(name, "carved_tomato_block")) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            PolymerBlockItem blockItem = new PolymerBlockItem(new Item.Settings().equippable(EquipmentSlot.HEAD).registryKey(itemKey).useBlockPrefixedTranslationKey(), block);

            Registry.register(Registries.ITEM, itemKey, blockItem);
        }
        else if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            // Wow look I edited it very slightly to use Polymer Wowowowowowowowow im so innovative
            PolymerBlockItem blockItem = new  PolymerBlockItem(new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey(), block);;

            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Tomato.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Tomato.MOD_ID, name));
    }


    public static Block registerWithKey(RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = (Block)factory.apply(settings.registryKey(key));
        return (Block)Registry.register(Registries.BLOCK, key, block);
    }

    public static void init(){
        ATTACHED_TOMATO_STEM = registerWithKey(ATTACHED_TOMATO_STEM_KEY, AttachedTomatoStem::new, AbstractBlock.Settings.copy(Blocks.ATTACHED_MELON_STEM));
        TOMATO_STEM  = registerWithKey(TOMATO_STEM_KEY, TomatoStem::new, AbstractBlock.Settings.copy(Blocks.MELON_STEM));
    }

}
