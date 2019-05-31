package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by
 * kim on 2019/5/27.
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference
    ItemCatService itemCatService;


    @RequestMapping("findByParentId")
    public List<TbItemCat> findByParentId(long parentId){

        return itemCatService.findByParentId(parentId);
    }

    /**
     * 修改
     * @param itemCat
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbItemCat itemCat){
        try {
            itemCatService.update(itemCat);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            return new Result(false, "修改失败");
        }
    }

}
