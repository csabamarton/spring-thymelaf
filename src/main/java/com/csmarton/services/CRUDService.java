package com.csmarton.services;

import java.util.List;

public interface CRUDService<T>
{
	List<?> listAll();

	T getById(Integer id);

	T updateOrCreate(T domainObject);

	void deleteById(Integer id);
}
