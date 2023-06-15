package Models;

import java.util.Stack;

public class Calculator {
    public double main(String expression) {

        return resultFromRPN(reversePolishNotation(expression));
    }

    /* Обратная польская запись */
    private String reversePolishNotation(String expression) {
        StringBuilder value = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int priority;
        expression = expression.replaceAll("//", "|");
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));

            switch (priority) {
                /* Математическая операция с приорететом -1*/
                case -1 -> {
                    value.append(' ');
                    while (getPriority(stack.peek()) != 1) {
                        value.append(stack.pop());
                    }
                    stack.pop();
                }
                case 0 -> value.append(expression.charAt(i));
                case 1 -> stack.push(expression.charAt(i));
                default -> {
                    value.append(' ');
                    while (!stack.empty()) {
                        if (getPriority(stack.peek()) >= priority) {
                            value.append(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(expression.charAt(i));
                }
            }
        }

        while (!stack.empty()) {
            value.append(stack.pop());
        }
        return value.toString();
    }
 /*  получение Обратная польской нотации в результат */
    private double resultFromRPN(String rpn) {
        StringBuilder value = new StringBuilder();
        /* */
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    value.append(rpn.charAt(i++));
                    if (i == rpn.length()) {
                        break;
                    }
                }

                stack.push(Double.parseDouble(String.valueOf(value)));
                value = new StringBuilder();
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                /* Математические значения */
                double firstValue = stack.pop(), secondValue = stack.pop();

                switch (rpn.charAt(i)) {
                    case '+' -> stack.push(secondValue + firstValue);
                    case '-' -> stack.push(secondValue - firstValue);
                    case '*' -> stack.push(secondValue * firstValue);
                    case '/' -> stack.push(secondValue / firstValue);
                    case '^' -> stack.push(Math.pow(secondValue, firstValue));
                    case '|' -> stack.push((double)((int)Math.round(secondValue) / (int)Math.round(firstValue)));
                    case '%' -> stack.push(secondValue % firstValue);
                }
            }
        }
        return stack.pop();
    }
    private int getPriority(char sign) {
        return switch (sign) {
            /* Математические операции с приоритетом 3 */
            case '*', '/', '^', '%', '|' -> 3;
            /* Математические операции с приоритетом 2 */
            case '+', '-' -> 2;
            /* Математические операции с приоритетом 1 */
            case '(' -> 1;
            /* Математические операции с приоритетом -1 */
            case ')' -> -1;
            /* Математические значения [0-9] с приоритетом 0 */
            default -> 0;
        };
    }
}
