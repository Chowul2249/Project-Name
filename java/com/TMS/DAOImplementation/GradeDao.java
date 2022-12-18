package com.TMS.DAOImplementation;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.Grade;
import com.TMS.serviceclass.GradeService;


@Repository

public class GradeDao implements GradeService{
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	@Transactional
	
public List<Grade> getGrade()

    {		
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Grade> grade=session.createQuery("from Grade order by weightage").list();
		return grade;
	}

	@Override
	@Transactional
	
public int addGrade(Grade grade)
	{
		
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		session.save(grade);
		Serializable ids=session.getIdentifier(grade);
		return(Integer) ids;
		
	}
	@Override
	@Transactional
	
public int deleteGrade(int id)

	{
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		Grade grade = (Grade) session.get(Grade.class, id);
		session.delete(grade);
	    Serializable ids = session.getIdentifier(grade);	
		return (Integer) ids;
	}

	@Override
	@Transactional
	
public Grade getGrade(int id)

	{
		
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		Grade grade=(Grade) session.get(Grade.class,id);
		return grade;
	}
	@Override
	@Transactional
	public int update(Grade UpGrade) {
	
		
		Session session=sessionFactory.getCurrentSession();
		session.update(UpGrade);
		Serializable idss=session.getIdentifier(UpGrade);
		return (Integer) idss;
		
	}
	

	
}
