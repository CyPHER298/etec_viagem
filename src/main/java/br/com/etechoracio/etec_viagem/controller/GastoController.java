package br.com.etechoracio.etec_viagem.controller;

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gasto")
public class GastoController {
	
	@Autowired
	private GastoRepository repository;
	List<Gasto> gasto = new ArrayList<Gasto>();
	
	@GetMapping
	public ResponseEntity<List<Gasto>> getAll(){
		gasto = repository.findAll();
		return ResponseEntity.ok(gasto);
	}
	
	@GetMapping("/{id}")
	
	public ResponseEntity<Gasto>getById(@PathVariable Integer id){
		Optional<Gasto> gastoOptional = repository.findById(id);
		if(gastoOptional.isPresent()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(gastoOptional.get());
		
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Gasto> insert(@RequestBody Gasto gasto){
		repository.save(gasto);
		return ResponseEntity.status(HttpStatus.CREATED).body(gasto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Gasto> update(@PathVariable Integer id, @RequestBody Gasto gasto){
		
		boolean existe = repository.existsById(id);
		if (existe) {
			repository.save(gasto);
			return ResponseEntity.status(HttpStatus.CREATED).body(gasto);
		}
		return ResponseEntity.noContent().build();	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Gasto> delete(@PathVariable Integer id){
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
}