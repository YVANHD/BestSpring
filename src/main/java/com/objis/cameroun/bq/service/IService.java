package com.objis.cameroun.bq.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.objis.cameroun.bq.domaine.entity.Livre;

public interface IService {
	
	public List<Livre> findListeLivreService(@Param("mc") String des);
	public Livre saveLivreService(Livre livre);
	public Page<Livre> findPageLivreService(@Param("mc") String des, Pageable pageable);

	public Optional<Livre> editLivreService(Long id);
	public void deleteLivreService(Long id);

	

}
