package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Scanner;

public class Messages
{
    public void parseMassage() throws IOException {

        // Создаем файл для записи
        File outputFile = new File("data/outputFiles/output.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

        // Меняем стандартный вывод в консоль на вывод в файл
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(fileOutputStream));

        // Считывание ключ-значений
        String keysFilePath = "data/inputFiles/keys.txt";
        File keysFile = new File(keysFilePath);
        String[] keysArr = fileReader(keysFilePath);

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Читаем JSON-файл
            JsonNode root = mapper.readTree(new File("data/outputFiles/messages.json"));
            JsonNode dataArray = root.path("data");

            // Проходим по каждому сообщению
            for (JsonNode message : dataArray) {
                String text = message.path("text").asText();
                for (String key: keysArr) {
                    if (text.toLowerCase().contains(key.toLowerCase())) {
                        System.out.println("КЛЮЧЕВОЕ СЛОВО: " + key);
                        int messageId = message.path("messageId").asInt();

                        // Извлекаем данные группы
                        JsonNode group = message.path("group");
                        long groupId = group.path("id").asLong();
                        String groupTitle = group.path("title").asText();
                        boolean isPrivate = group.path("isPrivate").asBoolean();
                        // Если username может быть null, то можно передать значение по умолчанию
                        String username = group.path("username").isNull() ? null : group.path("username").asText();

                        // Выводим найденную информацию
                        System.out.println("MessageId: " + messageId);
                        System.out.println("TEXT: " + text);
                        System.out.println("Group ID: " + groupId);
                        System.out.println("Group Title: " + groupTitle);
                        System.out.println("Group isPrivate: " + isPrivate);
                        System.out.println("Group Username: " + username);
                        System.out.println("-----------------------------");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Обратно меняем стандартный вывод в консоль
            System.setOut(originalOut);
            fileOutputStream.close();
            System.out.println("✔\uFE0F Фильтрация по ключевым словам выгружена в TXT-файл");
        }
    }


    public String[] fileReader(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc1 = new Scanner(file);
        //Определяем размер файла, чтобы создать массив такой же размерности
        int fileSize = 0;
        for (int i = 0; sc1.hasNextLine(); i++) {
            sc1.nextLine();
            fileSize++;
        }
        sc1.close();

        Scanner sc2 = new Scanner(file);
        String[] arr = new String[fileSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc2.nextLine();
        }
        sc2.close();

        return arr;
    }
}
