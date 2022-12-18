package com.TMS.DAOImplementation;
import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.TMS.Entity.Designation;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.DesignationService;


@Repository
public class DesignationDaoImpl  extends HibernateServiceDaoImpl<Designation, Serializable> implements DesignationService{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Designation> getDesignation() {
		// TODO Auto-generated method stub
		org.hibernate.Session session= sessionFactory.getCurrentSession();
		 @SuppressWarnings("unchecked")
		List<Designation> designation = session.createQuery("from Designation").list();	
		 return designation;
	}
}
