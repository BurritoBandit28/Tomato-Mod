package io.github.burritobandit28.tomato.block.stems;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class AttachedTomatoStem extends AttachedStemBlock implements PolymerBlock{

    public AttachedTomatoStem(Settings settings) {
        super(BlockRegister.TOMATO_STEM_KEY, BlockRegister.TOMATO_BLOCK.getRegistryEntry().registryKey(), ItemRegister.tomato_mega_seed_key, settings);

    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return Blocks.ATTACHED_MELON_STEM.getDefaultState().with(FACING, state.get(FACING));
    }
}
