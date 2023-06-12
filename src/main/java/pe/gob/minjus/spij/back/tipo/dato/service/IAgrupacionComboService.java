package pe.gob.minjus.spij.back.tipo.dato.service;

import java.util.List;
import java.util.Optional;

import pe.gob.minjus.spij.back.tipo.dato.entity.AgrupacionComboEntity;
import pe.gob.minjus.spij.back.tipo.dato.entity.SectorComboEntity;

public interface IAgrupacionComboService {
	public List<AgrupacionComboEntity> findAll();
}
