package com.TMS.DAOImplementation;


import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.Country;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.CountryService;

@Repository
@Transactional
public class CountryDaoImpl extends HibernateServiceDaoImpl<Country, Serializable> implements CountryService{
	
	@Autowired
	EntityManager entitymanager;
	
	@Override
	public void delete(int id) {
		Country a=entitymanager.find(Country.class, id);
		Query q1 = entitymanager.createQuery("delete from City where mappingCountryCity=:id").setParameter("id",a.getId());
		q1.executeUpdate(); 
		Query q	= entitymanager.createQuery("delete from State where mappingCountryState=:id").setParameter("id",a.getId());
		q.executeUpdate();
		
		entitymanager.remove(a);
}

	
}