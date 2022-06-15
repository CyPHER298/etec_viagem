package br.com.etechoracio.etec_viagem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.etec_viagem.entity.Viagem;



public interface ViagemRepository extends JpaRepository<Viagem, Long> {

	

	boolean existsById(Integer id);

	void deleteById(Integer id);

	Optional<Viagem> findById(Integer id);

	
}
