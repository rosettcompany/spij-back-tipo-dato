package pe.gob.minjus.spij.back.tipo.dato.service;

import java.util.List;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;

public interface IAgrupamientoNormaService {
	public List<AgrupamientoNormaEntity> findAll();
	public List<AgrupamientoNormaEntity> listaTipoNorma();
}
