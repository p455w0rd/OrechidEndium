package p455w0rd.orechidendium.blocks.tiles;

import java.util.Map;
import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import p455w0rd.orechidendium.api.OrechidEndiumAPI;
import p455w0rd.orechidendium.init.ModConfig.Options;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.subtile.functional.SubTileOrechid;

/**
 * @author p455w0rd
 *
 */
public class SubTileOrechidEndium extends SubTileOrechid {

	private static final int COST = 20000;
	private static final Random r = new Random();

	@Override
	public boolean canOperate() {
		return Options.endDimensionOnly ? supertile.getWorld().provider.getDimensionType() == DimensionType.THE_END : true;
	}

	@Override
	public Map<String, Integer> getOreMap() {
		return OrechidEndiumAPI.getOreWeights();
	}

	@Override
	public ItemStack getOreToPut() {
		if (getOreMap().isEmpty()) {
			return ItemStack.EMPTY;
		}
		return super.getOreToPut();
	}

	@Override
	public Predicate<IBlockState> getReplaceMatcher() {
		return state -> state.getBlock() == Blocks.END_STONE;
	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	public int getColor() {
		int[] colors = new int[] {
				0xFFE354, 0xFF1FBB, 0xB533FF
		};
		int rand = r.nextInt(3);
		return colors[rand];
	}

	@Override
	public LexiconEntry getEntry() {
		return OrechidEndiumAPI.orechidEndiumLexiconEntry;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res) {
		String name = I18n.format("tile.botania:flower." + getUnlocalizedName() + ".name");
		BotaniaAPI.internalHandler.drawComplexManaHUD(0xFF1FBB, knownMana, getMaxMana(), name, res, BotaniaAPI.internalHandler.getBindDisplayForFlowerType(this), isValidBinding());
	}

}
