package com.chenxp.webdemo.Controller;

import com.chenxp.webdemo.Utils.JsonUtils;
import com.chenxp.webdemo.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @author: chenxp
 * @date: 2020/8/1416:54
 */
@Api(value = "测试返回数据")
@RestController
@RequestMapping("/value")
public class HelloController {

    @ApiOperation("获取测试返回String类型数据")
    @GetMapping("/string")
    public JsonUtils getHello() {
        int count = 0;
        System.out.println("count: " + count++);
        return JsonUtils.ok("HelloWorld");

    }
    @ApiOperation("测试有参数的接口swagger")
    @PostMapping("/sum")
    public JsonUtils getSum(@ApiParam @RequestParam(value = "integer1") int integer1,@ApiParam @RequestParam(value = "integer2") int integer2) {
        return JsonUtils.ok(integer1 + integer2);
    }

    @ApiOperation("测试有参数的接口2-swagger")
    @PostMapping("/object")
    public JsonUtils get(@ApiParam @RequestBody User user) {
        System.out.println("hhh");
        return JsonUtils.ok(user);
    }

}
