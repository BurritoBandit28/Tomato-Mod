package io.github.burritobandit28.tomato.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import io.github.burritobandit28.tomato.Tomato;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import xyz.nucleoid.packettweaker.PacketContext;

public class LitCarvedTomatoBlock extends SimplePolymerBlock implements PolymerTexturedBlock {


    public static final EnumProperty<Direction> FACING;
    private BlockState[] states = new BlockState[4];

    public LitCarvedTomatoBlock(Settings settings) {
        super(settings, Blocks.PUMPKIN);

        PolymerBlockModel model0 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/lit_carved_tomato_block"),0,0);
        PolymerBlockModel model1 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/lit_carved_tomato_block"),0,90);
        PolymerBlockModel model2 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/lit_carved_tomato_block"),0,180);
        PolymerBlockModel model3 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/lit_carved_tomato_block"),0,270);


        this.states[0] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, model0);
        this.states[1] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, model1);
        this.states[2] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, model2);
        this.states[3] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, model3);


        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));


    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        switch (state.get(FACING)) {
            case EAST -> {return this.states[1];}
            case SOUTH -> {return this.states[2];}
            case WEST -> {return this.states[3];}
            default -> {return this.states[0];}
        }
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
