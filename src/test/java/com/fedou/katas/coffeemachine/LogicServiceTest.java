package com.fedou.katas.coffeemachine;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LogicServiceTest {

    @Mock
    DrinkMakerService maker;
    @InjectMocks
    LogicService logic;

    @TestFactory
    DynamicTest[] should_make_right_drink() {
        return new DynamicTest[]{
                testOnlyMakeRightDrink("Tea is T::", Command.DrinkType.TEA, "T::"),
                testOnlyMakeRightDrink("Hot Chocolate is H::", Command.DrinkType.CHOCOLATE, "H::"),
                testOnlyMakeRightDrink("Coffee is C::", Command.DrinkType.COFFEE, "C::"),
                testOnlyMakeRightDrink("Orange juice is O::", Command.DrinkType.ORANGE, "O::"),
        };
    }

    private DynamicTest testOnlyMakeRightDrink(String displayName, Command.DrinkType drink, String expectedResult) {
        return testMakingDrinkWithFullDetailsAndExpectations(displayName,
                drink, false, 0, 100, expectedResult);
    }

    @TestFactory
    DynamicTest[] should_make_extra_hot_drink_or_always_cold() {
        return new DynamicTest[]{
                testOnlyMakeExtraHotDrink("Extra Hot Tea is Th::", Command.DrinkType.TEA, "Th::"),
                testOnlyMakeExtraHotDrink("Extra Hot Chocolate is Hh::", Command.DrinkType.CHOCOLATE, "Hh::"),
                testOnlyMakeExtraHotDrink("Extra Hot Coffee is Ch::", Command.DrinkType.COFFEE, "Ch::"),
                testOnlyMakeExtraHotDrink("Extra Hot Orange juice is COLD O::", Command.DrinkType.ORANGE, "O::"),
        };
    }

    private DynamicTest testOnlyMakeExtraHotDrink(String displayName, Command.DrinkType drink, String expectedResult) {
        return testMakingDrinkWithFullDetailsAndExpectations(displayName, drink, true, 0, 100, expectedResult);
    }


    @TestFactory
    List<DynamicTest> should_make_drinks_that_may_have_sugar() {
        List<DynamicTest> tests = new ArrayList<>();
        tests.addAll(testDrinksThatShouldHandleSugar(Command.DrinkType.TEA, "Tea", "T"));
        tests.addAll(testDrinksThatShouldHandleSugar(Command.DrinkType.CHOCOLATE, "Chocolate", "H"));
        tests.addAll(testDrinksThatShouldHandleSugar(Command.DrinkType.COFFEE, "Coffee", "C"));
        return tests;
    }

    private List<DynamicTest> testDrinksThatShouldHandleSugar(Command.DrinkType drink, String displayName, String machineName) {
        return Arrays.asList(
                testMakingDrinkWithFullDetailsAndExpectations("No Sugar with " + displayName + " is " + machineName + "::",
                        drink, false, 0, 100, machineName + "::"),
                testMakingDrinkWithFullDetailsAndExpectations("One Sugar with " + displayName + " is" + machineName + ":1:0",
                        drink, false, 1, 100, machineName + ":1:0"),
                testMakingDrinkWithFullDetailsAndExpectations("Two Sugar with " + displayName + " is " + machineName + ":2:0",
                        drink, false, 2, 100, machineName + ":2:0")
        );
    }

    @TestFactory
    List<DynamicTest> should_make_drinks_that_may_not_have_sugar() {
        return Arrays.asList(
                testMakingDrinkWithFullDetailsAndExpectations("No Sugar with Orange is O::",
                        Command.DrinkType.ORANGE, false, 0, 100, "O::"),
                testMakingDrinkWithFullDetailsAndExpectations("One Sugar with Orange is O::",
                        Command.DrinkType.ORANGE, false, 1, 100, "O::"),
                testMakingDrinkWithFullDetailsAndExpectations("Two Sugar with Orange is O::",
                        Command.DrinkType.ORANGE, false, 2, 100, "O::")
        );
    }


    @TestFactory
    DynamicTest[] should_make_tea_with_enough_money() {
        Command.DrinkType drink = Command.DrinkType.TEA;
        return new DynamicTest[]{
                testMakingDrinkWithMoney("Tea costs 40 cents as shown when no money is provided",
                        drink, 0, "M:Tea costs 40 cents more"),
                testMakingDrinkWithMoney("Tea is done with 1 euro",
                        drink, 100, "T::"),
                testMakingDrinkWithMoney("Tea is done with 40 cents",
                        drink, 40, "T::"),
                testMakingDrinkWithMoney("Tea is not done when 1 cent misses",
                        drink, 39, "M:Tea costs 1 cents more"),
        };
    }

    @TestFactory
    DynamicTest[] should_make_coffee_with_enough_money() {
        Command.DrinkType drink = Command.DrinkType.COFFEE;
        return new DynamicTest[]{
                testMakingDrinkWithMoney("Coffee costs 60 cents as shown when no money is provided",
                        drink, 0,"M:Coffee costs 60 cents more"),
                testMakingDrinkWithMoney("Coffee is done with 1 euro",
                        drink, 100, "C::"),
                testMakingDrinkWithMoney("Coffee is done with 50 cents",
                        drink, 60,"C::"),
                testMakingDrinkWithMoney("Coffee is not done when 1 cent misses",
                        drink, 59, "M:Coffee costs 1 cents more"),
        };
    }

    @TestFactory
    DynamicTest[] should_make_chocolate_with_enough_money() {
        return new DynamicTest[]{
                testMakingDrinkWithMoney("Chocolate costs 50 cents as shown when no money is provided",
                        Command.DrinkType.CHOCOLATE, 0, "M:Chocolate costs 50 cents more"),
                testMakingDrinkWithMoney("Chocolate is done with 1 euro",
                        Command.DrinkType.CHOCOLATE, 100, "H::"),
                testMakingDrinkWithMoney("Chocolate is done with 50 cents",
                        Command.DrinkType.CHOCOLATE, 50, "H::"),
                testMakingDrinkWithMoney("Chocolate is not done when 1 cent misses",
                        Command.DrinkType.CHOCOLATE, 49, "M:Chocolate costs 1 cents more"),
        };
    }

    @TestFactory
    DynamicTest[] should_make_orange_with_enough_money() {
        return new DynamicTest[]{
                testMakingDrinkWithMoney("Orange costs 60 cents as shown when no money is provided",
                        Command.DrinkType.ORANGE, 0, "M:Orange costs 60 cents more"),
                testMakingDrinkWithMoney("Orange is done with 1 euro",
                        Command.DrinkType.ORANGE, 100, "O::"),
                testMakingDrinkWithMoney("Orange is done with 60 cents",
                        Command.DrinkType.ORANGE, 60, "O::"),
                testMakingDrinkWithMoney("Orange is not done when 1 cent misses",
                        Command.DrinkType.ORANGE, 59, "M:Orange costs 1 cents more"),
        };
    }

    private DynamicTest testMakingDrinkWithMoney(String displayName, Command.DrinkType drink, int amountPaid, String expectedResult) {
        return testMakingDrinkWithFullDetailsAndExpectations(displayName,
                drink, false, 0, amountPaid, expectedResult);
    }

    private DynamicTest testMakingDrinkWithFullDetailsAndExpectations(String displayName, Command.DrinkType drink, boolean extraHot, int nbSugar, int amountPaid, String expectedResult) {
        return DynamicTest.dynamicTest(displayName, () -> {
            // dynamicTest is NOT surrounded by @beforeEach and @AfterEach executions, only the @TestFactory is, so I need to explicitly handle mocks lifecycle.
            Mockito.reset(maker);

            logic.receives(new Command(drink, extraHot, nbSugar, amountPaid));
            Mockito.verify(maker).make(Mockito.eq(expectedResult));
        });
    }

}
