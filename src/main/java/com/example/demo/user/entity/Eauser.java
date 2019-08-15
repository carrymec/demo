package com.example.demo.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author chen
 * @since 2019-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Eauser对象", description="用户信息表")
public class Eauser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId("USERID")
    private String userid;

    @ApiModelProperty(value = "用户名")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty(value = "登陆帐户")
    @TableField("ACCOUNT")
    private String account;

    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "性别(0:未知;1:男;2:女)")
    @TableField("SEX")
    private String sex;

    @ApiModelProperty(value = "锁定标志(1:锁定;0:激活)")
    @TableField("LOCKED")
    private String locked;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "人员类型(1:经办员;2:管理员;3:系统内置人员;)")
    @TableField("USERTYPE")
    private String usertype;

    @ApiModelProperty(value = "启用状态")
    @TableField("ENABLED")
    private String enabled;


}
