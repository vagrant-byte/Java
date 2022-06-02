package 字符串;
//左旋字符串
public class reverseLeftWords {
//    public String reverseLeftWords(String s, int n) {
//        if(s==null||n==0) {
//            return s;
//        }
//        n%=s.length();//去重
//        char[] chars=s.toCharArray();
//        for (int i = 0; i <n ; i++) {
//            LeftRotateStringOne(chars);
//        }
//        return new String(chars);
//    }
//
//    private void LeftRotateStringOne(char[] chars) {
//        int size=chars.length;
//        char cur=chars[0];
//        for (int i = 0; i <size-1 ; i++) {
//            chars[i]=chars[i+1];
//        }
//        chars[size-1]=cur;
//    }
    public String reverseLeftWords(String s, int n) {
        if(s.length()==0||n==0) {
            return s;
        }
        char[] chars=s.toCharArray();
        swap(chars,0,n-1);
        swap(chars,n,chars.length-1);
        swap(chars,0,chars.length-1);
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        while (i<j) {
            char tmp=chars[i];
            chars[i]=chars[j];
            chars[j]=tmp;
            i++;
            j--;
        }
    }
}
