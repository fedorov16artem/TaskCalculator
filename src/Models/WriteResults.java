package Models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class WriteResults {
    private List<String> results = new ArrayList<>();
    private final Path writeResults = Paths.get(System.getProperty("user.dir")+"\\WriteResults.txt");


    public  List<String> getWriteResults() {
        try {
            results = Files.readAllLines(writeResults);
        } catch ( Exception e){
            System.out.println(e.getMessage());
        }
        return results;
    }

    public void setWriteResults(Double result) {
        try{  /* Проверка существует ли файл по умолчанию  */
            if (Files.notExists(writeResults)){
                Files.createFile(writeResults);
            }
            /* Добавление нового значения */
            results.add(Double.toString(result));
            /* ЗАписаь в файл данных */
            Files.write(writeResults,results, StandardOpenOption.APPEND);
            System.out.println("Результат сохранен"+ writeResults.getParent()+ "\\" + writeResults.getFileName()+ ".");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void setWriteResultsRecord (String logWriteResultsRecord){
        try{
            if (Files.notExists(writeResults)){
                System.out.println("Файл"+writeResults.getParent() + "\\" + writeResults.getFileName() + "не найден.");
            } else { /*  Разшрение файлов  */
                int md = logWriteResultsRecord.indexOf(".md");
                int txt = logWriteResultsRecord.indexOf(".txt");
                int log = logWriteResultsRecord.indexOf(".log");
                Path logResultsRecordPath = Paths.get(logWriteResultsRecord);

                /* Если указали имя файла  и разширение*/
                if (md > 0||txt > 0||log > 0){
                    Files.copy(writeResults,logResultsRecordPath);
                    System.out.println("Резульат сохранен" + logResultsRecordPath.getParent()+ "\\"+ logResultsRecordPath.getFileName()+ ".");
                    /* Если указали только путь файла  */
                } else if (!logWriteResultsRecord.contains(".")){
                    Files.copy(writeResults,Paths.get("log.log"));
                    System.out.println("Резульат сохранен"+ System.getProperty("user.dir")+ "\\" + "log.log");
                    /* Если указали асболютный путь с имением файла */
                } else if (logResultsRecordPath.isAbsolute()){
                    Files.copy(writeResults,logResultsRecordPath);
                    System.out.println("Резульат сохранен"+ logResultsRecordPath.getParent()+ "\\" + logResultsRecordPath.getFileName() + ".");
                } else {
                    System.out.println("Резульат сохранен" + writeResults.getParent()+ " \\" + writeResults.getFileName() + ".");
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
