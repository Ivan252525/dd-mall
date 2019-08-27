package com.dd.manager.modules.product.controller;

import com.dd.common.web.BaseResult;
import com.dd.common.web.ResultUtils;
import com.dd.manager.modules.product.entity.ProductEntity;
import com.dd.manager.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 产品表
 *
 * @author lihj
 * @email 
 * @date 2019-04-12 21:57:30
 */
@RestController
@RequestMapping("/openapi/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    /**
     * 列表
     */
    @GetMapping("/list")
    public BaseResult<List<ProductEntity>> list(@RequestParam Map<String, Object> params){
        return ResultUtils.success(productService.selectList(params));
    }

}
