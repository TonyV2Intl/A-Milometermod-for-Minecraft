package com.tonyv2.milometermod.client;

import com.tonyv2.milometermod.MilometerModClient;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class MilometerHud implements HudRenderCallback {
    private static final int TEXT_COLOR = 0xFFFFFF;

    @Override
    public void onHudRender(GuiGraphics guiGraphics, float tickDelta) {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null) {
            Font font = client.font;
            double speed = MilometerModClient.movementTracker.getSpeed();
            double distance = MilometerModClient.movementTracker.getTotalDistance();

            String speedText = String.format("速度: %.2f m/s", speed);
            String distanceText = String.format("距离: %.2f m", distance);


            // Default position: top-left corner
            int x = MilometerModClient.config.hudX;
            int y = MilometerModClient.config.hudY;

            guiGraphics.drawString(font, speedText, x, y, TEXT_COLOR);
            guiGraphics.drawString(font, distanceText, x, y + font.lineHeight + 2, TEXT_COLOR);
        }
    }
}