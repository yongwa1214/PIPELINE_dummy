package com.green.pipeline_dummy;

import net.datafaker.Faker;


import java.util.Locale;


public class JpaDummy {
    public static Faker koFaker = new Faker(new Locale("ko")); // 한글
    public static Faker faker = new Faker(new Locale("en")); // 영어
}
