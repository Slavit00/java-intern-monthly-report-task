import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which responsibility is to define report properties and to generate a CSV report
 * Takes the following definitions:
 * topPerformersThreshold - includes only the top X of the employees
 * useExperienceMultiplier - determines if the score should be calculated with the experience multiplier of the employee
 * periodLimit - includes only employees that have sales period under the indicated
 */
public class Report {
    private int topPerformersThreshold;
    private boolean useExperienceMultiplier;
    private int periodLimit;

    public Report(int topPerformersThreshold, boolean useExperienceMultiplier, int periodLimit) {
        this.topPerformersThreshold = topPerformersThreshold;
        this.useExperienceMultiplier = useExperienceMultiplier;
        this.periodLimit = periodLimit;
    }

    public void generateReport(List<Employee> employees) throws IOException {
        List<String[]> data = employees.stream()
                .filter(e -> e.getSalesPeriod() <= this.periodLimit)
                .sorted(Comparator.comparing(
                        // Sort the employees by score in descending order
                        e -> e.getScore(this.useExperienceMultiplier), (e1, e2) -> (int) (e2 - e1))
                )
                .limit(this.topPerformersThreshold)
                .map(e -> new String[]{e.getName(), e.getScore(this.useExperienceMultiplier).toString()})
                .collect(Collectors.toList());

        String filePath = "reports/EmployeeReport " + new Timestamp(System.currentTimeMillis()) + ".csv";
        try {
            File file = new File(filePath);

            if (!file.createNewFile()) {
                throw new FileAlreadyExistsException(filePath);
            }

            FileWriter fileWriter = new FileWriter(file);
            CSVWriter writer = new CSVWriter(fileWriter);

            String[] header = {"Name", "Score"};
            writer.writeNext(header);
            writer.writeAll(data);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
