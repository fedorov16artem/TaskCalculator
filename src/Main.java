import Controllers.Controller;
import Models.Calculator;
import Models.WriteExpression;
import Models.WriteResults;
import Views.ViewCall;

import javax.swing.text.View;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        WriteExpression writeExpression = new WriteExpression();
        WriteResults writeResults = new WriteResults();
        Controller controller = new Controller(calculator,writeExpression,writeResults);
        ViewCall viewCall = new ViewCall( controller, scanner);
        viewCall.main();

    }
}