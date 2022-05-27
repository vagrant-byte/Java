public class findClosest {
    //单词距离
    public int findClosest(String[] words, String word1, String word2) {
        int i=-10000;
        int j=10000;
        int min=Integer.MAX_VALUE;
        for (int k = 0; k <words.length ; k++) {
            if(words[k].equals(word1)) {
                i=k;
            }
            if(words[k].equals(word2)) {
                j=k;
            }
            min=Math.min(min,Math.abs(j-i));
        }
        return min;
    }
}
