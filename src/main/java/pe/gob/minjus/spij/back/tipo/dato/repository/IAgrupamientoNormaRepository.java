package pe.gob.minjus.spij.back.tipo.dato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;

@Repository
public interface IAgrupamientoNormaRepository extends CrudRepository<AgrupamientoNormaEntity, Integer>{ 
	// Aqui se pueden crear otros metodos
	@Query(value = "SELECT * FROM spij_ext_agrupamiento_norma " + 
	        "WHERE spij_ext_agrupamiento_norma.grupo = 1 " +
	        "ORDER BY spij_ext_agrupamiento_norma.nombre ASC", nativeQuery = true)
	List<AgrupamientoNormaEntity> listaTipoNorma();


}
