package com.zzp.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.pojo.Type;
import com.zzp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 20:48
 */
@Controller
@RequestMapping("/admin")
public class AdminTypesController {

    @Autowired
    TypeService typeService;

    @RequestMapping("/types")
    public String adminTypes(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){

        //设置第几页和每页多少条数据
        PageHelper.startPage(pageNum, 10);

        List<Type> typeList = typeService.queryTypeAll();
        //查询的结果进行分页
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        List<Type> list = pageInfo.getList();
        model.addAttribute("types",list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";

    }

    @RequestMapping("/typesinsert")
    public String adminTypesInsert(){
        return "admin/types-insert";
    }

    @RequestMapping("/totypesinsert")
    public String toAdminTypesInsert(@RequestParam("name") String name, Model model, RedirectAttributes attributes){
        Type type = new Type();
        Type type1 = typeService.queryTypeByName(name);
        if (type1 == null){
            type.setName(name);
            typeService.addType(type);
            attributes.addFlashAttribute("msg","类别名称添加成功！");
            return "redirect:/admin/types";
        }else {
            model.addAttribute("msg","类别名称已存在，请重新输入！");
            return "admin/types-insert";
        }
    }

    @RequestMapping("/typesdelete/{id}")
    public String adminTypeDelete(@PathVariable("id") int id, RedirectAttributes attributes){
//        int i = typeService.deleteTypeById(id);
//        if (i  > 0){
//            attributes.addFlashAttribute("msg","类别名称删除成功！");
//            return "redirect:/admin/types";
//        }else{
//            attributes.addFlashAttribute("msg","类别名称删除失败！");
//            return "redirect:/admin/types";
//        }
        attributes.addFlashAttribute("msg","傻逼吧！标签不让删，只是存在这个按钮而已！");
        return "redirect:/admin/types";

    }


    @RequestMapping("/typesupdate/{id}")
    public String adminTypeUpdate(@PathVariable("id") int id,Model model){
        Type type = typeService.queryTypeById(id);
        model.addAttribute("type",type);
        return "admin/types-update";
    }

    @RequestMapping("/totypesupdate/{id}")
    public String toAdminTypeUpdate(@PathVariable("id") int id,@RequestParam("name") String name, Model model, RedirectAttributes attributes){
        Type type = new Type();
        Type type1 = typeService.queryTypeByName(name);
        if (type1 == null){
            type.setId(id);
            type.setName(name);
            typeService.updateTypeById(type);
            attributes.addFlashAttribute("msg","ID:"+id+"类别名称修改成功！");
            return "redirect:/admin/types";
        }else {
            Type type2 = typeService.queryTypeById(id);
            model.addAttribute("type",type2);
            model.addAttribute("msg","类别名称已存在，请重新输入！");
            return "admin/types-update";
        }
    }

}
