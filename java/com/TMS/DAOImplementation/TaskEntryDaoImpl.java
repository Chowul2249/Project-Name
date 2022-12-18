package com.TMS.DAOImplementation;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.TaskEntryModel;
import com.TMS.serviceclass.TaskEntryService;

@Repository
@Transactional
public class TaskEntryDaoImpl implements TaskEntryService{

	@Autowired
	SessionFactory sessionfactory;
	
	@Autowired
	EntityManager em;
	
	@Override
	public void add(TaskEntryModel addtaskentry) {
		
		em.persist(addtaskentry);
		
	}

	@Override
	public List<TaskEntryModel> showtasklist(String id) {
		
		//int empid=Integer.parseInt(id);
		Session session=sessionfactory.getCurrentSession();
		@SuppressWarnings({ "unused", "unchecked" })
		/*List <TaskEntryModel> list=session.createQuery("from TaskEntryModel").g();*/
		List<TaskEntryModel>list=(List<TaskEntryModel>) session.createQuery("from TaskEntryModel Where emp_id=:id").setParameter("id", id).list();
		return list;
		
		
	}
	@Override
	public TaskEntryModel edit(int id) {
	
		Session session = sessionfactory.getCurrentSession();
		TaskEntryModel list = (TaskEntryModel) session.createQuery("from TaskEntryModel where id=:id").setParameter("id", id).uniqueResult();
		return list;
	}

	@Override
	public void update(TaskEntryModel updateTentry) {
		
		Session session=sessionfactory.getCurrentSession();
		session.update(updateTentry);		
		
	}

	@Override
	public List<TaskEntryModel> listdel(int projectact_id) {
		Session session=sessionfactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TaskEntryModel> list=session.createQuery("from TaskEntryModel "
				+ "where projectact_id=:proj ").setParameter("proj", projectact_id).list();
		return list;
	}

	@Override
	@Transactional
	public List<TaskEntryModel> chagedatelist(String id, java.sql.Date date1) {
		
		Session session=sessionfactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TaskEntryModel> list=session.createQuery("from TaskEntryModel "
		+ "where emp_id=:id and taskentry_date=:date").setParameter("id", id).setParameter("date", date1).list();
		return list;
	}

	@Override
	public List<TaskEntryModel> showbasedontime(String id, java.sql.Date sqlD,
			Time sqlfromtime, Time sqltotime) {
	
		Session session=sessionfactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TaskEntryModel> list=session.createQuery("FROM TaskEntryModel WHERE emp_id=:id and taskentry_date=:date and (('"+sqlfromtime+"' "
				+ "between taskentry_frmtime24 and taskentry_totime24) or ('"+sqltotime+"' between taskentry_frmtime24 and taskentry_totime24)) ").setParameter("id", id).setParameter("date", sqlD).list();
		return list;
	}

	@Override
	public List<TaskEntryModel> showbasedontimeupdate(int taskentryid,
			String id, java.sql.Date sqlD, Time sqlfromtime, Time sqltotime) {
		
		Session session=sessionfactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TaskEntryModel> list=session.createQuery("FROM TaskEntryModel WHERE id!=:taskentryid and emp_id=:id and taskentry_date=:date and (('"+sqlfromtime+"' "
				+ "between taskentry_frmtime24 and taskentry_totime24) or ('"+sqltotime+"' between taskentry_frmtime24 and taskentry_totime24)) ").setParameter("taskentryid", taskentryid).setParameter("id", id).setParameter("date", sqlD).list();
		return list;
	}
}
