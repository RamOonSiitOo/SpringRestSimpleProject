package es.springrestproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.springrestproject.dao.IClientDAO;
import es.springrestproject.entity.Client;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDAO clientDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return (List<Client>)clientDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Client> findAll(Pageable page) {
		return (Page<Client>)clientDAO.findAll(page);
	}

	@Override
	public Client findById(Long id) {
		return clientDAO.findById(id).orElse(null);
	}

	@Override
	public Client save(Client client) {
		return clientDAO.save(client);
	}

	@Override
	public void delete(Long id) {
		clientDAO.delete(findById(id));
	}

}
