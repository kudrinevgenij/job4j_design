package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte bt = 21;
        int i = 22;
        double d = 2.3;
        char c = 'c';
        short s = 25;
        float f = 2.4F;
        long l = 25L;
        boolean bl = true;

        LOG.debug("vars byte : {}, int : {}, double : {}, char : {}, short : {}, float : {}, long : {}, boolean : {}", bt, i, d, c, s, f, l, bl);
    }
}