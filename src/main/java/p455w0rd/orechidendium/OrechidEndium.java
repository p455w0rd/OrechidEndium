package p455w0rd.orechidendium;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import p455w0rd.orechidendium.init.ModGlobals;
import p455w0rd.orechidendium.proxy.ProxyCommon;

@Mod(modid = ModGlobals.MODID, name = ModGlobals.NAME, version = ModGlobals.VERSION, dependencies = ModGlobals.DEPENDENCIES, acceptedMinecraftVersions = "1.12")
public class OrechidEndium {

	@Instance(ModGlobals.MODID)
	public static OrechidEndium INSTANCE;

	@SidedProxy(clientSide = ModGlobals.CLIENT_PROXY, serverSide = ModGlobals.SERVER_PROXY)
	public static ProxyCommon PROXY;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		PROXY.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		PROXY.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		PROXY.postInit(e);
	}

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent e) {
		PROXY.loadComplete(e);
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent e) {
		PROXY.serverStarting(e);
	}

}
