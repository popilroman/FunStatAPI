package org.example;

import java.util.Scanner;
import org.example.FunStatClient;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String helpText = "ПЕРЕЧЕНЬ ДОСТУПНЫХ API-МЕТОДОВ:\n\n" +
                "1.  ВРЕМЕННО НЕ РАБОТАЕТ (ЦЕНА 0.25)     /api/v1/users/resolve_username     получить информацию о пользователе\n" +
                "2.                       (ЦЕНА 0)        /api/v1/users/{id}/stats_min       получить основную информацию о пользователе \n" +
                "3.                       (ЦЕНА 1)        /api/v1/users/{id}/stats           получить полную информацию о пользователе\n" +
                "4.  ВРЕМЕННО НЕ РАБОТАЕТ (ЦЕНА 0.25)     /api/v1/users/basic_info_by_id     получить информацию по ID\n" +
                "5.                       (ЦЕНА 0)        /api/v1/users/{id}/groups_count    получить количество групп пользователя\n" +
                "6.                       (ЦЕНА 10)       /api/v1/users/{id}/messages        получить сообщения пользователя\n" +
                "7.                       (ЦЕНА 0)        /api/v1/users/{id}/messages_count  получить количество сообщений пользователя\n" +
                "8.                       (ЦЕНА 5)        /api/v1/users/{id}/groups          получить известные группы пользователя\n" +
                "9.                       (ЦЕНА 3)        /api/v1/users/{id}/names           получить историю изменений имени пользователя \n" +
                "10.                      (ЦЕНА 3)        /api/v1/users/{id}/usernames       получить историю изменений юзернейма пользователя ";
        System.out.println(helpText);
        Scanner sc = new Scanner(System.in);
        System.out.print("\nВведите № метода: ");
        int number = sc.nextInt();

        FunStatClient client = new FunStatClient();
        try {
            String userId = "539754813"; // ID пользователя
            String username = "popilroman";
            switch (number) {
                case (1):
                    System.out.println(client.resolveUsername(username).toPrettyString());
                    break;
                case (2):
                    System.out.println(client.getUserStatsMin(userId).toPrettyString());
                    break;
                case (3):
                    System.out.println(client.getUserStats(userId).toPrettyString());
                    break;
                case (4):
                    System.out.println(client.getUserBasicInfo(userId).toPrettyString());
                    break;
                case (5):
                    System.out.println(client.getUserGroupsCount(userId).toPrettyString());
                    break;
                case (6):
                    System.out.println(client.getUserMessages(userId).toPrettyString());
                    break;
                case (7):
                    System.out.println(client.getUserMessagesCount(userId).toPrettyString());
                    break;
                case (8):
                    System.out.println(client.getUserGroups(userId).toPrettyString());
                    break;
                case (9):
                    System.out.println(client.getUserNamesHistory(userId).toPrettyString());
                    break;
                case (10):
                    System.out.println(client.getUsernameHistory(userId).toPrettyString());
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}