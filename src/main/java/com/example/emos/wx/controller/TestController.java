package com.example.emos.wx.controller;


import com.example.emos.wx.common.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/test")
@Api("测试web接口")
public class TestController {
    @PostMapping("/sayhello")
    @ApiOperation(value = "简单的测试方法")
    public R sayHello(@Valid @RequestBody TestSayHelloForm form){
        return R.ok().put("message",form.getName());
    }
}