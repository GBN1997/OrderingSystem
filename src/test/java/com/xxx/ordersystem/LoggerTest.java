package com.xxx.ordersystem;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LoggerTest {
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test1(){
        log.debug("debug...");
        log.info("info...");
        log.error("error...");
    }
}
