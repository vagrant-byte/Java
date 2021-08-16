package 寒假作业;

import java.util.*;

public class HomeWork {
    //在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个
    //函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。时间复杂度小于O(n) ,空间复杂度O(1).
    public static boolean find(int[][] array, int n) {
        int row=array.length;//行
        int col=array[0].length;//列
        if(row==0||col==0) {
            return false;
        }
        if(n<array[0][0]||n>array[row-1][col-1]) {
            return false;
        }
        int i=0;
        int j=col-1;
        while (i<row&&j>=0) {
            if(n<array[i][j]) {
                j--;
            } else if(n>array[i][j]) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
    //调整数组中元素的位置，使得奇数位于偶数之前.
    public static int[] swap(int[] array) {
        int left=0;
        int right=array.length-1;
        while (left<right) {
            if(array[left]%2!=0) {
                left++;
            }else if(array[right]%2==0) {
                right--;
            } else {
                int tmp=array[left];
                array[left]=array[right];
                array[right]=tmp;
                left++;
                right--;
            }
        }
        return array;
    }
    public static int count1(int n) {
        int count=0;
        int i=0;
        for ( i = 0; i < 32; i++) {
            if( ((n>>i)&1) == 1) {
                count++;
            }
        }
        return count;
    }
    public static int count(int n) {
        int count=0;
        while (n!=0) {
            count++;
            n=n&(n-1);
        }
        return count;
    }
    //求一个有序数组中，两个值相加为key的数字，返回这两个数字的下标
    public static int[] sum(int[] array,int key) {
        int left=0;
        int right=array.length-1;
        while (left<right) {
            if (array[left]+array[right]==key) {
                return new int[] {left,right};
            } else if (array[left]+array[right]>key) {
                right--;
            } else {
                left++;
            }
        }
        return null;
    }
    public static void main2(String[] args) {
        int[] array=new int[] {2,4,3,5,7,8};
        int[] a=swap(array);
        for (int i = 0; i <a.length ; i++) {
            System.out.println(a[i]+" ");
        }
    }
    //求一个数组中前k个最小的数字
    public static int[] minKArray(int[] array,int k) {
        if(k==0||array.length==0) {
            return new int[0];
        }
        Queue<Integer> queue=new PriorityQueue<>((v1,v2)->v2-v1);
        for (int num:array) {
            if(queue.size()<k) {
                queue.offer(num);
            } else if(queue.peek()>num) {
                queue.poll();
                queue.offer(num);
            }
        }
        int[] res=new int[queue.size()];
        int index=0;
        for (int num:queue) {
            res[index++]=num;
        }
        return res;
    }
    //找出无序数组当中，出现次数超过数组长度一半的数字
    public static int selectHalfNum(int[] array) {
        Arrays.sort(array);
        int index=array.length/2;
        return array[index];
    }
    //输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的
    //最大值。要求时间复杂度为O(n)
    public static int findMax(int[] array) {
        if(array==null||array.length<1) {
            System.out.println("数组为空");
        }
        int max=Integer.MIN_VALUE;
        int tmpMax=0;
        for (int i:array) {
            if(tmpMax<=0) {
                tmpMax=i;
            } else {
                tmpMax+=i;
            }
            if(max<tmpMax) {
                max=tmpMax;
            }
        }
        return max;
    }
    //一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
    public static void findFirst(int[] array) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <array.length ; i++) {
            if(map.containsKey(array[i])) {
                int j=map.get(array[i]);
                map.put(array[i],j+1);
            } else {
                map.put(array[i],1);
            }
        }
        ArrayList<Integer> arrayList=new ArrayList<>();
        for (int i = 0; i <array.length ; i++) {
            if(map.get(array[i])==1) {
                arrayList.add(array[i]);
                System.out.print(array[i]+" ");
            }
        }
    }
    //将一个数组从左开始前k个字符进行旋转：左旋数组
    public static String reverse(String str,int start,int end) {
        char[] chars=str.toCharArray();
        while (start<end) {
            char tmp=chars[start];
            chars[start]=chars[end];
            chars[end]=tmp;
            start++;
            end--;
        }
        return String.copyValueOf(chars);
    }
    public static void leftArray(String str,int k) {
        if(str==null||k>str.length()) {
            return;
        }
        str=reverse(str,0,k-1);
        str=reverse(str,k,str.length()-1);
        str=reverse(str,0,str.length()-1);
        System.out.println(str);
    }
    //字符串逆置，如 "I am a student" 逆置为 "student a am I"
    public static void reverseString(String str) {
        String[] strings=str.split(" ");
        int start=0;
        int end=strings.length-1;
        while (start<end) {
            String tmp=strings[start];
            strings[start]=strings[end];
            strings[end]=tmp;
            start++;
            end--;
        }
        for (int i = 0; i <strings.length ; i++) {
            System.out.print(strings[i]+" ");
        }
    }
    //字符串压缩 “aabbccdaa" -> "a2b2c2d1a2"
    public static String stringCompress(String str) {
        StringBuilder stringBuilder=new StringBuilder();
        char[] chars=str.toCharArray();
        char ch=str.charAt(0);
        int count=1;
        for (int i = 1; i <str.length() ; i++) {
            if(str.charAt(i)==ch) {
                count++;
                continue;
            }
            stringBuilder.append(ch).append(count);
            ch=str.charAt(i);
            count=1;
        }
        stringBuilder.append(ch).append(count);
        return stringBuilder.toString();
    }

    public static void main6(String[] args) {
       String s="i am a student";
       reverseString(s);
    }
    public static void main1(String[] args) {
        int[][] array={{1,2,3,4},{5,6,7,8},{9,10,12,11},{13,14,15,16}};
        int n=19;
        System.out.println(find(array, n));
    }

    public static void main4(String[] args) {
        int[] array={1,-2,3,10,-4,7,2,-5};
        System.out.println(findMax(array));

    }
    public static boolean foo(char c) {
        System.out.print(c);
        return true;
    }
        public static void main3( String[] args ) {
            int i = 0;
            for ( foo('A'); foo('B') && (i < 2); foo('C')) {
                i++ ;
                foo('D');
            }
        }
    public static void print(){
        System.out.println("MTDP");
    }
    public static void main5(String[] args) {
        try{
            ((HomeWork)null).print();
        }catch(NullPointerException e){
            System.out.println("NullPointerException");
        }
    }

    public static void main(String[] args) {
        int[][] x=new int[3][2];
        System.out.println(x.length);
    }

        public HomeWork() {
            System.out.println("HelloA");
        } {
            System.out.println("i am A class");
        } static {
            System.out.println("static A");
        }
    }
    class Test extends HomeWork{
        public Test() {
            System.out.println("TestClass");
        } {
            System.out.println("i am TestClass");
        }
        static {
            System.out.println("static TestClass");
        }
        public static void main7(String[] args) {
            new Test();
        }
}

