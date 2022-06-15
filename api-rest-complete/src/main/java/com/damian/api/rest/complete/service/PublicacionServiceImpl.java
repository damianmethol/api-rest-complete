package com.damian.api.rest.complete.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.api.rest.complete.dto.PublicacionDTO;
import com.damian.api.rest.complete.entity.Publicacion;
import com.damian.api.rest.complete.exception.ResourceNotFoundException;
import com.damian.api.rest.complete.repository.PublicacionRepository;

@Service
public class PublicacionServiceImpl implements PublicacionService {

	@Autowired
	private PublicacionRepository publicacionRepository;
	
	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = mapEntity(publicacionDTO);
		
		Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
		
		PublicacionDTO publicacionRespuesta = mapDTO(nuevaPublicacion);
		
		return publicacionRespuesta;
	}

	@Override
	public List<PublicacionDTO> listarPublicaciones() {
		List<Publicacion> publicaciones = publicacionRepository.findAll();
		return publicaciones.stream().map(publicacion -> mapDTO(publicacion)).collect(Collectors.toList());
	}
	
	// Convierte Entidad a DTO
	private PublicacionDTO mapDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();
		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());
		
		return publicacionDTO;
	}
	
	// Convierte DTO a Entidad
	private Publicacion mapEntity(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = new Publicacion();
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		return publicacion;
	}

	@Override
	public PublicacionDTO findPublicacionById(Long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		return mapDTO(publicacion);
	}

	@Override
	public PublicacionDTO editPublicacion(PublicacionDTO publicacionDTO, Long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
		
		return mapDTO(publicacionActualizada);
	}

	@Override
	public void deletePublicacion(Long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		publicacionRepository.delete(publicacion);
	}

}
