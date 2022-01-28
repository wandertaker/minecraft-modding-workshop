package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkshopMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	BlockPos coordinate = BlockPos.ORIGIN;
	int counter = 0;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Item newSword = new SwordItem(ToolMaterials.DIAMOND, 150, 2.0f, new FabricItemSettings().group(ItemGroup.COMBAT));
		net.minecraft.util.registry.Registry.register(Registry.ITEM, new Identifier("modid", "new_sword"), newSword);

		PlayerBlockBreakEvents.AFTER.register(((world, player, position, state, blockEntity) -> {
			System.out.println("Block broken");

			// Stop if broken block is not an emerald block
			if(!state.isOf(Blocks.EMERALD_BLOCK)) {
				counter = 0;
				return;
			}

			// Increase counter while 5 emerald blocks are broken vertically
			if(counter < 4 && position.getY() == coordinate.getY() - 1 && position.getX() == coordinate.getX() && position.getZ() == coordinate.getZ()) {
				counter++;
			}
			// Increase counter if 6th broken emerald block is positioned above and besides the 5th block
			else if(counter==4) {
				if((position.getY() == coordinate.getY() + 1 && position.getX() == coordinate.getX() + 1 && position.getZ() == coordinate.getZ()) ||
						(position.getY() == coordinate.getY() + 1 && position.getX() == coordinate.getX() && position.getZ() == coordinate.getZ() + 1 )
				){
					counter++;
				} else {
					counter = 0;
				}
			}
			// Increase counter if the 7th broken emerald block is positioned two blocks beside the 6th block
			else if(counter == 5) {
				if((position.getY() == coordinate.getY() && position.getX() == coordinate.getX() - 2 && position.getZ()== coordinate.getZ()) ||
						(position.getY() == coordinate.getY() && position.getX() == coordinate.getX() && position.getZ()== coordinate.getZ() - 2)
				) {
					// Give player the new sword
					player.getInventory().offerOrDrop(newSword.getDefaultStack());
				}
				else {
					counter = 0;
				}
			}
			else {
				counter = 0;
			}

			coordinate = position;
			System.out.println("Counter is " + counter);
			System.out.println("Current coordinate is " + coordinate);
		}));
	}
}
