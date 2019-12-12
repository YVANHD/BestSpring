package com.objis.cameroun.bq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.objis.cameroun.bq.domaine.entity.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {

}
