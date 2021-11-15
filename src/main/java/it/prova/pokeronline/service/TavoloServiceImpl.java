package it.prova.pokeronline.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService{

	@Autowired
	private TavoloRepository repository;


	@Transactional(readOnly = true)
	public List<Tavolo> listAllElements() {
		return (List<Tavolo>)repository.findAll();
	}

	@Transactional(readOnly = true)
	public Tavolo caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}
	
//	@Transactional(readOnly = true)
//	public Tavolo caricaSingoloTavoloConGiocatori(Long id) {
//		return repository.findByIdConGiocatori(id).orElse(null);
//	}

	@Transactional
	public void aggiorna(Tavolo tavoloInstance) {
		repository.save(tavoloInstance);
	}

	@Transactional
	public void inserisciNuovo(Tavolo tavoloInstance) {
		tavoloInstance.setDateCreated(new Date());
		repository.save(tavoloInstance);
	}

	@Transactional
	public void rimuovi(Tavolo tavoloInstance) {
		repository.delete(tavoloInstance);
	}

	@Transactional(readOnly = true)
	public List<Tavolo> findMieiTavoliByExample(TavoloDTO example) {
		return repository.findMieiTavoliByExample(example);
	}

	@Override
	public Tavolo caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Tavolo> listAllTavoliUtente(Utente user) {
		return repository.findAllByUtenteCreatore_IdIs(user.getId());
	}


}
