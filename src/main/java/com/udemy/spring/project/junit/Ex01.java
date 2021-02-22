package com.udemy.spring.project.junit;

/**
 * JUnit이 아닌 클래스에서 직접 테스트하는 코드를 작성
 */
public class Ex01 {

    public static class Calculator {
        public double sum(double n1, double n2) {
            return n1 + n2;
        }
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();

        double res = c.sum(10, 20);

        // c.sum()의 결과가 30이 아닌 경우 에러 발생
        if (res != 30) {
            System.err.println("err : " + res);
        }

        double res2 = c.sum(1.1, 2);

        // c.sum()의 결과가 3.1이 아닌 경우 에러 발생
        if (res2 != 3.1) {
            System.err.println("err : " + res2);
        }

        double res3 = c.sum(1.1, -2);

        // c.sum()의 결과가 0.9가 아닌 경우 에러 발생
        if (res3 != -0.9) {
            System.err.println("err : " + res3);
        }
    }
}
