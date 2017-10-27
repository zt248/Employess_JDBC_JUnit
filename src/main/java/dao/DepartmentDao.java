package dao;


import entity.Department;

import java.util.List;

public interface DepartmentDao {

    //create
    void add(Department department);

    //read
    List<Department> getAll();

    Department getById(Long id);

    //update
    void update(Department department);

    //delete

    void remove(Department department);
}

