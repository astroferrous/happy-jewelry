package com.happyjewelry;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import com.happyjewelry.HappyJewelryPlugin;

public class HappyJewelryPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HappyJewelryPlugin.class);
		RuneLite.main(args);
	}
}
