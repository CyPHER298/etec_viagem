package br.com.etechoracio.etec_viagem.controller;

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.entity.Viagem;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;
import br.com.etechoracio.etec_viagem.service.GastoService;

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
	private GastoService service;

	
	@GetMapping
	public List<Gasto> listarTodos(){
		return service.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Gasto> buscarPorId(@PathVariable Long id){
		Optional<Gasto> existe = service.buscarPorId(id);
		return existe.isPresent() ? ResponseEntity.ok(existe.get())
								  : ResponseEntity.notFound().build();
		}
	
	@PostMapping
	public ResponseEntity<Gasto> insert(@RequestBody Gasto obj){
		service.inserir(obj);
		return ResponseEntity.ok(obj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Gasto> atualizar(@PathVariable Long id, @RequestBody Gasto gasto){
		
		Optional<Gasto> existe = service.atualizar(id,gasto);
		if (!existe.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();	
	}

	
}