package com.martsforever.core.template.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Integer> {

    /**
     * 根据年龄查询学生
     *
     * @param age
     * @return
     */
    public List<Student> findByAge(Integer age);

}
