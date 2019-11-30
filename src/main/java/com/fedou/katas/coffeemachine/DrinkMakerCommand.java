package com.fedou.katas.coffeemachine;

class DrinkMakerCommand {
    private static final DrinkMakerCommand TEA = new DrinkMakerCommand("Tea", "T", 40);
    private static final DrinkMakerCommand CHOCOLATE = new DrinkMakerCommand("Chocolate", "H", 50);
    private static final DrinkMakerCommand COFFEE = new DrinkMakerCommand("Coffee", "C", 60);
    private final String displayName;
    private final String machineName;
    private final int priceInCents;

    public static String buildMachineCommand(Command command) {
        switch (command.drink) {
            case TEA:
                return TEA.handleCommand(command);
            case CHOCOLATE:
                return CHOCOLATE.handleCommand(command);
            case COFFEE:
                return COFFEE.handleCommand(command);
        }
        return "M:Command not understood";
    }

    private DrinkMakerCommand(String displayName, String machineName, int priceInCents) {
        this.displayName = displayName;
        this.machineName = machineName;
        this.priceInCents = priceInCents;
    }

    private String handleCommand(Command command) {
        if (command.amountPaid < priceInCents) {
            return displayMissingAmount(displayName, priceInCents, command.amountPaid);
        }
        return machineName + handleSugar(command);
    }

    private String displayMissingAmount(String displayName, int price, int amountPaid) {
        return "M:" + displayName + " costs " + (price - amountPaid) + " cents more";
    }

    private String handleSugar(Command command) {
        if (command.nbSugar == 0) {
            return "::";
        } else {
            return ":" + command.nbSugar + ":0";
        }
    }


}
