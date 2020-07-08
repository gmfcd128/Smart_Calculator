import java.util.*;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        // write your code here
        String[] strArray = str.split(" ");
        Set<Integer> output = new TreeSet<>();
        for (String number : strArray) {
            output.add(Integer.parseInt(number));
        }
        return output;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        // write your code here
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() > 10) {
                iterator.remove();
            }
        }
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}