package p455w0rd.orechidendium.init;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import p455w0rd.orechidendium.api.OrechidEndiumAPI;
import p455w0rd.orechidendium.blocks.tiles.SubTileOrechidEndium;
import vazkii.botania.api.BotaniaAPI;

/**
 * Common events handler
 * @author p455w0rd
 *
 */
@EventBusSubscriber(modid = ModGlobals.MODID)
public class ModEvents {

	@SubscribeEvent
	public static void onRegistryReady(RegistryEvent.Register<Block> event) {
		BotaniaAPI.registerSubTile(OrechidEndiumAPI.FFLOWER_ORECHID_ENDIUM, SubTileOrechidEndium.class);
	}

	@SubscribeEvent
	public static void onConfigChange(ConfigChangedEvent event) {
		ModConfig.getInstance().sync();
	}

}
