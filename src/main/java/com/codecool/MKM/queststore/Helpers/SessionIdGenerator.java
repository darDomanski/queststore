package com.codecool.MKM.queststore.Helpers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class SessionIdGenerator {
    private final int LENGTH = 32;
    private SecureRandom random = new SecureRandom();
    private static List<String> secureNumbersList = new ArrayList<>();

    public String getSessionId() {

        BigInteger bigInteger;

        do {
            bigInteger = new BigInteger(130, random);
        } while (secureNumbersList.contains(bigInteger.toString(LENGTH)));

        String sessionId = bigInteger.toString(LENGTH);

        secureNumbersList.add(sessionId);

        return sessionId.toUpperCase();
    }
}
