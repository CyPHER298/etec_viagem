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
public class GastoService {

	@Autowired 
	private GastoRepository repository;
	
	@Autowired
	private ViagemRepository viagemRepository;
	
	public List<Gasto> listarTodos(){
		return repository.findAll();
	}
	
	public Gasto inserir(Gasto obj) {
		return repository.save(obj);
	}
	
	public Optional<Gasto> atualizar(Long id, Gasto gasto ){
		
		boolean existe = repository.existsById(id);
		if(!existe) {
			return Optional.empty();
		}
		
		return Optional.of(repository.save(gasto));
	}
	
	public boolean excluir(Long id) {
		boolean existe = repository.existsById(id);
		if (!existe) {
			return false;
		}
		
		List<Viagem> viagem = viagemRepository.findByGastoId(id);
		if(!viagem.isEmpty()) {
			viagemRepository.deleteAll(viagem);
		}
		
		repository.deleteById(id);
		return true;
	}

	public Optional<Gasto> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
