package com.kata;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty","html:target/cucumber"},
        tags = {"~@todo"},
        features = {"src/test/ressources/features"},
        glue = {"com.kata"}
)
public class BankAccountLocalIT {
}
