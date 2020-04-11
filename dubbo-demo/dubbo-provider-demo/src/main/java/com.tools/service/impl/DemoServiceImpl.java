package com.tools.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tools.service.DemoService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component//不管是注解还是xml都需要加
@Service//注解的时候加
public class DemoServiceImpl implements DemoService {
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }
}
