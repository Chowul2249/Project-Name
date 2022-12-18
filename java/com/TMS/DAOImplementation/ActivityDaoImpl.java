package com.TMS.DAOImplementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.activity;
import com.TMS.serviceclass.ActivityService;

@Repository
@Transactional
public class ActivityDaoImpl implements ActivityService{

	@Autowired
	EntityManager entitymanager;
	
	@Override
	public void add(activity a) {
		
		entitymanager.persist(a);
	}

	@Override
	public List<activity> show() {
		@SuppressWarnings("unchecked")
		List<activity> list=entitymanager.createQuery("from activity").getResultList();
		return list;
	}

	@Override
	public void delete(int id) {
		activity a=entitymanager.find(activity.class, id);
		entitymanager.remove(a);
		
		
	}

	@Override
	public activity edit(int id) {
		activity list= (activity) entitymanager.createQuery("from activity where id=:id").setParameter("id",id).getSingleResult();
		return list;
	}

	@Override
	public void update(activity m) {
		entitymanager.merge(m);
		
	}

	@Override
	public List<activity> activityfindAllByAscendingOrder() {
		@SuppressWarnings("unchecked")
		 List<activity> list=(List<activity>) entitymanager.createQuery("from activity where activity_status='Y'").getResultList();
		return list;	
		}

	@Override
	public List<activity> showbasedonactivity(int id,int projectid) {
		

		@SuppressWarnings("unchecked")
		List<activity> list=entitymanager.createQuery("from activity where activity_status='Y' and id in "
				+ "(select activity_id from project_activity where projectdetid in"
				+ " (select id from projectdetails where projectdet_status='Y'"
				+ " and module_id=:id and project_id=:projectid))").setParameter("id", id).setParameter("projectid", projectid).getResultList();

		return list;
	}

	@Override
	public List<activity> editbasedonprojectid(int projectdet_id,
			int activity_id) {

		
		@SuppressWarnings("unchecked") 
		 List<activity> list=(List<activity>) entitymanager.createQuery("from activity where id in"
		 		+ " (select activity_id from project_activity where  projectdetid not in "
		 		+ "(select projectdetid from project_activity where projectdetid=:projectdelid)"
		 		+ " or activity_id=:activityid)").setParameter("projectdelid", projectdet_id)
		 		.setParameter("activityid", activity_id).getResultList();
		return list;	
				
		
		
		
	}

	@Override
	public List<activity> showactivitybasedonprojectdetid(int projectdelid) {
		
		@SuppressWarnings("unchecked")
		 List<activity> list=(List<activity>) entitymanager.createQuery("from activity where activity_status='Y' "
		 		+ "and id in(select activity_id from project_activity where projectdetid=:id)").setParameter("id", projectdelid).getResultList();
		 
		return list;	
	} 

}
