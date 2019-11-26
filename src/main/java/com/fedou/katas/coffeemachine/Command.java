package com.fedou.katas.coffeemachine;

public class Command {
    public enum DrinkType {CHOCOLATE, COFFEE, TEA }

    final DrinkType drink;
    final int nbSugar;

    public Command(Command.DrinkType drink, int nbSugar) {
        this.drink = drink;
        this.nbSugar = nbSugar;
    }

}
