package p455w0rd.orechidendium.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import p455w0rd.orechidendium.init.ModEventsClient;

/**
 * @author p455w0rd
 *
 */
public class ProxyClient extends ProxyCommon {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(ModEventsClient.class);
	}

}
