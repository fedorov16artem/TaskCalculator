package Controllers;

import Models.Calculator;
import Models.WriteExpression;
import Models.WriteResults;

import java.nio.file.Paths;
import java.util.List;

public class Controller {
    private final Calculator calculator;
    private final WriteExpression writeExpression;
    private final WriteResults writeResults;


    public Controller(Calculator calculator, WriteExpression writeExpression, WriteResults writeResults) {           /* Конструктоор класса */
        this.calculator = calculator;
        this.writeExpression = writeExpression;
        this.writeResults = writeResults;
    }

    public double calculator(String expression) {                                                                   /* Получение результата*/

        return calculator.main(expression);
    }

    public void setLog(String expression) {                                                                        /*Запись выражения в файл по умолчанию*/
        writeExpression.setWriteExpression(expression);
        writeResults.setWriteResults(calculator.main(expression));
    }

    public void setLogWriteExpression(String logExpressionRecordPath,String expression){                          /* Запись в пользовательский файл*/
        writeExpression.setWriteExpressionRecord(Paths.get(logExpressionRecordPath),expression);
    }

    public void setLogResultsCustom(String logWriteResultsRecord) {                                            /*Запись результат в пользовательский файл */
        writeResults.setWriteResultsRecord(logWriteResultsRecord);
    }

    public void history() {                                                                                      /* История резултатов */
        List<String> expressions = writeExpression.getWriteExpression(), results = writeResults.getWriteResults();
        for (int i = 0; i < expressions.size(); i++) {
            System.out.println(expressions.get(i) + "\t= " + results.get(i));
        }
    }

    public void exit() {                                                                                        /*Завершение работы */
        System.out.print("Выход");
        System.exit(1);
    }


}
