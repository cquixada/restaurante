package br.com.fa7.restaurante.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "especificao_item")
public class EspecificacaoItem extends BaseModel {

	private static final long serialVersionUID = -1847808194505694235L;

	@Id
	@SequenceGenerator(name = "sequence_espec_item", sequenceName = "espec_item_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_espec_item")
	private Integer id;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(length = 100, nullable = false)
	private String nome;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(length = 100, nullable = true)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_item", nullable = false)
	private CategoriaItem categoriaItem;

	@NotNull
	@Column(nullable = false)
	private BigDecimal preco;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaItem getCategoriaItem() {
		return categoriaItem;
	}

	public void setCategoriaItem(CategoriaItem categoriaItem) {
		this.categoriaItem = categoriaItem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
