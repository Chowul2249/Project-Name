package com.TMS.DAOImplementation;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.PrintCategory;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.PrintCategoryService;

@Repository
public class PrintCategoryDaoImpl extends HibernateServiceDaoImpl<PrintCategory, Serializable> implements PrintCategoryService{
@Autowired
SessionFactory sesfac;
	
	
	@Override
	@Transactional
	public List<PrintCategory> getPrintCatListById(String pcId) {
		// TODO Auto-generated method stub
		Session session=sesfac.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<PrintCategory> list=session.createQuery("from PrintCategory where id in ("+pcId+") and status='Y'").list();
		System.out.println("pclistSize.."+list.size());
		
		return list;
	}


	@Override
	public void delete(int id) {
		Session session=sesfac.getCurrentSession();
		PrintCategory m=(PrintCategory)session.load(PrintCategory.class, id);
		session.delete(m);
		
	}

}
