// removes an Ore
mods.botania.OrechidEndium.removeOre(<ore:oreEndCoal>);
mods.botania.OrechidEndium.removeOre("oreEndGold");

// clears the Ore List
// mods.botania.OrechidEndium.removeOre("*");
mods.botania.OrechidEndium.clear();

// Adds an Ore (with weight; higher number = more probability to spawn)
mods.botania.OrechidEndium.setOre("oreEndDraconium", 200);
mods.botania.OrechidEndium.setOre(<ore:oreEndIron>, 700);
mods.botania.OrechidEndium.addOre(<ore:oreEndBiotite>, 500);
mods.botania.OrechidEndium.addOre("oreEndRedstone", 450);