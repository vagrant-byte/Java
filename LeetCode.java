import com.sun.javafx.image.impl.BaseByteToIntConverter;

import java.io.CharArrayWriter;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.*;
 class ListNode {
     //矩阵中的幸运数字
     public List<Integer> luckyNumbers (int[][] matrix) {
         List<Integer> list1=new ArrayList<>();
         for (int i = 0; i <matrix.length ; i++) {
             //行的最小值
             int cMin=matrix[i][0];
             //最小值下标
             int index=0;
             for (int j = 1; j <matrix[i].length ; j++) {
                 if(cMin>matrix[i][j]) {
                     cMin=matrix[i][j];
                     index=j;
                 }
             }
             int rMax=matrix[0][index];
             for (int j = 0; j <matrix.length ; j++) {
                 if(rMax<matrix[j][index]) {
                     rMax=matrix[j][index];
                 }
             }
             if(rMax==cMin) {
                 list1.add(cMin);
             }
         }
         return list1;

     }
     //有序数组中单一的元素
     public static int singleNonDuplicate(int[] nums) {
         int res=0;
         for (int i = 0; i <nums.length ; i++) {
             res^=nums[i];
         }
         return res;
//         if(nums.length==1) {
//             return nums[0];
//         }
//         if(nums[0]!=nums[1]) {
//             return nums[0];
//         }
//         for (int i = 1; i <=nums.length-2 ; i++) {
//             if(nums[i]!=nums[i-1]&&nums[i]!=nums[i+1]) {
//                 return nums[i];
//             }
//         }
//         if(nums[nums.length-1]!=nums[nums.length-2]) {
//             return nums[nums.length-2];
//         }
//         return -1;
     }
     public static void main(String[] args) {
         int[] nums={3,3,7,7,10,11,11,8,8};
         int min=singleNonDuplicate(nums);
         System.out.println(min);
     }
     //“气球”的最大数量
     public int maxNumberOfBalloons(String text) {
         int b=0;
         int a=0;
         int l=0;
         int o=0;
         int n=0;
         char[] chars=text.toCharArray();
         for (int i = 0; i <chars.length ; i++) {
             if(chars[i]=='a') {
                 a++;
             } else if(chars[i]=='b') {
                 b++;
             } else if(chars[i]=='l') {
                 l++;
             } else if(chars[i]=='o') {
                 o++;
             } else if(chars[i]=='n') {
                 n++;
             }
         }
         int count1= Math.min(Math.min(b,a),n);
         int count2=Math.min(l/2,o/2);
         return Math.min(count1,count2);


     }
     //飞地的数量
     public int numEnclaves(int[][] grid) {
         int m=grid.length;
         int n=grid[0].length;
         for(int i=0;i<m;i++) {
             for (int j = 0; j <n ; j++) {
                 //将其边界的值的上下左右为1的都置为0
                 if((i==0||j==0||i==m-1||j==n-1)&&grid[i][j]==1) {
                     dfs(grid,i,j);
                 }
             }
         }
         int count=0;
         for (int i = 0; i <m ; i++) {
             for (int j = 0; j <n ; j++) {
                 if(grid[i][j]==1) {
                     count++;
                 }
             }
         }
         return count;
     }

     private void dfs(int[][] grid, int i, int j) {
         if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
             return;
         }
         grid[i][j]=0;
         dfs(grid,i+1,j);
         dfs(grid,i-1,j);
         dfs(grid,i,j+1);
         dfs(grid,i,j-1);
     }

     //学生分数的最小差值
     public static int minimumDifference(int[] nums, int k) {
         if(k==1) {
             return 0;
         }
         Arrays.sort(nums);
         int num= Integer.MAX_VALUE;
         for (int i = 0; i <=nums.length-k ; i++) {
             int tmp=nums[i+k-1]-nums[i];
             if(tmp>=0&&tmp<num) {
                 num=tmp;
             }
         }
         return num;
     }

     //最简分数
     public static List<String> simplifiedFractions(int n) {
         List<String> list=new ArrayList<>();
         if(n==1) {
             return list;
         }
         for (int i = 2; i <=n ; i++) {
             list.add("1"+"/"+i);
             for (int j = 2; j <i ; j++) {
                 if(gcb(i,j)==1) {
                     list.add(j+"/"+i);
                 }
             }
         }
         return list;
     }
     public static int gcb(int a,int b) {
         if(a%b==0) {
             return b;
         }
         return gcb(b,a%b);
     }

     //差的绝对值为K的数对数目
     public static int countKDifference(int[] nums, int k) {
         int count=0;
         for (int i = 0; i <nums.length ; i++) {
             for (int j = i+1; j <nums.length ; j++) {
                 if(nums[j]-nums[i]==k||nums[i]-nums[j]==k) {
                     count++;
                 }
             }
         }
         return count;
     }
     //唯一元素的和
     public static int sumOfUnique(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
         for (int i = 0; i <nums.length ; i++) {
             if(map.containsKey(nums[i])) {
                 map.put(nums[i],map.get(nums[i])+1);
             } else {
                 map.put(nums[i],1);
             }
         }
         int sum=0;
         for (int i = 0; i <nums.length ; i++) {
             if(map.get(nums[i])==1) {
                 sum+=nums[i];
             }
         }
         return sum;
     }

     //黄金矿工
     int ans=0;
     int grid[][];
     public int getMaximumGold(int[][] grid) {
         this.grid=grid;
         for(int i=0;i<grid.length;i++){for(int j=0;j<grid[0].length;j++){if(grid[i][j]>0){getGold(i,j,0);}}}
         return ans;
     }
     public void getGold(int i,int j,int gold){
         if(i==-1||j==-1||i==grid.length||j==grid[0].length||grid[i][j]==0){return;}
         int g=grid[i][j];
         gold+=g;
         grid[i][j]=0;
         ans=Math.max(ans,gold);
         getGold(i+1,j,gold);
         getGold(i-1,j,gold);
         getGold(i,j+1,gold);
         getGold(i,j-1,gold);
         gold-=g;
         grid[i][j]=g;
     }
     //可以形成最大正方形的矩形数目
     public static int countGoodRectangles(int[][] rectangles) {
         int maxlen=0;
         int count=0;
         for (int i = 0; i <rectangles.length ; i++) {
             if(rectangles[i][0]<rectangles[i][1]) {
                 int tmp=rectangles[i][0];
                 rectangles[i][0]=rectangles[i][1];
                 rectangles[i][1]=tmp;
             }
             maxlen=Math.max(maxlen,rectangles[i][1]);
         }
         for (int i = 0; i <rectangles.length ; i++) {
             if(maxlen==rectangles[i][1]) {
                 count++;
             }
         }
         return count;
     }

         //和为K的最少斐波那契数字数目
     public int fib(int n) {
         if(n==0) {
             return 0;
         }
         int a=1;
         int b=1;
         int c=0;
         while (n>=c) {
             c=a+b;
             a=b;
             b=c;
         }
         return a;
     }
     public int findMinFibonacciNumbers(int k) {
         int res=0;
         while (k>0) {
             int a=fib(k);
             k-=a;
             res++;
         }
         return res;

     }
     //反转单词前缀
     public static String reversePrefix(String word, char ch) {
         String res="";
         char[] chars=word.toCharArray();
         int tmp=0;
         for (int i = 0; i <chars.length ; i++) {
             if(chars[i]==ch) {
                 tmp=i;
                 for(int j=i;j>=0;j--) {
                     res+=chars[j];
                 }
                 break;
             }
         }
         if(tmp==0) {
             return word;
         } else {
             res+=word.substring(tmp+1,word.length());
             return res;
         }
     }

     //最长的美好子字符串
     public String longestNiceSubstring(String s) {
         int n=s.length();
         int left=0;
         int right=0;
         for(int i=0;i<n;i++) {
             for(int j=i+1;j<=n;j++) {
                 if(isNice(s.substring(i,j))&&j-i>right-left) {
                     left=i;
                     right=j;
                 }
             }
         }
         return s.substring(left,right);
     }
     private boolean isNice(String str){

         boolean[] flag = new boolean[100];

         for(char c:str.toCharArray()){
             flag[c-'A'] = true;
         }

         for(char c:str.toCharArray()){
             if(!(c >= 'a' && flag[c-'a'] || c <= 'Z' && flag[c-'A' + 32])){
                 return false;
             }
         }

         return true;
     }

     //将数字变成0的操作数
     public static int numberOfSteps(int num) {
         int count=0;
         while (num>0) {
             if(num%2==0) {
                 num=num/2;
             } else {
                 num--;
             }
             count++;

         }
         return count;
     }

     public static void main7(String[] args) {
         int num=123;
         int n=numberOfSteps(num);
         System.out.println(n);
     }
     //游戏中弱角色的数量
     public int numberOfWeakCharacters(int[][] properties) {
         Arrays.sort(properties,(((o1, o2) -> o1[0]==o2[0]?o1[1]-o2[1]:o2[1]-o1[1])));
         int count=0;
         int tmp=properties[0][1];
         for (int i = 1; i <properties.length ; i++) {
             if(properties[i][1]<tmp) {
                 count++;
                 continue;
             }
             tmp=Math.max(tmp,properties[i][1]);
         }
         return count;
     }
     //句子中的有效单词数
     public static int countValidWords(String sentence) {
         if(sentence==null||sentence.length()==0) {
             return 0;
         }
         String[] tmp=sentence.split(" ");
         int count=0;
         for (int i = 0; i <tmp.length ; i++) {
             if(!tmp[i].equals("")) {
                 if(isToken(tmp[i])) {
                     count++;
                 }
             }
         }
         return count;
     }

     private static boolean isToken(String s) {
         boolean Hyphen=false;
         char[] chars=s.toCharArray();
         for (int i = 0; i <chars.length ; i++) {
             if(chars[i]>='0'&&chars[i]<='9') {
                 return false;
             } else if(chars[i]=='-') {
                 if(Hyphen || i==0 ||i==chars.length-1) {
                     return false;
                 }
                 if(chars[i-1]<'a'||chars[i-1]>'z'||chars[i+1]<'a'||chars[i+1]>'z') {
                     return false;
                 }
                 Hyphen=true;
             } else if(chars[i]=='!'||chars[i]=='.'||chars[i]==',') {
                 if(i!=chars.length-1) {
                     return false;
                 }
             }

         }
         return true;
     }

     public static void main8(String[] args) {
         String s="this and dog";
         System.out.println(countValidWords(s));
     }

     //比赛中的配对次数
     public static int numberOfMatches(int n) {
         int count=0;
         while (n>1) {
             count+=n/2;
             n=((n%2==1)?n/2+1:n/2);
         }
         return count;
        // return n-1;
     }

     public static void main6(String[] args) {
         int n=14;
         System.out.println(numberOfMatches(n));
     }
     //股票价格波动
     public HashMap<Integer,Integer> map;
     public TreeMap<Integer,Integer> prices;
     int max_t;
     public void StockPrice() {
         map=new HashMap<>();
         prices=new TreeMap<>();
         max_t=0;
     }

     public void update(int timestamp, int price) {
         max_t=Math.max(timestamp,max_t);
         Integer n=map.get(timestamp);
         if(n!=null) {
             int num=prices.get(n);
             if(num==1) {
                 prices.remove(n);
             } else {
                 prices.put(n,num-1);
             }
         }
         prices.put(price,prices.getOrDefault(price,0)+1);
         map.put(timestamp,price);
     }

     public int current() {
         return map.get(max_t);
     }

     public int maximum() {
         return prices.lastKey();

     }

     public int minimum() {
         return prices.firstKey();

     }
