package com.mibcommerce.user; // Changed package to user

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.mibcommerce.user", "com.mibcommerce.common"})
public class UserServiceApplication // Renamed class
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserServiceApplication.class, args); // Use new class name
        System.out.println( "User Service is running on port 8081!" ); // Updated message
    }
}
    