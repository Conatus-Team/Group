package conatus.common;

import conatus.GroupApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { GroupApplication.class })
public class CucumberSpingConfiguration {}
