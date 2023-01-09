package com.prospecta.service;

import java.util.List;

import com.prospecta.Exception.CategoryException;
import com.prospecta.model.Entry;
import com.prospecta.model.ResultDto;

public interface ProspectaService {
	
	List<ResultDto> getTitleDesc(String category) throws CategoryException;
	public String saveEntry(Entry entry);

}
