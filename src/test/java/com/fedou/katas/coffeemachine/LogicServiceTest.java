package com.fedou.katas.coffeemachine;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        };
    }

    private DynamicTest testMakeRightDrinkWithoutSugar(String displayName, Command.DrinkType drink, String expectedResult) {
        return DynamicTest.dynamicTest(displayName, () -> {
            logic.receives(new Command(drink, 0, 100));
            Mockito.verify(maker).make(Mockito.eq(expectedResult));
        });
    }

    @TestFactory
    DynamicTest[] should_make_tea_with_sugar() {
        return new DynamicTest[]{
                testMakingTeaWithSugar("No Sugar with Tea is T::", 0, "T::"),
                testMakingTeaWithSugar("One Sugar with Tea is T:1:0", 1, "T:1:0"),
                testMakingTeaWithSugar("Two Sugar with Tea is T:2:0", 2, "T:2:0"),
        };
    }

    private DynamicTest testMakingTeaWithSugar(String displayName, int nbSugar, String expectedResult) {
        return DynamicTest.dynamicTest(displayName, () -> {
            logic.receives(new Command(Command.DrinkType.TEA, nbSugar, 100));
            Mockito.verify(maker).make(Mockito.eq(expectedResult));
        });
    }

    @TestFactory
    DynamicTest[] should_make_tea_with_enough_money() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Tea costs 40 cents as shown when no money is provided", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, 0, 0));
                    Mockito.verify(maker).make(Mockito.eq("M:Tea costs 40 cents more"));
                }),
                DynamicTest.dynamicTest("Tea is done with 1 euro", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, 0, 100));
                    Mockito.verify(maker).make(Mockito.eq("T::"));
                }),
                DynamicTest.dynamicTest("Tea is done with 40 cents", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, 0, 40));
                    Mockito.verify(maker).make(Mockito.eq("T::"));
                }),
                DynamicTest.dynamicTest("Tea is not done when 1 cent misses", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.TEA, 0, 39));
                    Mockito.verify(maker).make(Mockito.eq("M:Tea costs 1 cents more"));
                }),
        };
    }

    @TestFactory
    DynamicTest[] should_make_coffee_with_enough_money() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Coffee costs 60 cents as shown when no money is provided", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, 0, 0));
                    Mockito.verify(maker).make(Mockito.eq("M:Coffee costs 60 cents more"));
                }),
                DynamicTest.dynamicTest("Coffee is done with 1 euro", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, 0, 100));
                    Mockito.verify(maker).make(Mockito.eq("C::"));
                }),
                DynamicTest.dynamicTest("Coffee is done with 50 cents", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, 0, 60));
                    Mockito.verify(maker).make(Mockito.eq("C::"));
                }),
                DynamicTest.dynamicTest("Coffee is not done when 1 cent misses", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.COFFEE, 0, 59));
                    Mockito.verify(maker).make(Mockito.eq("M:Coffee costs 1 cents more"));
                }),
        };
    }

    @TestFactory
    DynamicTest[] should_make_chocolate_with_enough_money() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Chocolate costs 50 cents as shown when no money is provided", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, 0, 0));
                    Mockito.verify(maker).make(Mockito.eq("M:Chocolate costs 50 cents more"));
                }),
                DynamicTest.dynamicTest("Chocolate is done with 1 euro", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, 0, 100));
                    Mockito.verify(maker).make(Mockito.eq("H::"));
                }),
                DynamicTest.dynamicTest("Chocolate is done with 50 cents", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, 0, 50));
                    Mockito.verify(maker).make(Mockito.eq("H::"));
                }),
                DynamicTest.dynamicTest("Chocolate is not done when 1 cent misses", () -> {
                    Mockito.reset(maker);
                    logic.receives(new Command(Command.DrinkType.CHOCOLATE, 0, 49));
                    Mockito.verify(maker).make(Mockito.eq("M:Chocolate costs 1 cents more"));
                }),
        };
    }
}
