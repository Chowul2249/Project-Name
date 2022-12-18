package com.TMS.DAOImplementation;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TMS.Entity.Department;
import com.TMS.Entity.Division;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.DepartmentService;


@Repository
public class DepartmentDaoImpl extends HibernateServiceDaoImpl<Department, Serializable> implements DepartmentService{

	@Autowired
	SessionFactory sesFac;

	@Autowired
	EntityManager entitymanager;
	
	@Override
	@Transactional
	public List<Division> getListbydeptID(int departmentId) {
		Session session = sesFac.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Division> divisionList = session.createQuery("from Division where departmentToDivision.id=:departmentId and status='Y'").setParameter("departmentId", departmentId).list();
		return divisionList;
	}

	@Override
	@Transactional
	public List<Department> getListByStatus() {
		org.hibernate.Session session= getSessionFactory().getCurrentSession();
		 @SuppressWarnings("unchecked")
		List<Department> function = session.createQuery("from Department where status='Y' order by depart_name").list();	
		 return function;
	}

	@Override
	@Transactional
	public List<Object[]> getdeptByPrintCat(String startEndYrss,Integer printCateId, String month_det) {
		System.out.println("startEndYrss..."+startEndYrss+"printCateId..."+printCateId+"month_det..."+month_det);
		org.hibernate.Session session= getSessionFactory().getCurrentSession();
		 @SuppressWarnings("unchecked")
		 List<Object[]> list = session.createSQLQuery("select distinct(sey.depart_id),dept.depart_name from "+startEndYrss+" sey,department dept  where sey.depart_id=dept.depart_id and sey.print_cat='"+printCateId+"' and sey.MONTH_SAL='"+month_det+"' ").list();
		return list;
	}

	@Override
	@Transactional
	public List<Object[]> getDistinctList(int printCatId) {
		org.hibernate.Session session= getSessionFactory().getCurrentSession();
		 @SuppressWarnings("unchecked")
		List<Object[]> list = session.createQuery("select distinct(ep.deptToempBasic.id),dt.depart_name from EmployeeProfile ep,Department dt where ep.deptToempBasic.id=dt.id and ep.printCatId.id="+printCatId+" order by dt.id").list();
		return list;
	}

	@Override
	@Transactional
	public List<Object[]> getDistinctListIncrement(Integer printCateId) {
		org.hibernate.Session session= getSessionFactory().getCurrentSession();
		 @SuppressWarnings("unchecked")
		 List<Object[]> list = session.createQuery("select distinct(ep.deptToempBasic.id),dt.depart_name from EmployeeProfile ep,Department dt where ep.deptToempBasic.id=dt.id and ep.printCatId.id=:printCateId and ep.status='Y'").setParameter("printCateId", printCateId).list();
			return list;
	}

	@Override
	@Transactional
	public void delete(int id) {
		Department a=entitymanager.find(Department.class, id);
		Query q = entitymanager.createQuery("delete from Division where departmentToDivision=:id").setParameter("id",a.getId());
		q.executeUpdate(); 
		entitymanager.remove(a);
}

	
	

}
