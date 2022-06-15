package com.damian.api.rest.complete.service;

import java.util.List;

import com.damian.api.rest.complete.dto.PublicacionDTO;

public interface PublicacionService {

	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public List<PublicacionDTO> listarPublicaciones();
	
	public PublicacionDTO findPublicacionById(Long id);
	
	public PublicacionDTO editPublicacion(PublicacionDTO publicacionDTO, Long id);
	
	public void deletePublicacion(Long id);
}
