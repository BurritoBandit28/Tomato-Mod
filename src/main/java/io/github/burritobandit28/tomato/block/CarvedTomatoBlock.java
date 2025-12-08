package io.github.burritobandit28.tomato.block;


import com.mojang.serialization.MapCodec;
import io.github.burritobandit28.tomato.Tomato;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class CarvedTomatoBlock extends Block{


    public static final EnumProperty<Direction> FACING;

    public CarvedTomatoBlock(Settings settings) {
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
