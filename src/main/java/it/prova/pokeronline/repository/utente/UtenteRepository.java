package it.prova.pokeronline.repository.utente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.model.StatoUtente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, CustomUtenteRepository{

	@EntityGraph(attributePaths = { "ruoli" })
	Optional<Utente> findByUsername(String username);
	
	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	Optional<Utente> findByIdConRuoli(Long id);
	
	Utente findByUsernameAndPassword(String username, String password);
	
	//caricamento eager, ovviamente si può fare anche con jpql
	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
	
	List<Utente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome, String nome);

	@Query("from Utente u left join fetch u.tavolo where u.username like ?1")
	Utente findByUsernameConTavolo(String username);
}
