import java.io.OutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            URL url = new URL("http://localhost:8000/add");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String data = "num1=" + num1 + "&num2=" + num2;
            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(out.length));

            OutputStream os = conn.getOutputStream();
            os.write(out);

            InputStream is = conn.getInputStream();
            byte[] response = is.readAllBytes();
            String result = new String(response);

            System.out.println("Result from server: " + result);

            os.close();
            is.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
