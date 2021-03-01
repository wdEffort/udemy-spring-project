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

---

## 로깅(Logging)

> [스프링 log4j.xml 파일 설정 참고 링크](https://to-dy.tistory.com/20)

1. 로그(Log)란 프로그램 개발이나 운영 중에 발생하는 문제점을 추적하거나 운영 상태를 모니러팅 하기 위한 텍스트이다.
2. Java에서 로그를 남기는 가장 쉬운 방법은 `System.out.println();` 문법을 사용하는 방법이 있을 수 있다.
3. `System.out.println();` 문법을 사용하는 방법보다 향상된 방법으로 로그를 기록하는 방법은 `로깅 프레임워크(Logging Framework)`를 이용하는 방법이 있다.
4. 로깅 프레임워크 종류
    - [commons-logging](http://commons.apache.org/proper/commons-logging/)
    - [Log4j](http://logging.apache.org/log4j/2.x/index.html)
    - [java.util.logging](http://docs.oracle.com/javase/8/docs/api/java/util/logging/package-summary.html)
    - [Logback](http://logback.qos.ch/)
    - (기본)[SLF4J](http://slf4j.org/)

## log4jdbc-log4j2 라이브러리 사용하기

> [logback을 사용할 경우 설정 참고 링크 - 1](https://sonegy.wordpress.com/2014/05/23/how-to-slf4j/)
>
> [logback을 사용할 경우 설정 참고 링크 - 2](https://m.blog.naver.com/PostView.nhn?blogId=0neslife&logNo=221161290205&proxyReferer=https:%2F%2Fwww.google.com%2F)
>
> [log4jdbc-log4j2 설정 참고 링크](https://www.hanumoka.net/2018/07/27/spring-20180727-Spring-add-log4jdbc-log4j2-in-mysql-mybatis/)

1. log4jdbc는 스프링에서 SQL문을 실행한 로그를 효과적이고 직관적으로 볼 수 있도록 해주는 라이브러리이다.
2. 설정 방법
    - pom.xml에 `log4jdbc-log4j2` 의존 설정 추가
      ```xml
      <dependencies>
           <dependency>
               <groupId>org.bgee.log4jdbc-log4j2</groupId>
               <artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
               <version>1.16</version>
           </dependency>
      </dependencies>
      ```
    - DataSource 설정 변경
       ```xml
       <!-- DataSource 설정(DB 연결을 담당) -->
       <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
            <!-- <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/> -->
            <!-- <property name="url" value="jdbc:mysql://localhost:3306/udemy?characterEncoding=UTF-8&amp;serverTimezone=UTC&amp;useSSL=false"/> -->
            <!-- MyBatis 로그 라이브러리를 사용하는 경우 드라이버 및 URL 설정 -->
            <property name="driverClassName" value="net.sf.log4jdbc.sql.jdbcapi.DriverSpy"/>
            <property name="url" value="jdbc:log4jdbc:mysql://localhost:3306/udemy?characterEncoding=UTF-8&amp;serverTimezone=UTC&amp;useSSL=false"/>
            <property name="username" value="udemy"/>
            <property name="password" value="1234"/>
       </bean> 
      ```
    - src/main/resources 경로에 `log4jdbc.log4j2.properties` 파일 생성 및 프로퍼티 추가
      ```properties
      #사용할 Driver를 설정한다.
      log4jdbc.drivers=com.mysql.cj.jdbc.Driver
      log4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator
      #SQL문 최대 출력 라인 수를 설정한다. 0인 경우 무제한이며, 설정하지 않은 경우 한줄 출력으로 설정된다.
      log4jdbc.dump.sql.maxlinelength=0
      #Disable - Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
      log4jdbc.auto.load.popular.drivers=false
      ```
    - src/main/resources 경로에 `logback.xml` 파일 생성(이미 존재할 경우 <logger/> 태그 부분만 추가하도록 한다.)
      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <configuration>
      
          <include resource="org/springframework/boot/logging/logback/base.xml"/>

          <!-- Other ... -->
            
          <!-- SQL Loggers -->
          <logger name="jdbc" level="OFF"/>
          <!-- SQL문만 로깅. PreparedStatement일 경우 관련된 Arguments 값으로 대체된 SQL문을 출력한다. -->
          <logger name="jdbc.splonly" level="DEBUG"/>
          <!-- SQL문 수행 시간(milliseconds) 로깅 한다. -->
          <logger name="jdbc.sqltiming" level="INFO"/>
          <!-- ResultSet을 제외한 모든 JDBC 호출 정보 로깅. JDBC 문제를 추적해야 할 필요가 있는 경우에만 사용하는 것을 권정한다. -->
          <logger name="jdbc.audit" level="WARN"/>
          <!-- ResultSet을 포함한 모든 JDBC 호출 정보 로깅. 로그 양이 많다. -->
          <logger name="jdbc.resultset" level="ERROR"/>
          <!-- SQL문 실행 결과를 Table(표) 형태로 로깅 한다. -->
          <logger name="jdbc.resultsettable" level="ERROR"/>
          <!-- DB Connection 연결 및 종료 로깅. 메모리 누수 확인도 가능하다. -->
          <logger name="jdbc.connection" level="INFO"/>
      
          <!-- Other ... -->
      
      </configuration>
      ```   

---

## MyBatis Test Rollback 처리하기

1. MyBatis 테스트의 주 목적은 DB와 연결해서 SQL문이 정상적으로 수행되는지를 확인하는 것이기 때문에 실제로 데이터가 저장이 되면 안된다.
2. 특히 INSERT, UPDATE, DELETE문을 테스트하는 경우 트랜잭션 처리를 통해 Rollback 과정이 마지막에 수행되어야 한다.
3. 테스트 트랜잭션 설정 방법
    - root-context.xml에 `TransactionManager` Bean 설정
       ```xml
      <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
         <property name="dataSource" ref="dataSource"/>
      </bean>
      ```
    - @Transactional과 @Rollback(value = true) 어노테이션을 사용하여 테스트 코드 작성
       ```java
       @RunWith(SpringJUnit4ClassRunner.class)
       @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
       public class MemberDAOTest {
       
           @Autowired
           private MemberDAO memberDAO;
       
           @Test
           @Transactional // 트랜잭션 처리를 하기 위한 어노테이션 설정
           @Rollback(value = true) // 테스트가 진행되고 나서 Rollback 처리를 하기 위한 어노테이션 설정
           public void testInsertMember() throws Exception {
               System.out.println("sqlSession을 이용한 회원 정보 등록 테스트 ...");
       
               MemberVO memberVO = new MemberVO();
               memberVO.setId("test");
               memberVO.setPassword("1234");
               memberVO.setUsername("홍길동");
               memberVO.setEmail("test@example.com");
       
               memberDAO.insertMember(memberVO);
           }
       }
       ```

---

## MyBatis Type Alias 설정

1. Type Alias란 MyBatis를 사용하면서 parameterType, resultType 등에 클래스 전체 경로를 지정해서 사용하는 불편함을 줄여주고자 지원하는 기능이다.
2. 사용하는 방법은 <typeAlias/> 태그를 이용하거나 <package/> 태그를 이용하는 방법이 있다.
    - \<typeAlias/\>
        1) `클래스별` Type Alias를 지정할 경우에 사용한다.
        2) `type` 속성 : Type Alias로 사용할 클래스의 경로를 클래스 이름을 포함하여 지정한다.
        3) `alias` 속성 : 클래스의 별칭을 설정한다.
        4) mybatis-config.xml 파일에 Type Alias 설정
           ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
            <configuration>
            
                <typeAliases>
                    <!-- com.udemy.spring.project.board.vo.BoardPostVO 클래스를 "BoardPostVO" 라는 이름으로 Alias를 지정한다. -->
                    <typeAlias type="com.udemy.spring.project.board.vo.BoardPostVO" alias="BoardPostVO"/>
                </typeAliases>
            
            </configuration>
           ```
        5) 사용방법
            ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
            
            <mapper namespace="BoardPostMapper">
            
                <!-- SQL Mapper에서 parameterType, resultType 등에 지정해 놓은 Type Alias의 별칭을 사용한다. -->
                <insert id="insert" parameterType="BoardPostVO">
                    <![CDATA[
                    INSERT INTO TBL_BOARD_POST (SUBJECT, CONTENT, WRITER, REG_DATE)
                    VALUES (#{subject}, #{content}, #{writer}, NOW())
                    ]]>
                </insert>
           </mapper>
           ```

    - \<package/\>
        1) VO 객체가 여러 개이고, `한 Package 안에 존재할 경우` 사용한다.
        2) `name` 속성 : Type Alias로 사용할 클래스의 경로를 지정한다.
        3) mybatis-config.xml 파일에 Type Alias 설정
           ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
            <configuration>

                <typeAliases>
                    <!-- com.udemy.spring.project.vo 패키지 안에 있는 모든 클래스에 대해 Alias를 설정한다. -->
                    <package name="com.udemy.spring.project.vo"/>
                </typeAliases>
            
            </configuration>
           ```
        4) VO 클래스마다 @Alias("사용할 Alias 명") 어노테이션을 사용해서 Alias를 지정한다.
           ```java
            @Alias("BoardPostVO")
            public class BoardPostVO {
                // code ...
            }
           ```
        5) 사용방법
           ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
            
            <mapper namespace="BoardPostMapper">
            
                <!-- SQL Mapper에서 parameterType, resultType 등에 지정해 놓은 Type Alias의 별칭을 사용한다. -->
                <insert id="insert" parameterType="BoardPostVO">
                    <![CDATA[
                    INSERT INTO TBL_BOARD_POST (SUBJECT, CONTENT, WRITER, REG_DATE)
                    VALUES (#{subject}, #{content}, #{writer}, NOW())
                    ]]>
                </insert>
           </mapper> 
           ```

---

## 예외 처리

1. 스프링 MVC를 사용할 때 Controller 쪽에서 예외(Exception)를 처리하기 위해서 사용하는 방식
    - @ExceptionHandler 어노테이션을 사용하는 방식
    - @ControllerAdvice 어노테이션을 이용하는 방식(가장 많이 사용하는 방식)
        1) 공통의 Exception 처리 전용 객체를 사용하는 방법이다.
        2) 사용방식
            - 클래스에 @ControllerAdvice 어노테이션을 선언한다.
            - 각 메소드에 @ExceptionHandler 어노테이션을 이용해서 적절한 타입의 Exception을 처리한다.
               ```java
               // 현재 클래스가 Controller에서 발생하는 Exception을 전문적으로 처리하는 클래스임을 알려준다.
               @ControllerAdvice
               public class CommonExceptionAdvice {
               
                   /**
                    * Exception 타입 예외가 발생하면 처리하는 메소드
                    *
                    * @param e
                    * @return
                    */
                   @ExceptionHandler(Exception.class)
                   public String common(Exception e) {
                       return "exception/exception";
                   }
               }
               ```
    - @ResponseStatus 어노테이션을 이용한 HTTP 상태 코드 처리 방식

---

## 페이징 처리

1. 끝 페이지(endPage)와 시작 페이지(startPage) 구하기
    - 끝 페이지 : (int) (Math.ceil(현재 페이지 번호 / `출력할 페이지 목록 개수`)) * `출력할 페이지 목록 개수`
        1) `double Math.ceil(double d)` 메소드는 올림 메소드이다.
        2) 현재 페이지 번호가 12 페이지이고 출력할 페이지 목록 개수가 10개로 설정된 경우
            - (int) (Math.ceil(12 / 10)) * 10 = 20
    - 시작 페이지 : (끝 페이지 - `출력할 페이지 목록 개수`) + 1
        1) 끝 페이지 번호가 20이고, 출력할 페이지 목록 개수가 10개로 설정된 경우
            - (20 - 10) + 1 = 11
2. 전체 데이터 개수(totalCount)에 대한 끝 페이지(finalEndPage) 재계산
    - 끝 페이지 : (int) (Math.ceil(전체 데이터 개수 / `한 페이지당 데이터 출력 개수`))
    - 만약, 1번 항목에서 구한 끝 페이지가 2번 항목에서 구한 끝 페이지보다 큰 경우 `강제로` 2번 항목에서 구한 끝 페이지로 설정한다.
      ```java
      if (endPage > finalEndPage) {
          endPage = finalEndPage;
      }
      ```
3. 이전 페이지(prev)와 다음 페이지(next) 구하기
    - 이전 페이지 : 시작 페이지 == 1 ? false : true
    - 다음 페이지 : (끝 페이지 * `한 페이지당 데이터 출력 개수`) >= 전체 데이터 개수 ? false : true
4. 데이터 출력 시작 위치(startRecordNum) 구하기
    - 데이터 출력 시작 위치 : (현재 페이지 번호 - 1) * `한 페이지당 데이터 출력 개수`

---

## UriComponentsBuilder를 사용해서 파라미터 유지하기

1. org.spring.framework.web.util.UriComponentsBuilder는 여러 개의 파라미터들을 연결하여 하나의 URL 형태로 만들어 주는 기능을 가지고 있다.
2. 웹 페이지에서 매번 파라미터를 유지하는 일이 번거로운 경우 사용한다.
    - 컨트롤러에서 리다이렉트시 여러 파라미터들에 대해 일일이 addAttribute를 사용해야 하는 불편함을 해결해 준다.
        1) 예 : 각 수정과 삭제할 때 페이지 번호, 개수, 검색 조건, 검색 키워드를 매번 넘겨줘야 하는 경우
3. 사용방법
   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
   public class BoardPostDAOImplTest {
      
        private static final Logger LOGGER = LoggerFactory.getLogger(BoardPostDAOImplTest.class);
   
        @Test
        public void uriComponentsBuilderTest() throws Exception {
              String uri = "/board/view?postId=100&numPerPage=20&stx=" + URLEncoder.encode("가나다", "UTF-8");
              UriComponents uriComponents = UriComponentsBuilder.newInstance()
                      .path("/board/view")
                      .queryParam("postId", 100)
                      .queryParam("numPerPage", 20)
                      .queryParam("stx", "가나다")
                      .encode()
                      .build();
      
              LOGGER.info("\'" + uri + "\' eqauls uriComponents.toString() => " + uri.equals(uriComponents.toString()));
        }
      
        @Test
        public void uriComponentsBuilderTest2() throws Exception {
              String uri = "/board/view?postId=100&numPerPage=20&stx=" + URLEncoder.encode("가나다", "UTF-8");
              UriComponents uriComponents = UriComponentsBuilder.newInstance()
                      .path("/{module}/{page}")
                      .queryParam("postId", 100)
                      .queryParam("numPerPage", 20)
                      .queryParam("stx", "가나다")
                      .encode()
                      .build()
                      .expand("board", "view");
      
              LOGGER.info("\'" + uri + "\' eqauls uriComponents.toString() => " + uri.equals(uriComponents.toString()));
        }
   }
   ```

---

