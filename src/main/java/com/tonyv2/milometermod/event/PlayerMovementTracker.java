package com.tonyv2.milometermod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

public class PlayerMovementTracker implements ClientTickEvents.EndTick {

    private Vec3 lastPosition;
    private double totalDistance = 0.0;
    private double speed = 0.0;

    public double newtotalDistance = 0.0;


    public PlayerMovementTracker() {
        ClientTickEvents.END_CLIENT_TICK.register(this);
    }

    @Override
    public void onEndTick(Minecraft client) {
        LocalPlayer player = client.player;
        if (player != null) {
            Vec3 currentPosition = player.position();
            if (lastPosition != null) {
                double distance = lastPosition.distanceTo(currentPosition);
                totalDistance += distance;
                speed = distance * 20; // distance per tick * 20 ticks per second
            }
            lastPosition = currentPosition;
        }
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getSpeed() {
        return speed;
    }

    public void resetDistance() {
        totalDistance = 0.0;
    }
    public void SetDistanceManually() {
        totalDistance = newtotalDistance;
    }
}