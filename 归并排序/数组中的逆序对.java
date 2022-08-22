package 排序.归并排序;

public class 数组中的逆序对 {
    class Solution {
        public int reversePairs(int[] nums) {
            if(nums==null||nums.length<2) {
                return 0;
            }
            return procrss(nums,0,nums.length-1);
        }
        public int procrss(int[] arr,int left,int right) {
            if(left==right) {
                return 0;
            }
            int mid=left+((right-left)>>1);
            return procrss(arr,left,mid)+procrss(arr,mid+1,right)+merge(arr,left,mid,right);
        }
        public int merge(int[] arr,int left,int mid,int right) {
            int p1=left;
            int p2=mid+1;
            int res=0;
            int[] tmp=new int[right-left+1];
            int help=0;
            while(p1<=mid&&p2<=right) {
                res+=arr[p1]>arr[p2]?(right-p2+1):0;
                tmp[help++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++];
            }
            while(p1<=mid) {
                tmp[help++]=arr[p1++];
            }
            while(p2<=right) {
                tmp[help++]=arr[p2++];
            }
            for(int i=0;i<tmp.length;i++) {
                arr[left+i]=tmp[i];
            }
            return res;
        }
    }
}
