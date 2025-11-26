package com.green.pipeline_dummy;

//마이바티스용 상속

import net.datafaker.Faker;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Locale;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MbDummy {
    public static Faker koFaker = new Faker(new Locale("ko")); // 한글
    public static Faker faker = new Faker(new Locale("en")); // 영어
}
