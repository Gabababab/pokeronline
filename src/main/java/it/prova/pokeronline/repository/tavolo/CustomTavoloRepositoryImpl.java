package it.prova.pokeronline.repository.tavolo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

public class CustomTavoloRepositoryImpl implements CustomTavoloRepository {

	@PersistenceContext
	private EntityManager entityManager;

//	@Override
//	public List<Tavolo> findByExample(TavoloDTO example) {
//		Map<String, Object> paramaterMap = new HashMap<String, Object>();
//		List<String> whereClauses = new ArrayList<String>();
//
//		StringBuilder queryBuilder = new StringBuilder(
//				"select distinct t from Tavolo t join fetch t.utenteCreatore uc where t.id =t.id");
//
//		if (StringUtils.isNotEmpty(example.getDenominazione())) {
//			whereClauses.add(" t.denominazione  like :denominazione ");
//			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
//		}
//		if (example.getDateCreated() != null) {
//			whereClauses.add(" t.dateCreated >= :dateCreated ");
//			paramaterMap.put("dateCreated", example.getDateCreated());
//		}
//		if (example.getCreditoMinimo() != null) {
//			whereClauses.add(" t.creditoMinimo >= :credito");
//			paramaterMap.put("credito", example.getCreditoMinimo());
//		}
//		if (example.getEsperienzaMinima() != null) {
//			whereClauses.add(" t.esperienzaMinima >= :exp");
//			paramaterMap.put("exp", example.getEsperienzaMinima());
//		}
//		if (example.getUtenteCreatore() != null) {
//			whereClauses.add(" uc.id = :idUtenteCreatore ");
//			paramaterMap.put("idUtenteCreatore", example.getUtenteCreatore().getId());
//		}
//
////		if (example.getGiocatoreCercato() != null) {
////			whereClauses.add("g.id = :idGiocatore");
////			for (Utente item : example.getUtentiGiocatori()) {
////				if (item.getId() == example.getGiocatoreCercato().getId())
////					paramaterMap.put("idGiocatore", item.getId());
////			}
////		}
//
//		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
//		queryBuilder.append(StringUtils.join(whereClauses, " and "));
//		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);
//
//		for (String key : paramaterMap.keySet()) {
//			typedQuery.setParameter(key, paramaterMap.get(key));
//		}
//
//		return typedQuery.getResultList();
//	}
	
	@Override
	public List<Tavolo> findByExample(TavoloDTO example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();
		
		if(example.getEsperienzaMinima() == null)
			example.setEsperienzaMinima(null);
		
		if(example.getCreditoMinimo() == null)
			example.setCreditoMinimo(0);
		
		StringBuilder queryBuilder = new StringBuilder("select distinct t from Tavolo t join fetch t.utenteCreatore uc");

		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" t.denominazione  like :denominazione ");
			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
		}
		if (example.getDateCreated() != null) {
			whereClauses.add(" t.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		if (example.getCreditoMinimo() != null) {
			whereClauses.add(" t.creditoMinimo >= :credito");
			paramaterMap.put("credito", example.getCreditoMinimo());
		}
		if (example.getEsperienzaMinima() != null) {
			whereClauses.add(" t.esperienzaMinima >= :exp");
			paramaterMap.put("exp", example.getEsperienzaMinima());
		}
		if (example.getUtenteCreatore() != null) {
			whereClauses.add(" uc.id = :idUtenteCreatore ");
			paramaterMap.put("idUtenteCreatore", example.getUtenteCreatore());
		}
		if(example.getGiocatoreCercato() != null) {
			whereClauses.add(" g.id = :idGiocatore ");
			paramaterMap.put("idGiocatore", example.getGiocatoreCercato());
		}
		
		if(example.getGiocatoreCercato() != null)
			queryBuilder.append(" join fetch t.utenti g ");
		queryBuilder.append(" where t.id = t.id ");
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Tavolo> findMieiTavoliByExample(TavoloDTO example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select distinct t from Tavolo t join fetch t.utenteCreatore uc where t.id = t.id");

		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" t.denominazione  like :denominazione ");
			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
		}
		if (example.getCreditoMinimo() != null) {
			whereClauses.add(" t.creditoMinimo >= :credito");
			paramaterMap.put("credito", example.getCreditoMinimo());
		}
		if (example.getDateCreated() != null) {
			whereClauses.add(" t.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		if (example.getEsperienzaMinima() != null) {
			whereClauses.add(" t.esperienzaMinima >= :exp");
			paramaterMap.put("exp", example.getEsperienzaMinima());
		}
		whereClauses.add(" uc.id = :idCreatore ");
		paramaterMap.put("idCreatore", example.getUtenteCreatore());

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));

		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Tavolo> findByExampleGestioneAdmin(TavoloDTO example) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		if (example.getEsperienzaMinima() == null)
			example.setEsperienzaMinima(0);

		if (example.getCreditoMinimo() == null)
			example.setCreditoMinimo(0);

		StringBuilder queryBuilder = new StringBuilder(
				"select t from Tavolo t join fetch t.utenteCreatore uc where t.id = t.id");

		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" t.denominazione  like :denominazione ");
			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
		}
		if (example.getDateCreated() != null) {
			whereClauses.add(" t.dateCreated >= :dataCreazione ");
			paramaterMap.put("dataCreazione", example.getDateCreated());
		}
		if (example.getEsperienzaMinima() != null) {
			whereClauses.add(" t.esperienzaMinima >= :esperienzaMin ");
			paramaterMap.put("esperienzaMin", example.getEsperienzaMinima());
		}
		if (example.getCreditoMinimo() != null) {
			whereClauses.add(" t.creditoMinimo >= :cifraMinima ");
			paramaterMap.put("cifraMinima", example.getCreditoMinimo());
		}
		if (example.getUtenteCreatore() != null ) {
			whereClauses.add(" uc.id = :idUtenteCreatore ");
			paramaterMap.put("idUtenteCreatore", example.getUtenteCreatore());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
