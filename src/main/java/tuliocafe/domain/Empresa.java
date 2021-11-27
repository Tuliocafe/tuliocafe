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
	private List<Cargo> cargos;
	
	
	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
