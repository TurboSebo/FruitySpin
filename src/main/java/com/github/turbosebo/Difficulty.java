package com.github.turbosebo;

public enum Difficulty {
    EASY("Easy",1),
    MEDIUM("Normal",2),
    HARD("Hard",3);

    private final String displayName;
    private final int id;

    Difficulty(String name, int id) {
        this.displayName = name;
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }

    public static Difficulty fromId(int number){
        return switch (number){
            case 1 ->EASY;
            case 2 -> MEDIUM;
            case 3 -> HARD;
            default -> throw new IllegalArgumentException("Invalid difficulty level number: " + number);

        };
    }

}
