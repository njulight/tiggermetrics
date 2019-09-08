package com.tiggermetrics.accoutservice.model;

import org.apache.commons.lang.enums.Enum;

public enum Currency {

	USD("USD", 1), EUR("EUR", 2), RMB("RMB", 3);

	private String name;
	private int value;

	Currency(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public static Currency parseByValue(int value) {
		Currency currency;
		switch (value) {
			case 1:
				currency = Currency.USD;
				break;
			case 2:
				currency = Currency.EUR;
				break;
			case 3:
				currency = Currency.RMB;
				break;
			default:
				currency = null;
		}
		return currency;
	}

	public static Currency getDefault() {
		return RMB;
	}
}
