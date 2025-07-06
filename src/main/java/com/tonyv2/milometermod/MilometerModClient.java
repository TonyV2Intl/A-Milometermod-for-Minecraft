package com.tonyv2.milometermod;

import com.tonyv2.milometermod.client.MilometerHud;
import com.tonyv2.milometermod.command.SetupCommand;
import com.tonyv2.milometermod.config.MilometerConfig;
import com.tonyv2.milometermod.event.PlayerMovementTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class MilometerModClient implements ClientModInitializer {
    public static PlayerMovementTracker movementTracker;
    public static MilometerConfig config = MilometerConfig.INSTANCE;

    @Override
    public void onInitializeClient() {
        config = MilometerConfig.load();
        movementTracker = new PlayerMovementTracker();
        HudRenderCallback.EVENT.register(new MilometerHud());
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> SetupCommand.register(dispatcher));
    }
}
