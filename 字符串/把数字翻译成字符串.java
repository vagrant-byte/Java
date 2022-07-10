package 字符串;

public class 把数字翻译成字符串 {
    public int translateNum(int num) {
       if(num<=9) {
           return 1;
       }
       //获取输入数字的余数，然后递归的计算翻译方法
       int ba=num%100;
       if(ba<=9||ba>=26) {
           //如果小于等于9或者大于等于26，余数不能按照两位数拆分  如56，130
           return translateNum(num/10);
       }else {
           //ba=[0,25]可以按照两位数拆分 如100-125，200-225  1000-1025
           return translateNum(num/10)+translateNum(num/100);
       }
    }
}
