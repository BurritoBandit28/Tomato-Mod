package io.github.burritobandit28.tomato.block.stems;

import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.block.ModBlockKeys;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class AttachedTomatoStem extends AttachedStemBlock {

    public AttachedTomatoStem(Settings settings) {
        super(ModBlockKeys.TOMATO_STEM_KEY, ModBlockKeys.TOMATO_BLOCK_KEY, ModBlockKeys.tomato_mega_seed_key, settings);

    }


}
