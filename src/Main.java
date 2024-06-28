import models.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        HashTable hashTable1 = new HashTable(1000);
        HashTable hashTable2 = new HashTable(1000);

        String line;
        String splitBy = ",";
        int id = 1;

        try (BufferedReader br = new BufferedReader(new FileReader("bussines.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] business = line.split(splitBy);
                if (business.length >= 5) {
                    String key = business[1];
                    String value = "ID=" + business[0] + ", Address=" + business[2] +
                            ", City=" + business[3] + ", State=" + business[4];

                    hashTable1.put(key, value, 1);
                    hashTable2.put(key, value, 2);

                    System.out.println("[" + id + "] Business [ID=" + business[0] + ", Name=" +
                            business[1] + ", Address=" + business[2] + ", City=" +
                            business[3] + ", State=" + business[4] + "]");
                    id++;
                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese clave para buscar: ");
            String searchKey = scanner.nextLine().trim();

            List<Integer> foundIndices1 = hashTable1.searchAndGetIndices(searchKey);
            List<Integer> foundIndices2 = hashTable2.searchAndGetIndices(searchKey);

            if (!foundIndices1.isEmpty() || !foundIndices2.isEmpty()) {
                System.out.print("Clave '" + searchKey + "' encontrada en los índices: ");
                for (int index : foundIndices1) {
                    System.out.print(index + " ");
                }
                for (int index : foundIndices2) {
                    System.out.print(index + " ");
                }
                System.out.println();
            } else {
                System.out.println("Clave '" + searchKey + "' no encontrada.");
            }

            int searchIndex = -1;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Ingrese el índice para mostrar los datos: ");
                if (scanner.hasNextInt()) {
                    searchIndex = scanner.nextInt();
                    validInput = true;
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                    scanner.next();
                }
            }

            System.out.println("Datos en el índice " + searchIndex + " de la Tabla Hash 1:");
            List<String> data1 = hashTable1.getDataAtIndex(searchIndex);
            for (String data : data1) {
                System.out.println(data);
            }

            System.out.println("Datos en el índice " + searchIndex + " de la Tabla Hash 2:");
            List<String> data2 = hashTable2.getDataAtIndex(searchIndex);
            for (String data : data2) {
                System.out.println(data);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
