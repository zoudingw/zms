package com.zdw.zms.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/20
 * Description:异常处理类，提供友好提示
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
@ControllerAdvice
//@ControllerAdvice 注解，可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping中
public class MyController {


    /*
     * @author zoudw
     * @param [dataBinder]
     * @return void
     * @date 2019/3/20
     * @description :应用到所有@requestMapping主解的方法，在方法执行前初始化数据绑定器
     */
    @InitBinder
    public void initBind(WebDataBinder dataBinder) {
    }

    /*
     * @author zoudw
     * @param []
     * @return void
     * @date 2019/3/20
     * @description :把值绑定到model中，使所有@RequesrtMapping 的方法能获取这个值
     */
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("test", "test11");
    }

    /*
     * @author zoudw
     * @param []
     * @return java.util.Map
     * @date 2019/3/20
     * @description :全局异常捕捉，可以自定义异常信息处理，返回给前端显示
     */
    @ExceptionHandler(value = Exception.class)//value:表示该方法捕捉异常类型
    @ResponseBody
    public Map errorHandel(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }
}
