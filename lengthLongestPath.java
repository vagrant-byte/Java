import java.util.HashMap;

public class lengthLongestPath {
    //文件的最长绝对路径
    public static int lengthLongestPath(String input) {
        //用来存放其层级和字符串
        HashMap<Integer,String> map=new HashMap<>();
        int n=input.length();
        //返回结果
        String ans=null;
        for (int i = 0; i <n ; ) {
            //用来表示层级
            int level=0;
            //使用/t进行分层
            while (i<n&&input.charAt(i)=='\t'&&++level>=0) {
                i++;
            }
            int j=i;
            //用来标记当前是目录还是文件
            boolean isDir=true;
            while (j<n&&input.charAt(j)!='\n') {
                //当前是文件
                if(input.charAt(j++)=='.') {
                    isDir=false;
                }
            }
            //获取当前层级的字符串
            String cur=input.substring(i,j);
            //获取上一层集的字符串
            String prev=map.getOrDefault(level-1,null);
            //如果上一层集为空则返回当前的文件否则对上层文件与当前文件进行拼接
            String path=prev==null?cur:prev+"/"+cur;
            if(isDir) {
                //当前是目录加入map中
                map.put(level,path);
            } else if(ans==null||path.length()>ans.length()) {
                //不是目录与最长的字符串进行比较
                ans=path;
            }
            //继续遍历下一层
            i=j+1;
        }
        return ans==null?0:ans.length();
    }

    public static void main(String[] args) {
        String S="dir/n/sb/n/t/tt/gggg.txt";
        System.out.println(lengthLongestPath(S));
    }
}
