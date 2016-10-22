package br.com.fa7.restaurante.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = -3396602965804125292L;

	@Column(name = "data_criacao", nullable = true)
	private Date dataCriacao;

	@Column(name = "data_atualizacao", nullable = true)
	private Date dataAtualizacao;

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}