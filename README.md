# Minecraft Modding Workshop

This repository is the setup for a workshop to teach the basics of programming by creating a modification for Minecraft.
It is based on the [Fabric Example Mod](https://github.com/FabricMC/fabric-example-mod).

## Setup

### For Participants
If you want to create your own mod, but have no idea where to start, you can follow [this video](https://www.youtube.com/watch?v=x7cPbAFv19E) on YouTube to set everything up on your PC.
You can find more information about the Fabric Minecraft Mod in the [official wiki](https://fabricmc.net/wiki/start).
To create textures for Minecraft you can use the [Nova Skin Editor](https://minecraft.novaskin.me/resourcepacks).

### For Instructors
If you have IntelliJ and a JDK > version 17 ready, just checkout this repository and open the `build.gradle` file as new IntelliJ project.
Gradle will automatically setup everything for you.
To run Minecraft add the run configuration `Add configuration` -> `Application` -> `Minecraft Client`.


For further setup instructions please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the IDE that you are using.

## Example Workshop

1. Decide with the participants on a name for the mod and rename the following files and properties accordingly
    1. `gradle.properties` -> `maven-group`
    2. `gradle.properties` -> `archives_base_name`
    3. `src/main/java/net/fabricmc/example/ExampleMod.java`
    4. `src/main/java/net/fabricmc/example/mixin/ExampleMixin.java`
    5. `src/main/resources/fabric.mod.json` -> `entrypoints` -> `main`
    6. `src/main/resources/modid.mixins.json` -> `ExampleMixin`
2. Add a new sword to the game in the file <your-name>Mod.java.
    1. In the `onInitialize` method, create a new variable `Item newSword`.
    2. Initialize `newSword` with a new `SwordItem` and use the following parameters (IntelliJ will handle all imports for you):
       1. `ToolMaterials.DIAMOND` to give the sword a material
       2. Let the group decide for a number to use as `attackDamage` (15 is very powerful, for reference).
       3. Use `2.0f` or another float for the attack speed.
       4. Use `FabricItemSettings().group(ItemGroup.COMBAT)` as settings to give the sword a group. This is important, because it sets the group where you can find your sword later in the inventory in creative mode.
    3. Register the new item in the game by adding `net.minecraft.util.registry.Registry.register(Registry.ITEM, new Identifier("modid", "new_sword"), newSword);`
    4. To add metadata for the new item create the file `src/main/resources/models/item/new_sword.json` with content `{
       "parent": "item/generated"
       }`
    5. The game should run properly and you should have the new item without texture in the combat group in the creative mode inventory.
3. Decide for a name for the new sword
   1. Add the file `src/main/resources/assets/modid/lang/en_us.json`
   2. Set the name in this file in the following format: `{
      "item.modid.new_sword": "<your_name>"
      } `
4. Add a texture to for the new sword
   1. Go to the [NovaSkinEditor](https://minecraft.novaskin.me/resourcepacks#default/assets/minecraft/textures/items/diamond_sword.png) and create a texture for the new item together.
   2. Download the new new icon and place it under `src/main/resources/assets/modid/textures/item/new_sword.png`.
   3. Open the `new_sword.json` file and add the property `"textures": {
      "layer0": "modid:item/new_sword"
      }` to reference the new icon.
   4. The new icon should now show up for the new item.
5. Implement logic to give the player the new sword if an specific formation of special blocks is broken in correct order (for example if emerald blocks in the shape of a sword are broken from top to bottom). You can use the following snippets:
   1. `PlayerBlockBreakEvents.AFTER.register(((world, player, position, state, blockEntity) -> {})` to register to execute code whenever the player breaks a block
   2. `player.getInventory().offerOrDrop(newSword.getDefaultStack());` to give the new sword to the player.
   3. `state.isOf(Blocks.EMERALD_BLOCK)` to check if the broken block is an emerald block.

## License

This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
