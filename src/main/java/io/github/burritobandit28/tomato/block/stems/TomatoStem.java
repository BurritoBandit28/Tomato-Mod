package io.github.burritobandit28.tomato.block.stems;

import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StemBlock;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import xyz.nucleoid.packettweaker.PacketContext;

public class TomatoStem extends StemBlock implements PolymerBlock {

    public TomatoStem(Settings settings) {
        super(BlockRegister.TOMATO_BLOCK.getRegistryEntry().registryKey(), BlockRegister.ATTACHED_TOMATO_STEM_KEY, ItemRegister.tomato_mega_seed_key, settings);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return Blocks.MELON_STEM.getDefaultState().with(AGE, state.get(AGE));
    }
}
