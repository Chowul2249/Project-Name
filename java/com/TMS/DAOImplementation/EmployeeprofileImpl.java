package com.TMS.DAOImplementation;



import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.EmpProfile;
import com.TMS.serviceclass.EmployeeProfileService;

@Repository
public class EmployeeprofileImpl implements EmployeeProfileService{

	@Autowired
	SessionFactory sf;
	
	@Autowired
	EntityManager em;
	
	@Transactional
	public void add(EmpProfile save) 
	{		
		Session session=sf.getCurrentSession();
		session.save(save);
	}
	@Override
	@Transactional
	public void delete(int id) {
		
		Session session=sf.getCurrentSession();
		
		EmpProfile em=(EmpProfile)session.load(EmpProfile.class, id);
		
		
		session.delete(em);
	}
	@Transactional
	public EmpProfile edit(int id) {
	
		
		Session session = sf.getCurrentSession();
		EmpProfile list = (EmpProfile) session.createQuery("from EmpProfile where id=:id").setParameter("id", id).uniqueResult();
		return list;
	}

	@Override
	@Transactional
	public List<EmpProfile> display(int id) {
		Session session=sf.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<EmpProfile> list=session.createQuery("from EmpProfile where empb_id=:id").setParameter("id",id).list();
		return list;
	}	
	@Override
	@Transactional
	public List<EmpProfile> getReportToPerson(int level) {
		Session session=sf.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<EmpProfile> list=session.createQuery("from EmpProfile where levelToemp=:level").setParameter("level", level).list();
		return list;
	}

 
	public List<EmpProfile> display() {
		
		
		return null;
	}
	@Override
	@Transactional
	public List<Object[]> getEmpCodeAndNameListBySearch(int empCode) {
		Session session=sf.getCurrentSession();
		Query query = session.createSQLQuery("SELECT empb_id,concat(empb_fname,' ',empb_lname) AS fullname,empcat_id,depart_id,"
     			+ "division_id,desig_id,level_id,empb_mobile FROM employeeprofile WHERE empb_id Like '"+empCode+"%' ORDER BY empb_id");
        @SuppressWarnings("unchecked")
		List<Object[]> abc=query.list(); 
	    return abc; 
	}	
	@Override
	@Transactional
	public List<Object[]> showaddprojectdetail() {
		
		Session session=sf.getCurrentSession();
		Query query = session.createSQLQuery("select distinct(PROJECT_NAME),1 from project inner join project_detail"
				+ " on project_detail.project_id=project.PROJECT_ID inner join project_activity"
				+ " on project_activity.projectdet_id=project_detail.projectdet_id");
		@SuppressWarnings("unchecked")
		List<Object[]> li=query.list();
		
		return li;
	}
	@Override
	@Transactional
	public List<EmpProfile> show() {
		
		Session session=sf.getCurrentSession();
		@SuppressWarnings("unchecked")
		List <EmpProfile> list=session.createQuery("from EmpProfile").list();
		
		return list;
	}

	@Override
	@Transactional
	public List<Object[]> showreport(int empCode) 
	{
			Session session=sf.getCurrentSession();
			Query query = session.createSQLQuery("SELECT empb_id FROM employeeprofile WHERE empb_id Like '"+empCode+"%' ORDER BY empb_id");
		     @SuppressWarnings("rawtypes")
			List abc=query.list();
		     int id=(int) abc.get(0);
		     Query query1 = session.createSQLQuery("SELECT empb_id,empb_fname,empb_lname,"
		     		+ " empb_mobile,empb_email,desig_id,empb_status FROM employeeprofile WHERE empb_report Like '"+id+"%' ORDER BY empb_report");
		     @SuppressWarnings("unchecked")
			List<Object[]> report=query1.list();
		     
		    
		return report; 
	}

	@Override
	@Transactional
	public void update(EmpProfile updatefield) {
		
		Session session=sf.getCurrentSession();
		session.update(updatefield);
		
	}
	@Override
	@Transactional 
	public List<EmpProfile> basedonreportid(String id) {
		@SuppressWarnings("unchecked")
		 List<EmpProfile> list=(List<EmpProfile>) em.createQuery("from EmpProfile where empb_report=:id").setParameter("id", id).getResultList();
		return list;
	}
	@Override
	public boolean loginreturn(int username,HttpSession session) {
		
		boolean status=false;  
		
	try
	{
		@SuppressWarnings("unchecked")
	
		List<EmpProfile> list=em.createQuery("from EmpProfile where empb_id=:id").setParameter("id", username).getResultList();
		
		//String name=list.get(0).getEmpb_fname();//+" "+list.get(0).getEmpb_lname();
		//System.out.println(name);
		
		//String name=list.get(0).getEmpb_fname()+" "+list.get(0).getEmpb_lname();
		String name=list.get(0).getEmpb_fname();
		
		session.getAttribute(name);
		session.setAttribute("headername", name);
		
		
		status=true;
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return status;
		
		
		
		
	}
}
