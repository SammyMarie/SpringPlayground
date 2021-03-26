package com.sammy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class SpringPlayGroundApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads(){
        assertThat(applicationContext).isNotNull();
    }
}
