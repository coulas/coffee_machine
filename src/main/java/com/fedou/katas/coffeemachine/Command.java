package com.fedou.katas.coffeemachine;

public class Command {
    public enum DrinkType {
        CHOCOLATE, COFFEE, TEA, ORANGE;
    }

    final DrinkType drink;
    final boolean extraHot;
    final int nbSugar;
    final int amountPaid;

    public Command(DrinkType drink, boolean extraHot, int nbSugar, int amountPaid) {
        this.drink = drink;
        this.extraHot = extraHot;
        this.nbSugar = nbSugar;
        this.amountPaid = amountPaid;

    }

}
