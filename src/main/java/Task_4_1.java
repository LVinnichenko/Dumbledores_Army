import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

public class Task_4_1 {

    public static int[] numbers;

    static {
        System.out.println("Введите числа через пробел");
        try {
            String text = Helpers.getText();
            numbers = Arrays.stream(text.split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int n;

    static {
        System.out.println("Введите N:");
        try {
            n = Integer.parseInt(Helpers.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] getDifference(int[] numbers, int n) {
        int[] diff = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            diff[i] = n - numbers[i];
        }

        return diff;
    }

    public static boolean getResolution(int[] numbers, int[] diff, int n) {
        boolean res = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                //System.out.println(String.valueOf(numbers[i] + diff[j]));
                if (numbers[i] + diff[j] == n && j != i) {

                    res = true;
                }
            }
        }
        return res;
    }



    public static void getResult(){

        System.out.println(getResolution(numbers, getDifference(numbers, n), n));

    }
}
