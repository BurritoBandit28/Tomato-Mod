package io.github.burritobandit28.tomato.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerBlockItem extends BlockItem implements PolymerItem {


    private final Item polymerItem;
    private final boolean polymerUseModel;

    public PolymerBlockItem(Settings settings, Block block) {
        this(settings, Items.TRIAL_KEY, true, block);
    }

    public PolymerBlockItem(Settings settings, Item polymerItem, Block block) {
        this(settings, polymerItem, false, block);
    }

    public PolymerBlockItem(Settings settings, Item polymerItem, boolean useModel, Block block) {
        super(block, settings);
        this.polymerItem = polymerItem;
        this.polymerUseModel = useModel;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return this.polymerItem;
    }

    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return this.polymerUseModel ? PolymerItem.super.getPolymerItemModel(stack, context) : null;
    }

    // https://github.com/Patbox/FactoryTools/blob/b4ef77da7ac01bbbbc29a6dae899968815cb2784/src/main/java/eu/pb4/factorytools/api/item/FactoryBlockItem.java#L36
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var x = super.useOnBlock(context);
        if (x == ActionResult.SUCCESS) {
            if (context.getPlayer() instanceof ServerPlayerEntity player) {
                var pos = Vec3d.ofCenter(context.getBlockPos().offset(context.getSide()));
                var blockSoundGroup = this.getBlock().getDefaultState().getSoundGroup();
                player.networkHandler.sendPacket(new PlaySoundS2CPacket(Registries.SOUND_EVENT.getEntry(this.getPlaceSound(this.getBlock().getDefaultState())), SoundCategory.BLOCKS, pos.x, pos.y, pos.z, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F, context.getPlayer().getRandom().nextLong()));
            }
            return ActionResult.SUCCESS_SERVER;
        }
        return x;
    }
}

