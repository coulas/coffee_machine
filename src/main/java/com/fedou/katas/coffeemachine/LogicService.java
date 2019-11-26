package com.fedou.katas.coffeemachine;

public class LogicService {
    private DrinkMakerService maker;

    public LogicService(DrinkMakerService maker) {
        this.maker = maker;
    }

    public void receives(Command command) {
        StringBuilder message = new StringBuilder();
        switch (command.drink) {
            case TEA:
                message.append("T");
                break;
            case CHOCOLATE:
                message.append("H");
                break;
            case COFFEE:
                message.append("C");
                break;
        }
        if (command.nbSugar == 0) {
            message.append("::");
        } else {
            message.append(":")
                    .append(command.nbSugar)
                    .append(":0");
        }
        maker.make(message.toString());
    }
}
