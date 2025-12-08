package io.github.burritobandit28.tomato.block;

import com.mojang.serialization.MapCodec;
import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SmallTomatoPlant extends CropBlock {

    public static final MapCodec<SmallTomatoPlant> CODEC = createCodec(SmallTomatoPlant::new);

    @Override
    public MapCodec<SmallTomatoPlant> getCodec() {
        return CODEC;
    }

    public SmallTomatoPlant(Settings settings) {
        super(settings);
    }


    @Override
    public ItemConvertible getSeedsItem() {
        return ItemRegister.tomato_seeds;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    public int getAge(BlockState state) {
        return Math.clamp(state.get(this.getAgeProperty()), 0,3);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {

            if (state.get(AGE) >= 3) {
                // drop 2 tomatoes
                var stack = ItemRegister.tomato.getDefaultStack();
                stack.setCount(2);
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);

                var blockSoundGroup = state.getSoundGroup();
                ((ServerPlayerEntity) player).networkHandler.sendPacket(new PlaySoundS2CPacket(Registries.SOUND_EVENT.getEntry(SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES), SoundCategory.BLOCKS, pos.getX(), pos.getY(), pos.getZ(), (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F, player.getRandom().nextLong()));
                world.setBlockState(pos, state.with(AGE, 2));
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

}
