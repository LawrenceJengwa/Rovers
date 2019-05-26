package com.lawrence.trackmatic.Entity;


import com.lawrence.trackmatic.enums.Command;

import java.io.Serializable;


public class Rover implements Serializable {

	private static final long serialVersionUID = -1745462525621431812L;
	
	private Position position;


	public Rover(Position position) {
		
		if (position == null)
			throw new IllegalArgumentException("Position cannot be null!");
		
		this.position = position;
	}

	/**
	 * @param command
	 */
	public void move(Command command) {

		if (command == null)
			throw new IllegalArgumentException("Command cannot be null!");

		command.move(position);
	}

	/**
	 * @return Position
	 */
	public Position getPosition() {
		return position;
	}
}
