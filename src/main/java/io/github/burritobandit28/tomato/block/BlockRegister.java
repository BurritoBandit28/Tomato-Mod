package io.github.burritobandit28.tomato.block;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.block.stems.AttachedTomatoStem;
import io.github.burritobandit28.tomato.block.stems.TomatoStem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;

public class BlockRegister {

    public static Block TOMATO_BLOCK = new TomatoBlock(AbstractBlock.Settings.copy(Blocks.PUMPKIN).mapColor(MapColor.BRIGHT_RED));
    public static Block CARVED_TOMATO_BLOCK = new CarvedTomatoBlock(AbstractBlock.Settings.copy(Blocks.CARVED_PUMPKIN).mapColor(MapColor.BRIGHT_RED));
    public static Block LIT_CARVED_TOMATO_BLOCK = new LitCarvedTomatoBlock(AbstractBlock.Settings.copy(Blocks.JACK_O_LANTERN).mapColor(MapColor.BRIGHT_RED));
    public static Block SMALL_TOMATO_PLANT = new SmallTomatoPlant(AbstractBlock.Settings.copy(Blocks.BEETROOTS).mapColor(MapColor.BRIGHT_RED));

    public static Block ATTACHED_TOMATO_STEM = new AttachedTomatoStem(AbstractBlock.Settings.copy(Blocks.ATTACHED_MELON_STEM));
    public static Block TOMATO_STEM = new TomatoStem(AbstractBlock.Settings.copy(Blocks.MELON_STEM));


    public static void init() {

        Registry.register(Registries.BLOCK, Tomato.ID("tomato_block"), TOMATO_BLOCK);
        Registry.register(Registries.BLOCK, Tomato.ID("carved_tomato_block"), CARVED_TOMATO_BLOCK);
        Registry.register(Registries.BLOCK, Tomato.ID("lit_carved_tomato_block"), LIT_CARVED_TOMATO_BLOCK);
        Registry.register(Registries.BLOCK, Tomato.ID("small_tomato_plant"), SMALL_TOMATO_PLANT);

        Registry.register(Registries.BLOCK, ModBlockKeys.TOMATO_ATTACHED_STEM_KEY, ATTACHED_TOMATO_STEM);
        Registry.register(Registries.BLOCK, ModBlockKeys.TOMATO_STEM_KEY, TOMATO_STEM);

    }

}
