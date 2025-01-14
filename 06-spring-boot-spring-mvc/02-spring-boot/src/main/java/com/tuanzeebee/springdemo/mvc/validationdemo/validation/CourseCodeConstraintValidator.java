package com.tuanzeebee.springdemo.mvc.validationdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode,String> {

    private String coursePrefix;

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContextContext) {

        boolean result ;
        if(theCode != null)
        {
         result = theCode.startsWith(coursePrefix);
        }
        else {
            result =true;
        }

        return result;
    }

    @Override
    public void initialize(CourseCode theCourseCode) {
        coursePrefix = theCourseCode.value();
    }
}
