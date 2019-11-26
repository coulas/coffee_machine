package com.fedou.katas.coffeemachine;

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

    // TODO : do tests for :
    /**
     *  The drink maker should receive the correct instructions for my coffee / tea / chocolate order
     *  without sugar
     *  T:: => Tea
     *  H:: => Hot Chocolate
     *  C:: => Coffee
     *
     */

    /**
     *  I want to be able to send instructions to the drink maker to add one or two sugars
     *  When my order contains sugar the drink maker should add a stick (touillette) with it
     *  with sugar : whatever is X followed by one or two sugar followed by a zero meaning you have a stick.
     *  X:1:0
     *  X:2:0
     */

}
