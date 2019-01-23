package p455w0rd.orechidendium.proxy;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.*;
import p455w0rd.orechidendium.api.OrechidEndiumAPI;
import p455w0rd.orechidendium.init.ModConfig;
import p455w0rd.orechidendium.init.integration.*;
import vazkii.botania.api.BotaniaAPI;

/**
 * @author p455w0rd
 *
 */
public class ProxyCommon {

	public void preInit(FMLPreInitializationEvent event) {
		ModConfig.getInstance().init();
		if (Loader.isModLoaded("draconicevolution")) {
			DE.addEndDraconiumOreDict();
		}
	}

	public void init(FMLInitializationEvent event) {
		OrechidEndiumAPI.registerPetalRecipe();
		OrechidEndiumAPI.registerLexiconEntry();
		BotaniaAPI.subtilesForCreativeMenu.add(OrechidEndiumAPI.FFLOWER_ORECHID_ENDIUM);
		if (Loader.isModLoaded("quark")) {
			Quark.addEndBiotiteOreDict();
		}
	}

	public void postInit(FMLPostInitializationEvent event) {
		OrechidEndiumAPI.registerOreWeights();
		if (Loader.isModLoaded("crafttweaker")) {
			CraftTweaker.performCraftTweakerActions();
		}
	}

	public void loadComplete(FMLLoadCompleteEvent event) {

	}

	public void serverStarting(FMLServerStartingEvent event) {
		if (Loader.isModLoaded("crafttweaker")) {
			CraftTweaker.registerCommand();
		}
	}

}
