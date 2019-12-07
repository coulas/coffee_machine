package com.fedou.katas.coffeemachine;

class DrinkMakerCommand {
    private static final DrinkMakerCommand TEA = new DrinkMakerCommand("Tea", "T", 40);
    private static final DrinkMakerCommand CHOCOLATE = new DrinkMakerCommand("Chocolate", "H", 50);
    private static final DrinkMakerCommand COFFEE = new DrinkMakerCommand("Coffee", "C", 60);
    private static final DrinkMakerCommand ORANGE = new OrangeMakerCommand(60);
    final String displayName;
    private final String machineName;
    final int priceInCents;

    public static DrinkMakerCommand buildMachineCommand(Command command) {
        switch (command.drink) {
            case TEA:
                return TEA;
            case CHOCOLATE:
                return CHOCOLATE;
            case COFFEE:
                return COFFEE;
            case ORANGE:
                return ORANGE;
        }
        return null;
    }

    private DrinkMakerCommand(String displayName, String machineName, int priceInCents) {
        this.displayName = displayName;
        this.machineName = machineName;
        this.priceInCents = priceInCents;
    }

    public String handleCommand(Command command) {
        if (command.amountPaid < priceInCents) {
            return displayMissingAmount(displayName, priceInCents, command.amountPaid);
        }
        // return machineName + handleExtraHot() + handleSugar(command);
        return machineName + handleExtraHot(command.extraHot) + handleSugar(command.nbSugar);
    }

    protected String handleExtraHot(boolean extraHot) {
        return extraHot ? "h" : "";
    }

    private String displayMissingAmount(String displayName, int price, int amountPaid) {
        return "M:" + displayName + " costs " + (price - amountPaid) + " cents more";
    }

    protected String handleSugar(int nbSugar) {
        if (nbSugar == 0) {
            return "::";
        } else {
            return ":" + nbSugar + ":0";
        }
    }


    private static class OrangeMakerCommand extends DrinkMakerCommand {
        public OrangeMakerCommand(int priceInCents) {
            super("Orange", "O", priceInCents);
        }

        @Override
        protected String handleSugar(int nbSugar) {
            return "::";
        }

        @Override
        protected String handleExtraHot(boolean extraHot) {
            return "";
        }
    }
}
