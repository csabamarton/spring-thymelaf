package com.csmarton.services;

import com.csmarton.model.DomainObject;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService
{
	protected Map<Integer, DomainObject> domainMap;

	public AbstractMapService()
	{
		domainMap = Maps.newHashMap();
		loadDomainObjects();
	}

	public List<DomainObject> listAll()
	{
		return new ArrayList<>(domainMap.values());
	}

	public DomainObject getById(Integer id)
	{
		return domainMap.get(id);
	}

	public DomainObject updateOrCreate(DomainObject domainObject)
	{
		if(domainObject != null) {
			if (domainObject.getId() == null) {
				domainObject.setId(getNextKex());
			}

			domainMap.put(domainObject.getId(), domainObject);

			return domainObject;
		}else {
			throw  new RuntimeException("Domain Object cant be nil");
		}
	}

	public void deleteById(Integer id)
	{
		if(id != null){
			domainMap.remove(id);
		}
	}

	private Integer getNextKex() {
		return Collections.max(domainMap.keySet()) + 1;
	}

	protected abstract void loadDomainObjects();
}
