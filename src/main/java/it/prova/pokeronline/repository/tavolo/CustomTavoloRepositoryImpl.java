package it.prova.pokeronline.repository.tavolo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

public class CustomTavoloRepositoryImpl implements CustomTavoloRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Tavolo> findMieiTavoliByExample(Tavolo example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select distinct t from Tavolo t join fetch t.utenteCreatore uc join fetch where t.id = t.id");

		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" t.denominazione  like :denominazione ");
			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
		}
		if (example.getCreditoMinimo() > 0) {
			whereClauses.add(" t.creditoMinimo >= :credito");
			paramaterMap.put("credito", example.getCreditoMinimo());
		}
		if (example.getDateCreated() != null) {
			whereClauses.add(" t.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		if (example.getEsperienzaMinima() > 0) {
			whereClauses.add(" t.esperienzaMinima >= :exp");
			paramaterMap.put("exp", example.getEsperienzaMinima());
		}
		whereClauses.add(" uc.id = :idCreatore ");
		paramaterMap.put("idCreatore", example.getUtenteCreatore().getId());

//		if(example.getUtenti() != null && example.getUtenti().size() > 0) {
//			int i = 0;
//			for (Utente giocatoreTmp : example.getUtenti()) {
//				if(i == 0)
//					giocatore += " g.id = " + giocatoreTmp.getId();
//				else
//					giocatore += " g.id = " + giocatoreTmp.getId();
//				
//				i++;
//			}
//		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
//		if(example.getUtenti() != null)
//			queryBuilder.append(" and " + giocatore);
		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
}
