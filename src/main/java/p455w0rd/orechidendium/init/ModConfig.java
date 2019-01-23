package p455w0rd.orechidendium.init;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

/**
 * @author p455w0rd
 *
 */
public class ModConfig extends Configuration {

	private static final ModConfig INSTANCE = new ModConfig(new File(ModGlobals.CONFIG_FILE));

	private ModConfig(File file) {
		super(file);
	}

	public static ModConfig getInstance() {
		return INSTANCE;
	}

	public void init() {
		load();
		sync();
	}

	public void sync() {
		Options.endDimensionOnly = getInstance().getBoolean("EndDimensionOnly", CATEGORY_GENERAL, true, "Should the flower only be functional in the End Dimension?");
		if (getInstance().hasChanged()) {
			getInstance().save();
		}
	}

	public static class Options {

		public static boolean endDimensionOnly = true;

	}

}
