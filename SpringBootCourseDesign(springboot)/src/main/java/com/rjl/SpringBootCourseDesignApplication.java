package com.rjl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class}
//@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class SpringBootCourseDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCourseDesignApplication.class, args);
	}

}
