package com.prospecta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospecta.Exception.CategoryException;
import com.prospecta.model.Entry;
import com.prospecta.model.ResultDto;
import com.prospecta.service.ProspectaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProspectaController {
	
	@Autowired
	ProspectaService ps;
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<ResultDto>> getEntriesHandler(@PathVariable("category") String category) throws CategoryException{
		
		 List<ResultDto> list=ps.getTitleDesc(category);
		 return new ResponseEntity<List<ResultDto>>(list,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/entries")
	public ResponseEntity<String> saveEntriesHandler(@RequestBody Entry entry) {
		String s=ps.saveEntry(entry);
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
	

}
