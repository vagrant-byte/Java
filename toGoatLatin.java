public class toGoatLatin {
    //山羊拉丁文
    public static String toGoatLatin(String sentence) {
        String[] strings=sentence.split(" ");
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <strings.length ; i++) {
            char tmp=strings[i].toLowerCase().charAt(0);
            if(tmp=='a'||tmp=='e'||tmp=='i'||tmp=='o'||tmp=='u') {
                stringBuilder.append(strings[i]+"ma");
            } else {
                char cur=strings[i].charAt(0);
                for (int j = 1; j <strings[i].length() ; j++) {
                    stringBuilder.append(strings[i].charAt(j));
                }
                stringBuilder.append(cur+"ma");
            }
            for (int j = 0; j <i+1; j++) {
                stringBuilder.append('a');
            }
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        String s="I speak Goat Latin";
        System.out.println(toGoatLatin(s));
    }
}
