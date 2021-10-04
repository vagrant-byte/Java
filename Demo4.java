package 算法第一天;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Demo4 {
    //回文数索引
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        while (num>0) {
            StringBuffer stringBuffer=new StringBuffer(scanner.next());
            int[] start=new int[1];
            int[] end=new int[1];
            if(IsPalindrome(stringBuffer,start,end)) {
                //本身就是回文
                System.out.println(-1);
            } else {
                stringBuffer.deleteCharAt(start[0]);
                if(IsPalindrome(stringBuffer,null,null)) {
                    System.out.println(start[0]);
                } else {
                    System.out.println(end[0]);
                }
            }
            num--;

        }
    }

    private static boolean IsPalindrome(StringBuffer stringBuffer, int[] start, int[] end) {
        boolean result=true;
        int i=0;
        int j=stringBuffer.length()-1;
        while (i<=j) {
            if(stringBuffer.charAt(i)==stringBuffer.charAt(j)) {
                i++;
                j--;
            } else {
                result=false;
                break;
            }
        }
        if(start!=null) {
            start[0]=i;
        }
        if(end!=null) {
            end[0]=j;
        }
        return result;

    }

    //把数组排成最小的数
    public String PrintMinNumber(int [] numbers) {
        if(numbers.length==0) {
            return "";
        }
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 0; i <numbers.length ; i++) {
            list.add(numbers[i]);
        }
        //重写排序算法
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String xs=o1+""+o2;
                String ys=o2+""+o1;
                return xs.compareTo(ys);
            }
        });
        String result="";
        for (Integer e:list) {
            result+=e;
        }
        return result;

    }

    //两个链表的第一个公共节点
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null) {
            return null;
        }
        int length1=mylength(pHead1);
        int length2=mylength(pHead2);
        int num=Math.abs(length1-length2);
        if(length1>length2) {
            while (num>0) {
                pHead1=pHead1.next;
                num--;
            }
        } else {
            while (num>0) {
                pHead2=pHead2.next;
                num--;
            }
        }
        while (pHead1!=null&&pHead2!=null) {
            if(pHead1==pHead2) {
                return pHead1;
            }
            pHead1=pHead1.next;
            pHead2=pHead2.next;
        }
        return null;

    }

    private int mylength(ListNode pHead) {
        int count=0;
        while (pHead!=null) {
            pHead=pHead.next;
            count++;
        }
        return count;
    }
}
