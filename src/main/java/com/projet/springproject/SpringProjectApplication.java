package com.projet.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class SpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
		open();
	}

	private static void open() {
		System.setProperty("java.awt.headless", "false");
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI("http://localhost:8080/"));
		}
		catch(Exception e) {
			System.out.println("ERROR LOADING PAGE");
		}
	}
}
