package com.tuzhi.ucenter_service.controller;

import com.tuzhi.ucenter_service.pojo.UcenterMember;
import com.tuzhi.ucenter_service.pojo.vo.MemberForm;
import com.tuzhi.ucenter_service.service.UcenterMemberService;
import com.tuzhi.utilcommon.JwtUtils;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-23
 */

@Api(tags = "会员操作接口")
@RestController
@RequestMapping("/eduservice/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation("注册会员")
    @PostMapping("/register")
    public Result register(@RequestBody MemberForm memberForm){
        ucenterMemberService.register(memberForm);
        return Result.ok();
    }

    @ApiOperation("登录会员")
    @PostMapping("/login")
    public Result login(@RequestBody MemberForm memberForm){
        //memberForm 对象封装手机号和密码

        //返回token 值,使用jwt生成
        String token = ucenterMemberService.login(memberForm);

        return Result.ok().data("token",token);
    }

    //根据token获取用户信息
    @ApiOperation("根据token获得会员信息")
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);
        return Result.ok().data("userInfo",member);
    }

}
