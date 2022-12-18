package com.TMS.DAOImplementation;

import java.util.List;


import com.TMS.Entity.Otheractivtiy;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.TMS.serviceclass.OtheractivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public class otheractivityDaoImpl implements OtheractivityService {

	@Autowired
	EntityManager entitymanager;
	public void add(Otheractivtiy otheractivity) {
		
		entitymanager.persist(otheractivity);
		
	}
	@Override
	public List<Otheractivtiy> show() {

		@SuppressWarnings("unchecked")
		List<Otheractivtiy> list=entitymanager.createQuery("from Otheractivtiy").getResultList();
		return list;
	}
	@Override
	public Otheractivtiy edit(int id)
	{
		Otheractivtiy list= (Otheractivtiy) entitymanager.createQuery("from Otheractivtiy where id=:id").setParameter("id",id).getSingleResult();
		return list;
	}
	@Override
	public void update(Otheractivtiy otheractivity) {
		
		
		entitymanager.merge(otheractivity);
		
	}
	@Override
	public void delete(int id) {
		
		Otheractivtiy a=entitymanager.find(Otheractivtiy.class, id);
		entitymanager.remove(a);
	}
	
}
