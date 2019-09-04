package com.tiggermetrics.accoutservice.model;

public enum Currency {

	USD("USD", 1), EUR("EUR", 2), RUB("RUB", 3);

	private String name;
	private int value;

	Currency(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static Currency getDefault() {
		return USD;
	}
}
