package p455w0rd.orechidendium.init;

import java.util.Locale;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import p455w0rd.orechidendium.api.OrechidEndiumAPI;
import p455w0rd.orechidendium.blocks.tiles.SubTileOrechidEndium;
import vazkii.botania.api.BotaniaAPIClient;

/**
 * Client-only events handler
 * @author p455w0rd
 *
 */
@SideOnly(Side.CLIENT)
public class ModEventsClient {

	@SubscribeEvent
	public static void onModelRegistryReady(ModelRegistryEvent event) {
		BotaniaAPIClient.registerSubtileModel(SubTileOrechidEndium.class, new ModelResourceLocation(ModGlobals.MODID + ":" + OrechidEndiumAPI.FFLOWER_ORECHID_ENDIUM.toLowerCase(Locale.US)));
	}

}
