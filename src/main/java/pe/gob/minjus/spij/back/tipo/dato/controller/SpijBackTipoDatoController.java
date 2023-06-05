package pe.gob.minjus.spij.back.tipo.dato.controller;

import java.util.List;

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
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupamientoNormaService;
import pe.gob.minjus.spij.back.tipo.dato.service.impl.AgrupamientoNormaServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SpijBackTipoDatoController {

	@Autowired
	private IAgrupamientoNormaService agrupamientoNormaService;

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

}
