# 🏃 Trainguy's Animation Overhaul

Repository for a 1.19.3+ Minecraft mod centered around improving the game's character animations via a realtime animation system, included in the Moonflower suite of mods. The primary focus is third and first person character animation improvements, and once those are done the mod will be released and I will move on to entity and block animations.

> **Warning**
> This project is still in heavy development! You are free to compile yourself and try it out, but keep in mind that there will be missing animations, placeholders, and debugging visuals that will not look correct. **This mod is not in a playable state!**

## 🔗 Socials
- My Discord server: https://discord.gg/HRg4nxvWWW
- My Twitter: https://twitter.com/Trainguy9512
- Moonflower Website: https://moonflower.gg/
- Moonflower Twitter: https://www.moonflower.gg/twitter
- Moonflower Discord: https://www.moonflower.gg/discord

## 📘 Additional Credits
- Timeline and lerp system
  - [Marvin Schürz](https://twitter.com/minetoblend)
- Contributors
  - [TomB-134](https://github.com/TomB-134)
  - [AlphaKR93](https://github.com/AlphaKR93)
  - [LizIsTired](https://github.com/LizIsTired)
  - [CaioMGT](https://github.com/CaioMGT)
  - [Superpowers04](https://github.com/superpowers04)

## 🧵 Usage and Contribution
- Pull requests are welcome!
- You may not upload compiled versions of the mod to public sites like Curseforge or Modrinth **without getting explicit permission from me to do so**.
- You can read the license [here](https://github.com/Trainguy9512/trainguys-animation-overhaul/blob/master/LICENSE)
  - Code portions of the project are under GNU General Public License v3, while all resources/non-code assets are under All Rights Reserved

## 🔍 FAQ

- What versions of the game will this mod support?
> For right now, the mod is being worked on in 1.19.3. Once I figure out better methods of version control for handling multiple versions, I would like to make the mod available on 1.18.2+, but this depends on whether 1.19's keyframed animation changes make me want to make 1.19.2 as the floor version. It will always support the latest release version of the game, barring huge rendering or animation changes.
- Will the mod come to forge?
> Yes, for the time being the mod is being developed on Fabric but the mod will eventually be moved back to an architectury project once the mod is finished enough to move back over to a pollen-based architectury project.
- What will the mod require as a dependency?
> Right now, just Fabric API. Once Pollen is updated to 1.19, the project will be reformatted to an architectury project which will add Pollen as a dependency, for things like registries and custom block renderers.
- What is this mod compatible with?
> Currently there is no official list of what will or will not work, but generally most cosmetic vanilla-friendly mods like Essential, 3D Skin Layers, and armor mods should work perfectly fine. Mods that change the player model to add additional animations like Better Combat or Emotecraft will not work, as they have different implementations for their animations that either overwrite or get overwritten by this mod. Content heavy mods that add additional animations for items and abilities are very likely to be visually absent, but this shouldn't inherently break anything.

If you feel this FAQ is missing anything or you have any additional questions, please let me know! You can reach me at Trainguy#9512 on discord
