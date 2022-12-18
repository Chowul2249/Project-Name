package com.TMS.DAOImplementation;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.State;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.StateService;



@Repository
@Transactional
public class StateDaoImpl extends HibernateServiceDaoImpl<State, Serializable> implements StateService{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override 
	public List<State> getListbyCountryID(int id) {
	org.hibernate.Session session=sessionFactory.getCurrentSession();
	@SuppressWarnings("unchecked")
	List<State> stateList=session.createQuery("from State where mappingCountryState=:id and status='Y'").setParameter("id", id).list();
	return stateList;	
	}

	@Autowired
	EntityManager entitymanager;
	
	@Override
	public void delete(int id) {
		State a=entitymanager.find(State.class, id);
		Query q = entitymanager.createQuery("delete from City where mappingStateCity=:id").setParameter("id",a.getId());
		q.executeUpdate(); 
		entitymanager.remove(a);
}

}
