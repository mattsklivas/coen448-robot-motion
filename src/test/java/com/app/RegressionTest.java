package com.app;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RegressionTest {


    /*
     * What changed:
     * - parseCommand -> replayingHistory added, commandHistory.add, added condition if (param == null || !validateParamFormat(param)),
     * - add condtion if (option == 'h') replayHistory();
     * - formatKnownCommands -> added alpha.add('h');
     * -- added replayHistory() method
     */


    // Test Function #27
    // Test type : Regression
    // Input : 
    // Description : 
    // Expected output : 
    // Tester : Nicholas Harris
    // Date : 4th April
    

}
