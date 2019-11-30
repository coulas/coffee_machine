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
                if (handleCost("Tea", 40, command.amountPaid)) return;
                message.append("T");
                handleSugar(command, message);
                maker.make(message.toString());
                break;
            case CHOCOLATE:
                if (handleCost("Chocolate", 50, command.amountPaid)) return;
                message.append("H");
                handleSugar(command, message);
                maker.make(message.toString());
                break;
            case COFFEE:
                if (handleCost("Coffee", 60, command.amountPaid)) return;
                message.append("C");
                handleSugar(command, message);
                maker.make(message.toString());
                break;
        }
    }

    private boolean handleCost(String drink, int price, int amountPaid) {
        if (amountPaid < price) {
            maker.make("M:" + drink + " costs " + (price - amountPaid) + " cents more");
            return true;
        }
        return false;
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
