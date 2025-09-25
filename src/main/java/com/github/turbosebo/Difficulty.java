package com.github.turbosebo;

public enum Difficulty {
    EASY("Easy",1, 3),
    MEDIUM("Normal",2,4),
    HARD("Hard",3, 5), ;

    private final String displayName;
    private final int id;
    private final int slotsNumber;

    Difficulty(String name, int id, int slotsNumber) {
        this.displayName = name;
        this.id = id;
        this.slotsNumber = slotsNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }

    public static Difficulty setDifficulty(int level) {
        return switch (level) {
            case 1 -> EASY;
            case 2 -> MEDIUM;
            case 3 -> HARD;
            default -> MEDIUM;
        };
    }

    public static Difficulty fromId(int number){
        return switch (number){
            case 1 ->EASY;
            case 2 -> MEDIUM;
            case 3 -> HARD;
            default -> throw new IllegalArgumentException("Invalid difficulty level number: " + number);

        };
    }

    public int getSlotsNumber() {
        return slotsNumber;
    }
}
