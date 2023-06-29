package by.htp.ex.controller;

import by.htp.ex.controller.command.Command;
import by.htp.ex.controller.command.CommandName;
import by.htp.ex.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
	private final Map<CommandName, Command> commands = new HashMap<>();
	private final static CommandProvider instance = new CommandProvider();

	private CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.DO_SIGN_IN, new DoSIgnIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
		commands.put(CommandName.GO_TO_USERS_LIST, new GoToUsersList());
		commands.put(CommandName.GO_TO_PERSONAL_CABINET, new GoToPersonalCabinet());
		commands.put(CommandName.DO_EDIT_USER_INFO, new DoEditUserInfo());

		commands.put(CommandName.NO_COMMAND, new NoCommand());
	}

	public Command getCommand(String command) {
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(command.toUpperCase());
		}
		catch (NullPointerException | IllegalArgumentException e){
			commandName = CommandName.NO_COMMAND;
		}

		return commands.get(commandName);
	}

	public static CommandProvider getInstance() {
		return instance;
	}
}
