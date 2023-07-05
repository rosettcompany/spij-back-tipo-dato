package pe.gob.minjus.spij.back.tipo.dato.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@RequestMapping(value = "/lista-normas-y-jurisprudencias", method = RequestMethod.GET)
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/norma/actualizar", method = RequestMethod.POST)
	public ResponseEntity<?> actualizarNormas(@RequestBody NormaActualizar norma) throws ParseException {

		try {
			agrupamientoNormaService.Actualizar(norma);
			return ResponseEntity.ok("Operación exitosa"); // Devuelve respuesta exitosa con mensaje
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}

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
		try {
			sectorComboService.GuardarPadre(sectorPadre);
			return ResponseEntity.ok("Operación exitosa"); // Devuelve respuesta exitosa con mensaje
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/sector-hijo/insertar", method = RequestMethod.POST)
	public ResponseEntity<?> insertarSectorHijo(@RequestBody SectorComboEntity sectorHijo) throws ParseException {

		try {
			sectorComboService.GuardarHijo(sectorHijo);
			return ResponseEntity.ok("Operación exitosa"); // Devuelve respuesta exitosa con mensaje
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/sector-padre/actualizar", method = RequestMethod.POST)
	public ResponseEntity<?> actualizarSectorPadre(@RequestBody NormaActualizar norma) throws ParseException {

		try {
			sectorComboService.ActualizarPadre(norma);
			return ResponseEntity.ok("Operación exitosa"); // Devuelve respuesta exitosa con mensaje
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/sector-hijo/actualizar", method = RequestMethod.POST)
	public ResponseEntity<?> actualizarSectorHijo(@RequestBody SectorHijoActualizar hijo) throws ParseException {

		try {
			sectorComboService.ActualizarHijo(hijo);
			return ResponseEntity.ok("Operación exitosa"); // Devuelve respuesta exitosa con mensaje
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}
}
