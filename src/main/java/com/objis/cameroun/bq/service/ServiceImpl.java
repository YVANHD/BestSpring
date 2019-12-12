package com.objis.cameroun.bq.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Service;

import com.objis.cameroun.bq.domaine.entity.Livre;
import com.objis.cameroun.bq.repository.LivreRepository;

@Service
public class ServiceImpl implements IService {
	//Injection de l'interface AgriculteurRepository
	@Autowired
	private LivreRepository livreRepository;

	
	public Livre saveLivreService(Livre livre) {
		return livreRepository.save(livre);
	}

	@RestResource(path="/byDesignation")
	public List<Livre> findListeLivreService(@Param("mc") String des) {
		return livreRepository.findAll();
	}

	public Page<Livre> findPageLivreService(@Param("mc") String des, Pageable pageable) {
		return livreRepository.findAll(pageable) ;
	}

	public Optional<Livre> editLivreService(Long id) {
		return livreRepository.findById(id);
	}

	public void deleteLivreService(Long id) {
	 livreRepository.deleteById(id);
	}

	



}
