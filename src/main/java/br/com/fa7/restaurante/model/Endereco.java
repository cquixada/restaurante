package br.com.fa7.restaurante.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {
	@NotNull
	@Column(length = 100, nullable = false)
	private String logradouro;

	@NotNull
	@Column(length = 5, nullable = false)
	private String numero;

	@Column(length = 80, nullable = true)
	private String complemento;

	@NotNull
	@Column(length = 60, nullable = false)
	private String bairro;

	@NotNull
	@Basic(optional = false)
	private Integer cep;

	@NotNull
	@Column(length = 60, nullable = true)
	private String municipio;

	@NotNull
	@Basic(optional = true)
	private Integer uf;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Integer getUf() {
		return uf;
	}

	public void setUf(Integer uf) {
		this.uf = uf;
	}
}
