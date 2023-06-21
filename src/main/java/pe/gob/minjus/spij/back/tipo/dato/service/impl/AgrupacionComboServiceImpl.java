package pe.gob.minjus.spij.back.tipo.dato.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupacionComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.repository.IAgrupacionComboRepository;
import pe.gob.minjus.spij.back.tipo.dato.service.IAgrupacionComboService;

@Service
public class AgrupacionComboServiceImpl implements IAgrupacionComboService {

	@Autowired
	private IAgrupacionComboRepository iAgrupacionComboRepository;

	@Override
	public List<AgrupacionComboEntity> findAll() {
		return (List<AgrupacionComboEntity>) iAgrupacionComboRepository.findAll();
	}
}
