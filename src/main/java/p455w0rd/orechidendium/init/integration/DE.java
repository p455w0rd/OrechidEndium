package p455w0rd.orechidendium.init.integration;

import com.brandon3055.draconicevolution.DEFeatures;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author p455w0rd
 *
 */
public class DE {

	public static void addEndDraconiumOreDict() {
		ItemStack deEndOre = new ItemStack(DEFeatures.draconiumOre, 1, 2);
		OreDictionary.registerOre("oreEndDraconium", deEndOre);
		OreDictionary.registerOre("oreDraconiumEnd", deEndOre);
	}

}