//     public static HashMap<Integer,Integer> map;
//     public void StockPrice() {
//         map=new HashMap<>();
//     }
//
//     public void update(int timestamp, int price) {
//         if(map.containsKey(timestamp)) {
//             map.put(timestamp,price);
//         } else {
//             map.put(timestamp,price);
//         }
//     }
//
//     public int current() {
//         int max=Integer.MIN_VALUE;
//         for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
//             if(max<entry.getKey()) {
//                 max=entry.getKey();
//             }
//         }
//         return map.get(max);
//     }
//
//     public int maximum() {
//         int max=Integer.MIN_VALUE;
//         for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
//             if(max<entry.getValue()) {
//                 max=entry.getValue();
//             }
//         }
//         return max;
//
//     }
//
//     public int minimum() {
//         int min=Integer.MAX_VALUE;
//         for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
//             if(min>entry.getValue()) {
//                 min=entry.getValue();
//             }
//         }
//         return min;
//
//     }
     //删除回文子序列
     public int removePalindromeSub(String s) {
         if(s==null||s.length()==0) {
             return 0;
         }
         if(palindrome(s)) {
             return 1;
         } else {
             return 2;
         }
     }
     public boolean palindrome(String s) {
         String tmp="";
         for(int i=s.length()-1;i>=0;i--) {
             tmp+=s.charAt(i)+"";
         }
         return s.equals(tmp);
     }
     //最小时间差
     public static int findMinDifference(List<String> timePoints) {
         int[] times=new int[timePoints.size()];
         for (int i = 0; i <timePoints.size() ; i++) {
             String tmp=timePoints.get(i);
             int hour=Integer.parseInt(tmp.substring(0,2));
             int minute=Integer.parseInt(tmp.substring(3,5));
             times[i]=hour*60+minute;
         }
         Arrays.sort(times);
         int min=Integer.MAX_VALUE;
         for (int i = 1; i <times.length ; i++) {
             min=Math.min(times[i]-times[i-1],min);
         }

         System.out.println(times[0]+1440-times[times.length-1]);
         System.out.println(min);
         return Math.min(min,times[0]+1440-times[times.length-1]);
     }

     public static void main5(String[] args) {
         List<String> list=new ArrayList<>();
         list.add("23:59");
         list.add("00:00");
         System.out.println(findMinDifference(list));

     }
     //编辑距离
     public int minDistance(String word1, String word2) {
         char[] chars1=word1.toCharArray();
         char[] chars2=word2.toCharArray();
         int len1=word1.length();
         int len2=word2.length();
         int[][] a=new int[len1+1][len2+1];
         for (int i = 0; i <=len1 ; i++) {
             a[i][0]=i;
         }
         for (int i = 0; i <=len2 ; i++) {
             a[0][i]=i;
         }
         for (int i = 1; i <=len1 ; i++) {
             for (int j = 1; j <=len2 ; j++) {
                 if(chars1[i-1]==chars2[j-1]) {
                     a[i][j]=a[i-1][j-1];
                 } else {
                     a[i][j]=Math.min(a[i-1][j],Math.min(a[i][j-1],a[i-1][j-1]))+1;
                 }
             }
         }
         return a[len1][len2];
     }
     //链表随机节点
     ListNode head;
     Random random;
     public ListNode(ListNode head) {
         this.head=head;
         this.random=new Random();
     }

     public int getRandom() {

         int res=0;
         ListNode cur=head;
         int count=0;
         while(cur!=null) {
             count++;
             int randomint=random.nextInt(count)+1;
             if(randomint==count) {
                 res=cur.val;
             }
             cur=cur.next;
         }
         return res;

     }
     //计算力扣银行的钱
     public static int totalMoney(int n) {
//         int res=0;
//         for (int i = 1; i <=n ; i++) {
//             res+=(i-1)/7+(i-1)%7+1;
//         }
//         return res;
         int[] tmp=new int[]{0,1,2,3,4,5,6,7};
         if(n<=7) {
             int sum=0;
             for (int i = 0; i <n ; i++) {
                 sum+=i+1;
             }
             return sum;
         } else {
             int num=n/7+1;
             int sum=0;
             for (int i = 1; i <num ; i++) {
                 for (int j = tmp[i]; j <=8 ; j++) {
                     sum+=j;
                 }
             }
             for (int i = tmp[num]; i <tmp[num]+n%7 ; i++) {
                 sum+=i;
             }
             return sum-8;
         }
     }

     public static void main4(String[] args) {
         int n=10;
         System.out.println(totalMoney(n));
     }
     //按键持续时间最长的键
     public static char slowestKey(int[] releaseTimes, String keysPressed) {
         int[] a=new int[releaseTimes.length];
         a[0]=releaseTimes[0];
         int max=a[0];
         int count=0;
         for(int i=1;i<releaseTimes.length;i++) {
             a[i]=releaseTimes[i]-releaseTimes[i-1];
             if(a[i]>=max) {
                 max=a[i];
                 count=i;
             }
         }
         char[] chars=keysPressed.toCharArray();
         char res=chars[count];
         for (int i = 0; i <chars.length ; i++) {
             if(a[i]==max) {
                 if(chars[i]>res) {
                     res=chars[i];
                 }
             }
         }
         return res;
     }

     public static void main3(String[] args) {
         int[] a=new int[]{1,2};
         String s="ba";
         System.out.println(slowestKey(a, s));
     }
     //字符串通配符
     public static boolean match(String s1,String s2) {
         boolean[][] a=new boolean[s1.length()+1][s2.length()+1];
         a[0][0]=true;
         for(int i=1;i<=s1.length();i++) {
             a[i][0]=false;
         }
         for(int i=1;i<=s2.length();i++) {
             if(s2.charAt(i)=='*'||s2.charAt(i)=='?') {
                 a[0][i]=a[0][i-1];
             } else {
                 a[0][i]=false;
             }
         }
         for(int i=1;i<=s1.length();i++) {
             for(int j=1;j<=s2.length();j++) {
                 if(s2.charAt(j)=='*') {
                     a[i][j]=a[i-1][j]|a[i][j-1];
                 } else if(s2.charAt(j)=='?') {
                     a[i][j]=a[i-1][j-1];
                 } else if(s2.charAt(j)==s1.charAt(i)) {
                     a[i][j]=true;
                 } else {
                     a[i][j]=false;
                 }
             }
         }
         return a[s1.length()][s2.length()];
     }
     //括号的最大镶嵌程度
     public int maxDepth(String s) {
         int count=0;
         int max=0;
         for(int i=0;i<s.length();i++) {
             if(s.charAt(i)=='(') {
                 count++;
                 max=max>count? max:count;
             } else if(s.charAt(i)==')') {
                 count--;
             }
         }
         return max;
     }
     //简化路径
     public  static String simplifyPath(String path) {
         // ../返回上一级目录
         // ./ 当前的目录
         //  //视为一个/
         //  /  在结尾
         //将字符串以/分割，遍历字符串正常文件名入栈，如果是“..”弹出栈顶元素，如果是‘.’不管。最后
         //在遍历栈，将出栈元素插入到返回字符串的头上同时加上‘/’
         String[] strings=path.split("/");
         Stack<String> stack=new Stack<>();
         for (String s:strings) {
             if(s.equals("..")&&!stack.isEmpty()) {
                 stack.pop();
             }
             if (!s.equals(".")&&!s.equals("..")&&!s.equals("")) {
                 stack.push(s);
             }
         }
         StringBuilder stringBuilder=new StringBuilder();
         while (!stack.isEmpty()) {
             stringBuilder.insert(0,stack.pop());
             stringBuilder.insert(0,"/");
         }
         if(stringBuilder.length()==0) {
             stringBuilder.append("/");
         }
         return stringBuilder.toString();

     }

     public static void main2(String[] args) {
         String s="/home/";
         simplifyPath(s);
     }
     //替换所有的问号
     public String modifyString1(String s) {
         char[] arr=s.toCharArray();
         for (int i = 0; i <arr.length ; i++) {
             if(arr[i]=='?') {
                 for (char j = 'a'; j <='c' ; j++) {
                     if((i>0&&arr[i-1]==j)||(i<arr.length-1&&arr[i+1]==j)) {
                         continue;
                     }
                     arr[i]=j;
                 }
             }
         }
         return new String(arr);
     }
     public String modifyString(String s) {
         char[] chars=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t'
                 ,'u','v','w','x','y','z'};
         Set<Character> set=new HashSet<>();
         for (int i = 0; i <chars.length ; i++) {
             set.add(chars[i]);
         }
         for(int i=0;i<s.length();i++) {
             if(s.charAt(i)!='?') {
                 set.remove(s.charAt(i));
             }
         }
         String s1=set.toString();
         char[] list=s1.toCharArray();
         char[] chars1=s.toCharArray();
         for(int i=0;i<chars.length;i++) {
             if (chars[i] == '?') {
                 chars[i] = list[i];
             }
         }
         return new String(chars);

     }
     //一周中的第几天
     public String dayOfTheWeek(int day, int month, int year) {
         int days=4;
         String[] weeks={"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
         int[] months={31,28,31,30,31,30,31,31,30,31,30,31};
         for (int i = 1971; i <year ; i++) {
             boolean isLeap=(i%4==0&&i%100!=0)||i%400==0;
             days+=isLeap?366:365;
         }
         for (int i = 0; i <month-1 ; i++) {
             days+=months[i];
             if(i==2&&((year%4==0&&year%100!=0)||year%400==0)) {
                 days+=1;
             }
         }
         days+=day;
         return weeks[days%7];

     }
     //消除游戏
     public int lastRemaining(int n) {
         if(n==1) {
             return 1;
         }
         return 2*(n/2+1-lastRemaining(n/2));

     }
     //将一维数组转变成二维数组
     public int[][] construct2DArray(int[] original, int m, int n) {
         int[][] res=new int[m][n];
         if(m*n!=original.length) {
             return new int[][]{};
         }
         int count=0;
         for (int i = 0; i <m ; i++) {
             for (int j = 0; j <n ; j++) {
                 if(count<original.length) {
                     res[i][j]=original[count];
                     count++;
                 }
             }
         }
         return res;
     }
     //完美数
     public boolean checkPerfectNumber(int num) {
         int sum=0;
         for(int i=1;i<num;i++) {
             if(num%i==0) {
                 sum+=i;
             }
             if(sum>num) {
                 return false;
             }
         }
         return num==sum;
     }
     //一手顺子
     public boolean isNStraightHand(int[] hand, int groupSize) {
         if(hand.length%groupSize!=0) {
             return false;
         }
         //排序
         Arrays.sort(hand);
         //使用hash表存储
         Map<Integer,Integer> map=new HashMap<>();
         for (int i = 0; i <hand.length ; i++) {
             map.put(hand[i],map.getOrDefault(hand[i],0)+1);
         }
         for (int i = 0; i <hand.length ; i++) {
             //这张牌已经用完
             if(map.get(hand[i])==0) {
                 continue;
             }
             for (int j = 0; j <groupSize ; j++) {
                 if(!map.containsKey(hand[i]+j)||map.get(hand[i]+j)==0) {
                     return false;
                 }
                 map.put(hand[i]+j,map.get(hand[i]+j)-1);
             }
         }
         return true;
     }
     //统计特殊四元组
     public int countQuadruplets(int[] nums) {
         int count=0;
         for (int a = 0; a <nums.length ; a++) {
             for (int b =a+1 ; b <nums.length ; b++) {
                 for (int c = b+1; c <nums.length ; c++) {
                     for (int d = c+1; d <nums.length ; d++) {
                         if(nums[a]+nums[b]+nums[c]==nums[d]) {
                             count++;
                         }
                     }
                 }
             }
         }
         return count;

     }
     //括号匹配问题
     public boolean chkParenthesis(String A, int n) {
         // write code here
         if(n%2!=0) {
             return false;
         }
         Stack<Character> stack=new Stack<>();
         for (int i = 0; i <n ; i++) {
             if(A.charAt(i)=='(') {
                 stack.push('(');
             } else if(A.charAt(i)==')') {
                 stack.pop();
             }
         }
         if(stack.isEmpty()) {
             return true;
         } else {
             return false;
         }
     }
     //连接词
     public static List<String> findAllConcatenatedWordsInADict(String[] words) {
         List<String> list=new ArrayList<>();
         if(words.length<=2) {
             return list;
         }
         for (String s:words) {
             Map<Character,Integer> map=new HashMap<>();
             for (int i = 0; i <s.length() ; i++) {
                 if(map.containsKey(s.charAt(i))) {
                     map.put(s.charAt(i),map.get(s.charAt(i)+1));
                 }else {
                     map.put(s.charAt(i),1);
                 }
             }
             String tmp="";
             for (int i = 0; i <map.size() ; i++) {
                 if(map.get(i)==2) {
                     tmp+=map.get(i);
                 }
             }
             if (s.compareTo(tmp)!=-1) {
                 list.add(s);
             }
         }
         return list;
     }

     public static void main1(String[] args) {
         String[] words=new String[]{"cat","cats","catsgogcats","dog","dogcatsdog","hippoptamuses","rat","ratcatdogcat"};
         System.out.println(findAllConcatenatedWordsInADict(words));
     }
     //适龄的朋友
     public int numFriendRequests(int[] ages) {
         int left=0;
         int right=0;
         int res=0;
         Arrays.sort(ages);
         for (int i = 0; i <ages.length ; i++) {
             if(ages[i]<15) {
                 continue;
             }
             while (ages[left]<=0.5*ages[i]+7) {
                 left++;
             }
             while (right+1<ages.length&&ages[right+1]<=ages[i]) {
                 right++;
             }
             res+=right-left;
         }
         return res;
     }
     //Bigram分词
     public String[] findOcurrences(String text, String first, String second) {
         List<String> list=new ArrayList<>();
         if(text.length()<=2) {
             return new String[]{};
         }
         String[] tmp=text.split(" ");
         for (int i = 1; i <tmp.length-1 ; i++) {
             if(tmp[i-1].equals(first)&&tmp[i].equals(second)) {
                 list.add(tmp[i+1]);
             }
         }
         return list.toArray(new String[0]);
     }
     public static void main16(String[] args) {
         int [] arr=new int[]{6,-3,-2};
         int sumMax=0;
         for (int i = 0; i <arr.length ; i++) {
             int cur=0;
             for (int j = i; j <arr.length; j++) {
                 cur+=arr[j];
                 if (cur>sumMax) {
                     sumMax=cur;
                 }
             }
         }
         System.out.println(sumMax);
     }

     //吃苹果的最大数目
     public int eatenApples(int[] apples, int[] days) {
         int sum=0;
         int max=days[1];
         for (int i = 1; i <days.length ; i++) {
             if(max<days[i]) {
                 max=days[i];
             }
         }
         for (int i = 0; i <apples.length ; i++) {
             if(apples[i]<=days[i]&&apples[i]!=0&&days[i]!=0) {
                 sum+=1;
             } else if (apples[i]!=0&&days[i]!=0){
                 sum+=days[i];
                 if(days[i]==max) {
                     break;
                 }

             }
         }
         return sum;
     }
     //重复叠加字符串匹配
     public static int repeatedStringMatch1(String a, String b) {
//         String tmp=a;
//         int count=1;
//         int midRepCnt=b.length()/a.length();
//         while (count<=midRepCnt+2) {
//             if (tmp.indexOf(b)!=-1) {
//                 return count;
//             }
//             tmp+=a;
//             count++;
//         }
//         return -1;
         boolean[] arr=new boolean[26];
         for (int i = 0; i <a.length() ; i++) {
             arr[a.charAt(i)-'a']=true;
         }
         for (int i = 0; i <b.length() ; i++) {
             if(!arr[b.charAt(i)-'a']) {
                 return -1;
             }
         }
         StringBuilder stringBuilder=new StringBuilder();
         int count=b.length()/a.length();
         for (int i = 0; i <=2 ; i++) {
             if(a.indexOf(b)>=0) {
                 return count+i;
             }
             stringBuilder.append(a);
         }
         return -1;
     }
     public static int repeatedStringMatch(String a, String b) {
         //去除a中重复的字符
         String res="";
         LinkedHashSet<String> linkedHashSet=new LinkedHashSet<>();
         for (int i = 0; i <a.length() ; i++) {
             linkedHashSet.add(a.charAt(i)+"");
         }
         for (String s:linkedHashSet) {
             res+=s;
         }
         //字符串a与b相同
         if(a==b) {
             return 1;
         } else if(a.length()>b.length()) {
             //a串比b串长，当a串中包含b返回1，否则返回-1
             if(res.indexOf(b)!=-1&&res.charAt(0)!=b.charAt(0)) {
                 int count=0;
                 int num=0;
                 //判断b的第一个字符在a串中的位置，从而判断a拼接的次数
                 for (int i = 0; i <a.length() ; i++) {
                     if(a.charAt(i)==b.charAt(0)) {
                         num=i;
                     }
                 }
                return b.length()/res.length()+num;
             } else if(a.indexOf(b)!=-1&&res.charAt(0)==b.charAt(0)) {
                 return 1;
             } else {
                 return -1;
             }
         } else {
             String tmp="";
             int count=0;
             int num=0;
             //判断b的第一个字符在a串中的位置，从而判断a拼接的次数
             for (int i = 0; i <a.length() ; i++) {
                 if(a.charAt(i)==b.charAt(0)) {
                     num=i;
                 }
             }
             if(num!=0&&b.length()%a.length()!=0) {
                 for (int i = 0; i <b.length()/a.length()+num ; i++) {
                     tmp+=a;
                     count++;
                 }
             } else if(num!=0&&b.length()%a.length()==0) {
                 for (int i = 0; i <b.length()/a.length()+num-1 ; i++) {
                     tmp+=a;
                     count++;
                 }
             } else {
                 for (int i = 0; i <b.length()/a.length() ; i++) {
                     tmp+=a;
                     count++;
                 }
             }
             if(tmp.indexOf(b)!=-1) {
                 return count;
             } else {
                 return -1;
             }
         }
     }

     public static void main15(String[] args) {
         String a="aaaaaaaaaaaab";
         String b="ba";
         System.out.println(repeatedStringMatch(a, b));
     }
     //一年中的第几天
     public static int dayOfYear(String date) {
         int year= Integer.parseInt(date.substring(0,4));
         int[] dayOfMonth=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
         if((year%400==0)||(year%4==0&&year%100!=0)) {
             dayOfMonth[1]=29;
         }
         System.out.println(year);
         int month=Integer.parseInt(date.substring(5,7));
         System.out.println(month);
         int day=Integer.parseInt(date.substring(8,10));
         System.out.println(day);
         System.out.println(dayOfMonth[1]);
         int res=0;
         for (int i = 0; i <month-1 ; i++) {
             res+=dayOfMonth[i];
             System.out.println(res);
         }
         return res+day;
     }

     public static void main13(String[] args) {
         String s="2008-10-10";
         System.out.println(dayOfYear(s));
     }
     //供暖器
//     public int findRadius(int[] houses, int[] heaters) {
//         Arrays.sort(houses);
//         Arrays.sort(heaters);
//         int left=0;
//         int right=Math.max(houses[houses.length-1],heaters[heaters.length-1]);
//         while (left<right) {
//             int mid=(left+right)/2;
//             if(check(houses,heaters,mid)) {
//                 right=mid;
//             } else {
//                 left=mid+1;
//             }
//         }
//         return right;
//     }
//
//     private boolean check(int[] houses, int[] heaters, int mid) {
//         for (int i = 0,j=0; i <houses.length ; i++) {
//             //
//             while (j<heaters.length&&heaters[j]+mid<houses[i]) {
//                 j++;
//             }
//             if(j<heaters.length&&heaters[j]-mid<=houses[i]&&houses[i]<=heaters[j]+mid) {
//                 return false;
//             }
//         }
//         return true;
//     }

     //删除公共字符
     public static void main12(String[] args) {
         Scanner scanner=new Scanner(System.in);
         String str1=scanner.nextLine();
         String str2=scanner.nextLine();
         StringBuilder stringBuilder=new StringBuilder();
         for (int i = 0; i <str1.length() ; i++) {
             if(!str2.contains(str1.charAt(i)+"")) {
                 stringBuilder.append(str1.charAt(i));
             }
         }
         System.out.println(stringBuilder.toString());
//         Scanner scanner=new Scanner(System.in);
//         String str1=scanner.nextLine();
//         String str2=scanner.nextLine();
//         HashMap<Character,Integer> map=new HashMap<>();
//         //遍历第二个字符串
//         for (int i = 0; i <str2.length() ; i++) {
//             //判断当前的字符之前是否存在
//             if(map.get(str2.charAt(i))==null) {
//                 map.put(str2.charAt(i),1);
//             } else {
//                 map.put(str2.charAt(i),map.get(str2.charAt(i))+1);
//             }
//         }
//         String ret="";
//         //遍历第一个字符串
//         for (int i = 0; i <str1.length() ; i++) {
//             if(map.get(str1.charAt(i))==null) {
//                 ret+=str1.charAt(i);
//             }
//         }
//         System.out.println(ret);

//         Scanner scanner=new Scanner(System.in);
//         String str1=scanner.nextLine();
//         String str2=scanner.nextLine();
//         int[] hashtable=new int[256];
//         for (int i = 0; i <str2.length() ; i++) {
//             hashtable[str2.charAt(i)]++;
//         }
//         StringBuilder stringBuilder=new StringBuilder();
//         for (int i = 0; i <str1.length() ; i++) {
//             if(hashtable[str1.charAt(i)]==0) {
//                 stringBuilder.append(str1.charAt(i));
//             }
//         }
//         System.out.println(stringBuilder.toString());

     }
     //数组中的第k大数字
     public int findKthLargest(int[] nums, int k) {
         Arrays.sort(nums);
         int i=nums.length-k;
         return nums[i];


     }
     //甲板上的战舰
     public int countBattleships(char[][] board) {
         int ans=0;
         for (int i = 0; i <board.length ; i++) {
             for (int j = 0; j <board[0].length ; j++) {
                 if(board[i][j]=='.') continue;
                 if(i>0&&board[i-1][j]=='X') continue;
                 if(j>0&&board[i][j-1]=='X') continue;
                 ans++;
             }
         }
         return ans;

     }
     //小镇法官
     public int findJudge(int n, int[][] trust) {
         int[] inTrust=new int[n+1];
         int[] outTrust=new int[n+1];
         for (int[] a:trust) {
             int x=a[0];
             int y=a[1];
             inTrust[y]++;
             outTrust[x]++;
         }
         for (int i = 1; i <=n ; i++) {
             if(inTrust[i]==n-1&&outTrust[i]==0) {
                 return i;
             }
         }
         return -1;
     }
     //换酒问题
     public int numWaterBottles1(int n, int m) {
         int ans=n;
         while (n>=m) {
             //可以换几瓶酒
             int a=n/m;
             //剩下的没有换完的酒
             int b=n%m;
             ans+=a;
             //换酒后的空瓶加剩下的不能换酒的空瓶
             n=a+b;
         }
         return ans;
     }
     public static int numWaterBottles(int numBottles, int numExchange) {
         int ans=0;
         int wine=numBottles;
         while (numBottles>=numExchange) {
             ans+=wine;
             wine=numBottles/numExchange;
             numBottles-=(numExchange-1)*wine;
         }
         ans+=wine;
         return ans;
     }
     public static void main11(String[] args) {
         Scanner scanner=new Scanner(System.in);
         int n=scanner.nextInt();
         System.out.println(numWaterBottles(17, 3));
     }
     //盛最多水的容器
     public int maxArea(int[] height) {
         int left=0;
         int right=height.length-1;
         int ans=0;
         while (left<right) {
             int sum=Math.min(height[left],height[right])*(right-left);
             ans=Math.max(ans,sum);
             if(height[left]>height[right]) {
                 right--;
             } else {
                 left++;
             }
         }
         return ans;
     }
     //旋转矩阵
     public void rotate(int[][] matrix) {
         int n=matrix.length;
         int[][] new_matrix=new int[n][n];
         for (int i = 0; i <n ; i++) {
             for (int j = 0; j <n ; j++) {
                 new_matrix[j][n-i-1]=matrix[i][j];
             }
         }
         for (int i = 0; i <n ; i++) {
             for (int j = 0; j <n ; j++) {
                 matrix[i][j]=new_matrix[i][j];
             }
         }
     }
     //回文字串
     int num=0;
     public  int countSubstrings1(String s) {
         for (int i = 0; i <s.length() ; i++) {
             count(s,i,i);//回文长度是奇数
             count(s,i,i+1);//回文长度是偶数
         }
         return num;
     }

     private void count(String s, int start, int end) {
         while (start>=0&&end<s.length()&&s.charAt(start)==s.charAt(end)) {
             num++;
             start--;
             end++;
         }
     }

     public int countSubstrings(String s) {
         if(s==null||s.length()<1) {
             return -1;
         }
         int count=0;
         for (int i = 0; i <s.length() ; i++) {
             for (int j = i+1; j <=s.length() ; j++) {
                 String text=s.substring(i,j);
                 if(isPalindromic(text)) {
                     count++;
                 }
             }
         }
         return count+s.length();

     }
     //最长回文字串 暴力
     public String longestPalindrome(String s) {
         if(s==null||s.length()<1) {
             return null;
         }
         if(s.length()==1) {
             return s;
         }
         String ans="";
         int max=0;
         for (int i = 0; i <s.length() ; i++) {
             //此处的等于用于防止越界，并且保证了自己本身是一个回文
             for (int j = i+1; j <=s.length() ; j++) {
                 String text=s.substring(i,j);
                 //判断是否是回文 是否比当前的回文串长度长
                 if(isPalindromic(text)&&text.length()>max) {
                     ans=text;
                     max=Math.max(max,text.length());
                 }
             }
         }
         return ans;
     }

     private boolean isPalindromic(String text) {
         for (int i = 0; i <text.length()/2 ; i++) {
             if(text.charAt(i)!=text.charAt(text.length()-1-i)) {
                 return false;
             }
         }
         return true;
     }

     //保持城市天际线
     public int maxIncreaseKeepingSkyline(int[][] grid) {
         int n=grid.length;
         int[] rowMax=new int[n];//每一行的最大高度
         int[] colMax=new int[n];//每一列的最大高度
         for (int i = 0; i <n ; i++) {
             for (int j = 0; j <n ; j++) {
                 rowMax[i]=Math.max(rowMax[i],grid[i][j]);
                 colMax[j]=Math.max(colMax[j],grid[i][j]);
             }
         }
         int res=0;
         for (int i = 0; i <n ; i++) {
             for (int j = 0; j <n ; j++) {
                 //加行的最大值与列的最大值中较小的一个减去原来的值
                 res+=Math.min(rowMax[i],colMax[j])-grid[i][j];
             }
         }
         return res;

     }
     //转换大小写
     //大写变小写、小写变大写 : 字符 ^= 32;
     //大写变小写、小写变小写 : 字符 |= 32;
     //小写变大写、大写变大写 : 字符 &= -33;
     public String toLowerCase1(String s) {
         char[] chars=s.toCharArray();
         for (int i = 0; i <s.length() ; i++) {
             if(chars[i]>='A'&&chars[i]<='Z') {
                 chars[i]|=32;
             }
         }
         String s1="";
         for (int i = 0; i <chars.length ; i++) {
             s1+=chars[i];
         }
         return s1;
     }
     public String toLowerCase(String s) {
         if(s==null||s.length()==0) {
             return null;
         }
         StringBuilder stringBuilder=new StringBuilder();
         for (int i = 0; i <s.length() ; i++) {
             if(s.charAt(i)>='A'&&s.charAt(i)<='Z') {
                 stringBuilder.append((char) (s.charAt(i)+32));
             } else {
                 stringBuilder.append(s.charAt(i));
             }
         }
         return stringBuilder.toString();

     }
     public String shortestCompletingWord1(String licensePlate, String[] words) {String res="";
         int[] cnt = new int[26];
         for (int i = 0; i < licensePlate.length(); ++i) {
             char ch = licensePlate.charAt(i);
             if (Character.isLetter(ch)) {
                 ++cnt[Character.toLowerCase(ch) - 'a'];
             }
         }
         int idx = -1;
         for (int i = 0; i < words.length; ++i) {
             int[] c = new int[26];
             for (int j = 0; j < words[i].length(); ++j) {
                 char ch = words[i].charAt(j);
                 ++c[ch - 'a'];
             }
             boolean ok = true;
             for (int j = 0; j < 26; ++j) {
                 if (c[j] < cnt[j]) {
                     ok = false;
                     break;
                 }
             }
             if (ok && (idx < 0 || words[i].length() < words[idx].length())) {
                 idx = i;
             }
         }
         return words[idx];
     }

     //最短补全词
     public static String shortestCompletingWord(String licensePlate, String[] words) {
         String res="";
         for (int i = 0; i <licensePlate.length() ; i++) {
             if(licensePlate.charAt(i)>='A'&&licensePlate.charAt(i)<='Z') {
                 res+=licensePlate.charAt(i)-'A'+'a';
             }
             if(licensePlate.charAt(i)>='a'&&licensePlate.charAt(i)<='z') {
                 res+=licensePlate.charAt(i);
             }
         }

         String[] list=new String[words.length];
         int count=0;
         for (int i = 0; i <words.length ; i++) {
             if(words[i].contains(res)) {
                 list[count]=words[i];
                 count++;
             }
         }
         Arrays.sort(list);
         return list[0];

     }

     public static void main10(String[] args) {
         String li="1s3 PSt";
         String[] word={"step","steps","stripe","stepple"};
         System.out.println(shortestCompletingWord(li, word));


     }
     //有效的井字游戏
     public boolean validTicTacToe(String[] board) {
        int numX=0;
        int numO=0;
        char[][] boards=new char[3][3];
         for (int i = 0; i <board.length ; i++) {
             String s=board[i];
             for (int j = 0; j <s.length() ; j++) {
                 if(s.charAt(j)=='X') {
                     numX++;
                     boards[i][j]='X';
                 } else if(s.charAt(j)=='O') {
                     numO++;
                     boards[i][j]='O';
                 } else {
                     boards[i][j]=' ';
                 }
             }
         }
         if(numX>numO+1||numX<numO) {
             return false;
         }
         boolean wina=hasOneLine(boards,'X');
         boolean winb=hasOneLine(boards,'O');
         //1、X必须比O多1个或者相同，否则false；
         // 2、X和O不能同时都是赢家，否则false；
         // 3、X赢的时候必须比O多1个，否则false；
         // 4、O赢得时候个数与X相同，否则false；
         //其他的都是true
         if(numX==numO+1) {
             //X与O同时赢返回false X比O多一且输了返回false
             if(winb&&!wina||wina&&winb) {
                 return false;
             }
             return true;
         }
         if(numX==numO) {
             //O输了返回false
             if(wina&&!winb||wina&&winb) {
                 return false;
             }
             return true;
         }
         return false;


     }

     private boolean hasOneLine(char[][] boards, char x) {
         for (int i = 0; i <3 ; i++) {
             if(boards[i][0]==x&&boards[i][0]==boards[i][1]&&boards[i][1]==boards[i][2]) {
                 return true;
             }
             if(boards[0][i]==x&&boards[0][i]==boards[1][i]&&boards[1][i]==boards[2][i]) {
                 return true;
             }
         }
         if(boards[0][0]==x&&boards[0][0]==boards[1][1]&&boards[1][1]==boards[2][2]) {
             return true;
         }
         if(boards[0][2]==x&&boards[0][2]==boards[1][1]&&boards[1][1]==boards[2][0]) {
             return true;
         }
         return false;
     }

     //合并排序的数组
     public static void merge(int[] A, int m, int[] B, int n) {
        int k=m+n-1;
        int i=m-1;
        int j=n-1;
        while (i>=0&&j>=0) {
            if(A[i]>B[j]) {
                A[k--]=A[i--];
            } else {
                A[k--]=B[j--];
            }
        }
        while (j>=0) {
            A[k--]=B[j--];
        }
     }

     public static void main9(String[] args) {
         int[] A={1,2,3,0,0,0};
         int[] B={2,5,6};
         int m=3;
         int n=3;
         merge(A,m,B,n);
     }
     //跳跃游戏
     public boolean canJump(int[] nums) {
         if(nums.length<=1) {
             return true;
         }
         int cover=0;
         for (int i = 0; i <nums.length ; i++) {
             if(i<=cover) {
                 cover=Math.max(cover,i+nums[i]);
                 if(cover>=nums.length-1) {
                     return true;
                 }
             }

         }
         return false;

     }
     //平衡字符串
     public int balancedStringSplit(String s) {
         int count=0;
         int res=0;
         for (int i = 0; i <s.length() ; i++) {
             if(s.charAt(i)=='R') {
                 res++;
             } else if(s.charAt(i)=='L') {
                 res--;
             }
             if(res==0) {
                 count++;
             }
         }
         return count;

     }
     //买股票的最佳时机
     public int maxProfit(int[] prices) {
         int peace=0;
         for (int i = 1; i <prices.length; i++) {
             if(prices[i]-prices[i-1]>0) {
                 peace+=prices[i]-prices[i-1];
             }
         }
         return peace;
     }
       int val;
       ListNode next;
       ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
public class LeetCode {

     //汉诺塔问题
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(),A,B,C);
    }

    private void move(int size, List<Integer> a, List<Integer> b, List<Integer> c) {
        if(size==1) {
            c.add(a.remove(a.size()-1));
            return;
        } else {
           move(size-1,a,c,b);
           c.add(a.remove(a.size()-1));
           move(size-1,b,a,c);
        }
    }

    //魔术索引
    public static int findMagicIndex(int[] nums) {
        for (int i = 0; i <nums.length ; i++) {
            if (nums[i]==i) {
                return i;
            }
        }
        return -1;
    }

    public static void main9(String[] args) {
        int[] a=new int[]{0,2,3,4,5};
        System.out.println(findMagicIndex(a));
    }
     //截断句子
    public static String truncateSentence(String s, int k) {
        if(k<=0||k>s.length()) {
            return "";
        }
        String[] res=s.split(" ");
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <k-1 ; i++) {
            stringBuilder.append(res[i]+" ");
        }
        stringBuilder.append(res[k-1]);
        return stringBuilder.toString();
    }

    public static void main8(String[] args) {
        String s="hello how are you contest";
        int k=4;
        System.out.println(truncateSentence(s, k));
    }
     //k次取反后最大化的数组和
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum=0;
        for (int i = 0; i <nums.length && k>0; i++,k--) {
            if(nums[i]>=0) {
                break;
            }
            nums[i]=-nums[i];
        }
        Arrays.sort(nums);
        for (int i = 0; i <nums.length ; i++) {
            sum+=nums[i];
        }
        if(k!=0&&k%2!=0) {
            sum-=2*nums[0];
        }
        return sum;
    }

    public static void main7(String[] args) {
        int[] a={4,3,2};
        System.out.println(largestSumAfterKNegations(a, 1));
    }
     //赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length()>magazine.length()) {
            return false;
        }
        int[] nums=new int[26];
        for (int i = 0; i <magazine.length() ; i++) {
            nums[magazine.charAt(i)-'a']++;
        }
        for (int i = 0; i <ransomNote.length() ; i++) {
            nums[ransomNote.charAt(i)-'a']--;
            if(nums[ransomNote.charAt(i)-'a']<0) {
                return false;
            }
        }
        return true;

    }
     //相对名次
    public static String[] findRelativeRanks(int[] score) {
        String[] place=new String[]{"Cold Medal","Silver Medal","Bronze Medal"};
        String[] res=new String[score.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) ->{
            return score[o2] - score[o1];
        });
        for (int i = 0; i <score.length ; i++) {
            queue.add(i);
        }
        for(int i = 0; i < score.length; i++){
            int x = queue.poll();
            if(i < 3){
                res[x] = place[i];
            }
            else{
                res[x] = Integer.toString(i+1);
            }
        }
        return res;
    }

    public static void main6(String[] args) {
        int[] a={5,4,3,2,1};
        findRelativeRanks(a);
    }
     //回文链表
    public boolean isPalindrome(ListNode head) {
        ListNode node1=head;
        ListNode node2=head;
        Stack<Integer> stack=new Stack<>();
        while (node1!=null) {
            stack.push(node1.val);
            node1=node1.next;
        }
        while (node2!=null) {
            if(node2.val==stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
            node2=node2.next;
        }
        return true;

    }
     //用栈实现队列
//     Stack<Integer> stack1=new Stack<>();
//     Stack<Integer> stack2=new Stack<>();
//    public void push(int x) {
//        stack1.push(x);
//    }
//
//    /** Removes the element from in front of queue and returns that element. */
//    public int pop() {
//        if(stack1.isEmpty()&&stack2.isEmpty()) {
//            return -1;
//        } else if(stack2.isEmpty()) {
//            while (!stack1.isEmpty()) {
//                int tmp=stack1.peek();
//                stack1.pop();
//                stack2.push(tmp);
//            }
//        }
//        int tmp=stack2.peek();
//        stack2.pop();
//        return tmp;
//    }
//
//    /** Get the front element. */
//    public int peek() {
//        if(stack1.isEmpty()&&stack2.isEmpty()) {
//            return -1;
//        } else if(stack2.isEmpty()) {
//            while (!stack1.isEmpty()) {
//                int tmp=stack1.peek();
//                stack1.pop();
//                stack2.push(tmp);
//            }
//        }
//        return stack2.peek();
//
//    }
//
//    /** Returns whether the queue is empty. */
//    public boolean empty() {
//        return stack1.isEmpty()&&stack2.isEmpty();
//
//    }
    //连续字符
    public static int maxPower(String s) {
        int max=1;
        int count=1;
        for (int i = 1; i <s.length() ; i++) {
            if(s.charAt(i)==s.charAt(i-1)) {
                count++;
            } else {
                max=Math.max(max,count);
                count=1;
            }
        }
        return Math.max(max,count);
    }

    public static void main5(String[] args) {
        String s="abbcccddddeeeeedcba";
        System.out.println(maxPower(s));
    }
     //链表相交
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA=headA;
        ListNode curB=headB;
        int lengthA=0;
        int lengthB=0;
        while (curA!=null) {
            lengthA++;
            curA=curA.next;
        }
        while (curB!=null) {
            lengthB++;
            curB=curB.next;
        }
        curA=headA;
        curB=headB;
        int len=lengthA-lengthB;
        if(lengthA<lengthB) {
            curA=headB;
            curB=headA;
            len=lengthB-lengthA;
        }
        while (len!=0) {
            curA=curA.next;
            len--;
        }
        while (curA!=null&&curB!=null&&curA!=curB) {
            curA=curA.next;
            curB=curB.next;
        }
        return curA;
    }
     //第K个最小的素数分数
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        List<int[]> res=new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            for (int j = i+1; j <arr.length ; j++) {
                res.add(new int[]{arr[i],arr[j]});
            }
        }
        //自定义排序
        Collections.sort(res,(x,y) ->x[0]*y[1]-y[0]*x[1]);
        return res.get(k-1);

    }

    public static void main4(String[] args) {
        int[] arr={1,2,3,5};
        int k=3;
        int[] a=kthSmallestPrimeFraction(arr, k);
        for (int i = 0; i <a.length ; i++) {
            System.out.println(a[i]);
        }
    }
     //分割链表
    public ListNode partition(ListNode head, int x) {
        //分割链表
       ListNode node1=new ListNode();
       ListNode node2=new ListNode();
       ListNode head1=node1;
       ListNode head2=node2;
       ListNode cur=head;
       while (cur!=null) {
           if(cur.val<x) {
               node1.next=cur;
               node1=node1.next;
           } else {
               node2.next=cur;
               node2=node2.next;
           }
           cur=cur.next;
       }
       node1.next=head2.next;
       node2.next=null;
       return head1.next;

    }

     //找出字符串中所有字母异位词
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list=new ArrayList<>();
        if(s==null||p==null) {
            return list;
        }
        if(s.length()<p.length()) {
            return list;
        }
        int[] mark=new int[26];
        for (int i = 0; i <p.length() ; i++) {
            mark[p.charAt(i)-'a']++;
        }

        for (int i = 0; i <=s.length()-p.length() ; i++) {
            int[] temp=mark.clone();
            for (int j = i; j <i+p.length() ; j++) {
                if(temp[s.charAt(j)-'a']>0) {
                    temp[s.charAt(j)-'a']--;
                } else {
                    break;
                }
            }
            boolean flag=true;
            for (int j = 0; j <26 ; j++) {
                if(temp[j]!=0) {
                    flag=false;
                    break;
                }
            }
            if(flag) {
                list.add(i);
            }
        }
        return list;
    }
    public static void main3(String[] args) {
        String s="cbaebabacd";
        String p="abc";
        System.out.println(findAnagrams(s, p));

    }
     //删除中间节点
    public void deleteNode1(ListNode node) {
        if(node==null) {
            return;
        }
        node.val=node.next.val;
        node.next=node.next.next;
    }
     //倒数第k个节点
    public int kthToLast(ListNode head, int k) {
        if(head==null) {
            return -1;
        }
        ListNode first=head;
        ListNode slow=head;
        while (k!=0) {
            first=first.next;
            k--;
        }
        while (first!=null) {
            first=first.next;
            slow=slow.next;
        }
        return slow.val;

    }
     //移除重复节点
    public  static ListNode removeDuplicateNodes(ListNode head) {
        if(head==null) {
            return null;
        }
        Set<Integer> set=new HashSet<>();
        ListNode newHead=head;
        set.add(head.val);
        while (head.next!=null) {
            if (set.contains(head.next.val)) {
                head.next= head.next.next;
            } else {
                set.add(head.next.val);
                head = head.next;
            }

        }
        return newHead;

    }
    public boolean isFlipedString1(String s1, String s2) {
        if(s1.length()!=s2.length()) {
            return false;
        }
        String ss=s2+s2;
        return ss.contains(s1);
    }
     //字符串轮转
    public static boolean isFlipedString(String s1, String s2) {
        int length1=s1.length();
        int length2=s2.length();
        if(length1!=length2) {
            return false;
        }
        if(s1==null||s2==null) {
            return false;
        }
        int i=0;
        while (s1.charAt(i)!=s2.charAt(0)) {
            i++;
        }
        //System.out.println(i);
        StringBuilder stringBuilder=new StringBuilder();
        for (int j = i; j <length1 ; j++) {
            stringBuilder.append(s1.charAt(j));
        }
        for (int j = 0; j <i ; j++) {
            stringBuilder.append(s1.charAt(j));
        }
        //System.out.println(stringBuilder.toString());
        if(s2.equals(stringBuilder.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static void main2(String[] args) {
        String s1="";
        String s2="";
        System.out.println(isFlipedString(s1, s2));
    }


     //字符串压缩
    public String compressString(String S) {
        if(S==null||S.length()==0) {
            return S;
        }
        StringBuilder stringBuilder=new StringBuilder();
        char prev=S.charAt(0);
        int times=1;
        for (int i = 1; i <S.length() ; i++) {
            if(S.charAt(i)==prev) {
                times++;
            } else {
                stringBuilder.append(prev).append(times);
                prev=S.charAt(i);
                times=1;
            }
        }
        stringBuilder.append(prev).append(times);
        return S.length()>stringBuilder.toString().length()?stringBuilder.toString():S;


    }
     //一次编辑
     public boolean oneEditAway(String first, String second) {
         int[][] dp=new int[first.length()+1][second.length()+1];
         for (int i = 1; i <=first.length() ; i++) {
             dp[i][0]=i;
         }
         for (int i = 1; i <=second.length() ; i++) {
             dp[0][i]=i;
         }
         for (int i = 1; i <=first.length() ; i++) {
             for (int j = 1; j <=second.length() ; j++) {
                 if(first.charAt(i-1)==second.charAt(j-1)) {
                     dp[i][j]=dp[i-1][j-1];
                 } else {
                     dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]+1);
                 }
             }
         }
         return dp[first.length()][second.length()]<=1;

     }
     //最长和谐子序列
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin=0;
        int end=0;
        int res=0;
        for (end=0;end<nums.length;end++) {
            while (nums[end]-nums[begin]>1) {
                begin++;
            }
            if(nums[end]-nums[begin]==1) {
                 res=Math.max(res,end-begin+1);
            }
        }
        return res;


    }
     //回文结构
    public boolean canPermutePalindrome(String s) {
        char[] chars=s.toCharArray();
        Set<Character> set=new HashSet<>();
        for (int i = 0; i <s.length() ; i++) {
            if(!set.add(chars[i])) {
                set.remove(chars[i]);
            }
        }
        return set.size()<=1;

    }
     //灯泡开关
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);

    }
     //URL化
    public String replaceSpaces(String S, int length) {
        StringBuilder stringBuilder=new StringBuilder();
        char[] chars=S.toCharArray();
        for (int i = 0; i <length ; i++) {
            if(chars[i]==' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();

    }
     //是否互为字符串重排
    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length()!=s2.length()) {
            return false;
        }
        char[] chars1=s1.toCharArray();
        char[] chars2=s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i <chars1.length ; i++) {
            if(chars1[i]!=chars2[i]) {
                return false;
            }
        }
        return true;

    }
     //检测大写字母
    public boolean detectCapitalUse(String word) {
        int upper=0;//大写字母
        int lower=0;//小写字母
        for (int i = 0; i <word.length() ; i++) {
            if(word.charAt(i)>='a') {
                lower++;
            } else {
                upper++;
            }
        }
        if(lower==word.length()) {
            return true;
        }
        if(upper==word.length()) {
            return true;
        }
        if(upper==1&&word.charAt(0)<'a') {
            return true;
        }
        return false;


    }
     //提莫攻击
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum=0;
        for(int i=1;i<timeSeries.length;i++) {
            if(timeSeries[i]-timeSeries[i-1]>=duration) {
                sum=sum+duration;
            } else {
                sum+=timeSeries[i]-timeSeries[i-1];
            }
        }
        return sum+duration;

    }
     //丢失的数字
    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <nums.length ; i++) {
            if(i!=nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main1(String[] args) {
        int[] nums={0,1};
        System.out.println(missingNumber(nums));
    }
     //最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if(strs==null) {
            return "";
        }
        if(strs.length==1) {
            return strs[0];
        }
        String str=strs[0];
        for (int i = 0; i <strs.length ; i++) {
            while(strs[i].indexOf(str)!=0) {
                str=str.substring(0,str.length()-1);
            }
        }
        return str;

    }
     //两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur=new ListNode(-1);
        ListNode prev=cur;
        int tmp=0;
        while (l1!=null||l2!=null||tmp!=0) {
            if(l1!=null) {
                tmp+=l1.val;
                l1=l1.next;
            }
            if(l2!=null) {
                tmp+=l2.val;
                l2=l2.next;
            }
            prev.next=new ListNode(tmp%10);
            prev=prev.next;
            tmp/=10;
        }
        return cur.next;
    }
     //猜数字游戏
    public String getHint(String secret, String guess) {
        int a=0;
        int b=0;
        int[] arr=new int[10];
        for(int i=0;i<secret.length();i++) {
            if(secret.charAt(i)==guess.charAt(i)) {
                a++;
            } else {
                if(arr[secret.charAt(i)-'0']++<0) {
                    b++;
                }
                if(arr[guess.charAt(i)-'0']-->0) {
                    b++;
                }
            }
        }
        return a+"A"+b+"B";

    }
     //范围求和
    public int maxCount(int m, int n, int[][] ops) {
        for (int i = 0; i <ops.length ; i++) {
            m=m>ops[i][0]?ops[i][0]:m;
            n=n>ops[i][1]?ops[i][1]:n;
        }
        return m*n;

    }
     //有效平方数
    public boolean isPerfectSquare(int num) {
        int low=0;
        int high=num;
        while(low<=high) {
            int mid=low+(high-low)/2;
            long square=(long)mid*mid;
            if(square==num) {
                return true;
            } else if(square<num) {
                low=mid+1;
            } else{
                high=mid-1;
            }
        }
        return false;
    }
    
     //删除链表的节点
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
    //分糖果
    public int distributeCandies(int[] candyType) {
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i <candyType.length ; i++) {
            set.add(candyType[i]);
        }
        return Math.min(set.size(),candyType.length/2);

    }
    //键盘行
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        String s2 = "asdfghjklASDFGHJKL";
        String s3 = "zxcvbnmZXCVBNM";
        List<String> list = new ArrayList<>();
        for (int i = 0; i <words.length ; i++) {
            int n1=0;
            int n2=0;
            int n3=0;
            int len=words[i].length();
            for (int j = 0; j <len ; j++) {
                if(s1.contains(words[i].charAt(j)+"")) {
                    n1++;
                } else if(s2.contains(words[i].charAt(j)+"")) {
                    n2++;
                } else {
                    n3++;
                }
            }
            if(n1==len||n2==len||n3==len) {
                list.add(words[i]);
            }
        }
        return list.toArray(new String[list.size()]);

    }
    //只出现一次的数字
    public int[] singleNumber(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int[] result=new int[2];
        for (int i = 0; i <nums.length ; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i],1);
            } else {
                int tmp=map.get(nums[i]);
                map.put(nums[i],tmp+1);
            }
        }
        int i=0;
        for (int n:nums) {
            if(map.get(n)==1) {
                result[i++]=n;
            }
        }
        return result;


    }
    //重新排序得到2的幂
    public boolean reorderedPowerOf2(int n) {
        String s=countDigits(n);
        for(int i=1;i<=1e9;i<<=1){
            if(countDigits(i).equals(s)){
                return true;
            }
        }
        return false;
    }
    public String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            ++cnt[n % 10];
            n /= 10;
        }
        return new String(cnt);
    }
}
