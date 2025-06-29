package com.tonyv2.milometermod.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.tonyv2.milometermod.config.MilometerConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Component.literal("Milometer Mod Config"));

            ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            general.addEntry(entryBuilder.startIntField(Component.literal("HUD X"), MilometerConfig.INSTANCE.hudX)
                    .setDefaultValue(10)
                    .setSaveConsumer(newValue -> MilometerConfig.INSTANCE.hudX = newValue)
                    .build());

            general.addEntry(entryBuilder.startIntField(Component.literal("HUD Y"), MilometerConfig.INSTANCE.hudY)
                    .setDefaultValue(10)
                    .setSaveConsumer(newValue -> MilometerConfig.INSTANCE.hudY = newValue)
                    .build());

            builder.setSavingRunnable(MilometerConfig.INSTANCE::save);

            return builder.build();
        };
    }
}