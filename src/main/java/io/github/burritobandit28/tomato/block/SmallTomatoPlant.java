package io.github.burritobandit28.tomato.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.BeetrootsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

public class SmallTomatoPlant extends CropBlock implements PolymerBlock, PolymerTexturedBlock {

    private BlockState[] states = new BlockState[4];



    public SmallTomatoPlant(Settings settings) {
        super(settings);

        PolymerBlockModel model0 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/tomato_plant0"),0,0);
        PolymerBlockModel model1 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/tomato_plant1"),0,0);
        PolymerBlockModel model2 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/tomato_plant2"),0,0);
        PolymerBlockModel model3 = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/parent_plant"),0,0);


        this.states[0] = PolymerBlockResourceUtils.requestBlock(BlockModelType.VINES_BLOCK, model0);
        this.states[1] = PolymerBlockResourceUtils.requestBlock(BlockModelType.VINES_BLOCK, model1);
        this.states[2] = PolymerBlockResourceUtils.requestBlock(BlockModelType.VINES_BLOCK, model2);
        this.states[3] = PolymerBlockResourceUtils.requestBlock(BlockModelType.VINES_BLOCK, model3);
    }


    @Override
    public ItemConvertible getSeedsItem() {
        return ItemRegister.tomato_seeds;
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    public int getAge(BlockState state) {
        return Math.clamp(state.get(this.getAgeProperty()), 0,4);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        if (state.get(AGE) >= 4) {
            // drop 2 tomatoes
            var stack = ItemRegister.tomato.getDefaultStack();
            stack.setCount(2);
            ItemScatterer.spawn(world,pos.getX(),pos.getY(),pos.getZ(), stack);

            var blockSoundGroup = state.getSoundGroup();
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new PlaySoundS2CPacket(Registries.SOUND_EVENT.getEntry(SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES), SoundCategory.BLOCKS, pos.getX(), pos.getY(), pos.getZ(), (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F, player.getRandom().nextLong()));
            world.setBlockState(pos, state.with(AGE, 2));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        int age = state.get(AGE);
        if (age >= 4) {
            return this.states[3];
        }
        if (age >=2) {
            return this.states[2];
        }
        if (age == 1) {
            return this.states[1];
        }
        return this.states[0];
    }
}
