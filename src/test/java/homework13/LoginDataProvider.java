package homework13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider {
    public static List<LoginData> getPositiveLoginData() throws IOException {
        return readLoginDataFromCSV("/positiveTest.csv");
    }

    public static List<LoginData> getNegativeLoginData() throws IOException {
        return readLoginDataFromCSV("/negativeTest.csv");
    }

    public static List<LoginData> getMissingFieldsLoginData() throws IOException {
        return readLoginDataFromCSV("/missingFields.csv");
    }

    private static List<LoginData> readLoginDataFromCSV(String filePath) throws IOException {
        List<LoginData> data = new ArrayList<>();
        InputStream inputStream = LoginDataProvider.class.getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IOException("File not found: " + filePath);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // Пропускаємо заголовок (якщо є)
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(new LoginData(values[0], values[1], values.length > 2 ? values[2] : null));
            }
        }
        return data;
    }
}