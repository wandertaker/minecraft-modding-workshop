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

## License

This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
