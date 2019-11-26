package com.fedou.katas.coffeemachine;

public class LogicService {
    private DrinkMakerService maker;

    public LogicService(DrinkMakerService maker) {
        this.maker = maker;
    }

    public void receives(Command command) {
        StringBuilder message = new StringBuilder();
        // Command comes from pad (say Json in a usual rest app)
        // DrinkMaker Protocol comes from machine ( say SQL in a usual rest app)
        // Having Command knowing protocol details is an abstraction leak (your json knows about your database)
        // but this "one liner" is so tempting : message.append(command.drink.asLetter()) or **.toDrinkMakerProtocol())
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
        message.append("::");
        maker.make(message.toString());
    }
}
