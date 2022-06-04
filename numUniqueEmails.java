import java.util.HashSet;
import java.util.Set;

//独特的电子邮件
public class numUniqueEmails {
    public int numUniqueEmails(String[] emails) {
       Set<String> set=new HashSet<>();
       int len=emails.length;
        for (int i = 0; i <len ; i++) {
            String cur=emails[i];
            int index=cur.indexOf("@");
            String local=cur.substring(0,index);
            String domain=cur.substring(index);
            StringBuilder stringBuilder=new StringBuilder();
            char[] chars=local.toCharArray();
            for (int j = 0; j <chars.length ; j++) {
                if(chars[j]=='.') {
                    continue;
                }
                if(chars[j]=='+') {
                    break;
                }
                stringBuilder.append(chars[j]);
            }
            set.add(stringBuilder.toString()+domain);
        }
        return set.size();

    }
}
