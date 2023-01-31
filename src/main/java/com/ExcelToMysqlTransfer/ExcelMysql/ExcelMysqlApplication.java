package com.ExcelToMysqlTransfer.ExcelMysql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExcelMysqlApplication   implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExcelMysqlApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        System.out.println("this is testing");
    }

}
