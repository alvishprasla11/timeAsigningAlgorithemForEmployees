package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CalculationTest {

    @Test
    void alotTime() {

    }

    @Test
    void overTime() {
    }

    @Test
    void hours() {
    }

    @Test
    void hoursContinued() {
       int [] Expected={1,1,4};
       int []Result=Calculation.hoursContinued(5,4);
       assertArrayEquals(Expected,Result);
    }
}