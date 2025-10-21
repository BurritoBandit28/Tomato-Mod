package io.github.burritobandit28.tomato.item;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import io.github.burritobandit28.tomato.Tomato;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ItemRegister {


    public static final Item tomato = register(
            "tomato",
            TomatoItem::new,
            new Item.Settings()
                    .food(new FoodComponent(2, 1.0F, false))
    );

    public static final Item tomato_seeds = register(
            "tomato_seeds",
            TomatoSeedsItem::new,
            new Item.Settings()
    );

    public static final RegistryKey<Item> tomato_mega_seed_key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Tomato.MOD_ID, "tomato_mega_seeds"));
    public static Item tomato_mega_seeds;


    // I'll be completley honest I always take TS stright from fabric wiki ICBA to do this myself
    // After they made it more confusing with Registry Keys yeah its technically better
    // But I am a stubborn fool
    // https://docs.fabricmc.net/develop/items/first-item
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Tomato.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static Item registerWithKey(Function<Item.Settings, Item> itemFactory, Item.Settings settings, RegistryKey<Item> itemKey) {


        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {
        tomato_mega_seeds = registerWithKey(
                TomatoMegaSeedsItem::new,
                new Item.Settings(),
                tomato_mega_seed_key
        );
    }
}
