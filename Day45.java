import java.sql.SQLOutput;
import java.util.*;

public class Day45 {
    //查找兄弟单词
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            String[] strings=new String[n];
            for (int i = 0; i <n ; i++) {
                strings[i]=sc.next();
            }
            Arrays.sort(strings);
            String x=sc.next();
            int k=sc.nextInt();
            int count=0;
            List<String> list=new ArrayList<>();
            for (int i = 0; i <n ; i++) {
                if(isBrother(x,strings[i])) {
                    count++;
                    list.add(strings[i]);
                }
            }
            System.out.println(count);
            if(k<=list.size()) {
                System.out.println(list.get(k-1));
            }
        }
    }

    private static boolean isBrother(String x, String string) {
        if(x==null||string==null) {
            return false;
        }
        if(x.length()!=string.length()) {
            return false;
        }
        if(x.equals(string)) {
            return false;
        }
        char[] chars1=x.toCharArray();
        char[] chars2=string.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i <chars1.length ; i++) {
            if(chars1[i]!=chars2[i]) {
                return false;
            }
        }
        return true;
    }

    //乒乓球框
    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            boolean flag=true;
            StringBuffer stringBuffer=new StringBuffer(sc.next());
            char[] chars=sc.next().toCharArray();
            for (char c:chars) {
                int index=stringBuffer.indexOf(String.valueOf(c));
                if(index!=-1) {
                    stringBuffer.deleteCharAt(index);
                } else {
                    System.out.println("No");
                    flag=false;
                    break;
                }
            }
            if(flag) {
                System.out.println("Yes");
            }
        }
    }
    public static void main2(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            String A=sc.next();
            String B=sc.next();
            HashMap<Character,Integer> map=new HashMap<>();
            for (int i = 0; i <A.length() ; i++) {
                if(map.containsKey(A.charAt(i))) {
                    map.put(A.charAt(i),map.get(A.charAt(i))+1);
                } else {
                    map.put(A.charAt(i),1);
                }
            }
            for (int i = 0; i <B.length() ; i++) {
                if(map.containsKey(B.charAt(i))) {
                    if(map.get(B.charAt(i))==1) {
                        map.remove(B.charAt(i));
                    } else {
                        map.put(B.charAt(i),map.get(B.charAt(i))-1);
                    }
                } else {
                    System.out.println("No");
                    return;
                }
            }
            System.out.println("Yes");
        }
    }

    private static boolean comp(String A, String B) {
        int j=0;
        for (int i = 0; i <B.length() ; i++) {
            int pos=A.indexOf(B.charAt(i),j);
            System.out.println(pos);
            if(pos<A.length()) {
                A.replace(B.charAt(i),' ');
                System.out.println(A);
            } else {
                return false;
            }
        }
        return true;
//        HashMap<Character,Integer> mapA=new HashMap<>();
//        for (int i = 0; i <A.length() ; i++) {
//            if(mapA.containsKey(A.charAt(i))) {
//                mapA.put(A.charAt(i),mapA.get(A.charAt(i))+1);
//            } else {
//                mapA.put(A.charAt(i),1);
//            }
//        }
//        HashMap<Character,Integer> mapB=new HashMap<>();
//        for (int i = 0; i <B.length() ; i++) {
//            if(mapB.containsKey(B.charAt(i))) {
//                mapB.put(B.charAt(i),mapB.get(B.charAt(i))+1);
//            } else {
//                mapB.put(B.charAt(i),1);
//            }
//        }
//        boolean flag=true;
//        Iterator<Character> entries=  mapB.keySet().iterator();
//        while (entries.hasNext()) {
//            Character key=entries.next();
//            if(mapA.containsKey(key)&&(mapB.get(key)>=mapA.get(key))) {
//                continue;
//            } else {
//                flag=false;
//                break;
//            }
//        }
//        return flag;
    }
}
