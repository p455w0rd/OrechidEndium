package p455w0rd.orechidendium.init.integration;

import net.minecraftforge.oredict.OreDictionary;
import vazkii.quark.world.feature.Biotite;

/**
 * @author p455w0rd
 *
 */
public class Quark {

	public static void addEndBiotiteOreDict() {
		OreDictionary.registerOre("oreEndBiotite", Biotite.biotite_ore);
	}

}
