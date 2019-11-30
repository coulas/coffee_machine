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
                if (command.amountPaid < 40) {
                    maker.make("M:" + "Tea" + " costs " + (40 - command.amountPaid) + " cents more");
                    return;
                }
                break;
            case CHOCOLATE:
                message.append("H");
                break;
            case COFFEE:
                message.append("C");
                break;
        }
        handleSugar(command, message);
        maker.make(message.toString());
    }

    private void handleSugar(Command command, StringBuilder message) {
        if (command.nbSugar == 0) {
            message.append("::");
        } else {
            message.append(":")
                    .append(command.nbSugar)
                    .append(":0");
        }
    }
}
