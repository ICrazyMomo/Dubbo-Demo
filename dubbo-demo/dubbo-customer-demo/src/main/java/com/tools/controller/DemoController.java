package com.tools.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tools.service.DemoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Reference
    private DemoService demoService;

    @RequestMapping(value = "demo", method = RequestMethod.GET)
    @ResponseBody
    public String demo(){
        String str = "";
        List<String> permissions = demoService.getPermissions(10L);

        for (String s:permissions) {
            str += s + "+++++++++++++++++++++++++++";
        }
        return str;
    }
}
