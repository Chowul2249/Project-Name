package com.TMS.DAOImplementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.TMS.Entity.project_datechange;
import com.TMS.serviceclass.ProjectDateChangeservice;

@Repository
@Transactional
public class ProjectDateChangeImpl implements ProjectDateChangeservice {


	@PersistenceContext
	EntityManager entitymanager;
	
	@Override
	public void add(project_datechange pd) {
			
		entitymanager.persist(pd);
		
	}

}
