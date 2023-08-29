package com.app.enums;

public enum Stars {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

	int stars;

	Stars(int s) {
		stars = s;
	}

	int getStars() {
		return stars;
	}
}
