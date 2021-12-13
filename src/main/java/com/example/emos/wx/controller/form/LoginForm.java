package com.example.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/*
 * @ClassName:LoginForm
 * @descript:
 * @author: song
 * @date:2021/12/7
 */
@ApiModel
@Data
public class LoginForm {
    @NotBlank(message = "临时授权不能为空")
    private String code;
}
