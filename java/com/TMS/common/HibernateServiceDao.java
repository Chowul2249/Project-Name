package com.TMS.common;

import java.io.Serializable;
import java.util.List;



public interface HibernateServiceDao<T, ID extends Serializable> {
	
	public List<T> findAll();
	
	public List<T> findLimitedRecords(int index);

	public List<T> findAll(String flag);
	
	public List<T> findAllByColStatus(String colName,String flag);
	
	public List<T> findLimitedRecordsByAscendingOrder(String field,int index);

	public T findById(ID id);

	public int add(T entity);

	public int saveEntity(Object entiObject);
	
	public int update(T entity);
	
	public int updateEntity(T entity);

	public int delete(T entity);

	public int deleteById(ID id);
	
	public void saveOrUpdate(T entity);
	
	public int save(Object entity);
	
	public boolean saveComposite(Object entity) ;
	
	public List<T> findAllByAscendingOrder(String field,String flag);
	
	public List<T> findAllByAscendingOrder(String field);
		
	public double getWorkingDays(String fromDate, String toDate);
	
	public double getfinYrDays(String fromDate);

	public List<Object[]> getPotentialMonths();

    public List<Integer>  getYearList();
    
    public int getNoofDaysinMonth(Integer year,Integer month);
}
