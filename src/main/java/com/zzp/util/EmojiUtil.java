package com.zzp.util;

import com.github.binarywang.java.emoji.EmojiConverter;

/**
 *
 *@Author:zzp
 *@Date:2022/8/19 15:43
 *@Describe:表情处理类
 */
public final class EmojiUtil {
 
  private static EmojiConverter emojiConverter = EmojiConverter.getInstance();
  
  /**
   * 将emojiStr转为 带有表情的字符
   * @param emojiStr
   * @return
   */
  public static String emojiConverterUnicodeStr(String emojiStr){
     String result = emojiConverter.toUnicode(emojiStr);
     return result;
  }
  
  /**
   * 带有表情的字符串转换为编码
   * @param str
   * @return
   */
  public static String emojiConverterToAlias(String str){
    String result=emojiConverter.toAlias(str);
    return result;
  }
  
}