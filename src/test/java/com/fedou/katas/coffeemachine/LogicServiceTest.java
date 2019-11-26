package com.fedou.katas.coffeemachine;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LogicServiceTest {
    @Test
    void truth() {
        Assertions.assertThat(true).isFalse();
    }
}
