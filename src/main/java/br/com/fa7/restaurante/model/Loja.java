package br.com.fa7.restaurante.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fa7.restaurante.dao.BaseModel;

@Entity
@Table(name = "lojas")
public class Loja implements BaseModel {
	private static final long serialVersionUID = -6982852341809209178L;

	@Id
	@SequenceGenerator(name = "sequence_lojas", sequenceName = "loja_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_lojas")
	private Integer id;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(length = 100, nullable = false)
	private String nome;

	@Embedded
	private Endereco endereco;

	@Column(length = 15, nullable = true)
	private String telefone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
