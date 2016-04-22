package com.example.macyaren.sportman.helper;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by hennzr on 2016/3/9.
 */
public class PingYinTool {

	HanyuPinyinOutputFormat hanyuPinyinOutputFormat = null;

	public static enum Type {
		LOWERCASE, //全部小写
		UPPERCASE  //全部大写
	}

	public PingYinTool() {
		hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
		hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}

	/*
	* 重载方法之一：无分隔符方法
	* */
	public String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination {
		return toPinYin(str, "", Type.UPPERCASE);
	}

	/*
	* 重载方法之二：自定义分隔符方法
	* */
	public String toPinYin(String str, String spera) throws BadHanyuPinyinOutputFormatCombination {
		return toPinYin(str, spera, Type.UPPERCASE);
	}

	/*
	* 重载方法之三：汉字转换成拼音最终阶段
	* */
	public String toPinYin(String str, String spera,
						   Type type) throws BadHanyuPinyinOutputFormatCombination {
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		if (type == Type.UPPERCASE) {
			hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		} else {
			hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		}
		String spell = "";
		String temp = "";
		String[] t;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((int) c <= 128) {
				spell += c;
			} else {
				t = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat);
				if (t == null) {
					spell += c;
				} else {
					temp = t[0];
					spell += temp + (i == str.length() - 1 ? "" : spera);
				}
			}
		}
		return spell.trim();
	}

}
