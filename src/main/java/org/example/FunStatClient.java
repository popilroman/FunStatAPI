package org.example;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FunStatClient {
    private static final String BASE_URL = "https://funstat.info"; //Базовый url
    private static final String API_KEY = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiI1Mzk3NTQ4MTMiLCJqdGkiOiIxMmY0YTZhMy03ZGI2LTRiZjMtYTZjOS1iMGIwN2ZmMTM0ZjciLCJleHAiOjE3NzEzNDY3Njl9.oq7WT75SUdcHj__B06Ls_m6EzFPZQ5plGWiBQGIeLTiPnLRtZnhKgPFK3nc9Auw8nmgZwmfYhcQGJVx5c_osQKRWdFVM65W4nFZtpgdaCtJHFHwgvOakFr1upWnzqI7NOLEQl7r_2RpYcn87ILCzUJGJ9kyHe7HdBasqBYUjhO4";

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public FunStatClient() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    //Метод для GET-запросов
    private JsonNode sendGetRequest(String endpoint) throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URL)
                .newBuilder()
                .addPathSegments(endpoint.replaceFirst("^/", "")) // Убираем лишний "/"
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",  "Bearer " + API_KEY)
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new IOException("Пустой ответ от сервера");
            }
            String responseBody = response.body().string();

            if (!response.isSuccessful()) {
                System.out.println("Ошибка запроса: " + response.code());
                System.out.println("Ответ сервера: " + responseBody);
                throw new IOException("Ошибка запроса: " + response.code());
            }

            return objectMapper.readTree(responseBody);
        }
    }

    // 1 Получить информацию о пользователе по username.
    public JsonNode resolveUsername(String username) throws IOException {
        return sendGetRequest("api/v1/users/resolve_username?name=" + username);
    }

    // 2 Получить основную информацию о пользователе по ID.
    public JsonNode getUserStatsMin(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/stats_min");
    }

    // 3 Получить полную информацию о пользователе по ID.
    public JsonNode getUserStats(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/stats");
    }

    // 4 Получить базовую информацию о пользователе по ID.
    public JsonNode getUserBasicInfo(String userId) throws IOException {
        return sendGetRequest("api/v1/users/basic_info_by_id?id=" + userId);
    }

    // 5 Получить количество групп пользователя.
    public JsonNode getUserGroupsCount(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/groups_count");
    }

    // 6 Получить сообщения пользователя.
    public JsonNode getUserMessages(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/messages");
    }

    // 7 Получить количество сообщений пользователя.
    public JsonNode getUserMessagesCount(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/messages_count");
    }

    // 8 Получить известные группы пользователя.
    public JsonNode getUserGroups(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/groups");
    }

    // 9 Получить историю изменений имени пользователя.
    public JsonNode getUserNamesHistory(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/names");
    }

    // 10 Получить историю изменений юзернейма пользователя.
    public JsonNode getUsernameHistory(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/usernames");
    }
}