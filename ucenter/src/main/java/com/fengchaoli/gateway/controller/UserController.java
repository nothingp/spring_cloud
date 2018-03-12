package com.fengchaoli.gateway.controller;

import com.fengchaoli.gateway.dto.UserDto;
import com.fengchaoli.gateway.form.NotifyForm;
import com.fengchaoli.gateway.form.UserForm;
import com.fengchaoli.gateway.model.SecurityUser;
import com.fengchaoli.gateway.model.User;
import com.fengchaoli.gateway.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected UserService userService;

    @GetMapping("/api/users/current")
    public UserDto user(OAuth2Authentication currentUser) {
        String userId = ((SecurityUser)currentUser.getPrincipal()).getUserId();
        User user = userService.getOne(userId);
        return modelMapper.map(user, UserDto.class);
    }

    @PostMapping("/api/users/sync")
    public UserDto insert(@Valid UserForm userForm,OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        User user = userService.insert(userForm,client);
        return modelMapper.map(user, UserDto.class);
    }

    @PutMapping("/api/users/sync/{id}")
    public UserDto udpate(@PathVariable("id")String id, UserForm userForm,OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        User user = userService.update(id,userForm,client);
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * 账号、企业信息下发
     * @param notifyForm
     * @param currentUser
     * @return
     */
    @PostMapping("/api/users/notify")
    public UserDto notify(@Valid @ModelAttribute NotifyForm notifyForm, OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        User user = userService.notify(notifyForm,client);
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * 通过数据库同步数据
     * @param currentUser
     */
    @PostMapping("/api/users/notifyByDb")
    public void notifyByDb(OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        userService.notifyByDb(client);
    }
}