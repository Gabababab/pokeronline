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

	@NotBlank(message = "{denominazione.notblank}")
	private String denominazione;

	@NotNull(message = "{esperienzaMinima.notblank}")
	@Min(0)
	private Integer esperienzaMinima;

	@NotNull(message = "{creditoMinimo.notnull}")
	@Min(0)
	private Integer creditoMinimo;

	private UtenteDTO utenteCreatore;

	private Date dateCreated;

	private UtenteDTO giocatoreCercato;

	private Set<Utente> utentiGiocatori = new HashSet<Utente>();

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(Long id, String denominazione, Date dateCreated, Integer esperienzaMinima, Integer creditoMinimo,
			UtenteDTO utenteCreatore, Set<Utente> giocatori) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dateCreated = dateCreated;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.utenteCreatore = utenteCreatore;
		this.utentiGiocatori = giocatori;
	}

	public TavoloDTO(Long id, String denominazione, Date dateCreated, Integer esperienzaMinima,
			Integer creditoMinimo) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dateCreated = dateCreated;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
	}

	public TavoloDTO(Long id, @NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{esperienzaMinima.notblank}") @Min(0) Integer esperienzaMinima,
			@NotNull(message = "{creditoMinimo.notnull}") @Min(0) Integer creditoMinimo,
			@NotNull(message = "{utentecreatore.notnull}") UtenteDTO utenteCreatore, Date dateCreated,
			Set<Utente> utentiGiocatori) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.utenteCreatore = utenteCreatore;
		this.dateCreated = dateCreated;
		this.giocatoreCercato = giocatoreCercato;
		this.utentiGiocatori = utentiGiocatori;
	}

	public TavoloDTO(String denominazione, Date dateCreated, Integer esperienzaMinima, Integer creditoMinimo) {
		super();
		this.denominazione = denominazione;
		this.dateCreated = dateCreated;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
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

	public UtenteDTO getGiocatoreCercato() {
		return giocatoreCercato;
	}

	public void setGiocatoreCercato(UtenteDTO giocatoreCercato) {
		this.giocatoreCercato = giocatoreCercato;
	}

	public Integer getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Integer esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Set<Utente> getUtentiGiocatori() {
		return utentiGiocatori;
	}

	public void setUtentiGiocatori(Set<Utente> utentiGiocatori) {
		this.utentiGiocatori = utentiGiocatori;
	}

	public Integer getCreditoMinimo() {
		return creditoMinimo;
	}

	public void setCreditoMinimo(Integer cifraMinima) {
		this.creditoMinimo = cifraMinima;
	}

	public UtenteDTO getUtenteCreatore() {
		return utenteCreatore;
	}

	public void setUtenteCreatore(UtenteDTO utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}

	public static TavoloDTO buildTavoloDTOFromModel(Tavolo tavolo) {
		return new TavoloDTO(tavolo.getId(), tavolo.getDenominazione(), tavolo.getDateCreated(),
				tavolo.getEsperienzaMinima(), tavolo.getCreditoMinimo(),
				UtenteDTO.buildUtenteDTOFromModel(tavolo.getUtenteCreatore()), tavolo.getUtenti());
	}

	public static List<TavoloDTO> createTavoloDTOListFromModelList(List<Tavolo> modelListInput) {
		return modelListInput.stream().map(registaEntity -> {
			return TavoloDTO.buildTavoloDTOFromModel(registaEntity);
		}).collect(Collectors.toList());
	}

	public Tavolo buildTavoloModel() {
		return new Tavolo(this.id, this.denominazione, this.dateCreated, this.esperienzaMinima, this.creditoMinimo,
				this.utenteCreatore.buildUtenteModel(false));
	}
}
