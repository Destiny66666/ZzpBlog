package com.zzp.controller.admin;

import com.zzp.pojo.About;
import com.zzp.service.AboutService;
import com.zzp.util.EmojiUtil;
import com.zzp.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Author:zzp
 *@Date:2022/8/18 14:04
 */
@Controller
@RequestMapping("/admin")
public class AdminAboutController {

    @Autowired
    AboutService aboutService;

    @RequestMapping("/about")
    public String adminAbout(Model model){
        About about = aboutService.queryAbout();

        About about1 = new About();
        BeanUtils.copyProperties(about, about1);
        String content = about1.getContent();
        about1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        String content1 = about1.getContent();
        about1.setContent(EmojiUtil.emojiConverterUnicodeStr(content1));
        model.addAttribute("about", about1);

        return "admin/about";
    }


    @RequestMapping("/aboutupdate")
    public String adminAboutUpdate(Model model){

        About about = aboutService.queryAbout();

        model.addAttribute("about",about);

        return "admin/about-update";

    }

    @RequestMapping("/toaboutupdate")
    public String toAdminAboutUpdate(About about, Model model){
        about.setId(1);
        about.setContent(EmojiUtil.emojiConverterToAlias(about.getContent()));
        int i = aboutService.updateAbout(about);
        if (i > 0){
            model.addAttribute("msg","修改成功！");
        }else{
            model.addAttribute("msg","修改失败！");
        }
        return "redirect:/admin/about";
    }

}
