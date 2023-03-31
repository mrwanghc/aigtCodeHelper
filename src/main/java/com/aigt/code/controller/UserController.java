package com.aigt.code.controller;

import com.aigt.code.entity.User;
import com.aigt.code.service.UserService;
import com.aigt.code.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询列表
     */
    @GetMapping("/list")
    public R list(@RequestBody @Valid User user) {
        return R.ok(userService.getUserList(user));
    }


    /**
     * 获取详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return null;
    }

    /**
     * 新增
     */
    @PostMapping
    public R add(@RequestBody User user)
    {
        return null;
    }

    /**
     * 修改
     */
    @PutMapping
    public R edit(@RequestBody User user)
    {
        return null;
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        return null;
    }
}
