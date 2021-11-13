package it.prova.pokeronline.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.pokeronline.model.Tavolo;

public class TavoloDTO {

	private Long id;

	private Date dateCreated;

	@NotBlank(message = "{denominazione.notblank}")
	private String denominazione;

	@NotNull(message = "{esperienzaMinima.notblank}")
	private Integer esperienzaMinima;

	@NotNull(message = "{creditoMinimo.notnull}")
	private Integer creditoMinimo;

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(Long id) {
		super();
		this.id = id;
	}

	public TavoloDTO(Long id, Date dateCreated, @NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{esperienzaMinima.notblank}") Integer esperienzaMinima,
			@NotNull(message = "{creditoMinimo.notnull}") Integer creditoMinimo) {
		super();
		this.id = id;
		this.dateCreated = dateCreated;
		this.denominazione = denominazione;
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

	public Integer getCreditoMinimo() {
		return creditoMinimo;
	}

	public void setCreditoMinimo(Integer creditoMinimo) {
		this.creditoMinimo = creditoMinimo;
	}

	public Tavolo buildTavoloModel() {
		return new Tavolo(this.id, this.denominazione, this.esperienzaMinima, this.creditoMinimo);
	}

	public static TavoloDTO buildTavoloDTOFromModel(Tavolo tavoloInstance) {
		return new TavoloDTO(tavoloInstance.getId(), tavoloInstance.getDateCreated(), tavoloInstance.getDenominazione(),
				tavoloInstance.getEsperienzaMinima(), tavoloInstance.getCreditoMinimo());
	}

	public static List<TavoloDTO> createRegistaDTOListFromModelList(List<Tavolo> modelListInput) {
		return modelListInput.stream().map(tavoloEntity -> {
			return TavoloDTO.buildTavoloDTOFromModel(tavoloEntity);
		}).collect(Collectors.toList());
	}
}
