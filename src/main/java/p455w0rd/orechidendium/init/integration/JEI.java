package p455w0rd.orechidendium.init.integration;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import mezz.jei.api.*;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;
import p455w0rd.orechidendium.api.OrechidEndiumAPI;
import p455w0rd.orechidendium.init.ModGlobals;
import vazkii.botania.client.integration.jei.orechid.OrechidRecipeWrapper;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

/**
 * @author p455w0rd
 *
 */

@SuppressWarnings("deprecation")
@JEIPlugin
public class JEI implements IModPlugin {

	@Override
	public void register(@Nonnull IModRegistry registry) {
		registry.addRecipes(OrechidEndiumAPI.getOreWeights().entrySet().stream().filter(e -> doesOreExist(e.getKey())).map(OrechidEndiumRecipeWrapper::new).sorted().collect(Collectors.toList()), OrechidEndiumRecipeCategory.UID);
		registry.addRecipeCatalyst(ItemBlockSpecialFlower.ofType(OrechidEndiumAPI.FFLOWER_ORECHID_ENDIUM), OrechidEndiumRecipeCategory.UID);
		registry.addRecipeCatalyst(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), OrechidEndiumAPI.FFLOWER_ORECHID_ENDIUM), OrechidEndiumRecipeCategory.UID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new OrechidEndiumRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	public class OrechidEndiumRecipeCategory implements IRecipeCategory<OrechidEndiumRecipeWrapper> {

		public static final String UID = "botania.nei.orechidEndium";
		private final IDrawableStatic background;
		private final String localizedName;
		private final IDrawableStatic overlay;

		public OrechidEndiumRecipeCategory(IGuiHelper guiHelper) {
			background = guiHelper.createBlankDrawable(168, 64);
			localizedName = I18n.translateToLocal("botania.nei.orechidEndium");
			overlay = guiHelper.createDrawable(new ResourceLocation("botania", "textures/gui/pureDaisyOverlay.png"), 0, 0, 64, 46);
		}

		@Nonnull
		@Override
		public String getUid() {
			return UID;
		}

		@Nonnull
		@Override
		public String getTitle() {
			return localizedName;
		}

		@Nonnull
		@Override
		public IDrawable getBackground() {
			return background;
		}

		@Override
		public void setRecipe(IRecipeLayout recipeLayout, OrechidEndiumRecipeWrapper recipeWrapper, IIngredients ingredients) {
			final IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
			itemStacks.init(0, true, 40, 12);
			itemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
			itemStacks.init(1, true, 70, 12);
			itemStacks.set(1, ItemBlockSpecialFlower.ofType(OrechidEndiumAPI.FFLOWER_ORECHID_ENDIUM));
			itemStacks.init(2, true, 99, 12);
			itemStacks.set(2, ingredients.getOutputs(ItemStack.class).get(0));
		}

		@Override
		public void drawExtras(@Nonnull Minecraft minecraft) {
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			overlay.draw(minecraft, 48, 0);
			GlStateManager.disableBlend();
			GlStateManager.disableAlpha();
		}

		@Nonnull
		@Override
		public String getModName() {
			return ModGlobals.MODID;
		}

	}

	public class OrechidEndiumRecipeWrapper extends OrechidRecipeWrapper {

		private final List<List<ItemStack>> outputOreStacks;

		public OrechidEndiumRecipeWrapper(Map.Entry<String, Integer> entry) {
			super(entry);
			List<ItemStack> stackList = OreDictionary.getOres(entry.getKey()).stream().filter(s -> s.getItem() instanceof ItemBlock).map(ItemStack::copy).collect(Collectors.toList());
			stackList.forEach(s -> s.setCount(1));
			outputOreStacks = Collections.singletonList(stackList);
		}

		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, getInputStack());
			ingredients.setOutputLists(VanillaTypes.ITEM, outputOreStacks);
		}

		@Override
		protected ItemStack getInputStack() {
			return new ItemStack(Blocks.END_STONE, 1);
		}

		@Override
		public Map<String, Integer> getOreWeights() {
			return OrechidEndiumAPI.getOreWeights();
		}

	}

	public static boolean doesOreExist(String key) {
		return OreDictionary.doesOreNameExist(key) && OreDictionary.getOres(key).stream().anyMatch(s -> s.getItem() instanceof ItemBlock);
	}

}
