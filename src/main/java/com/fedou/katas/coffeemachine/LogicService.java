package com.fedou.katas.coffeemachine;

public class LogicService {
    private DrinkMakerService maker;

    public LogicService(DrinkMakerService maker) {
        this.maker = maker;
    }

    public void receives(Command command) {
        switch (command.drink) {
            case TEA:
                maker.make("T::");
                break;
            case CHOCOLATE:
                maker.make("H::");
                break;
            case COFFEE:
                maker.make("C::");
                break;
        }
    }
}
