package p455w0rd.orechidendium.api;

import static vazkii.botania.common.crafting.ModPetalRecipes.*;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.AlfheimLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

/**
 * @author p455w0rd
 *
 */
public class OrechidEndiumAPI {

	private static final Map<String, Integer> oreWeightsEnd = new HashMap<>();
	public static RecipePetals orechidEndiumRecipe;
	public static LexiconEntry orechidEndiumLexiconEntry;
	public static final String FFLOWER_ORECHID_ENDIUM = "orechidEndium";

	public static void registerOreWeights() {
		addOreWeightEnd("oreEndCoal", 9000);
		addOreWeightEnd("oreEndCopper", 4700);
		addOreWeightEnd("oreEndDiamond", 500);
		addOreWeightEnd("oreEndEmerald", 500);
		addOreWeightEnd("oreEndGold", 3635);
		addOreWeightEnd("oreEndIron", 5790);
		addOreWeightEnd("oreEndLapis", 3250);
		addOreWeightEnd("oreEndLead", 2790);
		addOreWeightEnd("oreEndNickel", 1790);
		addOreWeightEnd("oreEndPlatinum", 350);
		addOreWeightEnd("oreEndRedstone", 5600);
		addOreWeightEnd("oreEndSilver", 1550);
		addOreWeightEnd("oreEndSteel", 1690);
		addOreWeightEnd("oreEndMithril", 1000);
		addOreWeightEnd("oreEndCertusQuartz", 2000);
		addOreWeightEnd("oreEndChargedCertusQuartz", 950);
		addOreWeightEnd("oreEndUranium", 2000);
		addOreWeightEnd("oreEndArdite", 1000);
		addOreWeightEnd("oreEndCobalt", 1000);
		addOreWeightEnd("oreEndOsmium", 1000);
		addOreWeightEnd("oreEndIridium", 850);
		addOreWeightEnd("oreEndYellorite", 3000);
		addOreWeightEnd("oreClathrateEnder", 800);
		addOreWeightEnd("oreEndProsperity", 200);
		addOreWeightEnd("oreEndTin", 3750);
		addOreWeightEnd("oreEndInferium", 500);
		addOreWeightEnd("oreEndBiotite", 500, true);
		addOreWeightEnd("oreEndDraconium", 200);
	}

	public static void registerPetalRecipe() {
		orechidEndiumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(FFLOWER_ORECHID_ENDIUM), yellow, purple, new ItemStack(Items.DRAGON_BREATH), purple, pink, runePride, runeGreed, redstoneRoot, pixieDust);
	}

	public static void registerLexiconEntry() {
		orechidEndiumLexiconEntry = new AlfheimLexiconEntry(FFLOWER_ORECHID_ENDIUM, BotaniaAPI.categoryFunctionalFlowers);
		orechidEndiumLexiconEntry.setLexiconPages(new PageText("0"), new PagePetalRecipe<>("1", orechidEndiumRecipe));
	}

	public static Map<String, Integer> getOreWeights() {
		return oreWeightsEnd;
	}

	/**
	 * Maps an ore (ore dictionary key) to it's weight on the End world generation. This
	 * is used for the Orechid Endium flower. Check the static block in the EndiumOrechidAPI class
	 * to get the weights for the vanilla blocks.<br>
	 * Alternatively get the values with the OreDetector mod:<br>
	 * https://gist.github.com/Vazkii/9493322
	 */
	public static void addOreWeightEnd(String ore, int weight) {
		addOreWeightEnd(ore, weight, false);
	}

	public static void addOreWeightEnd(String ore, int weight, boolean override) {
		if (!override && ore.contains("End") && OreDictionary.getOres(ore.replace("End", "")).size() == 0) {
			return;
		}
		oreWeightsEnd.put(ore, weight);
	}

	public static void removeOre(String ore) {
		if (oreWeightsEnd.containsKey(ore)) {
			oreWeightsEnd.remove(ore);
		}
	}

	public static void clear() {
		oreWeightsEnd.clear();
	}

	public static int getOreWeightEnd(String ore) {
		return oreWeightsEnd.get(ore);
	}

	public static boolean hasOreWeightEnd(String ore) {
		return oreWeightsEnd.containsKey(ore);
	}

}
