package br.com.etechoracio.etec_viagem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.entity.Viagem;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;
import br.com.etechoracio.etec_viagem.repository.ViagemRepository;
 

@Service
public class ViagemService {

	@Autowired 
	private ViagemRepository repository;
	
	@Autowired
	private GastoRepository gastoRepository;
	
	public List<Viagem> listarTodos(){
		return repository.findAll();
	}
	
	public Viagem inserir(Viagem obj) {
		return repository.save(obj);
	}
	
	public Optional<Viagem> atualizar(Long id, Viagem viagem){
		
		boolean existe = repository.existsById(id);
		if(!existe) {
			return Optional.empty();
		}
		
		return Optional.of(repository.save(viagem));
	}
	
	public boolean excluir(Long id) {
		boolean existe = repository.existsById(id);
		if (!existe) {
			return false;
		}
		
		List<Gasto> gastos = gastoRepository.findByViagemId(id);
		if(!gastos.isEmpty()) {
			gastoRepository.deleteAll(gastos);
		}
		
		repository.deleteById(id);
		return true;
	}

	public Optional<Viagem> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

