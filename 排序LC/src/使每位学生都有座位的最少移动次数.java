import java.util.Arrays;

public class 使每位学生都有座位的最少移动次数 {
    public static int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int sum=0;
        for (int i = 0; i <seats.length ; i++) {
            if(seats[i]!=students[i]) {
                sum=sum+Math.abs(seats[i]-students[i]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] seats={3,1,5};
        int[] students={2,7,4};
        System.out.println(minMovesToSeat(seats, students));
    }
}
