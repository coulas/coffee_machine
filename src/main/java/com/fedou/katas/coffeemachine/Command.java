package com.fedou.katas.coffeemachine;

public class Command {
    public enum DrinkType {CHOCOLATE, COFFEE, TEA }

    final DrinkType drink;
    final int nbSugar;
    final int amountPaid;

    public Command(DrinkType drink, int nbSugar, int amountPaid) {
        this.drink = drink;
        this.nbSugar = nbSugar;
        this.amountPaid = amountPaid;
    }
}
