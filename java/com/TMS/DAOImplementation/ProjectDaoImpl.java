package com.TMS.DAOImplementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.LoginBean;
import com.TMS.Entity.addproject;
import com.TMS.Entity.emp_changedate;
import com.TMS.Entity.module;
import com.TMS.Entity.projectType;
import com.TMS.Entity.project_activity;
import com.TMS.Entity.project_assign;
import com.TMS.Entity.projectdetails;
import com.TMS.serviceclass.ProjectService;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectService {

	@PersistenceContext
	EntityManager entitymanager;
	
	@Autowired
	SessionFactory sf;
	
	@Override
	public LoginBean login(LoginBean e) {
		
		LoginBean li= (LoginBean) entitymanager.createQuery("from StudentBean where username=:uname"
						+ " and pass=:pass")
				.setParameter("uname", e.getUsername())
				.setParameter("pass", e.getPass())
				.getSingleResult();
		
		return li;
	}

	@Override
	public void add(addproject p) {
		
		
		entitymanager.persist(p);
	}

	@Override
	public List<addproject> show() {
		
		@SuppressWarnings("unchecked")
		List<addproject> list=entitymanager.createQuery("from addproject").getResultList();
		return list;
	}

	@Override
	public void delete(int id) {
		addproject a=entitymanager.find(addproject.class, id);
		/*Query q	= entitymanager.createQuery("delete from project_datechange where project_id=:id").setParameter("id",a.getId());
		q.executeUpdate();*/
		entitymanager.remove(a);
}
	@Override
	public addproject edit(int id) {

		addproject list= (addproject) entitymanager.createQuery("from addproject where id=:id").setParameter("id",id).getSingleResult();
		return list;
	}
	@Override
	public void update(addproject m) {
		
		entitymanager.merge(m);	
	}

	@Override
	public void save(projectType pt) {
		
		entitymanager.persist(pt);
	}

	@Override
	public List<projectType> display() {
		@SuppressWarnings("unchecked")
		List<projectType> list=entitymanager.createQuery("from projectType").getResultList();
		return list;

	}

	@Override
	public void projecttypedelete(int id) {
		projectType a=entitymanager.find(projectType.class, id);
		entitymanager.remove(a);
	}

	@Override
	public projectType projectTypeshow(int id) {
		
		projectType list= (projectType) entitymanager.createQuery("from projectType where id=:id").setParameter("id",id).getSingleResult();
		return  list;
	}

	@Override
	public void projecttypeupdate(projectType pt) {
		
		entitymanager.merge(pt);	
	}

	@Override
	public List<addproject> projectfindAllByAscendingOrder() {
		@SuppressWarnings("unchecked")
		 List<addproject> list=(List<addproject>) entitymanager.createQuery("from addproject where PROJECT_STATUS='Y'").getResultList();
		 
		return list;
	
		
		
	}

	@Override
	public List<module> modulefindAllByAscendingOrder() {
		@SuppressWarnings("unchecked")
		 List<module> list=(List<module>) entitymanager.createQuery("from module where MODULE_STATUS='Y'").getResultList();
		return list;
	}

	@Override
	public int addprojectdetails(projectdetails pd) {
		
		int count=0;
		
		@SuppressWarnings("unchecked")
		List<Object[]> li=(List<Object[]>) entitymanager.createQuery("select project_id,module_id from projectdetails where projectdet_status='Y'").getResultList();
		for(Object[] oj:li)
		{
			if(pd.getProject_id()==(int)oj[0] && pd.getModule_id()==(int)oj[1])
			{
				count=1;
			}
		}
		
		if(count==0)
		{
		entitymanager.persist(pd);
		return 0;
		}
		else
		{
		return count;
		}
	}

	@Override
	public List<projectdetails> projectdetailshow() {
		
		@SuppressWarnings("unchecked")
		List<projectdetails> list=entitymanager.createQuery("from projectdetails").getResultList();
		return list;
	}

	@Override
	public projectdetails projectdetailsedit(int id) {
		
		projectdetails list= (projectdetails) entitymanager.createQuery("from projectdetails where id=:id").setParameter("id",id).getSingleResult();
		return list;
	}

	@Override
	public void projectdetailupdate(projectdetails m) {
		entitymanager.merge(m);
		
	}

	@Override
	public List<projectdetails> projectdetailsfindAllByAscendingOrder() {
	
		@SuppressWarnings("unchecked")
		 List<projectdetails> list=(List<projectdetails>) entitymanager.createQuery("from projectdetails where projectdet_status='Y'").getResultList();
		return list;
	}

	@Override
	public void project_activityadd(project_activity pa) {
		entitymanager.persist(pa);
		
		
	}

	@Override
	public List<project_activity> project_activityshow() {
		
		@SuppressWarnings("unchecked")
		List<project_activity> list=entitymanager.createQuery("from project_activity where projectdetid in (select id from projectdetails where projectdet_status='Y')").getResultList();
		return list;
	}

	@Override
	public project_activity project_activityedit(int id) {
		
		project_activity list= (project_activity) entitymanager.createQuery("from project_activity where projectact_id=:id").setParameter("id",id).getSingleResult();
		return list;
	}

	@Override
	public void update(project_activity m) {
		
		entitymanager.merge(m);
		
	}

	@Override
	public void deleteprojectactivity(int id) {
		
		project_activity a=entitymanager.find(project_activity.class, id);
		entitymanager.remove(a);
		
	}

	@Override
	public List<projectdetails> projectdetailmoduleshow(int id) {
		@SuppressWarnings("unchecked")
		List<projectdetails> list=entitymanager.createQuery("from projectdetails where projectdet_status='Y' and project_id=:id").setParameter("id", id).getResultList();
	
		return list;
	}
	
	@Override
	public List<projectdetails> updateprojectdetailmoduleshow(int id) {
		@SuppressWarnings("unchecked")
		List<projectdetails> list=entitymanager.createQuery("select project_id from projectdetails where id=:id").setParameter("id", id).getResultList();
		@SuppressWarnings("unchecked")
		List<projectdetails> list1=entitymanager.createQuery("from projectdetails where  project_id=:id").setParameter("id", list.get(0)).getResultList();
		
		return list1;
	}
	
	@Override
	public List<Object[]> modulenamedistinct() {

		@SuppressWarnings("unchecked")
		 List<Object[]> list=(List<Object[]>) entitymanager.createQuery("select distinct(module_id),1 from projectdetails where projectdet_status='Y'").getResultList();
		return list;
	}

	@SuppressWarnings("unused")
	@Override
	public void updateactivitymodule(int id, int moduleid) {
	
		Session session=sf.getCurrentSession();
		int query =session.createSQLQuery("update project_detail set module_id="+moduleid+" where projectdet_id="+id).executeUpdate();
		
		
	}

	@Override
	public List<module> projectdetailsloadmodule(int id) {
		@SuppressWarnings("unchecked")
		List<module> list=entitymanager.createQuery("from module where module_status='Y' and id not in (select module_id from projectdetails where project_id=:id)").setParameter("id", id).getResultList();
		return list;
	}

	@Override
	public List<module> editprojectdetailsloadmodule(int projectid, int moduleid) {
		@SuppressWarnings("unchecked")
		List<module> list=entitymanager.createQuery("from module where module_status='Y' and id not "
				+ "in (select module_id from projectdetails where project_id=:id) or id=:id1")
		.setParameter("id", projectid).setParameter("id1", moduleid).getResultList();
		
		return list;
	
	}

	@Override
	public int getprojectdelid(int projectid, int moduleid) {
		@SuppressWarnings("unchecked")
		List<projectdetails> list=entitymanager.createQuery("from projectdetails where project_id=:projectid and module_id=:moduleid ").setParameter("projectid", projectid).setParameter("moduleid", moduleid).getResultList();
		int id=list.get(0).getId();
		return id;
	}

	@Override
	public void projectassignadd(project_assign projectassignsave) {
		
		entitymanager.persist(projectassignsave);
		
	}

	@Override
	public project_assign editprojectassign(int id) {
		
		project_assign list= (project_assign) entitymanager.createQuery("from project_assign where id=:id").setParameter("id",id).getSingleResult();
		return list;
	}

	@Override
	public List<addproject> projectshowbasedondetailid(int projectdelid) {
		@SuppressWarnings("unchecked")
		List<addproject> list=entitymanager.createQuery("from addproject where name=(select project_id from projectdetails "
				+ "where id=:id ").setParameter("id", projectdelid).getResultList();
		
		
		return list;
		
	}

	@Override
	public void updateprojectassign(project_assign PAUpdate) {
		
		entitymanager.merge(PAUpdate);
	}

	@Override
	public void addempldatechange(emp_changedate ed) {
		entitymanager.persist(ed);
		
	}

	@Override
	@SuppressWarnings("unused")
	public void setprojectchangedateinY(int projectassignid) {
		
		Session session=sf.getCurrentSession();
		int query =session.createSQLQuery("update project_assign set projectassigncompdtchng='Y' where projectassign="+projectassignid).executeUpdate();
		
		
	}

	@Override
	public int getprojectactid(int projectdetid, int activityid) {
		
		@SuppressWarnings("unchecked")
		List<project_activity> list=entitymanager.createQuery("from project_activity where "
				+ "projectdetid=:projectdetid and activity_id=:activity_id ").setParameter("projectdetid", projectdetid).setParameter("activity_id", activityid).getResultList();
		
		int id=list.get(0).getProjectact_id();
		
		
		return id;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<project_activity> project_activityslist() {

		List<project_activity> list=entitymanager.createQuery("from project_activity").getResultList();
		
		return list;
	}

	@Override
	public List<project_assign> projectassignshow(String id) {
		
		@SuppressWarnings("unchecked")
		List<project_assign> list=entitymanager.createQuery("from project_assign where empcode in"
				+ "(select empb_id from EmpProfile where empb_report=:id)").setParameter("id", id).getResultList();
		
		
		return list;
		
	}

	@Override
	public List<project_activity> showprojectactivity() {
		@SuppressWarnings("unchecked")
		List<project_activity> list=entitymanager.createQuery("from project_activity").getResultList();
		
		
		return list;
		
	}

	@Override
	public List<addproject> showbasedonid(String id) {
		
		int empid=Integer.parseInt(id);
		@SuppressWarnings("unchecked")
		List<addproject> list=entitymanager.createQuery("from addproject where emp_id=:id").setParameter("id", empid).getResultList();
		return list;
	}

	@Override
	public List<project_assign> projectassigninloginid(String id) {
		
		int empid=Integer.parseInt(id);
		
		@SuppressWarnings("unchecked")
		List<project_assign> list=entitymanager.createQuery("from project_assign where empcode=:id").setParameter("id", empid).getResultList();
		
		return list;
	}

	@Override
	public List<addproject> showbasedonassignemployee(String id) {
		
		int empid=Integer.parseInt(id);
		@SuppressWarnings("unchecked")
		List<addproject> list=entitymanager.createQuery("from addproject where emp_id=:id").setParameter("id", empid).getResultList();
		return list;
	}

	@Override
	public addproject findbyid(int id) {

		
		addproject list=(addproject) entitymanager.createQuery("from addproject where id="+id+" ").getSingleResult();
		return list;
	}
	@Override
	public projectdetails findbymoduleid(int id, int projectid) {
	
		projectdetails list=(projectdetails) entitymanager.createQuery("from projectdetails where module_id=:id and project_id=:projectid").setParameter("id", id).setParameter("projectid", projectid).getSingleResult();
		return list;
	}
}
