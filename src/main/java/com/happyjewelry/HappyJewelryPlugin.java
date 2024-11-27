package com.happyjewelry;

import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.MenuEntry;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.ChatMessageManager;
import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.sound.sampled.*;
import javax.inject.Inject;
import java.io.InputStream;

@PluginDescriptor(
		name = "Happy Jewelry",
		description = "Plays a sound when jewelry is rubbed",
		tags = {"jewelry"}
)

public class HappyJewelryPlugin extends Plugin {

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private Client client;

	@Inject
	private HappyJewelryConfig config;  // Inject the config

	@Override
	protected void startUp() throws Exception {
		// Code to run when the plugin starts
	}

	@Override
	protected void shutDown() throws Exception {
		// Code to run when the plugin stops
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event) {
		// Check if the menu option is "Rub"
		if (event.getOption().equals("Rub")) {
			// Get the menu entries
			MenuEntry[] entries = client.getMenuEntries();

			// Modify the last menu entry (which corresponds to the event)
			MenuEntry lastEntry = entries[entries.length - 1];
			lastEntry.setOption("Tickle"); // Change "Rub" to "Tickle"

			// Set the modified entries back
			client.setMenuEntries(entries);
		}
	}

	private void sendChatMessage(String message) {
		client.addChatMessage(
				ChatMessageType.GAMEMESSAGE,
				"",
				message,
				null
		);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event) {
		if (event.getMenuOption().equals("Tickle")) { // Match "Rub" menu option
			sendChatMessage("That tickles!");
			if (config.soundEnabled()) {  // Check if sound is enabled in the config
				playCustomSound();
			}
		}
	}

	private void playCustomSound() {
		try (InputStream soundFile = getClass().getResourceAsStream("/sounds/happyjewelrysound.wav")) {
			if (soundFile == null) {
				throw new IllegalArgumentException("Sound file not found!");
			}

			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			// Add a listener to release resources when the sound finishes
			clip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					clip.close();
				}
			});

			clip.start(); // Play the sound

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Provides
	HappyJewelryConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(HappyJewelryConfig.class);
	}
}
