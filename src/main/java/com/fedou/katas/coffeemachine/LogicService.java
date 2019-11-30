package com.fedou.katas.coffeemachine;

public class LogicService {
    private DrinkMakerService maker;

    public LogicService(DrinkMakerService maker) {
        this.maker = maker;
    }

    public void receives(Command command) {
        switch (command.drink) {
            case TEA:
                if (command.amountPaid < 40) {
                    maker.make("M:" + "Tea" + " costs " + (40 - command.amountPaid) + " cents more");
                } else {
                    StringBuilder message = new StringBuilder();
                    message.append("T");
                    if (command.nbSugar == 0) {
                        message.append("::");
                    } else {
                        message.append(":")
                                .append(command.nbSugar)
                                .append(":0");
                    }
                    maker.make(message.toString());
                }
                break;
            case CHOCOLATE:
                if (command.amountPaid < 50) {
                    maker.make("M:" + "Chocolate" + " costs " + (50 - command.amountPaid) + " cents more");
                } else {
                    StringBuilder message = new StringBuilder();
                    message.append("H");
                    if (command.nbSugar == 0) {
                        message.append("::");
                    } else {
                        message.append(":")
                                .append(command.nbSugar)
                                .append(":0");
                    }
                    maker.make(message.toString());
                }
                break;
            case COFFEE:
                if (command.amountPaid < 60) {
                    maker.make("M:" + "Coffee" + " costs " + (60 - command.amountPaid) + " cents more");
                    return;
                }
                StringBuilder message = new StringBuilder();
                message.append("C");
                if (command.nbSugar == 0) {
                    message.append("::");
                } else {
                    message.append(":")
                            .append(command.nbSugar)
                            .append(":0");
                }
                maker.make(message.toString());
                break;
        }
    }

}
