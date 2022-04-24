package com.tuzhi.ucenter_service.service;

import com.tuzhi.ucenter_service.pojo.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuzhi.ucenter_service.pojo.vo.MemberForm;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-23
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //注册会员
    void register(MemberForm memberForm);

    //会员登录
    String login(MemberForm memberForm);

    //根据open查询会员表
    UcenterMember getMemberByOpenid(String openid);
}
