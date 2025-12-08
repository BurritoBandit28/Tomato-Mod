package io.github.burritobandit28.tomato.block;

import com.mojang.serialization.MapCodec;
import io.github.burritobandit28.tomato.Tomato;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class LitCarvedTomatoBlock extends Block {


    public static final MapCodec<LitCarvedTomatoBlock> CODEC = createCodec(LitCarvedTomatoBlock::new);

    @Override
    public MapCodec<LitCarvedTomatoBlock> getCodec() {
        return CODEC;
    }

    public static final EnumProperty<Direction> FACING;


    public LitCarvedTomatoBlock(Settings settings) {
        super(settings);

        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }


    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }

}
