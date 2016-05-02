package com.weghst.setaria.core;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@ContextConfiguration("classpath:spring-setaria-core-test.xml")
public class SpringTestSupport extends AbstractTransactionalTestNGSpringContextTests {
}
