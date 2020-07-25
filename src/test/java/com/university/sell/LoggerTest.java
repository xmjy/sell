package com.university.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1(){
        String  fxm="林就远";
        String  mzh="毛紫慧";
        log.debug("debug...");
        log.info("info...");
        log.error("error...");
        log.info(fxm+" "+mzh);
        log.info("fxm:{},喜欢(❤ ω ❤)mzh:{}",fxm,mzh);//{ }是占位符
    }
}
