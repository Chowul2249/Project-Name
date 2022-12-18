package com.TMS.DAOImplementation;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.TMS.Entity.EmployeeCategory;
import com.TMS.common.HibernateServiceDaoImpl;
import com.TMS.serviceclass.EmployeeCategoryService;


@Repository
public class EmployeeCategoryDaoImpl extends HibernateServiceDaoImpl<EmployeeCategory, Serializable> implements EmployeeCategoryService{

}
