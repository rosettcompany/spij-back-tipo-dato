package pe.gob.minjus.spij.back.tipo.dato.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupacionComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.NormaActualizar;
import pe.gob.minjus.spij.back.tipo.dato.entity.NormaRequest;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorHijoActualizar;
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupacionComboService;
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupamientoNormaService;
import pe.gob.minjus.spij.back.tipo.dato.service.ISectorComboService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SpijBackTipoDatoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpijBackTipoDatoController.class);
	
	@Autowired
	private IAgrupacionComboService agrupacionComboService;

	@Autowired
	private IAgrupamientoNormaService agrupamientoNormaService;

	@Autowired
	private ISectorComboService sectorComboService;
	
	@RequestMapping(value = "/lista-grupos", method = RequestMethod.GET)
	public ResponseEntity<List<AgrupacionComboEntity>> getGrupos() throws Exception {
		List<AgrupacionComboEntity> data = agrupacionComboService.findAll();
		return ResponseEntity.ok(data);
	}

	@RequestMapping(value = "/lista-normar-y-jurisprudencia", method = RequestMethod.GET)
	public ResponseEntity<List<AgrupamientoNormaEntity>> getTodo() throws Exception {
		List<AgrupamientoNormaEntity> data = agrupamientoNormaService.findAll();
		return ResponseEntity.ok(data);
	}

	@RequestMapping(value = "/lista-normas", method = RequestMethod.GET)
	public ResponseEntity<List<AgrupamientoNormaEntity>> getAgrupamientoNorma() throws Exception {
		List<AgrupamientoNormaEntity> data = agrupamientoNormaService.listaTipoNorma();
		return ResponseEntity.ok(data);
	}

	@RequestMapping(value = "/lista-jurisprudencias", method = RequestMethod.GET)
	public ResponseEntity<List<AgrupamientoNormaEntity>> getJurisprudencias() throws Exception {
		List<AgrupamientoNormaEntity> data = agrupamientoNormaService.listaJurisprudencia();
		return ResponseEntity.ok(data);
	}

	@RequestMapping(value = "/normas/insertar", method = RequestMethod.POST)
	public ResponseEntity<?> insertarNormas(@RequestBody AgrupamientoNormaEntity normaRequest) throws ParseException {

		try {
	        agrupamientoNormaService.Guardar(normaRequest);
	        return ResponseEntity.ok("Operación exitosa"); // Devuelve respuesta exitosa con mensaje
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
	    }
	}

	@RequestMapping(value = "/norma/actualizar", method = RequestMethod.POST)
	public ResponseEntity<?> actualizarNormas(@RequestBody NormaActualizar norma) throws ParseException {

		String nombreAnterior = norma.nombreAnterior.toUpperCase();
		String nombreNuevo = norma.nombreNuevo.toUpperCase();
		Optional<AgrupamientoNormaEntity> data = agrupamientoNormaService.ConsultarPorNombre(nombreAnterior,
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

			agrupamientoNormaService.Guardar(entidad);
		} else {
			return ResponseEntity.badRequest()
					.body("ERROR: No se encontró la norma con el nombre y/o grupo especificado.");
		}

		return new ResponseEntity<>("Actualización exitosa.", HttpStatus.OK);
	}

	@RequestMapping(value = "/listar-sector-padre-hijo", method = RequestMethod.GET)
	public ResponseEntity<List<SectorComboEntity>> getSectores() throws Exception {
		List<SectorComboEntity> data = sectorComboService.findAll();
		return ResponseEntity.ok(data);
	}

	@RequestMapping(value = "/listar-sector-padre", method = RequestMethod.GET)
	public ResponseEntity<List<SectorComboEntity>> getSectorPadre() throws Exception {
		List<SectorComboEntity> data = sectorComboService.listaSectorPadre();
		return ResponseEntity.ok(data);
	}

	@RequestMapping(value = "/listar-sector-hijo", method = RequestMethod.GET)
	public ResponseEntity<List<SectorComboEntity>> getSectorHijo() throws Exception {
		List<SectorComboEntity> data = sectorComboService.listaSectorHijo();
		return ResponseEntity.ok(data);
	}

	@RequestMapping(value = "/sector-padre/insertar", method = RequestMethod.POST)
	public ResponseEntity<?> insertarSectorPadre(@RequestBody SectorComboEntity sectorPadre) throws ParseException {

		List<SectorComboEntity> data = sectorComboService.findAll();
		LOG.info("data.size: " + data.size());
		int lastIndex = data.size() - 1;
		SectorComboEntity lastEntity = data.get(lastIndex);
		int sector_combo_id = lastEntity.getSector_combo_id() + 1;
		LOG.info("lastEntity: " + sector_combo_id);

		SectorComboEntity padre = new SectorComboEntity();
		padre.setSector_combo_id(sector_combo_id);
		padre.setNombre(sectorPadre.nombre.toUpperCase());
		padre.setEs_padre(2);
		padre.setPadre_nombre("");

		int grupo = sectorPadre.grupo;
		if (grupo >= 1 && grupo <= 8) {
			if (grupo == 5) {//Gobierno local
				grupo = 6;
			}
			padre.setGrupo(grupo);
		} else {
			return ResponseEntity.badRequest()
					.body("ERROR: El grupo ingresado no está asociado a una agrupación válida.");
		}

		LOG.info("padre: " + padre);
		LOG.info("padre sector_combo_id: " + padre.sector_combo_id);
		LOG.info("padre nombre: " + padre.nombre);
		LOG.info("padre es_padre: " + padre.es_padre);
		LOG.info("padre padre_nombre: " + padre.padre_nombre);
		LOG.info("padre grupo: " + padre.grupo);

		sectorComboService.Guardar(padre);
		return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
	}

	@RequestMapping(value = "/sector-hijo/insertar", method = RequestMethod.POST)
	public ResponseEntity<?> insertarSectorHijo(@RequestBody SectorComboEntity sectorHijo) throws ParseException {

		Optional<SectorComboEntity> padre = sectorComboService
				.ConsultarPadrePorNombre(sectorHijo.padre_nombre.toUpperCase());
		if (padre.isPresent()) {
			SectorComboEntity padreEntidad = padre.get();
			List<SectorComboEntity> data = sectorComboService.findAll();
			int lastIndex = data.size() - 1;
			SectorComboEntity lastEntity = data.get(lastIndex);
			int sector_combo_id = lastEntity.getSector_combo_id() + 1;

			SectorComboEntity hijo = new SectorComboEntity();
			hijo.setSector_combo_id(sector_combo_id);
			hijo.setNombre(sectorHijo.nombre.toUpperCase());
			hijo.setEs_padre(1);
			hijo.setPadre_nombre(sectorHijo.padre_nombre.toUpperCase());
			hijo.setGrupo(padreEntidad.getGrupo());

			sectorComboService.Guardar(hijo);

			return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);

		} else {
			return ResponseEntity.badRequest()
					.body("ERROR: No se encontró un sector padre con el nombre especificado.");
		}

	}

	@RequestMapping(value = "/sector-padre/actualizar", method = RequestMethod.POST)
	public ResponseEntity<?> actualizarSectorPadre(@RequestBody NormaActualizar norma) throws ParseException {

		String nombreAnterior = norma.nombreAnterior.toUpperCase();
		String nombreNuevo = norma.nombreNuevo.toUpperCase();

		Optional<SectorComboEntity> padreB = sectorComboService.ConsultarPadrePorNombreYGrupo(nombreAnterior, norma.grupo);
		
		if (padreB.isPresent()) {
			SectorComboEntity padre = padreB.get();
			int agrupamiento_id = padre.getSector_combo_id();
			padre.setSector_combo_id(agrupamiento_id);
			padre.setNombre(nombreNuevo);
			int grupoNorma = padre.getGrupo();
			padre.setGrupo(grupoNorma);
			
			// Actualizar nombres de los sectores hijos
			List<SectorComboEntity> sectoresHijos = sectorComboService.listaSectorHijoPorPadre(nombreAnterior, norma.grupo);
	        
	        for (SectorComboEntity sectorHijo : sectoresHijos) {
	            sectorHijo.setPadre_nombre(nombreNuevo);
	            sectorComboService.Guardar(sectorHijo);
	        }
	        
	        sectorComboService.Guardar(padre);
	        
		} else {
			return ResponseEntity.badRequest()
					.body("ERROR: No se encontró el sector padre con el nombre y/o grupo especificado.");
		}

		return new ResponseEntity<>("Actualización exitosa.", HttpStatus.OK);
	}

	@RequestMapping(value = "/sector-hijo/actualizar", method = RequestMethod.POST)
	public ResponseEntity<?> actualizarSectorHijo(@RequestBody SectorHijoActualizar hijo) throws ParseException {

		String nombreAnterior = hijo.nombreAnterior.toUpperCase();
		String nombreNuevo = hijo.nombreNuevo.toUpperCase();
		String padre_nombre = hijo.padre_nombre.toUpperCase();
		Optional<SectorComboEntity> data = sectorComboService.ConsultarHijoPorNombrePadreYGrupo(nombreAnterior,padre_nombre,
				hijo.grupo);
		if (data.isPresent()) {
			SectorComboEntity entidad = data.get();
			entidad.setNombre(nombreNuevo);

			LOG.info("ID: " + entidad.getSector_combo_id());
			LOG.info("Nombre: " + entidad.getNombre());
			LOG.info("Es padre: " + entidad.getEs_padre());
			LOG.info("padre: " + entidad.getPadre_nombre());
			LOG.info("Grupo: " + entidad.getGrupo());

			sectorComboService.Guardar(entidad);
		} else {
			return ResponseEntity.badRequest()
					.body("ERROR: No se encontró el sector hijo con los parámetros especificados.");
		}

		return new ResponseEntity<>("Actualización exitosa.", HttpStatus.OK);
	}
}
