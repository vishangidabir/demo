package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateCensusAnalyserTest {

    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resource/StateCensus.csv";
    private static final String STATE_CENSUS_WRONG_FILENAME = "./src/test/resource/StateCensus12.csv";
    private static final String STATE_CENSUS_WRONG_DELIMETER = "./src/test/resource/WrongDelimiterStateCensus.csv";
    private static final String STATE_CENSUS_WRONG_HEADER = "./src/test/resource/WrongHeaderStateCensus.csv";

    @Test
    public void checkToEnsure_NumberOfRecordsMatches() throws CSVCensusException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(STATE_CENSUS_DATA_PATH);
        assertEquals(37, stateCensusAnalyser.readStateData(CSVStateCensus.class));
    }

    @Test
    public void givenWrongFileName_ShouldThrowNoSuchFileException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(STATE_CENSUS_WRONG_FILENAME);
        try {
            int value = stateCensusAnalyser.readStateData(CSVStateCensus.class);
            assertEquals(37, value);

        } catch (CSVCensusException e) {
            System.out.println(e.getMessage());
            assertEquals("Please enter proper file name", e.getMessage());
        }
    }

    @Test
    public void givenMethod_WrongDelimiterPosition_ShouldReturnException()  {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(STATE_CENSUS_WRONG_DELIMETER);
            int value = stateCensusAnalyser.readStateData(CSVStateCensus.class);
            assertEquals(37, value);
        } catch (CSVCensusException e) {
            System.out.println(e.getMessage());
            assertEquals("Exception due to incorrect delimiter position", e.getMessage());
        }
    }

    @Test
    public void givenMethod_ifFoundNoHeader_ShouldReturnException()
    {

        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(STATE_CENSUS_WRONG_HEADER);
            int value = stateCensusAnalyser.readStateData(CSVStateCensus.class);
            assertEquals(37, value);
        }
        catch (CSVCensusException e)
        {
            System.out.println(e.getMessage());
            assertEquals("Exception due to Header", e.getMessage());
        }
    }
}