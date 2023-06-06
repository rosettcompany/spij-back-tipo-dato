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
@Table(name="spij_ext_sector_combo")

public class SectorComboEntity {

	@Id
	@Column(name = "sector_combo_id")
	public int sector_combo_id;

	@Column(name = "nombre")
	public String nombre;
	
	@Column(name = "es_padre")
	public int es_padre;
	
	@Column(name = "padre_nombre")
	public String padre_nombre;

	@Column(name = "grupo")
	public int grupo;

	public SectorComboEntity() {
		super();
	}

	public SectorComboEntity(int sector_combo_id, String nombre, int es_padre, String padre_nombre, int grupo) {
		super();
		this.sector_combo_id = sector_combo_id;
		this.nombre = nombre;
		this.es_padre = es_padre;
		this.padre_nombre = padre_nombre;
		this.grupo = grupo;
	}

	public SectorComboEntity(String nombre, int es_padre, String padre_nombre, int grupo) {
		super();
		this.nombre = nombre;
		this.es_padre = es_padre;
		this.padre_nombre = padre_nombre;
		this.grupo = grupo;
	}

	public int getSector_combo_id() {
		return sector_combo_id;
	}

	public void setSector_combo_id(int sector_combo_id) {
		this.sector_combo_id = sector_combo_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEs_padre() {
		return es_padre;
	}

	public void setEs_padre(int es_padre) {
		this.es_padre = es_padre;
	}

	public String getPadre_nombre() {
		return padre_nombre;
	}

	public void setPadre_nombre(String padre_nombre) {
		this.padre_nombre = padre_nombre;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

}
