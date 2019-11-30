package com.fedou.katas.coffeemachine;

public class LogicService {
    private DrinkMakerService maker;

    public LogicService(DrinkMakerService maker) {
        this.maker = maker;
    }

    public void receives(Command command) {
        String machineCommand = DrinkMakerCommand.buildMachineCommand(command);
        maker.make(machineCommand);
    }

}
