package com.tristankechlo.wool_collection.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.tristankechlo.wool_collection.TheWoolCollection;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import static net.minecraft.commands.Commands.literal;

public class TheWoolCollectionCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> command = literal(TheWoolCollection.MOD_ID)
                .then(literal("github").executes(TheWoolCollectionCommand::github))
                .then(literal("issue").executes(TheWoolCollectionCommand::issue))
                .then(literal("discord").executes(TheWoolCollectionCommand::discord))
                .then(literal("curseforge").executes(TheWoolCollectionCommand::curseforge))
                .then(literal("modrinth").executes(TheWoolCollectionCommand::modrinth));
        dispatcher.register(command);
        TheWoolCollection.LOGGER.info("Command '/{}' registered", TheWoolCollection.MOD_ID);
    }

    private static int github(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Component link = ResponseHelper.clickableLink(TheWoolCollection.GITHUB_URL);
        Component message = new TextComponent("Check out the source code on GitHub: ").withStyle(ChatFormatting.WHITE).append(link);
        ResponseHelper.sendMessage(source, message, false);
        return 1;
    }

    private static int issue(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Component link = ResponseHelper.clickableLink(TheWoolCollection.GITHUB_ISSUE_URL);
        Component message = new TextComponent("If you found an issue, submit it here: ").withStyle(ChatFormatting.WHITE).append(link);
        ResponseHelper.sendMessage(source, message, false);
        return 1;
    }

    private static int discord(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Component link = ResponseHelper.clickableLink(TheWoolCollection.DISCORD_URL);
        Component message = new TextComponent("Join the Discord here: ").withStyle(ChatFormatting.WHITE).append(link);
        ResponseHelper.sendMessage(source, message, false);
        return 1;
    }

    private static int curseforge(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Component link = ResponseHelper.clickableLink(TheWoolCollection.CURSEFORGE_URL);
        Component message = new TextComponent("Check out the CurseForge page here: ").withStyle(ChatFormatting.WHITE).append(link);
        ResponseHelper.sendMessage(source, message, false);
        return 1;
    }

    private static int modrinth(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Component link = ResponseHelper.clickableLink(TheWoolCollection.MODRINTH_URL);
        Component message = new TextComponent("Check out the Modrinth page here: ").withStyle(ChatFormatting.WHITE).append(link);
        ResponseHelper.sendMessage(source, message, false);
        return 1;
    }

}