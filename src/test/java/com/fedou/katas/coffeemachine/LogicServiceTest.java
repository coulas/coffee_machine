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
            logic.receives(new Command(drink, 0));
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
            logic.receives(new Command(Command.DrinkType.TEA, nbSugar));
            Mockito.verify(maker).make(Mockito.eq(expectedResult));
        });
    }

}
