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
3. JUnit 라이브러리 설정 방법
    - 스프링 4.x 버전 이후부터는 `JUnit 4` 라이브러리가 추가되어 있다.
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