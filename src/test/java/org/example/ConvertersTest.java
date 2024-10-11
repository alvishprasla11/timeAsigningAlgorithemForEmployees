package org.example;

import static org.junit.jupiter.api.Assertions.*;

class ConvertersTest {

    @org.junit.jupiter.api.Test
    void converter() {
        int [] test={0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0};
        String Expected="2AM-3PM";
        String Result=Converters.Converter(test);
        assertEquals(Expected,Result);

    }

    @org.junit.jupiter.api.Test
    void timeConvert() {
    }

    @org.junit.jupiter.api.Test
    void hoursConverter() {
    }
}