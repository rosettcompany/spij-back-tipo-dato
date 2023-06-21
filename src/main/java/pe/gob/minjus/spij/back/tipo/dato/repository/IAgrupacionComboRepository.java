package pe.gob.minjus.spij.back.tipo.dato.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupacionComboEntity;

@Repository
public interface IAgrupacionComboRepository extends CrudRepository<AgrupacionComboEntity, Integer> {

}
