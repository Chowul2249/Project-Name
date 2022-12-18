package com.TMS.DAOImplementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.TaskEntryModel;
import com.TMS.Entity.addproject;
import com.TMS.Entity.module;
import com.TMS.serviceclass.ModuleService;

@Repository
@Transactional
public class moduleimpl implements ModuleService{

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	EntityManager em;
	
	@Override
	public void add(module module1) {
		
		Session session=sessionFactory.getCurrentSession();
		session.save(module1);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<module> display() {
		
		Session session=sessionFactory.getCurrentSession();
		List <module> list=session.createQuery("from module").list();
		
		return list;
	}


	@Override
	public module edit(int id) 
	{
		Session session = sessionFactory.getCurrentSession();
		module list = (module) session.createQuery("from module where id=:id").setParameter("id", id).uniqueResult();
		return list;
	}


	@Override
	public void update(module module) {
		Session session=sessionFactory.getCurrentSession();
		session.update(module);		
	}


	@Override
	public void delete(int id) {
		
		Session session=sessionFactory.getCurrentSession();
		module m=(module)session.load(module.class, id);
		session.delete(m);
	}


	@Override
	public List<module> showbasedonprojectid(int id) {
		@SuppressWarnings("unchecked")
		List<module> list=em.createQuery("from module where module_status='Y' and id in "
				+ "(select module_id from projectdetails where "
				+ "projectdet_status='Y' and project_id=:id) ").setParameter("id", id).getResultList();
	
		return list;
	}


	@Override
	public List<module> editshowmoduledtsbasedonprojectid(int projectdelid) {
		@SuppressWarnings("unchecked")
		List<module> list=em.createQuery("from module where module_status='Y' and id in "
				+ "(select module_id from projectdetails where projectdet_status='Y' and project_id in"
				+ "(select project_id from projectdetails where "
				+ " id in(select projectdelid from project_assign where projectdelid=:id))) ").setParameter("id", projectdelid).getResultList();
	
		return list;
	}
}