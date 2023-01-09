package com.prospecta.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiDataModel {
	
	private int count;
	private List<Entry> entries;

}
