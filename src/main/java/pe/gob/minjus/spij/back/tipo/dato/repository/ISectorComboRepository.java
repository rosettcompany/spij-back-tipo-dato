package pe.gob.minjus.spij.back.tipo.dato.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;

@Repository
public interface ISectorComboRepository extends CrudRepository<SectorComboEntity, Integer>{ 

	@Query(value = "SELECT * FROM spij_ext_sector_combo " + 
	        "WHERE spij_ext_sector_combo.es_padre = 2 " +
	        "ORDER BY spij_ext_sector_combo.nombre ASC", nativeQuery = true)
	List<SectorComboEntity> listaSectorPadre();

	@Query(value = "SELECT * FROM spij_ext_sector_combo " + 
	        "WHERE spij_ext_sector_combo.es_padre = 1 " +
	        "ORDER BY spij_ext_sector_combo.nombre ASC", nativeQuery = true)
	List<SectorComboEntity> listaSectorHijo();

	@Query(value = "SELECT * FROM spij_ext_sector_combo " + 
	        "WHERE spij_ext_sector_combo.nombre=?1", nativeQuery = true)
	Optional<SectorComboEntity>ConsultarPorNombre(String nombre);
	
	@Query(value = "SELECT * FROM spij_ext_sector_combo " + 
	        "WHERE spij_ext_sector_combo.nombre =?1 and spij_ext_sector_combo.grupo=?2", nativeQuery = true)
	Optional<SectorComboEntity>ConsultarPorNombreGrupo(String nombreAnterior, int grupo);
	
	@Query(value = "SELECT * FROM spij_ext_sector_combo " + 
	        "WHERE spij_ext_sector_combo.es_padre = 1 "
	        + "and spij_ext_sector_combo.padre_nombre =?1 "
	        + "and spij_ext_sector_combo.grupo=?2 " +
	        "ORDER BY spij_ext_sector_combo.nombre ASC", nativeQuery = true)
	List<SectorComboEntity> listaSectorHijoPorPadre(String nombreAnterior, int grupo);

}
