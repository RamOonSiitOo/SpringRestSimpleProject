package es.springrestproject.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.springrestproject.entity.Client;
import es.springrestproject.service.IClientService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {
	
	@Autowired
	private IClientService clientService;
	
	private final Logger log = LoggerFactory.getLogger(ClientRestController.class);
	
	@GetMapping("/clients")
	public List<Client> index(){
		return clientService.findAll();
	}
	
	@GetMapping("/clients/page/{page}")
	public Page<Client> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page,4);
		return clientService.findAll(pageable);
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Client client = null;
		Map<String,Object> response = new HashMap<>();
		try {
			client = clientService.findById(id);
		}catch(DataAccessException dae) {
			response.put("message", "Error trying to get the client id "+id+" from DB");
			response.put("error", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(client==null) {
			response.put("message", "The client id "+id+" doesn't exist on DB");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Client>(client,HttpStatus.OK);
	}
	
	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result){
		Client newClient = null;
		Map<String,Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List <String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The field '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			newClient = clientService.save(client);
		}catch(DataAccessException dae) {
			response.put("message", "Error trying to insert the client to the DB");
			response.put("error", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Client created successfully!!");
		response.put("client", newClient);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
			
	}
		
	@PutMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id){
		Client currentClient = clientService.findById(id);
		Client clientUpdated = null;
		Map<String,Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List <String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The field '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		if(currentClient==null) {
			response.put("message", "Error: The client couldn't be updated!!");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			currentClient.setFirstName(client.getFirstName());
			currentClient.setLastName(client.getLastName());
			currentClient.setEmail(client.getEmail());
			currentClient.setCreateAt(client.getCreateAt());
			clientUpdated = clientService.save(currentClient);
		}catch(DataAccessException dae) {
			response.put("message", "Error: The client couldn't be updated!!");
			response.put("error", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "The client has been updated successfully!!!");
		response.put("client", clientUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String,Object> response = new HashMap<>();
		try {
			Client client = clientService.findById(id);
			String previousPictureName = client.getPicture();
			
			if(previousPictureName != null && previousPictureName.length()>0) {
				Path previousFilePath = Paths.get("uploads").resolve(previousPictureName).toAbsolutePath();
				File previousPicture = previousFilePath.toFile();
				if(previousPicture.exists() && previousPicture.canRead()) {
					previousPicture.delete();
				}
			}
			
			clientService.delete(id);
		}catch(DataAccessException dae) {
			response.put("message", "Error trying remove the client...");
			response.put("error", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "The client has been removed successfully!!");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/clients/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id")Long id){
		Map<String, Object> response = new HashMap<>();
		Client client = clientService.findById(id);
		
		if(!file.isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_"+ file.getOriginalFilename().replace(" ", "");
			Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();
			log.info(filePath.toString());
			
			try {
				Files.copy(file.getInputStream(), filePath);
			}catch(IOException ioe) {
				response.put("message", "Error to upload the client picture "+fileName);
				response.put("error", ioe.getMessage().concat(": ").concat(ioe.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String previousPictureName = client.getPicture();
			
			if(previousPictureName != null && previousPictureName.length()>0) {
				Path previousFilePath = Paths.get("uploads").resolve(previousPictureName).toAbsolutePath();
				File previousPicture = previousFilePath.toFile();
				if(previousPicture.exists() && previousPicture.canRead()) {
					previousPicture.delete();
				}
			}
			
			client.setPicture(fileName);
			clientService.save(client);
			response.put("client",client);
			response.put("message", "You upload the picture "+fileName+" successfully!!");
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{pictureName:.+}")
	public ResponseEntity<Resource> showPicture(@PathVariable String pictureName){
		Path filePath = Paths.get("uploads").resolve(pictureName).toAbsolutePath();
		Resource resource = null;
		
		try {
			resource = new UrlResource(filePath.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!resource.exists() && !resource.isReadable()) {
			throw new RuntimeException("Error couldn't load the picture: "+ pictureName);
		}
		
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}

}
