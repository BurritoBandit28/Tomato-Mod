package io.github.burritobandit28.tomato;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.impl.object.builder.TradeOfferInternals;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;

public class Tomato implements ModInitializer {

    public static final String MOD_ID = "tomato";

    @Override
    public void onInitialize() {
        BlockRegister.init();

        ItemRegister.init();
        PolymerResourcePackUtils.addModAssets(MOD_ID);


        var tfactory = new TradeOffers.SellItemFactory(ItemRegister.tomato_seeds, 1, 1, 1, 20);

        TradeOfferHelper.registerWanderingTraderOffers(factory -> {
            factory.addAll(Identifier.of(MOD_ID,"tomato_seeds"), tfactory);
        });
    }
}
