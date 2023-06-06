package pe.gob.minjus.spij.back.tipo.dato.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
//	
//	@Query(value = "SELECT * FROM spij_ext_agrupamiento_norma " + 
//	        "WHERE spij_ext_agrupamiento_norma.nombre LIKE %?1% and spij_ext_agrupamiento_norma.grupo LIKE %?2%", nativeQuery = true)
//	Optional<AgrupamientoNormaEntity>ConsultarPorNombre(String nombreAnterior, int grupo);

}
