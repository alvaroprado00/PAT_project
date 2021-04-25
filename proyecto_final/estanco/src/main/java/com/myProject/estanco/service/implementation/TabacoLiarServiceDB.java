package com.myProject.estanco.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.estanco.model.TabacoLiar;
import com.myProject.estanco.model.TabacoLiarSearchModel;
import com.myProject.estanco.repository.TabacoLiarRepository;

@Service
public class TabacoLiarServiceDB {
	
	@Autowired
	TabacoLiarRepository tabacoLiarRepository;
	
	public TabacoLiarSearchModel getTabacoLiar() {
		
		Iterable<TabacoLiar> tabacosLiar = tabacoLiarRepository.findAll();

		Iterator<TabacoLiar> tabacosIt = tabacosLiar.iterator();
		
		List<TabacoLiar> listaTabacos = new ArrayList<TabacoLiar>();	
		
		TabacoLiarSearchModel tabacosLiarModel = new TabacoLiarSearchModel();
		
		int conter = 0;
		
		while(tabacosIt.hasNext()) {
			final TabacoLiar t = tabacosIt.next();
			listaTabacos.add(t);
			conter++;
		}
		
		tabacosLiarModel.setCount(conter-1);
		tabacosLiarModel.setItems(listaTabacos);
		
		return tabacosLiarModel;			
		
	}
	
}
