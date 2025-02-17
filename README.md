# 🏃 Trainguy's Animation Overhaul

Repository for a 1.21.4+ Minecraft mod centered around complex gameplay-driven character animations through a real-time animation system I'm building inspired by Unreal Engine's Animation Blueprints. Is currently included in the Moonflower suite of mods.

> **Warning!**
> This project is still in heavy development! You are free to compile yourself and try it out, but keep in mind that there will be missing animations, placeholders, and debugging visuals that will not look correct in a normal gameplay context.

## 📜 Planned Features

- 🟩 Complete
- 🟨 High Priority
- 🟥 Low Priority
- ❌ Currently out-of-scope (not permanently though!)

| Feature                         | Status | Notes                                                                                                                                                                                         |
|:--------------------------------|:-------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Pose Sampler System             | 🟩     | Implementation of state machines, blend spaces, and montage tracks.                                                                                                                           |
| PyQT Maya Exporter              | 🟨     | Tool for exporting animations out of Maya with new format with scale support.                                                                                                                 |
| First Person Player Animations  | 🟨     | The first proper stress-test of the system.                                                                                                                                                   |
| In-game configuration           | 🟨     | Settings for tweaking individual aspects of different joint animators.                                                                                                                        |
| Block / Block Entity Animations | 🟥     | Would like to re-add support for block animations, similar to the earlier implementation with Pollen, after first person animations are far enough along.                                     |
| Third Person Player Animations  | 🟥     | Whether or not this will be included with the release version or not is TBD.                                                                                                                  |
| Back-porting                    | 🟥     | Depends on the demand, given that this mod is intended to be used on vanilla-ish versions of the game and usually people playing vanilla don't often play older versions.                     |
| Synchronised Sound              | ❌      | I don't know how the sound system works currently, or what it would take to make sounds trigger with animations without breaking other sound mods, but it's something I'm keeping in mind.    |
| Open API for Modding            | ❌      | I would like to lock down the design of the animation systems further before considering making this an open API                                                                              |
| Entity Animations               | ❌      | Too high-scope to do on my own at this juncture, requires a large amount of animations/character rigs. Functionality will support it if I were to find somebody to help out on this.          |
| Data-Driven Joint Animators     | ❌      | Design would need to be locked down enough prior to considering this.                                                                                                                         |

## 🔗 Socials
- My Discord server: _Work-in-progress_
- My Twitter: https://twitter.com/Trainguy9512
- Moonflower Website: https://moonflower.gg/
- Moonflower Twitter: https://www.moonflower.gg/twitter
- Moonflower Discord: https://www.moonflower.gg/discord

## 📘 Credits
- Lead Development, Rigging, Animation
  - [James Pelter (Trainguy9512)](https://x.com/Trainguy9512)
- Timeline and easing system
  - [Marvin Schürz](https://twitter.com/minetoblend)
- Contributors
  - [TomB-134](https://github.com/TomB-134)
  - [AlphaKR93](https://github.com/AlphaKR93)
  - [LizIsTired](https://github.com/LizIsTired)
  - [CaioMGT](https://github.com/CaioMGT)
  - [Superpowers04](https://github.com/superpowers04)
- Special thanks to members of the Moonflower team for supporting my development on this and helping answer my questions!

## 🧵 Usage and Contribution
- Pull requests are welcome!
- You may not upload compiled versions of the mod to public sites like Curseforge or Modrinth **without getting explicit permission from me to do so**.
- You can read the license [here](https://github.com/Trainguy9512/trainguys-animation-overhaul/blob/master/LICENSE)
  - Code portions of the project are under GNU General Public License v3, while all resources/non-code assets are under All Rights Reserved

## 🔍 FAQ

- What versions of the game will this mod support?
> For right now, the mod is being worked on in the latest version of Java Minecraft. Upon release, the plan is to gear the project towards supporting multiple versions at once onwards. Whether or not there will be backports is TBD
- What mod loaders will this mod be compatible with?
> Initially, likely only Fabric, as this mod being built to be used in a vanilla-ish setting and to my knowledge, Fabric is generally more geared towards vanilla gameplay than Forge is.
- Will the mod come to Forge/NeoForge?
> Likely yes, as part of multi-version development I plan on gearing the project for closer to release.
- What will the mod require as a dependency?
> Right now, just the Fabric API.
- What is this mod compatible with?
> Currently there is no official list of what will or will not work, but generally most cosmetic vanilla-friendly mods like Essential, 3D Skin Layers, and other cosmetic mods should work perfectly fine. Mods that change the player model to add additional animations like Better Combat or Emotecraft will probably not work, as they have different implementations for their animations that either overwrite or get overwritten by this mod. 
>
> Additionally, right now there are no plans to implement compatibility with heavier content mods which would have their own interaction animations, though one day this may come in the form of separate mods with an API.

If you feel this FAQ is missing anything or you have any additional questions, please let me know by sending a message request to my Discord account, `Trainguy9512`
