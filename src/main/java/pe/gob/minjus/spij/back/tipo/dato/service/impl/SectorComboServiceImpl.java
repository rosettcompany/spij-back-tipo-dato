package pe.gob.minjus.spij.back.tipo.dato.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.NormaActualizar;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorHijoActualizar;
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
	public void GuardarPadre(SectorComboEntity sectorComboEntity) {
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
		} catch (Exception e) {
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

	@Override
	public void GuardarHijo(SectorComboEntity sectorComboEntity) {
		try {
			Optional<SectorComboEntity> padre = iSectorComboRepository
					.ConsultarPadrePorNombre(sectorComboEntity.padre_nombre.toUpperCase());
			if (padre.isPresent()) {
				SectorComboEntity padreEntidad = padre.get();
				List<SectorComboEntity> data = (List<SectorComboEntity>) iSectorComboRepository.findAll();
				int lastIndex = data.size() - 1;
				SectorComboEntity lastEntity = data.get(lastIndex);
				int sector_combo_id = lastEntity.getSector_combo_id() + 1;

				SectorComboEntity hijo = new SectorComboEntity();
				hijo.setSector_combo_id(sector_combo_id);
				hijo.setNombre(sectorComboEntity.nombre.toUpperCase());
				hijo.setEs_padre(1);
				hijo.setPadre_nombre(sectorComboEntity.padre_nombre.toUpperCase());
				hijo.setGrupo(padreEntidad.getGrupo());

				iSectorComboRepository.save(hijo);
			} else {
				String errorMessage = "No se encontró un sector padre con el nombre especificado.";
//	            LOG.error(errorMessage);
				throw new IllegalArgumentException(errorMessage); // Lanza una excepción en caso de error
			}
		} catch (Exception e) {
			LOG.error("Error al guardar el sector hijo: " + e.getMessage());
			throw e; // Lanza la excepción al controlador para que pueda manejarla
		}
	}

	@Override
	public void ActualizarPadre(NormaActualizar sectorComboEntity) {
		try {
			String nombreAnterior = sectorComboEntity.nombreAnterior.toUpperCase();
			String nombreNuevo = sectorComboEntity.nombreNuevo.toUpperCase();

			Optional<SectorComboEntity> padreB = iSectorComboRepository.ConsultarPadrePorNombreYGrupo(nombreAnterior,
					sectorComboEntity.grupo);
			if (padreB.isPresent()) {
				SectorComboEntity padre = padreB.get();
				int agrupamiento_id = padre.getSector_combo_id();
				padre.setSector_combo_id(agrupamiento_id);
				padre.setNombre(nombreNuevo);
				int grupoNorma = padre.getGrupo();
				padre.setGrupo(grupoNorma);
				
				// Actualizar nombres de los sectores hijos
				List<SectorComboEntity> sectoresHijos = iSectorComboRepository.listaSectorHijoPorPadre(nombreAnterior,
						sectorComboEntity.grupo);

				for (SectorComboEntity sectorHijo : sectoresHijos) {
					sectorHijo.setPadre_nombre(nombreNuevo);
					iSectorComboRepository.save(sectorHijo);
				}

				iSectorComboRepository.save(padre);
				
			} else {
	            String errorMessage = "No se encontró el sector padre con el nombre y/o grupo especificado.";
//	            LOG.error(errorMessage);
	            throw new IllegalArgumentException(errorMessage); // Lanza una excepción en caso de error
	        }

	        
	    } catch (Exception e) {
	        LOG.error("Error al actualizar el sector: " + e.getMessage());
	        throw e; // Lanza la excepción al controlador para que pueda manejarla
	    }
	}

	@Override
	public void ActualizarHijo(SectorHijoActualizar hijo) {
		try {
			String nombreAnterior = hijo.nombreAnterior.toUpperCase();
			String nombreNuevo = hijo.nombreNuevo.toUpperCase();
			String padre_nombre = hijo.padre_nombre.toUpperCase();
			Optional<SectorComboEntity> data = iSectorComboRepository.ConsultarHijoPorNombrePadreYGrupo(nombreAnterior,
					padre_nombre, hijo.grupo);
			if (data.isPresent()) {
				SectorComboEntity entidad = data.get();
				entidad.setNombre(nombreNuevo);

				LOG.info("ID: " + entidad.getSector_combo_id());
				LOG.info("Nombre: " + entidad.getNombre());
				LOG.info("Es padre: " + entidad.getEs_padre());
				LOG.info("padre: " + entidad.getPadre_nombre());
				LOG.info("Grupo: " + entidad.getGrupo());

				iSectorComboRepository.save(entidad);
				
			} else {
	            String errorMessage = "No se encontró el sector hijo con los parámetros especificados.";
//	            LOG.error(errorMessage);
	            throw new IllegalArgumentException(errorMessage); // Lanza una excepción en caso de error
	        }

	        
	    } catch (Exception e) {
	        LOG.error("Error al actualizar el sector: " + e.getMessage());
	        throw e; // Lanza la excepción al controlador para que pueda manejarla
	    }
	}

}
