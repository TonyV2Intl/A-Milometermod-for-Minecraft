package com.tonyv2.milometermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.tonyv2.milometermod.config.MilometerConfig;
import com.tonyv2.milometermod.MilometerModClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

public class ResetDistanceCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(ClientCommandManager.literal("milometer")
                .then(ClientCommandManager.literal("reset")
                        .executes(context -> {
                            MilometerModClient.movementTracker.resetDistance();
                            context.getSource().sendFeedback(Component.literal("里程表距离已重置"));
                            return 1;
                        }))
                .then(ClientCommandManager.literal("setpos")
                        .then(ClientCommandManager.argument("x", IntegerArgumentType.integer())
                                .then(ClientCommandManager.argument("y", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int x = IntegerArgumentType.getInteger(context, "x");
                                            int y = IntegerArgumentType.getInteger(context, "y");
                                            MilometerConfig.INSTANCE.hudX = x;
                                            MilometerConfig.INSTANCE.hudY = y;
                                            MilometerConfig.INSTANCE.save();
                                            context.getSource().sendFeedback(Component.literal("HUD位置设置为 (" + x + ", " + y + ")"));
                                            return 1;
                                        })))
                        .then(ClientCommandManager.argument("corner", StringArgumentType.word())
                                .executes(context -> {
                                    String corner = StringArgumentType.getString(context, "corner");
                                    Minecraft client = Minecraft.getInstance();
                                    int screenWidth = client.getWindow().getGuiScaledWidth();
                                    int screenHeight = client.getWindow().getGuiScaledHeight();

                                    int x, y;
                                    switch (corner.toLowerCase()) {
                                        case "topleft":
                                            x = 10;
                                            y = 10;
                                            break;
                                        case "topright":
                                            x = screenWidth - 150;
                                            y = 10;
                                            break;
                                        case "bottomleft":
                                            x = 10;
                                            y = screenHeight - 40;
                                            break;
                                        case "bottomright":
                                            x = screenWidth - 150;
                                            y = screenHeight - 40;
                                            break;
                                        default:
                                            context.getSource().sendError(Component.literal("无效的角落位置！使用: topleft, topright, bottomleft, bottomright"));
                                            return 0;
                                    }

                                    MilometerConfig.INSTANCE.hudX = x;
                                    MilometerConfig.INSTANCE.hudY = y;
                                    MilometerConfig.INSTANCE.save();
                                    context.getSource().sendFeedback(Component.literal("HUD位置设置为" + corner + "角落 (" + x + ", " + y + ")"));
                                    return 1;
                                })))
                .then(ClientCommandManager.literal("help")
                        .executes(context -> {
                            context.getSource().sendFeedback(Component.literal("=== 里程表模组指令帮助 ==="));
                            context.getSource().sendFeedback(Component.literal("/milometer reset - 重置累计距离"));
                            context.getSource().sendFeedback(Component.literal("/milometer setpos <x> <y> - 设置HUD位置"));
                            context.getSource().sendFeedback(Component.literal("/milometer setpos <corner> - 设置HUD到屏幕角落"));
                            context.getSource().sendFeedback(Component.literal("  可用角落: topleft, topright, bottomleft, bottomright"));
                            context.getSource().sendFeedback(Component.literal("/milometer help - 显示此帮助"));
                            return 1;
                        })));
    }
}