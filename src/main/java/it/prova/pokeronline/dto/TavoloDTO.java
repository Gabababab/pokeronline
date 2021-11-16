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

	private Long utenteCreatore;

	private Date dateCreated;

	private Long giocatoreCercato;

	private Set<Utente> utentiGiocatori = new HashSet<Utente>();

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(Long id, String denominazione, Date dateCreated, Integer esperienzaMinima, Integer creditoMinimo,
			Long utenteCreatore, Set<Utente> utentiGiocatori) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dateCreated = dateCreated;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.utenteCreatore = utenteCreatore;
		this.utentiGiocatori = utentiGiocatori;
	}

	public TavoloDTO(@NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{esperienzaMinima.notblank}") @Min(0) Integer esperienzaMinima,
			@NotNull(message = "{creditoMinimo.notnull}") @Min(0) Integer creditoMinimo, Date dateCreated) {
		super();
		this.denominazione = denominazione;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.dateCreated = dateCreated;
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

	public Integer getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Integer esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Integer getCreditoMinimo() {
		return creditoMinimo;
	}

	public void setCreditoMinimo(Integer creditoMinimo) {
		this.creditoMinimo = creditoMinimo;
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

	public Long getUtenteCreatore() {
		return utenteCreatore;
	}

	public void setUtenteCreatore(Long utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}

	public Set<Utente> getGiocatori() {
		return utentiGiocatori;
	}

	public void setGiocatori(Set<Utente> giocatori) {
		this.utentiGiocatori = giocatori;
	}

	public Long getGiocatoreCercato() {
		return giocatoreCercato;
	}

	public void setGiocatoreCercato(Long giocatoreCercato) {
		this.giocatoreCercato = giocatoreCercato;
	}

	public static TavoloDTO buildTavoloDTOFromModel(Tavolo tavolo) {
		return new TavoloDTO(tavolo.getId(), tavolo.getDenominazione(), tavolo.getDateCreated(),
				tavolo.getEsperienzaMinima(), tavolo.getCreditoMinimo(), tavolo.getUtenteCreatore().getId(),
				tavolo.getUtenti());
	}

	public static List<TavoloDTO> createTavoloDTOListFromModelList(List<Tavolo> modelListInput) {
		return modelListInput.stream().map(registaEntity -> {
			return TavoloDTO.buildTavoloDTOFromModel(registaEntity);
		}).collect(Collectors.toList());
	}

	public Tavolo buildTavoloModel(Utente creatore) {
		return new Tavolo(this.id, this.denominazione, this.dateCreated, this.esperienzaMinima, this.creditoMinimo,
				this.utentiGiocatori, creatore);
	}

	@Override
	public String toString() {
		return "TavoloDTO [id=" + id + ", dateCreated=" + dateCreated + ", denominazione=" + denominazione
				+ ", esperienzaMinima=" + esperienzaMinima + ", cifraMinima=" + creditoMinimo + ", utenteCreatore="
				+ utenteCreatore + ", utentiGiocatori=" + utentiGiocatori + "]";
	}
}
