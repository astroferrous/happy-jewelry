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

}
