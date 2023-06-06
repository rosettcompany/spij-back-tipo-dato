package pe.gob.minjus.spij.back.tipo.dato.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.NormaRequest;
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupamientoNormaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SpijBackTipoDatoController {

	@Autowired
	private IAgrupamientoNormaService agrupamientoNormaService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
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
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
