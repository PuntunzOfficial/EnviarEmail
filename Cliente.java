package aed.hibernate.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "clientes")
public class Cliente {

	@Id
	@Column(length = 9)
	private String coddnionie;

	private String nombre;

	private String nacionalidad;

	@OneToMany(mappedBy = "coddnionie", fetch = FetchType.LAZY)
	private List<Estancia> estancias = new ArrayList<>();

	public Cliente() {
		this.coddnionie = null;
		this.nombre = null;
		this.nacionalidad = null;
	}
	public Cliente(String coddnionie, String nombre, String nacionalidad) {
		this.coddnionie = coddnionie;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public String getCoddnionie() {
		return coddnionie;
	}

	public void setCoddnionie(String coddnionie) {
		this.coddnionie = coddnionie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Estancia> getEstancias() {
		return estancias;
	}

	public void setEstancias(List<Estancia> estancias) {
		this.estancias = estancias;
	}
	
	@Override
	public String toString() {
		return "Cliente [coddnionie=" + coddnionie + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}
}
