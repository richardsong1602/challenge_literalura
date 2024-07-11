package com.example.LiterAlura;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class CatalogoApplication {

    private static final String API_URL = "https://gutendex.com/books/";
    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        runCatalog();
    }

    private static void runCatalog() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("------------------------------------------------");
            System.out.println("1. Búsqueda de libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idiomas");
            System.out.println("0. Salir");
            System.out.println("------------------------------------------------");
            System.out.print("Ingrese una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    searchBookByTitle(scanner);
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorsByYear(scanner);
                    break;
                case 5:
                    listBooksByLanguage(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            if (choice != 0) {
                System.out.println("------------------------------------------------");
                System.out.println("¿Desea realizar otra búsqueda? (si/no): ");
                String continueChoice = scanner.nextLine();
                if (!continueChoice.toLowerCase().startsWith("s")) {
                    choice = 0;  // Terminar el bucle si no desea continuar
                }
            }

        } while (choice != 0);

        // Salir del programa
        System.out.println("------------------------------------------------");
        System.out.println("Saliendo del catálogo de libros.");
    }

    private static void searchBookByTitle(Scanner scanner) {
        System.out.println("------------------------------------------------");
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        System.out.println("------------------------------------------------");
        String url = API_URL + "?search=" + title;
        fetchAndPrintBooks(url);
    }

    private static void listRegisteredBooks() {
        fetchAndPrintBooks(API_URL);
    }

    private static void listRegisteredAuthors() {
        String url = API_URL;
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONArray books = jsonResponse.getJSONArray("results");
                for (int i = 0; i < books.length(); i++) {
                    JSONObject book = books.getJSONObject(i);
                    JSONArray authors = book.getJSONArray("authors");
                    for (int j = 0; j < authors.length(); j++) {
                        JSONObject author = authors.getJSONObject(j);
                        System.out.println("Author: " + author.getString("name"));
                    }
                }
            } else {
                System.out.println("Error en la solicitud a la API.");
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listLivingAuthorsByYear(Scanner scanner) {
        System.out.print("Ingrese el año: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        String url = API_URL + "?author_year=" + year;
        fetchAndPrintBooks(url);
    }

    private static void listBooksByLanguage(Scanner scanner) {
        System.out.print("Ingrese el idioma (por ejemplo, en, fr, es): ");
        String language = scanner.nextLine();
        String url = API_URL + "?languages=" + language;
        fetchAndPrintBooks(url);
    }

    private static void fetchAndPrintBooks(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONArray books = jsonResponse.getJSONArray("results");
                if (books.length() == 0) {
                    System.out.println("No se encontraron libros.");
                } else {
                    for (int i = 0; i < books.length(); i++) {
                        JSONObject book = books.getJSONObject(i);
                        System.out.println("Title: " + book.getString("title"));
                        JSONArray authors = book.getJSONArray("authors");
                        for (int j = 0; j < authors.length(); j++) {
                            System.out.println("Author: " + authors.getJSONObject(j).getString("name"));
                        }
                        System.out.println("Language: " + book.getJSONArray("languages"));
                        System.out.println();
                    }
                }
            } else {
                System.out.println("Error en la solicitud a la API.");
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
