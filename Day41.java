import java.util.Scanner;
import java.util.Stack;

public class Day41 {
    //Emacs计算器
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            Stack<Integer> stack=new Stack<>();
            for (int i = 0; i <n ; i++) {
                String s=scanner.next();
                int sum=0;
                if(s.equals("+")) {
                    int num2=stack.pop();
                    int num1=stack.pop();
                    sum=num1+num2;
                    stack.push(sum);
                } else if(s.equals("-")) {
                    int num2=stack.pop();
                    int num1=stack.pop();
                    sum=num1-num2;
                    stack.push(sum);
                } else if(s.equals("*")) {
                    int num2=stack.pop();
                    int num1=stack.pop();
                    sum=num1*num2;
                    stack.push(sum);
                } else if(s.equals("/")) {
                    int num2=stack.pop();
                    int num1=stack.pop();
                    sum=num1/num2;
                    stack.push(sum);
                } else {
                    stack.push(Integer.parseInt(s));
                }
            }
            System.out.println(stack.pop());
        }
    }
    //五子棋
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            Character[][] s=new Character[20][20];
            for (int i = 0; i <20 ; i++) {
                String s1=scanner.next();
                for (int j = 0; j <20 ; j++) {
                    s[i][j]=s1.charAt(j);
                }
            }
            System.out.println(func(s)?"YES":"NO");
        }
    }

    private static boolean func(Character[][] s) {
        for (int i = 0; i <20 ; i++) {
            for (int j = 0; j <20 ; j++) {
                int right=1;
                int right_down=1;
                int down=1;
                int left_down=1;
                for (int k = 1; k <5 ; k++) {
                    if(s[i][j]=='.') {
                        continue;
                    }else {
                        if(j<16&&s[i][j]==s[i][j+k]) {
                            right++;
                        }
                        if(i<16&&j<16&&s[i][j]==s[i+k][j+k]) {
                            right_down++;
                        }
                        if(i<16&&s[i][j]==s[i+k][j]) {
                            down++;
                        }
                        if(i<16&&j>4&&s[i][j]==s[i+k][j-k]) {
                            left_down++;
                        }
                    }
                    if(right==5||right_down==5||down==5||left_down==5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
