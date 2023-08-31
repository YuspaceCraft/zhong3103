package com.qf.controller;

import com.qf.config.HttpController;
import com.qf.entity.Test;
import com.qf.mapper.TestMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/test")
public class TestController extends HttpController<TestMapper, Test> {


}
