package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.exception.NotFoundException;
import com.marvel.dingdangcat.service.UserService;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Marvel on 2019/9/30.
 */
@ControllerAdvice
public class ExceptionController {

    private final UserService userService;

    @Autowired
    public ExceptionController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 捕捉 CustomRealm 抛出的异常
     */
    @ExceptionHandler(AccountException.class)
    public ModelAndView handleShiroException(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "欢迎登录");
        mv.addObject("page", "login");
        mv.addObject("errorMessage", ex.getMessage());
        mv.setViewName("user/login");
        return mv;
    }

    /**
     * 404 异常
     */
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "404");
        mv.addObject("errorMessage", ex.getMessage());
        mv.setViewName("common/notFound");
        return mv;
    }
}
