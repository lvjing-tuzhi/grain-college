package com.tuzhi.ucenter_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuzhi.base_service.exceptionhandler.GuiException;
import com.tuzhi.ucenter_service.pojo.UcenterMember;
import com.tuzhi.ucenter_service.mapper.UcenterMemberMapper;
import com.tuzhi.ucenter_service.pojo.vo.MemberForm;
import com.tuzhi.ucenter_service.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuzhi.utilcommon.JwtUtils;
import com.tuzhi.utilcommon.MD5;
import com.tuzhi.utilcommon.result.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-23
 */
@Service
public class UcenterMemberServiceImp extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    //注册会员
    @Override
    public void register(MemberForm memberForm) {
        //获取注册的数据

        String mobile = memberForm.getMobile();
        String password = memberForm.getPassword();
        String code = memberForm.getCode();
        String nickname = memberForm.getNickname();

        //非空判断
        if (   StringUtils.isBlank(mobile)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(code)
                || StringUtils.isBlank(nickname)
        ){
            throw GuiException.from(ResultCode.REGISTER_ERROR);
        }

        //判断验证码
        //获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equalsIgnoreCase(redisCode)){
            throw GuiException.from(ResultCode.REGISTER_ERROR);
        }

        //判断手机号是否重复，表里面是否存在相同手机号不进行添加
        LambdaQueryWrapper<UcenterMember> memberQuery = new LambdaQueryWrapper<>();
        memberQuery.eq(UcenterMember::getMobile,mobile);

        int count = (int) this.count(memberQuery);
        if (count > 0){
            throw GuiException.from(ResultCode.REGISTER_ERROR);
        }

        //数据添加到数据库
        UcenterMember member = new UcenterMember();
        BeanUtils.copyProperties(memberForm,member);
        //密码需要做加密处理
        member.setPassword(MD5.encrypt(password));
        this.save(member);
    }

    //会员登录
    @Override
    public String login(MemberForm memberForm) {
        //获取手机和密码
        String mobile = memberForm.getMobile();
        String password = memberForm.getPassword();

        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)){
            throw GuiException.from(ResultCode.LOGIN_ERROR);
        }


        //判断手机号是否正确
        LambdaQueryWrapper<UcenterMember> memberQuery = new LambdaQueryWrapper<>();
        memberQuery.eq(UcenterMember::getMobile,mobile);

        UcenterMember member = this.getOne(memberQuery);

        //判断对象是否为空
        member = Optional.ofNullable(member).orElseThrow(() -> GuiException.from(ResultCode.LOGIN_ERROR));


        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据密码进行比较
        //加密方式 MD5
        if(password.equalsIgnoreCase(MD5.encrypt(password))){
            throw GuiException.from(ResultCode.LOGIN_ERROR);
        }

        //判断用户是否禁用
        if (member.getIsDisabled()){
            throw GuiException.from(ResultCode.LOGIN_ERROR);
        }

        //登录成功
        //生成token字符串，使用jwt字符串
        String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return jwtToken;
    }

    //根据open查询会员表
    @Override
    public UcenterMember getMemberByOpenid(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        UcenterMember ucenterMember = this.getOne(wrapper);
        return ucenterMember;
    }
}
