package es.springrestproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import es.springrestproject.entity.Client;

public interface IClientDAO extends JpaRepository<Client, Long>{
	
}
