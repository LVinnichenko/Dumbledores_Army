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
import java.util.Scanner;

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

    public static String getTime() throws JSONException, IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://worldtimeapi.org/api/ip");
        HttpResponse httpresponse = httpclient.execute(httpget);
        HttpEntity entity = httpresponse.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        String datetime = JsonPath.parse(json).read("$.datetime");
        String hourse = datetime.substring(11,13);

        return hourse;
    }
    public static String getName() throws IOException {
        System.out.println("Как тебя зовут?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String yourName = br.readLine();
        return yourName;
    }
    public static void getGreeting() throws IOException {
        String yourTime = getTime();
        String yourName = getName();

        if (Integer.parseInt(yourTime) <= 4 && Integer.parseInt(yourTime) >= 0){
            System.out.println("Доброй ночи, " + yourName);
        }
        if (Integer.parseInt(yourTime) <= 9 && Integer.parseInt(yourTime) >= 5){
            System.out.println("Доброе утро, " + yourName);
        }
        if (Integer.parseInt(yourTime) <= 16 && Integer.parseInt(yourTime) >= 10){
            System.out.println("Добрый день, " + yourName);
        }
        if (Integer.parseInt(yourTime) <= 23 && Integer.parseInt(yourTime) >= 17){
            System.out.println("Добрый вечер, " + yourName);
        }
    }
}