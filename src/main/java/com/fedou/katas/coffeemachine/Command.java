package com.fedou.katas.coffeemachine;

public class Command {
    private final DrinkType drink;
    private final int nbSugar;

    public Command(Command.DrinkType drink, int nbSugar) {
        this.drink = drink;
        this.nbSugar = nbSugar;
    }

    public enum DrinkType { TEA }
}
