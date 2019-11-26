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

    /**
     * The drink maker should receive the correct instructions for my coffee / tea / chocolate order
     * without sugar
     * T:: => Tea
     * H:: => Hot Chocolate
     * C:: => Coffee
     */

    @TestFactory
    DynamicTest[] should_make_rigth_drink() {
        return new DynamicTest[]{
                DynamicTest.dynamicTest("Tea is T::", () -> executeTestsForCase(
                        commandWithoutSugarFor(Command.DrinkType.TEA), "T::")),
                DynamicTest.dynamicTest("Hot Chocolate is H::", () -> executeTestsForCase(
                        commandWithoutSugarFor(Command.DrinkType.CHOCOLATE), "H::")),
                DynamicTest.dynamicTest("Coffee is C::", () -> executeTestsForCase(
                        commandWithoutSugarFor(Command.DrinkType.COFFEE), "C::")),
        };
    }

        /**
         *  I want to be able to send instructions to the drink maker to add one or two sugars
         *  When my order contains sugar the drink maker should add a stick (touillette) with it
         *  with sugar : whatever is X followed by one or two sugar followed by a zero meaning you have a stick.
         *  X:1:0
         *  X:2:0
         */
        @TestFactory
        DynamicTest[] should_make_drink_with_sugar() {
            return new DynamicTest[]{
                    DynamicTest.dynamicTest("No Sugar with Tea is T::", () -> executeTestsForCase(
                            new Command(Command.DrinkType.TEA, 0), "T::")),
                    DynamicTest.dynamicTest("One Sugar with Tea is T:1:0", () -> executeTestsForCase(
                            new Command(Command.DrinkType.TEA, 0), "T::")),
                    DynamicTest.dynamicTest("Two Sugar with Tea is T:2:0", () -> executeTestsForCase(
                            new Command(Command.DrinkType.TEA, 0), "T::")),
            };
        }

    private void executeTestsForCase(Command command, String expectedResult) {
        logic.receives(command);
        Mockito.verify(maker).make(Mockito.eq(expectedResult));
    }

    private Command commandWithoutSugarFor(Command.DrinkType drinkType) {
        return new Command(drinkType, 0);
    }

}
