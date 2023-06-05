package pe.gob.minjus.spij.back.tipo.dato.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="spij_ext_agrupamiento_norma")

public class AgrupamientoNormaEntity {

	@Id
	@Column(name = "agrupamiento_id")
	public int agrupamiento_id;

	@Column(name = "nombre")
	public String nombre;

	@Column(name = "grupo")
	public int grupo;

	public AgrupamientoNormaEntity() {
		super();
	}

	public AgrupamientoNormaEntity(int agrupamiento_id, String nombre, int grupo) {
		super();
		this.agrupamiento_id = agrupamiento_id;
		this.nombre = nombre;
		this.grupo = grupo;
	}

	public AgrupamientoNormaEntity(String nombre, int grupo) {
		super();
		this.nombre = nombre;
		this.grupo = grupo;
	}

	public int getAgrupamiento_id() {
		return agrupamiento_id;
	}

	public void setAgrupamiento_id(int agrupamiento_id) {
		this.agrupamiento_id = agrupamiento_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	
}
