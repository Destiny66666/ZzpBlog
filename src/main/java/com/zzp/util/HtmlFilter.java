package com.zzp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *@Author:zzp
 *@Date:2022/8/19 16:56
 *@Describe:HTML标签过滤类
 */
public class HtmlFilter {

    public static String HTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        String regEx_blank="&nbsp;|&quot;|&amp;|&lt;|&gt;";//定义html字符&nbsp;|&quot;|&amp;|&lt;|&gt;

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        Pattern p_blank=Pattern.compile(regEx_blank,Pattern.CASE_INSENSITIVE);
        Matcher m_blank=p_blank.matcher(htmlStr);
        htmlStr=m_blank.replaceAll(""); //过滤;nbsp

        return htmlStr.trim(); //返回文本字符串
    }

}
