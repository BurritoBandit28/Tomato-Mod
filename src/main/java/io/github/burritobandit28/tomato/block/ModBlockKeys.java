package io.github.burritobandit28.tomato.block;

import io.github.burritobandit28.tomato.Tomato;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlockKeys {

    public static final RegistryKey<Block> TOMATO_BLOCK_KEY = of("tomato_block");
    public static final RegistryKey<Block> TOMATO_STEM_KEY = of("tomato_stem");
    public static final RegistryKey<Block> TOMATO_ATTACHED_STEM_KEY = of("tomato_attached_stem");

    // theres an item one here cus uh idc its relevant
    public static final RegistryKey<Item> tomato_mega_seed_key = RegistryKey.of(RegistryKeys.ITEM, Tomato.ID("tomato_mega_seeds"));


    private static RegistryKey<Block> of(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Tomato.ID(id));
    }

}
