package Models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class WriteExpression {
    private List<String> expressions = new ArrayList<>();
    /*  Файл по умолчанию содержаший массив данных */
    private final Path logWriteExpression = Paths.get(System.getProperty("user.dir")+"\\WriteExpression.txt");
    /* Чтение данных из файла по умолчанию */
    public  List<String> getWriteExpression() {
        try{ /*  Получение массива данных по умолчанию */
            expressions = Files.readAllLines(logWriteExpression);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expressions;
    }
    /* Записаь данных в файл по умолчанию */
    public void setWriteExpression(String expression) {
        try{ /* Проверка сущетсвует ли файл по умолчанию   */
            if (Files.notExists(logWriteExpression)){
                Files.createFile(logWriteExpression);
            }
            expressions.add(expression);
            Files.write(logWriteExpression, expressions, StandardOpenOption.APPEND);
            System.out.println("Успешно сохранил выражение"+logWriteExpression.getParent()+ "\\" + logWriteExpression.getFileName()+ ".");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /* Записаь в пользователский файл  */
    public void setWriteExpressionRecord(Path logExpressionRecordPath, String expression) {
        try { /*Проверка сущетсвет ли пользователский файл */
            if (Files.notExists(logExpressionRecordPath)){
                Files.createFile(logExpressionRecordPath);
        }       /*Запись данных в пользовательский файл  */
            Files.write(logExpressionRecordPath,(expressions), StandardOpenOption.APPEND);
            System.out.println("Успешно сохранил выражение "+logExpressionRecordPath.getParent()+ "\\" + logExpressionRecordPath.getFileName()+ ".");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
