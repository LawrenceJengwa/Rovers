package com.lawrence.trackmatic.enums;


import com.lawrence.trackmatic.Entity.Coordinate;

public enum Orientation {
	N {
		@Override
		public Orientation nextRight() {
			return E;
		}

		@Override
		public Orientation nextLeft() {
			return W;
		}

		@Override
		public void goForward(Coordinate coordinate) {
			
			if (coordinate == null)
				throw new IllegalArgumentException("Coordinate cannot be null!");
			
			coordinate.setY(coordinate.getY() + 1);
		}
	},
	E {
		@Override
		public Orientation nextRight() {
			return S;
		}

		@Override
		public Orientation nextLeft() {
			return N;
		}

		@Override
		public void goForward(Coordinate coordinate) {
			
			if (coordinate == null)
				throw new IllegalArgumentException("Coordinate cannot be null!");
			
			coordinate.setX(coordinate.getX() + 1);
		}
	},
	S {
		@Override
		public Orientation nextRight() {
			return W;
		}

		@Override
		public Orientation nextLeft() {
			return E;
		}

		@Override
		public void goForward(Coordinate coordinate) {
			
			if (coordinate == null)
				throw new IllegalArgumentException("Coordinate cannot be null!");
			
			coordinate.setY(coordinate.getY() - 1);
		}
	},
	W {
		@Override
		public Orientation nextRight() {
			return N;
		}

		@Override
		public Orientation nextLeft() {
			return S;
		}

		@Override
		public void goForward(Coordinate coordinate) {
			
			if (coordinate == null)
				throw new IllegalArgumentException("Coordinate cannot be null!");
			
			coordinate.setX(coordinate.getX() - 1);
		}
	};

	/**
	 * @return Orientation
	 */
	public abstract Orientation nextRight();

	/**

	 * @return Orientation
	 */
	public abstract Orientation nextLeft();

	/**
	 * @param coordinate
	 * makes rover to go forward
	 */
	public abstract void goForward(Coordinate coordinate);
}
