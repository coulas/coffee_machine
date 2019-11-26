package com.fedou.katas.coffeemachine;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
    void should_make_tea_without_sugar() {
        logic.receives(new Command(Command.DrinkType.TEA, 0));
        Mockito.verify(maker).make(Mockito.eq("T::"));
    }
}
