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

public class CustomTavoloRepositoryImpl implements CustomTavoloRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select t from Tavolo t where t.id = t.id ");

		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" t.denominazione  like :nome ");
			paramaterMap.put("nome", "%" + example.getDenominazione() + "%");
		}
		if (example.getCreditoMinimo() != null) {
			whereClauses.add(" t.creditoMinimo >= :credito");
			paramaterMap.put("credito", example.getCreditoMinimo() );
		}
		if (example.getEsperienzaMinima() != null) {
			whereClauses.add(" t.esperienzaMinima >= :exp");
			paramaterMap.put("exp", example.getEsperienzaMinima() );
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
}