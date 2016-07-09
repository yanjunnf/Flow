package com.flow.action.impl;

import com.flow.action.AbstractAction;
import com.flow.action.Action;

public class CommandlineAction extends AbstractAction implements Action{
	public String commandLine;
	
	public CommandlineAction(String commandLine, String name, boolean asyncAction) {
		super(name, asyncAction);
		this.commandLine = commandLine;
	}

	@Override
	public Object execute() {
		return null;
	}
}
