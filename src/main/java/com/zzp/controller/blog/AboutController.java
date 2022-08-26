package com.zzp.controller.blog;

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
 *@Date:2022/8/14 15:28
 */
@Controller
public class AboutController {

    @Autowired
    AboutService aboutService;

    @RequestMapping("/about")
    public String about(Model model){
        About about = aboutService.queryAbout();

        About about1 = new About();
        BeanUtils.copyProperties(about, about1);
        String content = about1.getContent();
        about1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        String content1 = about1.getContent();
        about1.setContent(EmojiUtil.emojiConverterUnicodeStr(content1));
        model.addAttribute("about", about1);
        return "blog/about";
    }

}
