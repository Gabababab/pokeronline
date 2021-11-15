package it.prova.pokeronline.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tavolo")
public class Tavolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "denominazione")
	private String denominazione;
	@Column(name = "dateCreated")
	private Date dateCreated;
	@Column(name = "esperienzaMinima")
	private Integer esperienzaMinima;
	@Column(name = "creditoMinimo")
	private Integer creditoMinimo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo")
	private Set<Utente> utenti = new HashSet<Utente>(0);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utenteCreatore_id", nullable = false)
	private Utente utenteCreatore;

	public Tavolo() {
		super();
	}

	public Tavolo(String denominazione, Date dateCreated, Integer esperienzaMinima, Integer creditoMinimo) {
		super();
		this.denominazione = denominazione;
		this.dateCreated = dateCreated;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
	}

	public Tavolo(Long id, String denominazione, Integer esperienzaMinima, Integer creditoMinimo) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
	}
	

	public Tavolo(Long id, String denominazione, Date dateCreated, Integer esperienzaMinima, Integer creditoMinimo,
			Utente utenteCreatore) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dateCreated = dateCreated;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.utenteCreatore = utenteCreatore;
	}


	public Tavolo(Long id, String denominazione, Date dateCreated, Integer esperienzaMinima, Integer creditoMinimo,
			Set<Utente> utenti, Utente utenteCreatore) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dateCreated = dateCreated;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.utenti = utenti;
		this.utenteCreatore = utenteCreatore;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	public Utente getUtenteCreatore() {
		return utenteCreatore;
	}

	public void setUtenteCreatore(Utente utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}

}
