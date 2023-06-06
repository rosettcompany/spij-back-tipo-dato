package pe.gob.minjus.spij.back.tipo.dato.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupamientoNormaEntity;
import pe.gob.minjus.spij.back.tipo.dato.repository.IAgrupamientoNormaRepository;
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupamientoNormaService;

@Service
public class AgrupamientoNormaServiceImpl implements IAgrupamientoNormaService {
	
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
		iAgrupamientoNormaRepository.save(agrupamientoNormaEntity);
		
	}


	@Override
	public Optional<AgrupamientoNormaEntity> ConsultarPorNombre(String nombreAnterior, int grupo) {
		return iAgrupamientoNormaRepository.ConsultarPorNombre(nombreAnterior, grupo);
	}


	
}
