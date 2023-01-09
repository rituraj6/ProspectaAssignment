package com.prospecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prospecta.Exception.CategoryException;
import com.prospecta.model.ApiDataModel;
import com.prospecta.model.Entry;
import com.prospecta.model.ResultDto;
import com.prospecta.repositry.ApiDataModelRepo;

@Service
public class ProspectaServiceImpl implements ProspectaService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ApiDataModelRepo apidm;

	@Override
	public List<ResultDto> getTitleDesc(String category) throws CategoryException {
		
	 ApiDataModel adm=	restTemplate.getForObject("https://api.publicapis.org/entries", ApiDataModel.class);
	 List<Entry> entries= adm.getEntries();
	 
	 List<ResultDto> list= entries.stream().filter(e -> e.getCategory().equals(category)).map(e -> new ResultDto(e.getApi(), e.getDescription())).toList();
		
	 if(list.size()==0) throw new CategoryException("no data found with this category");
	 
	 return list;
	}

	//for this method i am going to use our local data base because we have not permeation to write post method on this api;
	@Override
	public String saveEntry(Entry entry) {
		// TODO Auto-generated method stub
		 List<Entry> e= apidm.findAll();
		if(e.size()==0) {
		 ApiDataModel adm=	restTemplate.getForObject("https://api.publicapis.org/entries", ApiDataModel.class);
		 List<Entry> entries= adm.getEntries();
		 for(Entry em:entries) {
			 apidm.save(em);
		 }
		 apidm.save(entry);
		}else {
			apidm.save(entry);
		}
		
		
		return "entry is saved in to a database";
	}

}
