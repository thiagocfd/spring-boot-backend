package com.franz.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import com.franz.cursomc.domain.Categoria;
import com.franz.cursomc.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
