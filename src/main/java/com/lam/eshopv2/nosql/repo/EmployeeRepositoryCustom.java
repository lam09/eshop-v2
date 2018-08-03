package com.lam.eshopv2.nosql.repo;

/**
 * Created by a.lam.tuan on 1. 8. 2018.
 */
import java.util.Date;

public interface EmployeeRepositoryCustom {

    public long getMaxEmpId();

    public long updateEmployee(String empNo, String fullName, Date hireDate);

}