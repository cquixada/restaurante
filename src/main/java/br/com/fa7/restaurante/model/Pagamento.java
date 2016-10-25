package br.com.fa7.restaurante.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pagamentos")
public class Pagamento extends BaseModel {

	private static final long serialVersionUID = 4346970876913359019L;

	@Id
	@SequenceGenerator(name = "sequence_pagamentos", sequenceName = "pagamento_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_pagamentos")
	private Long id;

	@NotNull
	@Basic(optional = false)
	private Double valor;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
