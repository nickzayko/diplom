package utils;

import constants.ReCaptchaKeys;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ReCaptchaValidator {

    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify (String gReCaptchaResponse) {
        if (gReCaptchaResponse == null || gReCaptchaResponse.length() == 0) {
            return false;
        }

        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);

            // Открыть соединение (Connection) к URL выше
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) verifyUrl.openConnection();

            // Добавить информации Header в Request, чтобы приготовить отправку к server
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpsURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // Данные будут отправлены на Server
            String postParams = "secret=" + ReCaptchaKeys.SECRET_KEY + "&response=" + gReCaptchaResponse;

            // Send Request
            httpsURLConnection.setDoOutput(true);

            // Получить Output Stream (Выходной поток) соединения к Server.
            // Записать данные в Output Stream, значит отправить информацию на Server
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            outputStream.write(postParams.getBytes());

            outputStream.flush();
            outputStream.close();

            // Ответный код возвращает из Server
            int responseCode = httpsURLConnection.getResponseCode();
            System.out.println("responseCode=" + responseCode);

            // Получить Input Stream (Входной поток) Connection
            // чтобы прочитать данные отправленные от Server.
            InputStream inputStream = httpsURLConnection.getInputStream();

            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            // ==> {"success": true}
            System.out.println("Response: " + jsonObject);

            boolean success = jsonObject.getBoolean("success");
            return success;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
