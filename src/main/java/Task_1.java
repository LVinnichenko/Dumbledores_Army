import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
В задаче №1 мы получаем текущее время, указанное на компьютере.

Следует написать GET-запрос, используя HTTP-библиотеку вашего языка (например, requests для Python или Apache HTTP Client для Java)
и получить текущее время с учетом вашего UTC.
В остальном задача выглядит аналогично. Надо написать программу, которая приветствует Вас следующим образом:
C 00 часов до 04 часов включительно программа при запуске пишет - "Доброй ночи, {username}"
С 05 часов до 09 часов включительно программа при запуске пишет - "Доброе утро, {username}"
С 10 часов до 16 часов включительно программа при запуске пишет - "Добрый день, {username}"
С 17 часов до 23 часов включительно программа при запуске пишет - "Добрый вечер, {username}"

Само собой, {username} должен заменяться на Ваше имя.
 */

public class Task_1 {

    public static int getTime() throws JSONException, IOException, ParseException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://worldtimeapi.org/api/ip");
        HttpResponse httpresponse = httpclient.execute(httpget);
        HttpEntity entity = httpresponse.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        String datetime = JsonPath.parse(json).read("$.datetime");

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = parser.parse(datetime);
        SimpleDateFormat formatter = new SimpleDateFormat("HH");

        return Integer.parseInt(formatter.format(date));
    }
    public static String getName() throws IOException {
        System.out.println("Как тебя зовут?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
    public static void getGreeting() throws IOException {
        int yourTime = 0;
        try {
            yourTime = getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String yourName = getName();

        if (yourTime <= 4 && yourTime >= 0){
            System.out.println("Доброй ночи, " + yourName);
        }
        else if (yourTime <= 9 && yourTime >= 5){
            System.out.println("Доброе утро, " + yourName);
        }
        else if (yourTime <= 16 && yourTime >= 10){
            System.out.println("Добрый день, " + yourName);
        }
        else if (yourTime <= 23 && 17 <= yourTime){
            System.out.println("Добрый вечер, " + yourName);
        }
        else {
            System.out.println("Сорян, " +  yourName + ", не удалось определить твое время");
        }
    }
}