package com.TMS.DAOImplementation;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.JobLevel;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.JobLevelService;


@Repository
public class JobLevelDaoImpl extends HibernateServiceDaoImpl<JobLevel, Serializable> implements JobLevelService{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<JobLevel> getlevelListactive() {
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<JobLevel> levell=session.createQuery("from JobLevel where status='A' order by levelName ASC").list();
		return levell;
	}
	
	@Override
	@Transactional
	public List<JobLevel> getAscendingByLevel() {
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<JobLevel> levell=session.createQuery("from JobLevel order by levelName ASC").list();
		return levell;
	}
	
	
	
	@Override
	@Transactional
	public JobLevel getLevel(int id) 
	{
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		JobLevel level = (JobLevel) session.get(JobLevel.class, id);
		return level;
	}
	
	
}
