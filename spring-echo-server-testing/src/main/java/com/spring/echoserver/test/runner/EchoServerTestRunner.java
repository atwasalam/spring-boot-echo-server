package com.spring.echoserver.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/java/com/spring/echoserver/test/feature"},
        glue = {"com.spring.echoserver.test.stepdef"},
        plugin = { "pretty", "json:target/cucumber/cucumber.json" }
)
public class EchoServerTestRunner {

}
