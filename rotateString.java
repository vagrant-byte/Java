public class rotateString {
    //旋转字符串
    public static boolean rotateString(String s, String goal) {
        if(goal.length()!=s.length()) {
            return false;
        }
        String s1=s+s;
        return s1.contains(goal);
    }

    public static void main(String[] args) {
        String s="aa";
        String goal="a";
        System.out.println(rotateString(s, goal));
    }
}
