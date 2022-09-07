

public class 优势洗牌 {
    public static int[] advantageCount(int[] nums1, int[] nums2) {
        int index=0;
        while (index<nums1.length) {
            if(nums1[index]>nums2[index]) {
                int min=index;
                for (int i = index+1; i <nums1.length ; i++) {
                    if(nums1[i]>nums2[index]&&nums1[i]<nums1[min]) {
                        min=i;
                    }
                }
                swap(nums1,index,min);
                index++;
            }else {
                boolean flag=false;
                int min=index;//表示最小的数的下标
                for (int i = index+1; i <nums1.length ; i++) {
                    if(nums1[i]>nums2[index]) {
                        swap(nums1,index,i);
                        index++;
                        flag=true;
                        break;
                    }
                    if(nums1[i]<min) {
                        min=i;
                    }
                }
                if(!flag) {
                    //从当前位置向后都没有比num2[index]位置大的数
                    //将num1[index]位置向后最小的数和index进行交换
                    swap(nums1,index,min);
                    index++;
                }
            }
        }
        return nums1;
    }
    public static void swap(int[] tmp,int i,int j) {
        int num=tmp[i];
        tmp[i]=tmp[j];
        tmp[j]=num;
    }

    public static void main(String[] args) {
        int[] num1={15448,14234,13574,19893,6475};
        int[] num2={14234,6475,19893,15448,13574};
        int[] r=advantageCount(num1,num2);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}
