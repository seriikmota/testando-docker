package br.ueg.acervodigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.ueg.acervodigitalarquitetura", "br.ueg.acervodigital"})
public class AcervoDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcervoDigitalApplication.class, args);
	}

}
