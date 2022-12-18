package com.TMS.DAOImplementation;

import java.util.List;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.TMS.Entity.otheractivity_assign;
import com.TMS.Entity.otheractivity_change;
import com.TMS.serviceclass.OtherActivityAssignService;

@Repository
@Transactional
public class OtherActivityAssignDaoImpl  implements OtherActivityAssignService{

	@Autowired
	EntityManager entitymanager;

	@Override
	public void addotheractivityassign(
			otheractivity_assign saveotheractivityassignAssign) {
		
		entitymanager.persist(saveotheractivityassignAssign);
		
	}
   
	@Override
	public List<otheractivity_assign> show(String id) {
		
		List<otheractivity_assign> list=entitymanager.createQuery("from otheractivity_assign where empb_id in (select empb_id from EmpProfile where empb_report=:id)").setParameter("id", id).getResultList();
		return list;
	}

	@Override
	public otheractivity_assign edit(int id) {
		
		
		otheractivity_assign list= (otheractivity_assign) entitymanager.createQuery("from otheractivity_assign where id=:id").setParameter("id",id).getSingleResult();
		
		return list;
		
	}

	@Override
	public void update(otheractivity_assign updatefield) {
		
		entitymanager.merge(updatefield);
		
	}
	@Override
	public void addchangedate(otheractivity_change change) {
		
		entitymanager.persist(change);
		
	}

	

	/*@Override
	public int addotheractivityassign(otheractivity_assign saveotheractivityassignAssign) {
		*/
		
		
		
		
		/*
		 int count=0;
		 List<Object[]> li=(List<Object[]>) entitymanager.createQuery("select empcode,otheractid from otheractivity_assign").getResultList();
		
		for(Object[] oj:li)
		{
			if(otheractivity.getEmpb_code()==(int)oj[0] && otheractivity.getOtheract_id()==(int)oj[1])
			{
				count=1;
			}
		}
		
		if(count==0)
		{
		entitymanager.persist(otheractivity);
		return 0;
		}
		else
		{
		return count;
		}
		*/
   /* @Override
	public int getotheractivityassign(int empb_code, int otheract_id) {
		
			@SuppressWarnings("unchecked")
			List<otheractivity_assign> list=entitymanager.createQuery("from otheractivity_assign where empb_code=:empb_code and otheract_id=:otheract_id").setParameter("empb_code", empb_code).setParameter("otheract_id",otheract_id).getResultList();
			
			System.out.println("Employee code"+empb_code);
			System.out.println("Otheractivityid"+otheract_id);
			
			int id=list.get(0).getOtheractassign_id();
		
		return id;
	}
	@Override
	public int addotheractivityassign(
			otheractivity_assign saveotheractivityassignAssign) {
	 
		
		
		
			entitymanager.persist(saveotheractivityassignAssign);
		
		
		return 0;
	}*/
}
