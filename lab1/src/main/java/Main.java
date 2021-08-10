import ru.nsu.sergomyaso.handlers.*;

import java.io.IOException;


public class Main {

    public static void main(String args[]) {
        Handler handler = new TextHandler(new WordsLoader(new RegularParser()), new CountAnalyzer(), new TableExporter());
        try {
            handler.handle();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
