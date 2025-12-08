package io.github.burritobandit28.tomato.item;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.block.BlockRegister;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Optional;

public class ItemRegister {

    public static final Item tomato = new TomatoItem(
            new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(2).saturationModifier(1.0F).build())
            );
    public static final Item tomato_seeds = new BlockItem(BlockRegister.SMALL_TOMATO_PLANT,new Item.Settings());
    public static final Item tomato_mega_seeds = new BlockItem(BlockRegister.TOMATO_STEM,new Item.Settings());
    public static final Item tomato_block = new BlockItem(BlockRegister.TOMATO_BLOCK,new Item.Settings());
    public static final Item carved_tomato_block = new BlockItem(BlockRegister.CARVED_TOMATO_BLOCK,new Item.Settings());
    public static final Item lit_carved_tomato_block = new BlockItem(BlockRegister.LIT_CARVED_TOMATO_BLOCK,new Item.Settings());

    public static final RegistryKey<ItemGroup> TOMATO_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Tomato.ID("item_group"));
    public static final ItemGroup TOMATO_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ItemRegister.carved_tomato_block))
            .displayName(Text.translatable("itemGroup.tomato"))
            .build();

    public static void init() {
        Registry.register(Registries.ITEM, Tomato.ID("tomato"), tomato);
        Registry.register(Registries.ITEM, Tomato.ID("tomato_seeds"), tomato_seeds);
        Registry.register(Registries.ITEM, Tomato.ID("tomato_mega_seeds"), tomato_mega_seeds);
        Registry.register(Registries.ITEM, Tomato.ID("tomato_block"), tomato_block);
        Registry.register(Registries.ITEM, Tomato.ID("carved_tomato_block"), carved_tomato_block);
        Registry.register(Registries.ITEM, Tomato.ID("lit_carved_tomato_block"), lit_carved_tomato_block);

        Registry.register(Registries.ITEM_GROUP, TOMATO_GROUP_KEY, TOMATO_GROUP);

        ItemGroupEvents.modifyEntriesEvent(TOMATO_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(tomato_seeds);
            itemGroup.add(tomato);
            itemGroup.add(tomato_mega_seeds);
            itemGroup.add(tomato_block);
            itemGroup.add(carved_tomato_block);
            itemGroup.add(lit_carved_tomato_block);

        });

    }

}
