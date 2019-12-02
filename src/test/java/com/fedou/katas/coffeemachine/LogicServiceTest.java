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
                testMakeRightDrinkWithoutSugar("Tea is T::", Command.DrinkType.TEA, "T::"),
                testMakeRightDrinkWithoutSugar("Hot Chocolate is H::", Command.DrinkType.CHOCOLATE, "H::"),
                testMakeRightDrinkWithoutSugar("Coffee is C::", Command.DrinkType.COFFEE, "C::"),
                testMakeRightDrinkWithoutSugar("Orange juice is O::", Command.DrinkType.ORANGE, "O::"),
        };
    }

    private DynamicTest testMakeRightDrinkWithoutSugar(String displayName, Command.DrinkType drink, String expectedResult) {
        return testMakingDrinkWithExpectations(displayName, drink, false, 0, 100, expectedResult);
    }

    @TestFactory
    DynamicTest[] should_make_extra_hot_drink_or_always_cold() {
        return new DynamicTest[]{
                testMakeRightExtraHotDrinkWithoutSugar("Extra Hot Tea is Th::", Command.DrinkType.TEA, "Th::"),
                testMakeRightExtraHotDrinkWithoutSugar("Extra Hot Chocolate is Hh::", Command.DrinkType.CHOCOLATE, "Hh::"),
                testMakeRightExtraHotDrinkWithoutSugar("Extra Hot Coffee is Ch::", Command.DrinkType.COFFEE, "Ch::"),
                testMakeRightExtraHotDrinkWithoutSugar("Extra Hot Orange juice is COLD O::", Command.DrinkType.ORANGE, "O::"),
        };
    }

    private DynamicTest testMakeRightExtraHotDrinkWithoutSugar(String displayName, Command.DrinkType drink, String expectedResult) {
        return testMakingDrinkWithExpectations(displayName, drink, true, 0, 100, expectedResult);
    }


    @TestFactory
    List<DynamicTest> should_make_hot_drinks_with_sugar() {
        List<DynamicTest> tests = new ArrayList<>();
        tests.addAll(hotDrinkShouldHandleSugar(Command.DrinkType.TEA, "Tea", "T"));
        tests.addAll(hotDrinkShouldHandleSugar(Command.DrinkType.CHOCOLATE, "Chocolate", "H"));
        tests.addAll(hotDrinkShouldHandleSugar(Command.DrinkType.COFFEE, "Coffee", "C"));
        return tests;
    }

    private List<DynamicTest> hotDrinkShouldHandleSugar(Command.DrinkType drink, String displayName, String machineName) {
        return Arrays.asList(
                testMakingDrinkWithExpectations("No Sugar with " + displayName + " is " + machineName + "::", drink, false, 0, 100, machineName + "::"),
                testMakingDrinkWithExpectations("One Sugar with " + displayName + " is" + machineName + ":1:0", drink, false, 1, 100, machineName + ":1:0"),
                testMakingDrinkWithExpectations("Two Sugar with " + displayName + " is " + machineName + ":2:0", drink, false, 2, 100, machineName + ":2:0")
        );
    }
    @TestFactory
    List<DynamicTest> should_make_cold_drinks_ignoring_sugar() {
        return Arrays.asList(
                testMakingDrinkWithExpectations("No Sugar with Orange is O::", Command.DrinkType.ORANGE, false, 0, 100, "O::"),
                testMakingDrinkWithExpectations("One Sugar with Orange is O::", Command.DrinkType.ORANGE, false, 1, 100, "O::"),
                testMakingDrinkWithExpectations("Two Sugar with Orange is O::", Command.DrinkType.ORANGE, false, 2, 100, "O::")
        );
    }


    private DynamicTest testMakingDrinkWithExpectations(String displayName, Command.DrinkType drink, boolean extraHot, int nbSugar, int amountPaid, String expectedResult) {
        return DynamicTest.dynamicTest(displayName, () -> {
            Mockito.reset(maker);
            logic.receives(new Command(drink, extraHot, nbSugar, amountPaid));
            Mockito.verify(maker).make(Mockito.eq(expectedResult));
        });
    }

    @TestFactory
    DynamicTest[] should_make_tea_with_enough_money() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Tea costs 40 cents as shown when no money is provided", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, false, 0, 0));
                    Mockito.verify(maker).make(Mockito.eq("M:Tea costs 40 cents more"));
                }),
                DynamicTest.dynamicTest("Tea is done with 1 euro", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, false, 0, 100));
                    Mockito.verify(maker).make(Mockito.eq("T::"));
                }),
                DynamicTest.dynamicTest("Tea is done with 40 cents", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, false, 0, 40));
                    Mockito.verify(maker).make(Mockito.eq("T::"));
                }),
                DynamicTest.dynamicTest("Tea is not done when 1 cent misses", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, false, 0, 39));
                    Mockito.verify(maker).make(Mockito.eq("M:Tea costs 1 cents more"));
                }),
        };
    }

    @TestFactory
    DynamicTest[] should_make_coffee_with_enough_money() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Coffee costs 60 cents as shown when no money is provided", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, false, 0, 0));
                    Mockito.verify(maker).make(Mockito.eq("M:Coffee costs 60 cents more"));
                }),
                DynamicTest.dynamicTest("Coffee is done with 1 euro", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, false, 0, 100));
                    Mockito.verify(maker).make(Mockito.eq("C::"));
                }),
                DynamicTest.dynamicTest("Coffee is done with 50 cents", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, false, 0, 60));
                    Mockito.verify(maker).make(Mockito.eq("C::"));
                }),
                DynamicTest.dynamicTest("Coffee is not done when 1 cent misses", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, false, 0, 59));
                    Mockito.verify(maker).make(Mockito.eq("M:Coffee costs 1 cents more"));
                }),
        };
    }

    @TestFactory
    DynamicTest[] should_make_chocolate_with_enough_money() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Chocolate costs 50 cents as shown when no money is provided", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, false, 0, 0));
                    Mockito.verify(maker).make(Mockito.eq("M:Chocolate costs 50 cents more"));
                }),
                DynamicTest.dynamicTest("Chocolate is done with 1 euro", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, false, 0, 100));
                    Mockito.verify(maker).make(Mockito.eq("H::"));
                }),
                DynamicTest.dynamicTest("Chocolate is done with 50 cents", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, false, 0, 50));
                    Mockito.verify(maker).make(Mockito.eq("H::"));
                }),
                DynamicTest.dynamicTest("Chocolate is not done when 1 cent misses", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, false, 0, 49));
                    Mockito.verify(maker).make(Mockito.eq("M:Chocolate costs 1 cents more"));
                }),
        };
    }

    @TestFactory
    DynamicTest[] should_make_orange_with_enough_money() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Orange costs 60 cents as shown when no money is provided", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.ORANGE, false, 0, 0));
                    Mockito.verify(maker).make(Mockito.eq("M:Orange costs 60 cents more"));
                }),
                DynamicTest.dynamicTest("Orange is done with 1 euro", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.ORANGE, false, 0, 100));
                    Mockito.verify(maker).make(Mockito.eq("O::"));
                }),
                DynamicTest.dynamicTest("Orange is done with 60 cents", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.ORANGE, false, 0, 60));
                    Mockito.verify(maker).make(Mockito.eq("O::"));
                }),
                DynamicTest.dynamicTest("Orange is not done when 1 cent misses", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.ORANGE, false, 0, 59));
                    Mockito.verify(maker).make(Mockito.eq("M:Orange costs 1 cents more"));
                }),
        };
    }
}
