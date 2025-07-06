package com.tonyv2.milometermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.tonyv2.milometermod.config.MilometerConfig;
import com.tonyv2.milometermod.MilometerModClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class SetupCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("milometer")
                .then(ClientCommandManager.literal("reset")
                        .executes(context -> {
                            MilometerModClient.movementTracker.resetDistance();
                            context.getSource().sendFeedback(Component.literal("[MilometerMod] 里程表距离已重置"));
                            return 1;
                        }))
                .then(ClientCommandManager.literal("setdistancemanually")
                        .then(ClientCommandManager.argument("newtotaldistance", DoubleArgumentType.doubleArg())
                                .executes(context -> {
                                    MilometerModClient.movementTracker.newtotalDistance = DoubleArgumentType.getDouble(context, "newtotaldistance");
                                    MilometerModClient.movementTracker.SetDistanceManually();
                                    context.getSource().sendFeedback(Component.literal("[MilometerMod] 里程表距离已手动设置为 " + MilometerModClient.movementTracker.newtotalDistance + " m"));
                                    return 1;
                                })))
                .then(ClientCommandManager.literal("setpos")
                        .then(ClientCommandManager.literal("-xy")
                                .then(ClientCommandManager.argument("x", IntegerArgumentType.integer())
                                        .then(ClientCommandManager.argument("y", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int x = IntegerArgumentType.getInteger(context, "x");
                                                    int y = IntegerArgumentType.getInteger(context, "y");
                                                    MilometerConfig.INSTANCE.hudX = x;
                                                    MilometerConfig.INSTANCE.hudY = y;
                                                    MilometerConfig.INSTANCE.save();
                                                    MilometerModClient.config.hudX = MilometerConfig.INSTANCE.hudX;
                                                    MilometerModClient.config.hudY = MilometerConfig.INSTANCE.hudY;
                                                    context.getSource().sendFeedback(Component.literal("[MilometerMod] HUD位置设置为 (" + x + ", " + y + ")"));
                                                    return 1;
                                                }))))
                        .then(ClientCommandManager.literal("-corner")
                                .then(ClientCommandManager.literal("topleft")
                                        .executes(context -> {
                                            int x = 10;
                                            int y = 10;
                                            MilometerConfig.INSTANCE.hudX = x;
                                            MilometerConfig.INSTANCE.hudY = y;
                                            MilometerConfig.INSTANCE.save();
                                            MilometerModClient.config.hudX = MilometerConfig.INSTANCE.hudX;
                                            MilometerModClient.config.hudY = MilometerConfig.INSTANCE.hudY;
                                            context.getSource().sendFeedback(Component.literal("[MilometerMod] HUD位置设置为左上角 (" + x + ", " + y + ")"));
                                            return 1;
                                        }))
                                .then(ClientCommandManager.literal("topright")
                                        .executes(context -> {
                                            Minecraft client = Minecraft.getInstance();
                                            int screenWidth = client.getWindow().getGuiScaledWidth();
                                            int x = screenWidth - 80;
                                            int y = 10;
                                            MilometerConfig.INSTANCE.hudX = x;
                                            MilometerConfig.INSTANCE.hudY = y;
                                            MilometerConfig.INSTANCE.save();
                                            MilometerModClient.config.hudX = MilometerConfig.INSTANCE.hudX;
                                            MilometerModClient.config.hudY = MilometerConfig.INSTANCE.hudY;
                                            context.getSource().sendFeedback(Component.literal("[MilometerMod] HUD位置设置为右上角 (" + x + ", " + y + ")"));
                                            return 1;
                                        }))
                                .then(ClientCommandManager.literal("bottomleft")
                                        .executes(context -> {
                                            Minecraft client = Minecraft.getInstance();
                                            int screenHeight = client.getWindow().getGuiScaledHeight();
                                            int x = 10;
                                            int y = screenHeight - 30;
                                            MilometerConfig.INSTANCE.hudX = x;
                                            MilometerConfig.INSTANCE.hudY = y;
                                            MilometerConfig.INSTANCE.save();
                                            MilometerModClient.config.hudX = MilometerConfig.INSTANCE.hudX;
                                            MilometerModClient.config.hudY = MilometerConfig.INSTANCE.hudY;
                                            context.getSource().sendFeedback(Component.literal("[MilometerMod] HUD位置设置为左下角 (" + x + ", " + y + ")"));
                                            return 1;
                                        }))
                                .then(ClientCommandManager.literal("bottomright")
                                        .executes(context -> {
                                            Minecraft client = Minecraft.getInstance();
                                            int screenWidth = client.getWindow().getGuiScaledWidth();
                                            int screenHeight = client.getWindow().getGuiScaledHeight();
                                            int x = screenWidth - 80;
                                            int y = screenHeight - 30;
                                            MilometerConfig.INSTANCE.hudX = x;
                                            MilometerConfig.INSTANCE.hudY = y;
                                            MilometerConfig.INSTANCE.save();
                                            MilometerModClient.config.hudX = MilometerConfig.INSTANCE.hudX;
                                            MilometerModClient.config.hudY = MilometerConfig.INSTANCE.hudY;
                                            context.getSource().sendFeedback(Component.literal("[MilometerMod] HUD位置设置为右下角 (" + x + ", " + y + ")"));
                                            return 1;
                                        }))
                        ))
                .then(ClientCommandManager.literal("help")
                        .executes(context -> {
                            context.getSource().sendFeedback(Component.literal("===里程表模组指令帮助==="));
                            context.getSource().sendFeedback(Component.literal("/milometer reset - 重置累计距离为零"));
                            context.getSource().sendFeedback(Component.literal("/milometer setdistancemanually <newtotaldistance> - 手动设置累计距离"));
                            context.getSource().sendFeedback(Component.literal("/milometer setpos - 设置HUD位置"));
                            context.getSource().sendFeedback(Component.literal("  -xy <x> <y> - 到指定坐标"));
                            context.getSource().sendFeedback(Component.literal("  -corner - 到屏幕角落"));
                            context.getSource().sendFeedback(Component.literal("    topleft - 左上角"));
                            context.getSource().sendFeedback(Component.literal("    topright - 右上角"));
                            context.getSource().sendFeedback(Component.literal("    bottomleft - 左下角"));
                            context.getSource().sendFeedback(Component.literal("    bottomright - 右下角"));
                            context.getSource().sendFeedback(Component.literal("/milometer help - 显示此帮助"));
                            return 1;
                        })));
    }
}