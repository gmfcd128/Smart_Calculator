import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        // write your code here
        SortedMap<String, Integer> output = new TreeMap<>();
        for (String word : strings) {
            if (output.containsKey(word)) {
                output.put(word, output.get(word) + 1);
            } else {
                output.put(word, 1);
            }
        }
        return output;
    }

    public static void printMap(Map<String, Integer> map) {
        // write your code here
        map.forEach((character, count) -> System.out.println(character + " : " + count));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}