package com.damian.api.rest.complete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damian.api.rest.complete.dto.PublicacionDTO;
import com.damian.api.rest.complete.service.PublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

	@Autowired
	private PublicacionService publicacionService;
	
	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PublicacionDTO> listarPublicaciones(){
		return publicacionService.listarPublicaciones();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> findPublicacionById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(publicacionService.findPublicacionById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> editPublicacion(@RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") Long id) {
		PublicacionDTO publicacionRespuesta = publicacionService.editPublicacion(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublicacion(@PathVariable(name = "id") Long id){
		publicacionService.deletePublicacion(id);
		return new ResponseEntity<>("Publicacion eliminada exitosamente", HttpStatus.OK);
	}
}
