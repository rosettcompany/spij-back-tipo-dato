package pe.gob.minjus.spij.back.tipo.dato.service;

import java.util.List;
import java.util.Optional;

import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;

public interface ISectorComboService {
	public List<SectorComboEntity> findAll();

	public List<SectorComboEntity> listaSectorPadre();

	public List<SectorComboEntity> listaSectorHijo();

	public Optional<SectorComboEntity> ConsultarPadrePorNombre(String nombre);

	public Optional<SectorComboEntity> ConsultarHijoPorNombre(String nombre);

	public Optional<SectorComboEntity> ConsultarPadrePorNombreYGrupo(String nombreAnterior, int grupo);

	public List<SectorComboEntity> listaSectorHijoPorPadre(String nombreAnterior, int grupo);

	public Optional<SectorComboEntity> ConsultarHijoPorNombrePadreYGrupo(String nombreAnterior, String padre_nombre,
			int grupo);

	public void Guardar(SectorComboEntity sectorComboEntity);
}
