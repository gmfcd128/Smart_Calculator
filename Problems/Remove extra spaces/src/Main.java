import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RemoveExtraSpacesProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        text = text.trim();

        // write your code here
        final String regex = "[ \\t]+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.replaceAll(" "));
    }
}