package com.martsforever.core.template.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentService studentService;

    @GetMapping("list")
    public List<Student> list() {
        return studentDao.findAll();
    }

    @PostMapping("upsert")
    public Student upsert(@RequestBody Student student) {
        return studentDao.save(student);
    }

    @PostMapping("delete")
    public void delete(@RequestBody Student student) {
        studentDao.delete(student);
    }

    @PostMapping("get")
    public Student get(@RequestBody Student student) {
        return studentDao.findOne(student.getId());
    }

    @PostMapping("findByAge")
    public List<Student> findByAge(@RequestBody Student student) {
        return studentDao.findByAge(student.getAge());
    }

    @PostMapping("multiUpsert")
    public List<Student> multiUpsert(@RequestBody List<Student> students) {
        try {
            return studentService.multiUpsert(students);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
