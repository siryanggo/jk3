package com.yang.jk.controller.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.common.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.function.Function;

/**
 * @auther yhjStart
 * @create 2022-03-27 23:15
 */
@Validated
public abstract class baseController <V,P>{
    protected abstract Function<V,P> func();
    protected abstract IService<P> getService();

    @PostMapping("/save")
    @ApiOperation("添加或更新")
    public R saveOrupdate(@Valid V v) {
        P apply = func().apply(v);
        boolean flag = getService().saveOrUpdate(apply);
        return flag?R.ok():R.error();
    }
    @GetMapping("/removeByIds")
    @ApiOperation("删除,id参数字符串拼接")
    public R removeById(@NotBlank(message = "ids不能为空") @RequestParam String Ids) {
        String[] split = Ids.split(",");
        boolean flag = getService().removeByIds(Arrays.asList(split));
        return flag?R.ok():R.error();
    }

}
