package org.jlhi.service;

import java.util.List;

import org.jlhi.model.Vacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntVacantesService {
	
	public List<Vacante> obtenerVacantes();
	//mas logica
	public Vacante buscarPorId(Integer idVacante);
	
	public void guardar(Vacante vacante);
	public void eliminar(Integer idVacante);
	public Page<Vacante>buscarTodas(Pageable page); 

}
