package pe.gob.minjus.spij.back.tipo.dato.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.repository.ISectorComboRepository;
import pe.gob.minjus.spij.back.tipo.dato.service.ISectorComboService;

@Service
public class SectorComboServiceImpl implements ISectorComboService {
	
	@Autowired
	private ISectorComboRepository iSectorComboRepository;
	
	
	@Override
	public List<SectorComboEntity> findAll() {
		return (List<SectorComboEntity>) iSectorComboRepository.findAll();
	}

	@Override
	public List<SectorComboEntity> listaSectorPadre() {
		return (List<SectorComboEntity>) iSectorComboRepository.listaSectorPadre();
	}
	
	@Override
	public List<SectorComboEntity> listaSectorHijo() {
		return (List<SectorComboEntity>) iSectorComboRepository.listaSectorHijo();
	}
	
	@Override
	public List<SectorComboEntity> listaSectorHijoPorPadre(String nombreAnterior, int grupo){
		return (List<SectorComboEntity>) iSectorComboRepository.listaSectorHijoPorPadre(nombreAnterior, grupo);
	}
//	
//	@Override
//	public Optional<AgrupamientoNormaEntity> ConsultarId(int id) {
//		return iAgrupamientoNormaRepository.findById(id);
//	}
//
//
	@Override
	public void Guardar(SectorComboEntity sectorComboEntity) {
		iSectorComboRepository.save(sectorComboEntity);
		
	}

	@Override
	public Optional<SectorComboEntity> ConsultarPorNombre(String nombre) {
		return iSectorComboRepository.ConsultarPorNombre(nombre);
	}

	@Override
	public Optional<SectorComboEntity> ConsultarPorNombreGrupo(String nombreAnterior, int grupo) {
		return iSectorComboRepository.ConsultarPorNombreGrupo(nombreAnterior, grupo);
	}
	
}
