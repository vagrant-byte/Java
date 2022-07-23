package 排序;

public class 比较版本号 {
    public static int compare (String version1, String version2) {
        // write code here
        String[] strings1=version1.split("\\.");
        String[] strings2=version2.split("\\.");
        int[] arr1=new int[strings1.length];
        int[] arr2=new int[strings2.length];
        for (int i = 0; i <arr1.length ; i++) {
            arr1[i]=Integer.parseInt(strings1[i]);
        }
        for (int i = 0; i <arr2.length ; i++) {
            arr2[i]=Integer.parseInt(strings2[i]);
        }
        int i=0;
        for (; i <Math.min(arr1.length,arr2.length) ; i++) {
            if(arr1[i]<arr2[i]) {
                return -1;
            }
            if(arr1[i]>arr2[i]) {
                return 1;
            }
        }
        if(arr1.length>i) {
            for (int j = i; j <arr1.length ; j++) {
                if(arr1[j]!=0) {
                    return 1;
                }
            }
            return 0;
        }else {
            for (int j = i; j <arr2.length ; j++) {
                if(arr2[j]!=0) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        String s1="1.0";
        String s2="1.0.0";
        int n=compare(s1, s2);
        System.out.println(n);
    }
}
