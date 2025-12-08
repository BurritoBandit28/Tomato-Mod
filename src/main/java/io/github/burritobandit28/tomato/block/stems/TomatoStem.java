package io.github.burritobandit28.tomato.block.stems;

import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.block.ModBlockKeys;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StemBlock;


public class TomatoStem extends StemBlock {

    public TomatoStem(Settings settings) {
        super(ModBlockKeys.TOMATO_BLOCK_KEY, ModBlockKeys.TOMATO_ATTACHED_STEM_KEY, ModBlockKeys.tomato_mega_seed_key, settings);
    }

}
