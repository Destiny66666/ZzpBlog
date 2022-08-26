package com.zzp.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.pojo.MessageBoard;
import com.zzp.service.MessageBoardService;
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
 *@Date:2022/8/17 21:08
 */
@Controller
@RequestMapping("/admin")
public class AdminMessageBoardController {

    @Autowired
    MessageBoardService messageBoardService;

    @RequestMapping("/messageboard")
    public String adminMessageBoard(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        //设置第几页和每页多少条数据
        PageHelper.startPage(pageNum, 10);
        List<MessageBoard> messageBoardList = messageBoardService.queryMessageBoardAll();
        PageInfo<MessageBoard> pageInfo = new PageInfo<>(messageBoardList);
        List<MessageBoard> list = pageInfo.getList();
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("messageBoardList",list);
        return "admin/messageboard";
    }

    @RequestMapping("/messageboardelete/{id}")
    public String adminMessageBoardDelete(@PathVariable("id") int id, RedirectAttributes attributes){
        int i = messageBoardService.deleteMessageBoardById(id);
        if (i > 0){
            attributes.addFlashAttribute("msg","评论删除成功");
            return "redirect:/admin/messageboard";
        }else{
            attributes.addFlashAttribute("msg","评论删除失败");
            return "redirect:/admin/messageboard";
        }

    }

}
