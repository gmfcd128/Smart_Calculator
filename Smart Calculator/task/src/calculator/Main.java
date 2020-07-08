package calculator;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Pattern assignmentPattern = Pattern.compile(".+=.+");
    static Pattern valuePattern = Pattern.compile("[+-]?\\d+|[a-zA-Z]+");
    static Pattern numberPattern = Pattern.compile("-?[1-9][0-9]*");
    //detect sequential identical operator and add them at once, be resolved and examined during postfix conversion
    static Pattern operatorPattern = Pattern.compile("\\++|-+|\\*+|\\/+|\\(|\\)");
    static Pattern dataSanitizer = Pattern.compile("^[a-zA-Z0-9+-\\\\*\\/\\=\\(|\\)]+$");
    static Pattern onlyLatinLetters = Pattern.compile("[A-Za-z]+");
    static Map<String, BigInteger> variables = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        String input = scanner.nextLine();
        while (!"/exit".equals(input)) {
            //user enters '/help'
            if ("/help".equals(input)) {
                System.out.println("The program calculates the sum of numbers");
            } else if (!"".equals(input)) {
                if (input.charAt(0) != '/') {
                    if (variables.containsKey(input)) {
                        //echo existing variable
                        System.out.println(variables.get(input));
                    } else {
                        input = input.replaceAll(" ", "");
                        if (assignmentPattern.matcher(input).matches()) {
                            String[] assignment = input.split("=");
                            if (!onlyLatinLetters.matcher(assignment[0]).matches()) {
                                System.out.println("Invalid identifier");
                            } else if (valuePattern.matcher(assignment[1]).matches()) {
                                if (variables.containsKey(assignment[1])) {
                                    variables.put(assignment[0], variables.get(assignment[1]));
                                } else if (numberPattern.matcher(assignment[1]).matches()) {
                                    variables.put(assignment[0], new BigInteger(assignment[1]));
                                } else {
                                    System.out.println("Unknown variable");
                                }
                            } else {
                                System.out.println("Invalid assignment");
                            }
                        } else if (valuePattern.matcher(input).matches()) {
                            //user input single value, print out directly
                            if (variables.containsKey(input)) {
                                System.out.println(variables.get(input));
                            } else if(numberPattern.matcher(input).matches()){
                                System.out.println(input);
                            } else {
                                System.out.println("Unknown variable");
                            }
                        } else if (dataSanitizer.matcher(input).matches()) {
                            ArrayList<String> infix = makeInfixExpression(input);
                            ArrayList<String> postfix = infixToPostfix(infix);
                            if (postfix != null) {
                                System.out.println(calculatePostfix(postfix));
                            } else {
                                System.out.println("Invalid expression");
                            }
                        } else {
                            System.out.println("Invalid expression");
                        }
                    }
                } else {
                    System.out.println("Unknown command");
                }
            }
            input = scanner.nextLine();
        }
        System.out.print("Bye!");
        return;
    }


    static int getPrecedance(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 0;
        } else if (operator.equals("*") || operator.equals("/") || operator.equals("(") || operator.equals(")")) {
            return 1;
        }
        return -1;
    }

    static ArrayList<String> makeInfixExpression(String input) {
        int index = 0;
        String temp = "";
        ArrayList<String> infix = new ArrayList<>();
        Matcher operatorMatcher = operatorPattern.matcher(input);
        while (index < input.length()) {
            if (input.charAt(index) <= '9' && input.charAt(index) >= '0'
                    || input.charAt(index) <= 'z' && input.charAt(index) >= 'a') {
                temp += input.charAt(index);
                if (index == input.length() - 1) {
                    infix.add(checkVariableValue(temp));
                }
                index++;
            } else if (operatorMatcher.find()) {
                //valid operator starts at index position
                if (operatorMatcher.start() == index) {
                    if (!temp.isEmpty()) {
                        //push operand onto the ArrayList
                        infix.add(checkVariableValue(temp));
                        temp = "";
                    }
                    while (index < operatorMatcher.end()) {
                        temp += input.charAt(index);
                        index++;
                    }
                    //add operator to infix array...
                    infix.add(temp);
                    temp = "";
                } else {
                    System.out.println("Invalid expression");
                    return null;
                }
            }
        }
        return infix;
    }

    static ArrayList<String> infixToPostfix(ArrayList<String> infix) {
        if (infix != null) {
            Deque<String> stack = new ArrayDeque<>();
            ArrayList<String> postfix = new ArrayList<>();
            for (String item : infix) {
                if (valuePattern.matcher(item).matches()) {
                    postfix.add(item);
                } else if (operatorPattern.matcher(item).matches()) {
                    String operator = resolveMultiOperator(item);
                    if(operator == null) {
                        return null;
                    }
                    if (stack.isEmpty()) {
                        stack.addFirst(operator);
                    } else if (stack.peekFirst().equals("(")) {
                        stack.addFirst(operator);
                    } else if (item.equals("(")) {
                        stack.addFirst(item);
                    } else if (item.equals(")")) {
                        do {
                            postfix.add(stack.pollFirst());
                        } while (!stack.isEmpty() && !stack.peekFirst().equals("("));
                        if (stack.isEmpty()) {
                            return null;
                        } else {
                            stack.pollFirst();
                        }
                    } else if (getPrecedance(stack.peekFirst()) < getPrecedance(operator)) {
                        stack.addFirst(operator);
                    } else if (getPrecedance(stack.peekFirst()) >= getPrecedance(operator)) {
                        do {
                            postfix.add(stack.pollFirst());
                        } while (!stack.isEmpty() && getPrecedance(stack.peekFirst()) >= getPrecedance(operator) && !stack.peekFirst().equals("("));
                        stack.addFirst(operator);

                    }
                }
            }
            if (stack.contains("(") || stack.contains(")")){
                return null;
            } else {
                while (!stack.isEmpty()) {
                    postfix.add(stack.pollFirst());

                }
            }

            return postfix;
        }
        return null;
    }

    static BigInteger calculatePostfix(ArrayList<String> postfix) {
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < postfix.size(); i++) {
            if (valuePattern.matcher(postfix.get(i)).matches()) {
                stack.addFirst(postfix.get(i));
            } else if (operatorPattern.matcher(postfix.get(i)).matches()) {
                String operator = postfix.get(i);
                //重要，後置表示法的除法及減法順序
                BigInteger value2 = new BigInteger(stack.pollFirst());
                BigInteger value1 = new BigInteger(stack.pollFirst());
                if (operator.equals("+")) {
                    stack.addFirst(value1.add(value2).toString(10));
                } else if (operator.equals("-")) {
                    stack.addFirst(value1.subtract(value2).toString(10));
                } else if (operator.equals("*")) {
                    stack.addFirst(value1.multiply(value2).toString(10));
                } else if (operator.equals("/")) {
                    stack.addFirst(value1.divide(value2).toString(10));
                }
            }
        }
        return new BigInteger(stack.pollFirst());
    }

    static String resolveMultiOperator(String operator) {
        if (operator.charAt(0) == '+' || operator.equals("/") ) {
            return "" + operator.charAt(0);
        } else if (operator.charAt(0) == '-') {
            if (operator.length() % 2 == 1) {
                return "-";
            } else {
                return "+";
            }
        } else if (operator.equals("*") || operator.equals("(") || operator.equals(")")) {
            return operator;
        } else {
            return null;
        }
    }

    static String checkVariableValue(String key) {
        if (variables.containsKey(key)) {
            return variables.get(key).toString(10);
        } else {
            return key;
        }
    }
}
