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
@Table(name="spij_ext_agrupacion_combo")

public class AgrupacionComboEntity {

	@Id
	@Column(name = "agrupacion_combo_id")
	public int agrupacion_combo_id;

	@Column(name = "nombre")
	public String nombre;
	
	@Column(name = "grupo")
	public int grupo;

	public int getAgrupacion_combo_id() {
		return agrupacion_combo_id;
	}

	public void setAgrupacion_combo_id(int agrupacion_combo_id) {
		this.agrupacion_combo_id = agrupacion_combo_id;
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
