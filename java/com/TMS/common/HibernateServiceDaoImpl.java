package com.TMS.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;








import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;




@SuppressWarnings(value = "unchecked")
public class HibernateServiceDaoImpl<T, ID extends Serializable> implements HibernateServiceDao<T, ID> {
	
	protected Class<T> persistentClass; 
	public HibernateServiceDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<T> findAll() {
		return getSessionFactory().getCurrentSession().createCriteria(persistentClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	
	@Transactional
	public List<T> findLimitedRecords(int index) {
		return getSessionFactory().getCurrentSession().createCriteria(persistentClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setFirstResult(index).setMaxResults(10).list();
	}
	
	@Transactional()
	public List<T> findAll(String flag) {
		return getSessionFactory().getCurrentSession().createCriteria(persistentClass).add(Restrictions.eq("status", flag)).list();
	}
	
	@Transactional()
	public List<T> findAllByColStatus(String colName, String flag) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createCriteria(persistentClass).add(Restrictions.eq(colName, flag)).list();

	}	
	
	@Transactional()
	public List<T> findLimitedRecordsByAscendingOrder(String field,int index) {
		return getSessionFactory().getCurrentSession().createCriteria(persistentClass).addOrder(Order.asc(field)).setFirstResult(index).setMaxResults(10).list();
	}
	


	@Transactional
	public List<T> findAllByAscendingOrder(String field, String flag) {
		return getSessionFactory().getCurrentSession().createCriteria(persistentClass).add(Restrictions.eq("status", flag)).addOrder(Order.asc(field)).list();
	}
	
	@Transactional
	public List<T> findAllByAscendingOrder(String field) {
		return getSessionFactory().getCurrentSession().createCriteria(persistentClass).addOrder(Order.asc(field)).list();
	}
	
	@Transactional
	public T findById(ID id) {
		return (T) getSessionFactory().getCurrentSession().get(persistentClass, id);
	}

	@Transactional
	public int add(T entity) {
		getSessionFactory().getCurrentSession().save(entity);
		Serializable ids = getSessionFactory().getCurrentSession().getIdentifier(entity);
	    return (Integer) ids;
	}
	
	@Transactional
	public int save(Object entity) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().save(entity);
		Serializable ids = getSessionFactory().getCurrentSession().getIdentifier(entity);
	    return (Integer) ids;
	}

	@Transactional
	public int update(T entity) {
		getSessionFactory().getCurrentSession().update(entity);
		Serializable ids = getSessionFactory().getCurrentSession().getIdentifier(entity);
	    return (Integer) ids;
	}

	@Transactional
	public int delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
		Serializable ids = getSessionFactory().getCurrentSession().getIdentifier(entity);
	    return (Integer) ids;
	}

	@Transactional
	public int deleteById(ID id) {		
	    return (Integer) delete(findById(id));	
	}

	@Transactional
	public void saveOrUpdate(T entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
		
	}

	@Transactional
	public List<Object[]> getPotentialMonths(){
		return getSessionFactory().getCurrentSession().createSQLQuery("select to_char(trunc(SYSDATE()), 'Mon'),"
																	+ "to_char(add_months(trunc(SYSDATE()), -1), 'Mon'),"
																	+ "to_char(add_months(trunc(SYSDATE()), -2), 'Mon'),"
																	+ "to_char(add_months(trunc(SYSDATE()), -3), 'Mon')from dual").list();
	}

	@Transactional
	public double getWorkingDays(String fromDate, String toDate){
		String workquery= "select decode(COUNT(allobj),0,1,COUNT(allobj)) from (select to_date('"+fromDate+"','dd/mm/rrrr')+rownum-1 allobj from all_objects "
						+ "where rownum<= (select to_date('"+toDate+"','dd/mm/rrrr')+1-to_date('"+fromDate+"','dd/mm/rrrr') from dual)) where trim(to_char(allobj,'DAY')) <>'SUNDAY'";
		Query qry = getSessionFactory().getCurrentSession().createSQLQuery(workquery);
		return ((BigDecimal)qry.uniqueResult()).doubleValue();
	}
	
	@Transactional
	public double getfinYrDays(String fromDate){
		String finYrQuery="select COUNT(allobj) from (select to_date(microsharp.given_date_Fin_start_date(to_date('"+fromDate+"','dd/mm/rrrr')),'dd/mm/rrrr')+rownum allobj "
						+ "from all_objects where	rownum<= (select to_date(microsharp.given_date_Fin_end_date(to_date('"+fromDate+"','dd/mm/rrrr')),'dd/mm/rrrr') "
						+ "- to_date(microsharp.given_date_Fin_start_date(to_date('"+fromDate+"','dd/mm/rrrr')),'dd/mm/rrrr') from dual)) where trim(to_char(allobj,'DAY')) <>'SUNDAY'";
		Query qry = getSessionFactory().getCurrentSession().createSQLQuery(finYrQuery);
		return ((BigDecimal)qry.uniqueResult()).doubleValue();
	}
	
	@Transactional
	public List<Integer> getYearList() {
		// TODO Auto-generated method stub
		List<Integer> list =new ArrayList<Integer>();		
        int yearVal = Calendar.getInstance().get(Calendar.YEAR);	
        list.add((yearVal-1));
        list.add(yearVal);	        		
        return list;
	}
	
	
    @Transactional 
	public int getNoofDaysinMonth(Integer year, Integer month) {
		// TODO Auto-generated method stub
    	 Calendar cal = new GregorianCalendar(year, month,0);
		 // Get the number of days in the given month and year
		 int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		 return daysInMonth;
	}
    @Override
	@Transactional
	public boolean saveComposite(Object entity) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().save(entity);
		return false;
	}
	@Override
	@Transactional
	public int updateEntity(T entity) {
		getSessionFactory().getCurrentSession().update(entity);
		Serializable ids = getSessionFactory().getCurrentSession().getIdentifier(entity);
	    return  (int) ids;
	}
	

	@Override
	@Transactional
	public int saveEntity(Object entiObject) {		
	int id =	(Integer)getSessionFactory().getCurrentSession().save(entiObject);
		return id;
	}
	



}
