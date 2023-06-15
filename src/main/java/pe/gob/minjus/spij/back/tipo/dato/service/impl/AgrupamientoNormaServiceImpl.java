package pe.gob.minjus.spij.back.tipo.dato.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.NormaActualizar;
import pe.gob.minjus.spij.back.tipo.dato.repository.IAgrupamientoNormaRepository;
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupamientoNormaService;

@Service
public class AgrupamientoNormaServiceImpl implements IAgrupamientoNormaService {
	
	private static final Logger LOG = LoggerFactory.getLogger(AgrupamientoNormaServiceImpl.class);
	
	@Autowired
	private IAgrupamientoNormaRepository iAgrupamientoNormaRepository;
	
	
	@Override
	public List<AgrupamientoNormaEntity> findAll() {
		return (List<AgrupamientoNormaEntity>) iAgrupamientoNormaRepository.findAll();
	}


	@Override
	public List<AgrupamientoNormaEntity> listaTipoNorma() {
		return (List<AgrupamientoNormaEntity>) iAgrupamientoNormaRepository.listaTipoNorma();
	}
	
	@Override
	public List<AgrupamientoNormaEntity> listaJurisprudencia() {
		return (List<AgrupamientoNormaEntity>) iAgrupamientoNormaRepository.listaJurisprudencia();
	}
	
	@Override
	public Optional<AgrupamientoNormaEntity> ConsultarId(int id) {
		return iAgrupamientoNormaRepository.findById(id);
	}


	@Override
	public void Guardar(AgrupamientoNormaEntity agrupamientoNormaEntity) {
	    try {
	        List<AgrupamientoNormaEntity> data = (List<AgrupamientoNormaEntity>) iAgrupamientoNormaRepository.findAll();
	        LOG.info("data.size: " + data.size());
	        int lastIndex = data.size() - 1;
	        AgrupamientoNormaEntity lastEntity = data.get(lastIndex);
	        int agrupamiento_id = lastEntity.getAgrupamiento_id() + 1;
	        LOG.info("lastEntity: " + agrupamiento_id);

	        AgrupamientoNormaEntity norma = new AgrupamientoNormaEntity();
	        norma.setAgrupamiento_id(agrupamiento_id);
	        norma.setNombre(agrupamientoNormaEntity.nombre.toUpperCase());

	        int grupo = agrupamientoNormaEntity.grupo;
	        if (grupo >= 1 && grupo <= 8) {
	            if (grupo == 5) {
	                grupo = 6;
	            }
	            norma.setGrupo(grupo);
	        } else {
	            String errorMessage = "El grupo ingresado no está asociado a una agrupación válida.";
//	            LOG.error(errorMessage);
	            throw new IllegalArgumentException(errorMessage); // Lanza una excepción en caso de error
	        }

	        LOG.info("norma: " + norma);
	        LOG.info("norma id: " + norma.agrupamiento_id);
	        LOG.info("norma nombre: " + norma.nombre);
	        LOG.info("norma grupo: " + norma.grupo);

	        iAgrupamientoNormaRepository.save(norma);
	    } catch (Exception e) {
	        LOG.error("Error al guardar la norma: " + e.getMessage());
	        throw e; // Lanza la excepción al controlador para que pueda manejarla
	    }
	}



	@Override
	public Optional<AgrupamientoNormaEntity> ConsultarPorNombre(String nombreAnterior, int grupo) {
		return iAgrupamientoNormaRepository.ConsultarPorNombre(nombreAnterior, grupo);
	}


	@Override
	public void Actualizar(NormaActualizar norma) {
		try {
			String nombreAnterior = norma.nombreAnterior.toUpperCase();
			String nombreNuevo = norma.nombreNuevo.toUpperCase();
			Optional<AgrupamientoNormaEntity> data = iAgrupamientoNormaRepository.ConsultarPorNombre(nombreAnterior,
					norma.grupo);
			if (data.isPresent()) {
				AgrupamientoNormaEntity entidad = data.get();
				int agrupamiento_id = entidad.getAgrupamiento_id();
				entidad.setAgrupamiento_id(agrupamiento_id);
				entidad.setNombre(nombreNuevo);
				int grupoNorma = entidad.getGrupo();
				entidad.setGrupo(grupoNorma);

				LOG.info("ID: " + entidad.getAgrupamiento_id());
				LOG.info("Nombre: " + entidad.getNombre());
				LOG.info("Grupo: " + entidad.getGrupo());
				
				iAgrupamientoNormaRepository.save(entidad);
				
			} else {
	            String errorMessage = "No se encontró la norma con el nombre y/o grupo especificado.";
//	            LOG.error(errorMessage);
	            throw new IllegalArgumentException(errorMessage); // Lanza una excepción en caso de error
	        }

	        
	    } catch (Exception e) {
	        LOG.error("Error al actualizar la norma: " + e.getMessage());
	        throw e; // Lanza la excepción al controlador para que pueda manejarla
	    }
		
	}


	
}
