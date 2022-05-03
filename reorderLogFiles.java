import java.util.Arrays;
import java.util.Comparator;

public class reorderLogFiles {
    //重新排列日志文件
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //将标识符分开
                String[] split1=o1.split(" ",2);
                String[] split2=o2.split(" ",2);
                //判断除标识符的第一个是数字返回true
                boolean isDigit1=Character.isDigit(split1[1].charAt(0));
                boolean isDigit2=Character.isDigit(split2[1].charAt(0));
                //两个都是字母
                if(!isDigit1&&!isDigit2) {
                    //比较内容split1>split2则返回1，等于返0，小于返-1
                    int cmp=split1[1].compareTo(split2[1]);
                    if(cmp!=0) {
                        return cmp;
                    }
                    return split1[0].compareTo(split2[0]);
                }
                //split1 split2都是数字位置不变返回0，split1是数字返回1 split1>split2,从小到大排序split2在前
                return isDigit1?(isDigit2?0:1):-1;
            }
        });
        return logs;

    }

}
