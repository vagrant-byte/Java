public class nextGreatestLetter {
    //寻找比目标字母大的最小字母
    public static char nextGreatestLetter(char[] letters, char target) {
        for (int i = 0; i <letters.length ; i++) {
            if(letters[i]>target) {
                return letters[i];
            }
        }
        return letters[0];
    }

    public static void main(String[] args) {
        char[] chars={'c','f','j'};
        char t='d';
        System.out.println(nextGreatestLetter(chars, t));
    }
}
