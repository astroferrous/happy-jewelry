package com.happyjewelry;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("happyjewelry")
public interface HappyJewelryConfig extends Config
{
    @ConfigItem(
            keyName = "soundEnabled",
            name = "Enable sound",
            description = "Enable or disable the custom sound when rubbing jewelry",
            position = 1
    )
    default boolean soundEnabled()
    {
        return true; // Default to enabled
    }

    @ConfigItem(
            keyName = "soundVolume",
            name = "Sound Volume",
            description = "Set the volume for the custom sound",
            position = 2
    )
    default int soundVolume()
    {
        return 50; // Default volume level
    }
}
