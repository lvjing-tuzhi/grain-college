package com.tuzhi.edu_service.controller;

import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-03-20 19:32
 **/

@Api("用户接口")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token", "token");
    }

    @ApiOperation("得到用户信息")
    @GetMapping("/info")
    public Result getInfo() {
        return Result.ok().data("roles", "admin").data("name", "admin").data("avatar", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2Ffb%2Fa2%2F69%2Ffba2696b9fa4120d758eba82c04f1aad.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650370176&t=e1ff9a8198ea2700a2a35c2f52caee43");
    }
}
