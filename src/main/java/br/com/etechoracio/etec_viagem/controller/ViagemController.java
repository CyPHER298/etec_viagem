package br.com.etechoracio.etec_viagem.controller;

import br.com.etechoracio.etec_viagem.entity.Viagem;
import br.com.etechoracio.etec_viagem.repository.ViagemRepository;

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
@RequestMapping("/viagem")
public class ViagemController {
	
	@Autowired
	private ViagemRepository repository;
	List<Viagem> viagem = new ArrayList<Viagem>();
	
	@GetMapping
	public ResponseEntity<List<Viagem>> getAll(){
		viagem = repository.findAll();
		return ResponseEntity.ok(viagem);
	}
	
	@GetMapping("/{id}")
	
	public ResponseEntity<Viagem>getById(@PathVariable Integer id){
		Optional<Viagem> viagemOptional = repository.findById(id);
		if(viagemOptional.isPresent()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(viagemOptional.get());
		
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Viagem> insert(@RequestBody Viagem viagem){
		repository.save(viagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(viagem);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Viagem> update(@PathVariable Integer id, @RequestBody Viagem viagem){
		
		boolean existe = repository.existsById(id);
		if (existe) {
			repository.save(viagem);
			return ResponseEntity.status(HttpStatus.CREATED).body(viagem);
		}
		return ResponseEntity.noContent().build();	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Viagem> delete(@PathVariable Integer id){
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
}




	






