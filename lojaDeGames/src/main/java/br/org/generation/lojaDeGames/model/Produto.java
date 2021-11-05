package br.org.generation.lojaDeGames.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="O atributo nome é Obrigatório")
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotNull(message="O atributo descrição é obrigatório")
	@Size(max = 600)
	private String descricao;
	
	@NotNull(message="O preço é obrigatório")
	@Positive(message="O preço deve ser maior que zero")
	private BigDecimal preco; 
	
	@NotNull(message="A classificação indicativa é obrigatória")
	private int classificacao_indicativa;
	
	@NotNull
	private boolean usado;
	
	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getClassificacao_indicativa() {
		return classificacao_indicativa;
	}

	public void setClassificacao_indicativa(int classificacao_indicativa) {
		this.classificacao_indicativa = classificacao_indicativa;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

}	