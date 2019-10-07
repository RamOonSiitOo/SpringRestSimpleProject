package es.springrestproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.springrestproject.entity.Client;

public interface IClientService {
	public List<Client> findAll();
	public Page<Client> findAll(Pageable page);
	public Client findById(Long id);
	public Client save(Client client);
	public void delete(Long id);
	
}
