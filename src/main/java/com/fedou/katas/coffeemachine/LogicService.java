package com.fedou.katas.coffeemachine;

public class LogicService {
    private DrinkMakerService maker;
    private ReportService reporter;
    private final BeverageQuantityChecker beverageQuantityChecker;
    private final EmailNotifier emailNotifier;

    public LogicService(DrinkMakerService maker, ReportService reporter, BeverageQuantityChecker beverageQuantityChecker, EmailNotifier emailNotifier) {
        this.maker = maker;
        this.reporter = reporter;
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.emailNotifier = emailNotifier;
    }

    public void receives(Command command) {
        DrinkMakerCommand machineCommand = DrinkMakerCommand.buildMachineCommand(command);
        if (beverageQuantityChecker.isEmpty(machineCommand.displayName)) {
            handleShortage(machineCommand);
        } else {
            maker.make(machineCommand.handleCommand(command));
            reporter.record(machineCommand);
        }
    }

    private void handleShortage(DrinkMakerCommand machineCommand) {
        emailNotifier.notifyMissingDrink(machineCommand.displayName);
        maker.make("M:Missing "+machineCommand.displayName+", provider notified");
    }

    public void printReport() {
        reporter.printReport();
    }
}
