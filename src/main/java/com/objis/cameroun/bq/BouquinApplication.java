package com.objis.cameroun.bq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.objis.cameroun.bq.domaine.entity.Livre;
import com.objis.cameroun.bq.repository.LivreRepository;

@SpringBootApplication
public class BouquinApplication implements CommandLineRunner {

	@Autowired
	private LivreRepository livreRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BouquinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		livreRepository.save(new Livre(null, "Comment se faire des amis", "Dale Carnegie", null));
		livreRepository.save(new Livre(null, "Au Cameroun de Paul Biya", "Fanny Pigeaud", null));
		livreRepository.save(new Livre(null, "L'art de la guerre", "Sun Tzu", null));


	}

}
