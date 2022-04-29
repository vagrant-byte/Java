public class minimumSwap {
    //交换字符使得字符串相等
   // 最后一句的解释，x+y为奇数，说明不可能
//     1.若x和y都为偶数
//     例如：
//     XX YY YY
//     YY XX XX 就会进行(x+y)/2次交换，如示例一
//     +1 +1 +1

//     2.若x和y都为奇数
//     XX X YYYY Y
//     YY Y XXXX X 就会进行(x-1)/2+(y-1)/2+2=(x+y)/2+1
//        |______|
//          相连

    //     二者均可写作(x+1)/2+(y+1)/2;
    public int minimumSwap(String s1, String s2) {
        int x=0;
        int y=0;
        for (int i = 0; i <s1.length() ; i++) {
            if(s1.charAt(i)!=s2.charAt(i)) {
                if(s1.charAt(i)=='x') {
                    x++;
                } else {
                    y++;
                }
            }
        }
        return (x+y)%2==1?-1:(x+1)/2+(y+1)/2;

    }
}
