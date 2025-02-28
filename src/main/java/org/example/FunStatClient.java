package org.example;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FunStatClient {
    private static final String BASE_URL = "https://funstat.info"; //Базовый url
    private static final String API_KEY = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiI4MDUwNzQ4MjQ5IiwianRpIjoiMzNlODliNWQtN2UyNi00ZDhkLWE0MmQtNDE1NmYzMDMxOTVlIiwiZXhwIjoxNzcyMDg3MTU0fQ.t5OCpWJZJ_rO8izEgY9-HiHGnFDXpuKd1qlMQEBVo6D2dVVlWXg4RZJ5Vz3ddGyFMlW0sUrTkqSOrpJXbfeOc-on104EOTYziCCgJxTNXEghbyRTbQ90--dXQLXeFQiEROz6NFK0vBhAP728P0XXrg-yMU1CZRZddq8vpnDk3FA";

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
        HttpUrl url = HttpUrl.parse(BASE_URL)
                .newBuilder()
                .addPathSegments("api/v1/users/resolve_username")
                .addQueryParameter("name", username)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
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
        HttpUrl url = HttpUrl.parse(BASE_URL)
                .newBuilder()
                .addPathSegments("api/v1/users/basic_info_by_id")
                .addQueryParameter("id", userId)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
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

    // 5 Получить количество групп пользователя.
    public JsonNode getUserGroupsCount(String userId) throws IOException {
        return sendGetRequest("api/v1/users/" + userId + "/groups_count");
    }

    // 6 Получить сообщения пользователя.
//    public JsonNode getUserMessages(String userId) throws IOException {
//        return sendGetRequest("api/v1/users/" + userId + "/messages");
//    }
    public JsonNode getUserMessages(String userId, String page, String pageSize) throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URL)
                .newBuilder()
                .addPathSegments("api/v1/users/" + userId + "/messages")
                .addQueryParameter("page", page)
                .addQueryParameter("pageSize", pageSize)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
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

    // 11 Получить основную информацию, ссылки и статистику за сутки
    public JsonNode getGroupBasicInfo(String groupId) throws IOException {
        return sendGetRequest("/api/v1/groups/" + groupId);
    }

    public static void saveToJSONFile(JsonNode jsonNode, String filePath) throws IOException {
        String jsonString = jsonNode.toPrettyString();
        Path path = Paths.get(filePath);
        Files.write(path, jsonString.getBytes());
        System.out.println("✔\uFE0F Ответ выгружен в JSON-файл");
    }
}