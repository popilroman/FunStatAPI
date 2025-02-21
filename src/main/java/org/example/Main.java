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
                    System.out.println(client.resolveUsername(inputUserUsername()).toPrettyString());
                    break;
                case (2):
                    System.out.println(client.getUserStatsMin(inputUserId()).toPrettyString());
                    break;
                case (3):
                    System.out.println(client.getUserStats(inputUserId()).toPrettyString());
                    break;
                case (4):
                    System.out.println(client.getUserBasicInfo(inputUserId()).toPrettyString());
                    break;
                case (5):
                    System.out.println(client.getUserGroupsCount(inputUserId()).toPrettyString());
                    break;
                case (6):
                    System.out.println(client.getUserMessages(inputUserId()).toPrettyString());
                    break;
                case (7):
                    System.out.println(client.getUserMessagesCount(inputUserId()).toPrettyString());
                    break;
                case (8):
                    System.out.println(client.getUserGroups(inputUserId()).toPrettyString());
                    break;
                case (9):
                    System.out.println(client.getUserNamesHistory(inputUserId()).toPrettyString());
                    break;
                case (10):
                    System.out.println(client.getUsernameHistory(inputUserId()).toPrettyString());
                    break;
                case (11):
                    System.out.println(client.getGroupBasicInfo(inputGroupId()).toPrettyString());
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
}