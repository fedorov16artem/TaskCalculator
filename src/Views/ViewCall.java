package Views;

import Controllers.Controller;

import java.util.Scanner;

public class ViewCall {
    private final Controller controller;
    private final Scanner scanner;
    public ViewCall(Controller controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void main() {
        try {
            System.out.println("Навигация: \n1. Калькулятор \n2. Показать сохраненые результаты \n3. Сохранение результата  \n4. Выход");

            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("Введите выражение: ");
                    String expression = scanner.next();
                    System.out.println("Результат выражения: \n" + controller.calculator(expression));
                    controller.setLog(expression);
                    System.out.println("Сохраните выражение в отдельном файле? (Да/Нет)");
                    if (scanner.next().equals("YES")) {
                        System.out.println("Введите полное имя файла (Разрешены абсолютные пути): ");
                        String logExpressionRecord = scanner.next();
                        controller.setLogWriteExpression(logExpressionRecord, expression);
                    } else {
                        controller.exit();
                    }
                }
                case 2 -> controller.history();
                case 3 -> {

                    System.out.println("Введите полное имя файла (Расширение, абсолютный путь): ");
                    String logOfResultCustom = scanner.next();
                    controller.setLogResultsCustom(logOfResultCustom);
                }
                default -> controller.exit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


