package com.lawrence.trackmatic.enums;


import com.lawrence.trackmatic.Entity.Position;

import java.io.Serializable;

public enum Command implements Serializable {
	L {
		@Override
		public void move(Position position) {
			
			if (position == null)
				throw new IllegalArgumentException("Position cannot be null!");
			
			position.turnLeft();
		}
	},
	R {
		@Override
		public void move(Position position) {
			
			if (position == null)
				throw new IllegalArgumentException("Position cannot be null!");
			
			position.turnRight();
		}
	},
	M {
		@Override
		public void move(Position position) {
			
			if (position == null)
				throw new IllegalArgumentException("Position cannot be null!");
			
			position.goForward();
		}
	};

	/**
	 *
	 * @param position
	 * returns position of rover
	 */
	public abstract void move(Position position);
}
