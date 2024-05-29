import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (
                Reader reader = new FileReader(filePath);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                int size = csvRecord.size();
                String[] record = new String[size];
                for (int i = 0; i < size; i++) {
                    record[i] = csvRecord.get(i);
                }
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;

    }

}
