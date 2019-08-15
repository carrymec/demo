package com.example.demo.user.controller;


import com.example.demo.common.ServerResponse;
import com.example.demo.user.entity.Eauser;
import com.example.demo.user.service.IEauserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2019-08-14
 */
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
@RestController
@RequestMapping("/user/eauser")
public class EauserController {

    @Resource
    private IEauserService iEauserService;

    @GetMapping("/a")
    @ApiOperation(value = "查询用户" ,  notes="查询用户")
    public ServerResponse a() {
        List<Eauser> eausers = iEauserService.list();
        return ServerResponse.createBySuccess(eausers);
    }
}
