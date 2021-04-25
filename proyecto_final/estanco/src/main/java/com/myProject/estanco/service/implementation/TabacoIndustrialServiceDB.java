package com.myProject.estanco.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.estanco.model.TabacoIndustrial;
import com.myProject.estanco.model.TabacoIndustrialSearchModel;
import com.myProject.estanco.repository.TabacoIndustrialRepository;

@Service
public class TabacoIndustrialServiceDB {
	
	@Autowired
	TabacoIndustrialRepository tabacoIndustrialRepository;
	
	public TabacoIndustrialSearchModel getTabacoIndustrial() {
		
		Iterable<TabacoIndustrial> tabacosIndustrial = tabacoIndustrialRepository.findAll();

		Iterator<TabacoIndustrial> tabacosIt = tabacosIndustrial.iterator();
		
		List<TabacoIndustrial> listaTabacos = new ArrayList<TabacoIndustrial>();	
		
		TabacoIndustrialSearchModel tabacosIndustrialModel = new TabacoIndustrialSearchModel();
		
		int conter = 0;
		
		while(tabacosIt.hasNext()) {
			final TabacoIndustrial t = tabacosIt.next();
			listaTabacos.add(t);
			conter++;
		}
		
		tabacosIndustrialModel.setCount(conter);
		tabacosIndustrialModel.setItems(listaTabacos);
		
		return tabacosIndustrialModel;			
		
	}
	
}
