package com.codecool.MKM.queststore;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParseDataTest {

    @Test
    public void parseFormDataTest() throws IOException {
        Map<String, String> resultFromMetod;
        String formData = "a=a&b=b&c=c";
        Map<String, String> result = new HashMap();
        result.put("a", "a");
        result.put("b", "b");
        result.put("c", "c");
        ParseData parseData= new ParseData();
        resultFromMetod = parseData.parseFormData(formData);
        assertEquals(  result , resultFromMetod );
    }

}