package com.udemy.spring.project.board.validator;

import com.udemy.spring.project.board.vo.BoardPostVO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BoardPostValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardPostVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "required.subject", "제목을 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "required.writer", "작성자를 입력해 주세요.");
    }
}
