package com.tiggermetrics.statistics.model;

public enum ItemType {

    INCOMES("INCOMES", 0), EXPENSES("EXPENSES", 1);

    private String name;
    private int value;

    ItemType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static ItemType parseByValue(int value) {
        ItemType itemType;
        switch (value) {
            case 0:
                itemType = ItemType.INCOMES;
                break;
            case 1:
                itemType = ItemType.EXPENSES;
                break;
            default:
                itemType = null;
        }
        return itemType;
    }

    public static ItemType getDefault() {
        return INCOMES;
    }

}
