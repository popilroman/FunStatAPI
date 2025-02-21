package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;
import org.example.FunStatClient;
import java.io.IOException;

public class Main {

    private final static int PAGE_SIZE = 1000;

    public static void main(String[] args) {
        String helpText = "ПЕРЕЧЕНЬ ДОСТУПНЫХ API-МЕТОДОВ:\n" +
                "ПОЛЬЗОВАТЕЛИ:\n" +
                "1.  (ЦЕНА 0.25)     /api/v1/users/resolve_username     получить информацию о пользователе\n" +
                "2.  (ЦЕНА 0)        /api/v1/users/{id}/stats_min       получить основную информацию о пользователе \n" +
                "3.  (ЦЕНА 1)        /api/v1/users/{id}/stats           получить полную информацию о пользователе\n" +
                "4.  (ЦЕНА 0.25)     /api/v1/users/basic_info_by_id     получить информацию по ID\n" +
                "5.  (ЦЕНА 0)        /api/v1/users/{id}/groups_count    получить количество групп пользователя\n" +
                "6.  (ЦЕНА 10)       /api/v1/users/{id}/messages        получить сообщения пользователя\n" +
                "7.  (ЦЕНА 0)        /api/v1/users/{id}/messages_count  получить количество сообщений пользователя\n" +
                "8.  (ЦЕНА 5)        /api/v1/users/{id}/groups          получить известные группы пользователя\n" +
                "9.  (ЦЕНА 3)        /api/v1/users/{id}/names           получить историю изменений имени пользователя \n" +
                "10. (ЦЕНА 3)        /api/v1/users/{id}/usernames       получить историю изменений юзернейма пользователя \n" +
                "ЧАТЫ/ГРУППЫ: \n" +
                "11. (ЦЕНА 0)        /api/v1/groups/{id}                получить основную информацию, ссылки и статистику за сутки";

        System.out.println(helpText);
        Scanner sc = new Scanner(System.in);
        System.out.print("\nВведите № метода: ");
        int number = sc.nextInt();

        FunStatClient client = new FunStatClient();
        try {
            switch (number) {
                case (1):
                    client.saveToJSONFile(client.resolveUsername(inputUserUsername()), "data/resolveUsername.json");
                    break;
                case (2):
                    client.saveToJSONFile(client.getUserStatsMin(inputUserId()), "data/userStatsMin.json");
                    break;
                case (3):
                    client.saveToJSONFile(client.getUserStats(inputUserId()), "data/userStats.json");
                    break;
                case (4):
                    client.saveToJSONFile(client.getUserBasicInfo(inputUserId()), "data/userBasicInfo.json");
                    break;
                case (5):
                    client.saveToJSONFile(client.getUserGroupsCount(inputUserId()), "data/groupsCount.json");
                    break;
                case (6):
                    client.saveToJSONFile(client.getUserMessages(inputUserId(), inputPage(), String.valueOf(PAGE_SIZE)), "data/messages.json");
                    break;
                case (7):
                    client.saveToJSONFile(client.getUserMessagesCount(inputUserId()), "data/messagesCount.json");
                    break;
                case (8):
                    client.saveToJSONFile(client.getUserGroups(inputUserId()), "data/groups.json");
                    break;
                case (9):
                    client.saveToJSONFile(client.getUserNamesHistory(inputUserId()), "data/namesHistory.json");
                    break;
                case (10):
                    client.saveToJSONFile(client.getUsernameHistory(inputUserId()), "data/usernameHistory.json");
                    break;
                case (11):
                    client.saveToJSONFile(client.getGroupBasicInfo(inputGroupId()), "data/groupBasicInfo.json");
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String inputUserId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите id пользователя: ");
        String userId = sc.next();
        return userId;
    }
    public static String inputUserUsername() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите username пользователя: ");
        String userId = sc.next();
        return userId;
    }
    public static String inputGroupId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите id чата / группы: ");
        String groupId = sc.next();
        return groupId;
    }
    public static String inputPage () {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите № страницы: ");
        String page = sc.next();
        return page;
    }
}