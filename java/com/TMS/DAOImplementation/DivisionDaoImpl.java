package com.TMS.DAOImplementation;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.Division;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.DivisionService;


@Repository
public class DivisionDaoImpl extends HibernateServiceDaoImpl<Division, Serializable> implements DivisionService{

	@Autowired
	SessionFactory sesf;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Division> getDivisionBasedDepartment(Integer division) {
		org.hibernate.Session session=sesf.getCurrentSession();
		List<Division> subList=session.createQuery("from Division where departmentToDivision=:id").setParameter("id", division).list();		
		return subList;
	}

	
	
}
