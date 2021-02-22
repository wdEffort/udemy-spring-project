package com.udemy.spring.project.junit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * com.udemy.spring.project.junit.Calculator 클래스를 테스트하는 클래스
 * main() 메소드가 없어도 동작한다.
 */
public class CalculatorTest {

    /**
     * 테스트 하고자 하는 메소드를 지정할 때에는 @Test 어노테이션을 사용한다.
     * 이때 @Test 어노테이션이 선언된 메소드는 static 메소드가 아니어야 하고, 파라미터가 있으면 안된다.
     */
    @Test
    public void test() {
        Ex01.Calculator c = new Ex01.Calculator();

        double res = c.sum(10, 20);
        // c.sum()의 결과가 30이 아닌 경우 에러 발생(org.junit.Assert 단정 함수 사용)
        //if (res != 30) {
        //    System.err.println("err : " + res);
        //}
        assertEquals(30, res, 0); // assertEquals(기대 값, 실제 값, 허용 오차 범위)

        double res2 = c.sum(1.1, 2);
        // c.sum()의 결과가 3.1이 아닌 경우 에러 발생(org.junit.Assert 단정 함수 사용)
        //if (res2 != 3.1) {
        //    System.err.println("err : " + res2);
        //}
        assertEquals(3.1, res2, 0); // assertEquals(기대 값, 실제 값, 허용 오차 범위)


        double res3 = c.sum(1.1, -2);
        // c.sum()의 결과가 0.9가 아닌 경우 에러 발생(org.junit.Assert 단정 함수 사용)
        //if (res3 != -0.9) {
        //    System.err.println("err : " + res3);
        //}
        assertEquals(-0.9, res3, 0.1); // assertEquals(기대 값, 실제 값, 허용 오차 범위)
    }
}