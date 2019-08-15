package com.example.demo.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import com.example.demo.common.ServerResponse;
import com.example.demo.exception.ApiExceptionCode;
import com.example.demo.exception.MyRuntimeException;
import com.example.demo.system.entity.SysUser;
import com.example.demo.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2019-08-15
 */
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
@RestController
@RequestMapping("/api/system/sys-user")
public class SysUserController {

    @Resource
    private ISysUserService iSysUserService;

    @GetMapping("/list")
    @ApiOperation(value = "条件查询（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = ApiDataType.INT, paramType = ApiParamType.QUERY),
            @ApiImplicitParam(name = "size", value = "每页数据", dataType = ApiDataType.INT, paramType = ApiParamType.QUERY),
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY)
    })
    public ServerResponse listAll(Page page, String keyword) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>(new SysUser());
        wrapper.isNotNull(StringUtils.isNotBlank(keyword), "user_name");
        wrapper.orderByAsc("user_id");
        IPage<SysUser> users = iSysUserService.page(page, wrapper);
        return ServerResponse.createBySuccess(users);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口（DONE）")
    public ServerResponse addUser(@RequestBody SysUser user) {
        boolean b = iSysUserService.save(user);
        if (b) {
            return ServerResponse.createBySuccess(user);
        }
        throw new MyRuntimeException(ApiExceptionCode.DATA_IS_NULL.getValue(), ApiExceptionCode.DATA_IS_NULL.getDesc());
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改接口（DONE）")
    public ServerResponse update(@RequestBody SysUser user, @PathVariable Integer id) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>(new SysUser());
        wrapper.eq("user_id", id);
        boolean b = iSysUserService.update(user, wrapper);
        if (b) {
            return ServerResponse.createBySuccess(user);
        }
        throw new MyRuntimeException(ApiExceptionCode.DATA_IS_NULL.getValue(), ApiExceptionCode.DATA_IS_NULL.getDesc());
    }

    @ApiOperation(value = "主键查询（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户编号", dataType = ApiDataType.INT, paramType = ApiParamType.PATH),
    })
    @GetMapping("/getById/{id}")
    public ServerResponse getOne(@PathVariable Integer id) {
        SysUser user = iSysUserService.getById(id);
        return ServerResponse.createBySuccess(user);
    }


}
