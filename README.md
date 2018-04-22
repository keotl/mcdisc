[![Build Status](https://travis-ci.org/keotl/mcdisc.svg?branch=master)](https://travis-ci.org/keotl/mcdisc)
# Minecraft Discs
Dynamically loaded custom music discs based on YouTube videos. Requires building your resource pack by downloading YouTube videos. See [here](https://github.com/keotl/mcdisc-resource-builder) for an automated mcdisc resource pack builder.

For Minecraft 1.12. Requires Forge ModLoader.
## Setup
1. Copy the latest mcdisc release jar to your mods directory.
2. Define your own disc list. See [sample-disc-config.json](sample-disc-config.json) for a syntax example.
    - Each custom disc requires a YouTube URL. (Those are used to build your resource pack.)
3. Edit your forge configuration to use your custom disc list, either by editing manually the `config/mcdisc.cfg` file or from the `Mods Options` menu inside the game.
    - The disc list location can either be an URL, (starting with `http://` or `https://`), or a file path. 
    - For local files, the default directory is your `.minecraft` directory. For instance, setting `disc-list.json` as your disc location will cause the mod to load discs from `.minecraft/disc-list.json`.
    - Do not use `~` in the file location on Linux or MacOS, as that seems to cause issues.
    - By default, the disc list is set to this repository's [sample-disc-config.json](sample-disc-config.json).
4. Build your resource pack. (See [here](https://github.com/keotl/mcdisc-resource-builder)).
You will not be able to play your custom discs without the resource pack.

For multiplayer setups, repeat all steps 1-4 for the server and all clients.

## Configuration
- `DISC_LIST_LOCATION`: Disc list json file location. Can an URL or a file.
- `SPAWN_DISCS_IN_CHESTS`: Add custom discs in dungeons, temples, mineshafts.
- `CREEPERS_DROP_CUSTOM_DISCS`: Creepers should drop custom discs when slain by skeletons.
- `SHOULD_CACHE_DISC_LIST`: Load the disc list once in memory, to reduce disc/http access. (Better to leave this on!)

## Using custom textures
By default, custom discs reuse vanilla minecraft textures. You can define custom textures by adding a "texture" property to your discs in your disc-list.json. (See [here](/documentation/example-custom-texture.json)) for an example. You then need to define your new textures in a resource pack, either as a standalone resource pack, or by bundling them with your sound resource pack built at step 4.

The pack should have the following structure : ( [Example Here](/documentation/example-mcdisc-textures.zip))
```
/assets
  /mcdisc
    /models
      /item
        my-texture.json
  /minecraft
    /textures
      /items
        my_texture.png
```
`my-texture.json` should contain the following :
```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "minecraft:items/my_texture"
  }
}
```
