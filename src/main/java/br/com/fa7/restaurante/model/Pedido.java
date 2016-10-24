package br.com.fa7.restaurante.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pedidos")
public class Pedido extends BaseModel{

	private static final long serialVersionUID = -9169227340342516696L;

	@Id
	@SequenceGenerator(name = "sequence_pedidos", sequenceName = "pedido_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_pedidos")
	private Long id;
	
	@NotNull
	@Column(name = "data_hora", nullable = true)
	private Date dataHora;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = false)
	private String descricao;	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_pedido", nullable = false)
	private StatusPedido statusPedido;
	
	@NotNull
	@Column(nullable = false)
	private Long mesa;
	
	@Transient
	private EspecificacaoItem[] itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	public Long getMesa() {
		return mesa;
	}

	public void setMesa(Long mesa) {
		this.mesa = mesa;
	}
	
	public EspecificacaoItem[] getItens() {
		return itens;
	}

	public void setItens(EspecificacaoItem[] itens) {
		this.itens = itens;
	}
}