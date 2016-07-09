package com.flow.action.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
		String response = null;
		if (commandLine != null && !commandLine.isEmpty()) {
			BufferedReader br = null;
			try {
				Process process = Runtime.getRuntime().exec(commandLine);
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				br = null;
				response = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return response;
	}
}
