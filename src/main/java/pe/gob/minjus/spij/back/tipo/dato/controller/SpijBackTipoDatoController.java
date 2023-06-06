package pe.gob.minjus.spij.back.tipo.dato.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.NormaActualizar;
import pe.gob.minjus.spij.back.tipo.dato.entity.NormaRequest;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupamientoNormaService;
import pe.gob.minjus.spij.back.tipo.dato.service.ISectorComboService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SpijBackTipoDatoController {

	@Autowired
	private IAgrupamientoNormaService agrupamientoNormaService;
	
	@Autowired
	private ISectorComboService sectorComboService;

	@RequestMapping(value = "/listar-normar-y-jurisprudencia", method = RequestMethod.GET)
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
	public ResponseEntity<?> insertarNormas(@RequestBody NormaRequest normaRequest) throws ParseException {

		List<AgrupamientoNormaEntity> data = agrupamientoNormaService.findAll();
		System.out.println("data.size: " + data.size());
		int lastIndex = data.size() - 1;
		AgrupamientoNormaEntity lastEntity = data.get(lastIndex);
		int agrupamiento_id = lastEntity.getAgrupamiento_id() + 1;
		System.out.println("lastEntity: " + agrupamiento_id);

		AgrupamientoNormaEntity norma = new AgrupamientoNormaEntity();
		norma.setAgrupamiento_id(agrupamiento_id);
		norma.setNombre(normaRequest.nombre.toUpperCase());

		int grupo = normaRequest.grupo;
		if (grupo >= 1 && grupo <= 8) {
			if (grupo == 5) {
				grupo = 6;
			}
			norma.setGrupo(grupo);
		} else {
			return ResponseEntity.badRequest().body("ERROR: El grupo ingresado no está asociado a una agrupación válida.");
		}

		System.out.println("norma: " + norma);
		System.out.println("norma id: " + norma.agrupamiento_id);
		System.out.println("norma nombre: " + norma.nombre);
		System.out.println("norma grupo: " + norma.grupo);

		agrupamientoNormaService.Guardar(norma);
		return new ResponseEntity<>("Registro exitoso",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/norma/actualizar", method = RequestMethod.POST)
	public ResponseEntity<?> actualizarNormas(@RequestBody NormaActualizar norma) throws ParseException {

		String nombreAnterior= norma.nombreAnterior.toUpperCase();
		String nombreNuevo= norma.nombreNuevo.toUpperCase();
		Optional<AgrupamientoNormaEntity> data = agrupamientoNormaService.ConsultarPorNombre(nombreAnterior, norma.grupo);
		if (data.isPresent()) {
	        AgrupamientoNormaEntity entidad = data.get();
	        int agrupamiento_id = entidad.getAgrupamiento_id();
	        entidad.setAgrupamiento_id(agrupamiento_id);
	        entidad.setNombre(nombreNuevo);
	        int grupoNorma = entidad.getGrupo();
	        entidad.setGrupo(grupoNorma);
	        

	        System.out.println("ID: " + entidad.getAgrupamiento_id());
	        System.out.println("Nombre: " + entidad.getNombre());
	        System.out.println("Grupo: " + entidad.getGrupo());
	        
	        agrupamientoNormaService.Guardar(entidad);
	    } else {
	    	return ResponseEntity.badRequest().body("ERROR: No se encontró la norma con el nombre y/o grupo especificado.");
	    }
		
		return new ResponseEntity<>("Actualización exitosa.",HttpStatus.OK);
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
		System.out.println("data.size: " + data.size());
		int lastIndex = data.size() - 1;
		SectorComboEntity lastEntity = data.get(lastIndex);
		int sector_combo_id = lastEntity.getSector_combo_id() + 1;
		System.out.println("lastEntity: " + sector_combo_id);

		SectorComboEntity padre = new SectorComboEntity();
		padre.setSector_combo_id(sector_combo_id);
		padre.setNombre(sectorPadre.nombre.toUpperCase());
		padre.setEs_padre(sectorPadre.es_padre);
		padre.setPadre_nombre(sectorPadre.padre_nombre.toUpperCase());

		int grupo = sectorPadre.grupo;
		if (grupo >= 1 && grupo <= 8) {
			if (grupo == 5) {
				grupo = 6;
			}
			padre.setGrupo(grupo);
		} else {
			return ResponseEntity.badRequest().body("ERROR: El grupo ingresado no está asociado a una agrupación válida.");
		}

		System.out.println("padre: " + padre);
		System.out.println("padre sector_combo_id: " + padre.sector_combo_id);
		System.out.println("padre nombre: " + padre.nombre);
		System.out.println("padre es_padre: " + padre.es_padre);
		System.out.println("padre padre_nombre: " + padre.padre_nombre);
		System.out.println("padre grupo: " + padre.grupo);

		sectorComboService.Guardar(padre);
		return new ResponseEntity<>("Registro exitoso",HttpStatus.OK);
	}
}
