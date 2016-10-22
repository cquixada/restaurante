package br.com.fa7.restaurante.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.fa7.restaurante.util.DateAdapter;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario extends BaseModel {
	private static final long serialVersionUID = 2822605021060167039L;

	@Id
	@SequenceGenerator(name = "sequence_usuarios", sequenceName = "usuario_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_usuarios")
	private Long id;

	@NotNull
	@Size(min = 2, max = 100)
	@Column(length = 100, nullable = false)
	private String nome;

	@Past
	@XmlJavaTypeAdapter(DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nasc", nullable = true)
	private Date dataNascimento;

	@NotNull
	@Size(min = 3, max = 100)
	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@Embedded
	private Endereco endereco;

	@Column(length = 15, nullable = true)
	private String telefone;

	@NotNull
	@Size(min = 6, max = 10)
	@Column(length = 10, nullable = false)
	private String senha;

	@NotNull
	@Basic(optional = false)
	private Boolean ativo;

	@Enumerated(EnumType.STRING)
	private PerfilUsuario perfilUsuario;

	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
}