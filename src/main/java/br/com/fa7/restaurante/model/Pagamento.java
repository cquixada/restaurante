package br.com.fa7.restaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pagamentos")
public class Pagamento extends BaseModel{

	private static final long serialVersionUID = 4346970876913359019L;
	
	@Id
	@SequenceGenerator(name = "sequence_pagamentos", sequenceName = "pagamento_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_pagamentos")
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
