package com.martsforever.core.template.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * transactional这个注解好像只适用于数据库层面的异常，当其他异常发生时（自定义异常不行），是不会回滚事务的。
     * 只有数据库层面的异常发生时，才会回滚事务，比如插入的字段的类型不对，长度不对等等。
     *
     * @param students
     * @return
     * @throws Exception
     */
    @Transactional
    public List<Student> multiUpsert(List<Student> students) throws Exception {
        for (int i = 0; i < students.size(); i++) {
            studentDao.save(students.get(i));
        }
        return students;
    }

}
