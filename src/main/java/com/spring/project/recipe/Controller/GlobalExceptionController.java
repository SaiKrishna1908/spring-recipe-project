package com.spring.project.recipe.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView exceptionNumberFormat(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(exception);
        modelAndView.setViewName("error/400BadRequest");
        log.error("number format exception");
        return modelAndView;
    }
}
