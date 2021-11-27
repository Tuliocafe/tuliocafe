package tuliocafe.domain;


import java.util.List;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "EMPRESAS")
public class Empresa extends AbstractEntity<Long> {
	

	@Column(name ="nome", nullable = false, unique = true, length = 60)
	private String nome;

	
	@OneToMany(mappedBy = "empresa")
	private List<Profissao> profissoes;
	
	
	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
