import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String dataFileName = args[0];
        String reportDefinitionFileName = args[1];

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(dataFileName));
        List<Employee> employees = gson.fromJson(reader, new TypeToken<List<Employee>>() {}.getType());

        reader = new JsonReader(new FileReader(reportDefinitionFileName));
        Report report = gson.fromJson(reader, Report.class);

        report.generateReport(employees);
    }
}
