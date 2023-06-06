package pe.gob.minjus.spij.back.tipo.dato.service;

import java.util.List;
import java.util.Optional;


import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;

public interface ISectorComboService {
	public List<SectorComboEntity> findAll();
//	public List<AgrupamientoNormaEntity> listaTipoNorma();
//	public List<AgrupamientoNormaEntity> listaJurisprudencia();
//	public Optional<AgrupamientoNormaEntity> ConsultarPorNombre(String nombreAnterior, int grupo);
//	public Optional<AgrupamientoNormaEntity> ConsultarId(int id);
//	public void Guardar(AgrupamientoNormaEntity agrupamientoNormaEntity);
}
