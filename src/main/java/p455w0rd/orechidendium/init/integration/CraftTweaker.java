package p455w0rd.orechidendium.init.integration;

import java.util.LinkedList;
import java.util.List;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.oredict.IOreDictEntry;
import crafttweaker.mc1120.commands.*;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;
import p455w0rd.orechidendium.api.OrechidEndiumAPI;
import p455w0rd.orechidendium.init.ModGlobals;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author p455w0rd
 *
 */
@ZenClass("mods.botania.OrechidEndium")
@ModOnly(ModGlobals.MODID)
@ZenRegister
public class CraftTweaker {

	private static final List<IAction> LATE_REMOVALS = new LinkedList<>();
	private static final List<IAction> LATE_ADDITIONS = new LinkedList<>();

	public static void performCraftTweakerActions() {
		try {
			LATE_REMOVALS.forEach(CraftTweakerAPI::apply);
			LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
		}
		catch (Exception e) {
			e.printStackTrace();
			CraftTweakerAPI.logError("Error while applying actions", e);
		}
	}

	public static void registerCommand() {
		CTChatCommand.registerCommand(new OrechidEndiumLogger());
	}

	@ZenMethod
	public static void setOre(IOreDictEntry oreDict, int weight) {
		LATE_ADDITIONS.add(new Set(oreDict.getName(), weight));
	}

	@ZenMethod
	public static void setOre(String oreDict, int weight) {
		LATE_ADDITIONS.add(new Set(oreDict, weight));
	}

	@ZenMethod
	public static void addOre(IOreDictEntry oreDict, int weight) {
		setOre(oreDict, weight);
	}

	@ZenMethod
	public static void addOre(String oreDict, int weight) {
		setOre(oreDict, weight);
	}

	@ZenMethod
	public static void removeOre(IOreDictEntry oreDict) {
		LATE_REMOVALS.add(new Remove(oreDict.getName()));
	}

	@ZenMethod
	public static void removeOre(String oreDict) {
		LATE_REMOVALS.add(new Remove(oreDict));
	}

	@ZenMethod
	public static void clear() {
		removeOre("*");
	}

	private static class Set implements IAction {

		String oreDict;
		int weight;

		public Set(String ore, int prop) {
			oreDict = ore;
			weight = prop;
		}

		@Override
		public void apply() {
			OrechidEndiumAPI.addOreWeightEnd(oreDict, weight, true);
		}

		@Override
		public String describe() {
			return "Adding Orechid Endium Ore Weight: " + oreDict + ":" + weight;
		}

	}

	private static class Remove implements IAction {

		String oreDict;

		public Remove(String ore) {
			oreDict = ore;
		}

		@Override
		public void apply() {
			if (oreDict.equals("*")) {
				OrechidEndiumAPI.clear();
				return;
			}
			OrechidEndiumAPI.removeOre(oreDict);
		}

		@Override
		public String describe() {
			if (oreDict.equals("*")) {
				return "Cleared Orchid Endium Ore Weights List";
			}
			return "Removing Orechid Endium Ore: " + oreDict;
		}
	}

	public static class OrechidEndiumLogger extends CraftTweakerCommand {

		public OrechidEndiumLogger() {
			super("botorechidendium");
		}

		@Override
		public void executeCommand(MinecraftServer server, ICommandSender sender, String[] arguments) {
			for (String str : OrechidEndiumAPI.getOreWeights().keySet()) {
				CraftTweakerAPI.logCommand(str + ": " + OrechidEndiumAPI.getOreWeightEnd(str));
			}
			if (sender != null) {
				sender.sendMessage(SpecialMessagesChat.getLinkToCraftTweakerLog(TextFormatting.DARK_PURPLE + "" + TextFormatting.ITALIC + "Orechid Endium List generated", sender));
			}
		}

		@Override
		protected void init() {
		}
	}

}
