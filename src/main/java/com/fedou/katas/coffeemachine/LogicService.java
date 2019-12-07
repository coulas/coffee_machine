package com.fedou.katas.coffeemachine;

public class LogicService {
    private DrinkMakerService maker;
    private ReportService reporter;

    public LogicService(DrinkMakerService maker, ReportService reporter) {
        this.maker = maker;
        this.reporter = reporter;
    }

    public void receives(Command command) {
        DrinkMakerCommand machineCommand = DrinkMakerCommand.buildMachineCommand(command);
        maker.make(machineCommand.handleCommand(command));
        reporter.record(machineCommand);
    }

    public void printReport() {
        reporter.printReport();
    }
}
