package com.TMS.DAOImplementation;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.City;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.CityService;


@Repository
public class CityDaoImpl extends HibernateServiceDaoImpl<City, Serializable> implements CityService{
@Autowired
	SessionFactory sessionFactory;
	
	

	@Override
	@Transactional
	public List<City> getCityByState(int id) {
		// TODO Auto-generated method stub
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<City> list=session.createQuery("from City where mappingStateCity=:id").setParameter("id", id).list();	
		return list;
	}

}
