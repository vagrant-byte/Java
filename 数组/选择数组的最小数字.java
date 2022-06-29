package 数组;

public class 选择数组的最小数字 {
    public int minArray(int[] numbers) {
        for (int i = 1; i <numbers.length ; i++) {
            if(numbers[i]<numbers[i-1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }
}
