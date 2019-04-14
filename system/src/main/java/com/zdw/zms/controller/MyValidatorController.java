package com.zdw.zms.controller;


import com.alibaba.fastjson.JSONObject;
import com.zdw.zms.entity.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
public class MyValidatorController {

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/valid")
    public  String validaFiled(@Valid @RequestBody MyValidator myValidator, BindingResult result){
        if (result.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            Locale locale = LocaleContextHolder.getLocale();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors) {
                String message = messageSource.getMessage(error, locale);
                sb.append(message);
            }
            return sb.toString();
        }

        return JSONObject.toJSONString(myValidator);

    }

}
