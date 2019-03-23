package com.hc.bean.normalizer;

public class NumberUtil {

    public static String numberToChinese(int num){//转化一个阿拉伯数字为中文字符串
        if(num == 0){
            return "零";
        }
        int unitPos = 0;//节权位标识
        String all = new String();
        String chineseNum = new String();//中文数字字符串
        boolean needZero = false;//下一小结是否需要补零
        String strIns = new String();
        while(num>0){
            int section = num%10000;//取最后面的那一个小节
            if(needZero){//判断上一小节千位是否为零，为零就要加上零
                all = Tool.chnNumChar[0] + all;
            }
            chineseNum = sectionTOChinese(section,chineseNum);//处理当前小节的数字,然后用chineseNum记录当前小节数字
            if( section!=0 ){//此处用if else 选择语句来执行加节权位
                strIns = Tool.chnUnitSection[unitPos];//当小节不为0，就加上节权位
                chineseNum = chineseNum + strIns;
            }else{
                strIns = Tool.chnUnitSection[0];//否则不用加
                chineseNum = strIns + chineseNum;
            }
            all = chineseNum+all;
            chineseNum = "";
            needZero = (section<1000) && (section>0);
            num = num/10000;
            unitPos++;
        }

        if(all.startsWith("一十")){
            all = all.substring(1,all.length());
        }
        return all;
    }
    public static String sectionTOChinese(int section,String chineseNum){
        String setionChinese = new String();//小节部分用独立函数操作
        int unitPos = 0;//小节内部的权值计数器
        boolean zero = true;//小节内部的制零判断，每个小节内只能出现一个零
        while(section>0){
            int v = section%10;//取当前最末位的值
            if(v == 0){
                if( !zero ){
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个
                    chineseNum = Tool.chnNumChar[0] + chineseNum;
                }
            }else{
                zero = false;//有非零的数字，就把制零开关打开
                setionChinese = Tool.chnNumChar[v];//对应中文数字位
                setionChinese = setionChinese + Tool.chnUnitChar[unitPos];//对应中文权位
                chineseNum = setionChinese + chineseNum;
            }
            unitPos++;
            section = section/10;
        }

        return chineseNum;
    }


    public static int chineseToNumber(String str){
        String str1 = new String();
        String str2 = new String();
        String str3 = new String();
        int k = 0;
        boolean dealflag = true;
        for(int i=0;i<str.length();i++){//先把字符串中的“零”除去
            if('零' == (str.charAt(i))){
                str = str.substring(0, i) + str.substring(i+1);
            }
        }
        String chineseNum = str;
        for(int i=0;i<chineseNum.length();i++){
            if(chineseNum.charAt(i) == '亿'){
                str1 = chineseNum.substring(0,i);//截取亿前面的数字，逐个对照表格，然后转换
                k = i+1;
                dealflag = false;//已经处理
            }
            if(chineseNum.charAt(i) == '万'){
                str2 = chineseNum.substring(k,i);
                str3 = str.substring(i+1);
                dealflag = false;//已经处理
            }
        }
        if(dealflag){//如果没有处理
            str3 =  chineseNum;
        }
        int result = sectionChinese(str1) * 100000000 +
                sectionChinese(str2) * 10000 + sectionChinese(str3);
        return result;
    }

    public static int sectionChinese(String str){
        int value = 0;
        int sectionNum = 0;
        for(int i=0;i<str.length();i++){
            int v = (int) Tool.intList.get(str.charAt(i));
            if( v == 10 || v == 100 || v == 1000 ){//如果数值是权位则相乘
                sectionNum = v * sectionNum;
                value = value + sectionNum;
            }else if(i == str.length()-1){
                value = value + v;
            }else{
                sectionNum = v;
            }
        }
        return value;
    }
}
