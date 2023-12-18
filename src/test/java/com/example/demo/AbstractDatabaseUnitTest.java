package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = {AbstractDatabaseTest.Initializer.class})
public abstract class AbstractDatabaseUnitTest extends AbstractDatabaseTest {


}
