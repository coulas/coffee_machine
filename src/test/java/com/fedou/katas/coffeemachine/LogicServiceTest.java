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
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LogicServiceTest {

    @Mock
    DrinkMakerService maker;
    @InjectMocks
    LogicService logic;

    @TestFactory
    DynamicTest[] should_make_rigth_drink() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Tea is T::", () -> executeTestsForCase(
                        Command.DrinkType.TEA, 0, "T::")),
                DynamicTest.dynamicTest("Hot Chocolate is H::", () -> executeTestsForCase(
                        Command.DrinkType.CHOCOLATE, 0, "H::")),
                DynamicTest.dynamicTest("Coffee is C::", () -> executeTestsForCase(
                        Command.DrinkType.COFFEE, 0, "C::")),
        };
    }

    @TestFactory
    DynamicTest[] should_make_drink_with_sugar() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("No Sugar with Tea is T::", () -> executeTestsForCase(
                        Command.DrinkType.TEA, 0, "T::")),
                DynamicTest.dynamicTest("One Sugar with Tea is T:1:0", () -> executeTestsForCase(
                        Command.DrinkType.TEA, 1, "T:1:0")),
                DynamicTest.dynamicTest("Two Sugar with Tea is T:2:0", () -> executeTestsForCase(
                        Command.DrinkType.TEA, 2, "T:2:0")),
        };
    }

    @TestFactory
    List<DynamicTest> should_make_any_drink_with_any_number_of_sugar() {
        Collection<DrinkTest> drinks = Arrays.asList(
                new DrinkTest(Command.DrinkType.TEA, "T"),
                new DrinkTest(Command.DrinkType.CHOCOLATE, "H"),
                new DrinkTest(Command.DrinkType.COFFEE, "C")
        );
        Collection<SugarTest> sugars = Arrays.asList(
                new SugarTest(0, "::"),
                new SugarTest(1, ":1:0"),
                new SugarTest(2, ":2:0")
        );
        ArrayList<DynamicTest> dynamicTests = new ArrayList<>(drinks.size() * sugars.size());
        for (DrinkTest drink : drinks) {
            for (SugarTest sugar : sugars) {
                String expectedResult = drink.output + sugar.output;
                dynamicTests.add(
                        DynamicTest.dynamicTest("making " + drink.drink + " with " + sugar.nbSugar + " gives " + expectedResult,
                                () -> executeTestsForCase(drink.drink, sugar.nbSugar, expectedResult)
                        )
                );
            }
        }
        return dynamicTests;
    }

    class DrinkTest {
        Command.DrinkType drink;
        String output;

        public DrinkTest(Command.DrinkType drink, String output) {
            this.drink = drink;
            this.output = output;
        }
    }

    class SugarTest {
        int nbSugar;
        String output;

        public SugarTest(int nbSugar, String output) {
            this.nbSugar = nbSugar;
            this.output = output;
        }
    }

    private void executeTestsForCase(Command.DrinkType drink, int nbSugar, String expectedResult) {
        logic.receives(new Command(drink, nbSugar));
        Mockito.verify(maker).make(Mockito.eq(expectedResult));
    }

}
