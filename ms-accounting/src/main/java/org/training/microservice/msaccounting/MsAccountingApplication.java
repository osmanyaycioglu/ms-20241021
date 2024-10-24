package org.training.microservice.msaccounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.training.microservice.mscommon.error.ErrorConfig;

@SpringBootApplication
@Import(ErrorConfig.class)
public class MsAccountingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAccountingApplication.class,
                              args);
    }

}
