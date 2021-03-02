package com.udemy.spring.project.rest.controller;

import com.udemy.spring.project.rest.vo.StudentVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 리소스(데이터) 자체를 반환 하는데 사용하는 어노테이션이다.(JSON, XML, 문자열)
@RequestMapping("/demo")
public class DemoController {

    /**
     * @return
     * @RestController 어노테이션이 설정된 컨트롤러에서는 각 메소드에 @ResponseBody 어노테이션이 생략된 것과 같다.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "Hello";
    }

    /**
     * 학생 정보 조회
     * StudentVO 객체를 JSON 문자열로 반환한다.
     *
     * @return
     */
    @RequestMapping(value = "/students/1", method = RequestMethod.GET)
    public StudentVO studentInfo() {
        StudentVO studentVO = new StudentVO();
        studentVO.setNo(1);
        studentVO.setName("홍길동");
        studentVO.setGrade(6);

        return studentVO;
    }

    /**
     * 학생 목록 조회
     * List 객체를 JSON 문자열의 배열로 반환한다.
     *
     * @return
     */
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<StudentVO> studentList() {
        List<StudentVO> studentList = new ArrayList<>(); // JDK 1.8 이후부터는 제네릭 타입을 생략해도 된다.

        StudentVO studentVO1 = new StudentVO();
        studentVO1.setNo(1);
        studentVO1.setName("홍길동");
        studentVO1.setGrade(4);

        studentList.add(studentVO1);

        StudentVO studentVO2 = new StudentVO();
        studentVO2.setNo(2);
        studentVO2.setName("김길동");
        studentVO2.setGrade(5);

        studentList.add(studentVO2);

        StudentVO studentVO3 = new StudentVO();
        studentVO3.setNo(3);
        studentVO3.setName("박길동");
        studentVO3.setGrade(6);

        studentList.add(studentVO3);

        return studentList;
    }

    /**
     * 학생 목록 조회
     * Map 객체를 Key: Value(JSON) 형태의 JSON 문자열로 반환한다.
     *
     * @return
     */
    @RequestMapping(value = "/students/map", method = RequestMethod.GET)
    public Map<Integer, StudentVO> studentMap() {
        Map<Integer, StudentVO> studentMap = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            StudentVO studentVO = new StudentVO();
            studentVO.setNo(i);
            studentVO.setName("홍길동" + i);
            studentVO.setGrade(i);

            studentMap.put(i + 1, studentVO);
        }

        return studentMap;
    }
}
