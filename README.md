# Spring Framework MVC Project

---

## JUnit(제이유닛, 단위 테스트)

1. JUnit은 Java용 단위 테스트 작성을 위한 산업 표준 프레임워크이다.
    - 단위 테스트란 테스트의 대상이 되는 코드 중에서 아주 작은 특정 영역을 실행해 보는 개발자가 작성한 코드 조각이다.
    - 어떤 것을 테스트 해야 하는가?
        1) 자신이 생각한 대로 코드가 동작하는지 검증하고 할 때
        2) 디스크가 꽉 차있고, 네트워크가 단절되어 있고, 버퍼 오버프로우가 되는 예외 상홍에서도 코드가 동작하는지 알아보고자 할 때
        3) 실제 DB 접속이 되지 않은 상태에서 설정이 제대로 되어 있는지 확인하고 할 때
2. 단위 코드에서 문제 발생 소지가 있는 모든 부분을 테스트 하는 작업
    - 보통 클래스의 public method를 테스트 한다.
    - 좋은 단위 테스트란 모든 메소드를 테스트하는 것이 아니라, 상식 수준의 확인을 통해 의도대로 동작하는지 여부를 판단하는 테스트이다.
3. Maven 프로젝트에서 JUnit 라이브러리 설정 방법
    - pom.xml 설정 파일에 JUnit 4 의존 설정(스프링 4.x 버전 이후부터는 `JUnit 4` 라이브러리가 추가되어 있다.)
      ```xml
      <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
      </dependencies>
      ```

--- 

## 테스트 코드 작성

1. `test` 패키지에 테스트 코드를 작성한다.
    - 테스트 하고자 하는 메소드를 지정할 때에는 `@Test` 어노테이션을 사용한다. 이때 @Test 어노테이션이 선언된 메소드는 static 메소드가 아니어야 하고, 파라미터가 있으면 안된다.
        1) `org.junit.Assert`에서 제공하는 단정 함수를 사용해서 테스트 코드를 작성하는 예제
           ```java 
           package com.udemy.spring.project.junit;

           import org.junit.Test;
                  
           import static org.junit.Assert.*;
                  
           /**
            * com.udemy.spring.project.junit.Calculator 클래스를 테스트하는 클래스
            * main() 메소드가 없어도 동작한다.
            */
           public class CalculatorTest {
                  
               @Test
               public void test() {
                   Ex01.Calculator c = new Ex01.Calculator();
                  
                   double res = c.sum(10, 20);
                   assertEquals(30, res, 0); // assertEquals(기대 값, 실제 값, 허용 오차 범위)
                  
                   double res2 = c.sum(1.1, 2);
                   assertEquals(3.1, res2, 0); // assertEquals(기대 값, 실제 값, 허용 오차 범위)
                  
                  
                   double res3 = c.sum(1.1, -2);
                   assertEquals(-0.9, res3, 0.1); // assertEquals(기대 값, 실제 값, 허용 오차 범위)
               }
           }
           ```
      