package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;
import org.example.FunStatClient;
import java.io.IOException;

public class Main {
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
//                    System.out.println(client.resolveUsername(inputUserUsername()).toPrettyString());
                    client.saveToJSONFile(client.resolveUsername(inputUserUsername()), "resolveUsername.json");
                    break;
                case (2):
//                    System.out.println(client.getUserStatsMin(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUserStatsMin(inputUserId()), "data/userStatsMin.json");
                    break;
                case (3):
//                    System.out.println(client.getUserStats(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUserStats(inputUserId()), "data/userStats.json");
                    break;
                case (4):
//                    System.out.println(client.getUserBasicInfo(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUserBasicInfo(inputUserId()), "data/userBasicInfo.json");
                    break;
                case (5):
//                    System.out.println(client.getUserGroupsCount(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUserGroupsCount(inputUserId()), "data/groupsCount.json");
                    break;
                case (6):
//                    System.out.println(client.getUserMessages(inputUserId(), inputPage(), inputPageSize()).toPrettyString());
                    client.saveToJSONFile(client.getUserMessages(inputUserId(), inputPage(), inputPageSize()), "data/messages.json");
                    break;
                case (7):
//                    System.out.println(client.getUserMessagesCount(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUserMessagesCount(inputUserId()), "data/messagesCount.json");
                    break;
                case (8):
//                    System.out.println(client.getUserGroups(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUserGroups(inputUserId()), "data/groups.json");
                    break;
                case (9):
//                    System.out.println(client.getUserNamesHistory(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUserNamesHistory(inputUserId()), "data/namesHistory.json");
                    break;
                case (10):
//                    System.out.println(client.getUsernameHistory(inputUserId()).toPrettyString());
                    client.saveToJSONFile(client.getUsernameHistory(inputUserId()), "data/usernameHistory.json");
                    break;
                case (11):
//                    System.out.println(client.getGroupBasicInfo(inputGroupId()).toPrettyString());
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
    public static String inputPageSize () {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество сообщений: ");
        String pageSize = sc.next();
        return pageSize;
    }
}