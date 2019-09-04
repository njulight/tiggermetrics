package com.tiggermetrics.accoutservice.model;

public enum TimePeriod {

	YEAR("YEAR", 1), QUARTER("QUARTER", 2), MONTH("MONTH", 3), DAY("DAY", 4), HOUR("HOUR", 5);

	private String name;
	private int value;

	TimePeriod(String name, int value) {
		this.name = name;
		this.value = value;
	}
}
