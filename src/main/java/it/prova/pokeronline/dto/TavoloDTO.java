package it.prova.pokeronline.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

public class TavoloDTO {

	private Long id;

	private Date dateCreated;

	@NotBlank(message = "{denominazione.notblank}")
	private String denominazione;

	@NotNull(message = "{esperienzaMinima.notblank}")
	@Min(0)
	private Integer esperienzaMinima;

	@NotNull(message = "{creditoMinimo.notnull}")
	@Min(0)
	private Integer creditoMinimo;
	
	@NotNull(message = "{utentecreatore.notnull}")
	private Utente utenteCreatore;

	private Set<Utente> utentiGiocatori = new HashSet<Utente>();

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(Long id, String denominazione, Date dataCreazione, Integer esperienzaMin, Integer cifraMinima,
			Utente utenteCreatore, Set<Utente> giocatori) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dateCreated = dataCreazione;
		this.esperienzaMinima = esperienzaMin;
		this.creditoMinimo = cifraMinima;
		this.utenteCreatore = utenteCreatore;
		this.utentiGiocatori = giocatori;
	}

	public TavoloDTO(Long id, String denominazione, Date dataCreazione, Integer esperienzaMin, Integer cifraMinima) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dateCreated = dataCreazione;
		this.esperienzaMinima = esperienzaMin;
		this.creditoMinimo = cifraMinima;
	}

	public TavoloDTO(String denominazione, Date dataCreazione, Integer esperienzaMin, Integer cifraMinima) {
		super();
		this.denominazione = denominazione;
		this.dateCreated = dataCreazione;
		this.esperienzaMinima = esperienzaMin;
		this.creditoMinimo = cifraMinima;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataCreazione() {
		return dateCreated;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dateCreated = dataCreazione;
	}

	public Integer getEsperienzaMin() {
		return esperienzaMinima;
	}

	public void setEsperienzaMin(Integer esperienzaMin) {
		this.esperienzaMinima = esperienzaMin;
	}

	public Integer getCifraMinima() {
		return creditoMinimo;
	}

	public void setCifraMinima(Integer cifraMinima) {
		this.creditoMinimo = cifraMinima;
	}

	public Utente getUtenteCreatore() {
		return utenteCreatore;
	}

	public void setUtenteCreatore(Utente utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}

	public Set<Utente> getGiocatori() {
		return utentiGiocatori;
	}

	public void setGiocatori(Set<Utente> giocatori) {
		this.utentiGiocatori = giocatori;
	}

	public static TavoloDTO buildTavoloDTOFromModel(Tavolo tavolo) {
		return new TavoloDTO(tavolo.getId(), tavolo.getDenominazione(), tavolo.getDateCreated(),
				tavolo.getEsperienzaMinima(), tavolo.getCreditoMinimo(), tavolo.getUtenteCreatore(), tavolo.getUtenti());
	}
	
	public static List<TavoloDTO> createTavoloDTOListFromModelList(List<Tavolo> modelListInput) {
		return modelListInput.stream().map(registaEntity -> {
			return TavoloDTO.buildTavoloDTOFromModel(registaEntity);
		}).collect(Collectors.toList());
	}
}
