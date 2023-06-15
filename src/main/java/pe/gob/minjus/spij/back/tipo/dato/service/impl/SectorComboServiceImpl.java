package pe.gob.minjus.spij.back.tipo.dato.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.repository.ISectorComboRepository;
import pe.gob.minjus.spij.back.tipo.dato.service.ISectorComboService;

@Service
public class SectorComboServiceImpl implements ISectorComboService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SectorComboServiceImpl.class);

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
	public List<SectorComboEntity> listaSectorHijoPorPadre(String nombreAnterior, int grupo) {
		return (List<SectorComboEntity>) iSectorComboRepository.listaSectorHijoPorPadre(nombreAnterior, grupo);
	}

	@Override
	public void Guardar(SectorComboEntity sectorComboEntity) {
		try {
			List<SectorComboEntity> data = (List<SectorComboEntity>) iSectorComboRepository.findAll();
//			LOG.info("data.size: " + data.size());
			int lastIndex = data.size() - 1;
			SectorComboEntity lastEntity = data.get(lastIndex);
			int sector_combo_id = lastEntity.getSector_combo_id() + 1;
//			LOG.info("lastEntity: " + sector_combo_id);

			SectorComboEntity padre = new SectorComboEntity();
			padre.setSector_combo_id(sector_combo_id);
			padre.setNombre(sectorComboEntity.nombre.toUpperCase());
			padre.setEs_padre(2);
			padre.setPadre_nombre("");

			int grupo = sectorComboEntity.grupo;
			
	        if (grupo >= 1 && grupo <= 8) {
	            if (grupo == 5) {
	                grupo = 6;
	            }
	            padre.setGrupo(grupo);
	        } else {
	            String errorMessage = "El grupo ingresado no está asociado a una agrupación válida.";
//	            LOG.error(errorMessage);
	            throw new IllegalArgumentException(errorMessage); // Lanza una excepción en caso de error
	        }
	        
//	        LOG.info("padre: " + padre);
			LOG.info("padre sector_combo_id: " + padre.sector_combo_id);
//			LOG.info("padre nombre: " + padre.nombre);
//			LOG.info("padre es_padre: " + padre.es_padre);
//			LOG.info("padre padre_nombre: " + padre.padre_nombre);
//			LOG.info("padre grupo: " + padre.grupo);
			
			iSectorComboRepository.save(padre);
		}catch (Exception e) {
	        LOG.error("Error al guardar el sector padre: " + e.getMessage());
	        throw e; // Lanza la excepción al controlador para que pueda manejarla
	    }
		

	}

	@Override
	public Optional<SectorComboEntity> ConsultarPadrePorNombre(String nombre) {
		return iSectorComboRepository.ConsultarPadrePorNombre(nombre);
	}

	@Override
	public Optional<SectorComboEntity> ConsultarHijoPorNombre(String nombre) {
		return iSectorComboRepository.ConsultarHijoPorNombre(nombre);
	}

	@Override
	public Optional<SectorComboEntity> ConsultarPadrePorNombreYGrupo(String nombreAnterior, int grupo) {
		return iSectorComboRepository.ConsultarPadrePorNombreYGrupo(nombreAnterior, grupo);
	}

	@Override
	public Optional<SectorComboEntity> ConsultarHijoPorNombrePadreYGrupo(String nombreAnterior, String padre_nombre,
			int grupo) {
		return iSectorComboRepository.ConsultarHijoPorNombrePadreYGrupo(nombreAnterior, padre_nombre, grupo);
	}

}
