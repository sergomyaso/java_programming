import ru.nsu.sergomyaso.calculator.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    private static final String DEFAULT_COMMAND_PATH = "in.txt";

    public static void main(String[] args) throws CalculatorException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        try {
            StackCalculator calculator = new StackCalculator();
            calculator.calculate(DEFAULT_COMMAND_PATH);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }

}