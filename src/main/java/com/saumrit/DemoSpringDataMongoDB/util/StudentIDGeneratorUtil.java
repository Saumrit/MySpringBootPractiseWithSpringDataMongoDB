package com.saumrit.DemoSpringDataMongoDB.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

@Component
public class StudentIDGeneratorUtil {

    public  String generateByApacheCommons(int length) {
        String randomString = RandomStringUtils.secure().nextAlphanumeric(length);
        System.out.println("Random Alphanumeric String: " + randomString);
        return randomString;
    }

    public  String generateByApacheText(int length) {
        String randomString = RandomStringGenerator.builder().setAccumulate(true)
                .withinRange(new char[][] { { 'a', 'z' }, { 'A', 'Z' }, { '0', '9' } })
                        .get().generate(length);
        System.out.println("Random Alphanumeric String: " + randomString);
        return randomString;
    }
}
