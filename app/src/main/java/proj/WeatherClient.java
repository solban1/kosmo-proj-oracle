package proj;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.AbstractButton;

public class WeatherClient {

    public static void setWeatherAsync(AbstractButton btn, String prefix) {
        // 매시 30분의 데이터가 해당 시 45분에 발표됨(+1분 여유)
        LocalDateTime baseDateTime = LocalDateTime.now()
                .minusMinutes(46L)
                .truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(30L);
        String baseDate = baseDateTime.format(DateTimeFormatter.ofPattern("uuuuMMdd"));
        String baseTime = baseDateTime.format(DateTimeFormatter.ofPattern("HHmm"));

        HttpRequest request = HttpRequest.newBuilder(URI.create(
                "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?serviceKey=njJZGMUi7L8EWtSAuH1Rpf5wSlzldENkbaxgj4q5tX0%2B2H169vHVNIYWAusL62ihFyH5nnT%2FMq7%2FDgonu71QZQ%3D%3D&base_date="
                        + baseDate
                        + "&base_time="
                        + baseTime
                        + "&nx=58&ny=125&numOfRows=1000"))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        WeatherXMLHandler handler = new WeatherXMLHandler();
        client.sendAsync(request, BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body)
                .thenAccept(is -> {
                    handler.parse(is);
                    btn.setText(prefix + handler.getCurrentWeather());
                });
    }

    public static void setWeatherAsync(AbstractButton btn) {
        setWeatherAsync(btn, "");
    }
}
