package br.com.edson.spring.boot.hfinitializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//-- --------------------------------------------------------------------------
//
//Class:            SpringBootHfinitializrApplication
//Date:             April 28, 2021
//Last Modified on: April 28, 2021
//
//Author:           Edson Martins Copyright (2021). All rights reserved.
//
//Description:      This is an Spring Boot application used to generate bundle
//                  or a simple hotfix package zip archive. 
//                  A hotfix or quick-fix engineering update is a single, cumulative 
//                  package that includes information (often in the form of one or more files) 
//                  that is used to address a problem in a software product (i.e., a software bug).
//
//-- --------------------------------------------------------------------------
@SpringBootApplication
public class SpringBootHfinitializrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHfinitializrApplication.class, args);
	}

}
