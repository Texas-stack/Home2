package org.example.servlets;

import com.google.gson.Gson;
import org.example.dto.ProductDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для обработки запросов продуктов.
 */
public class ProductServlet extends HttpServlet {

    /**
     * Обработка POST запросов для создания продукта.
     *
     * @param request  HTTP запрос
     * @param response HTTP ответ
     * @throws ServletException если произошла ошибка при обработке запроса
     * @throws IOException      если произошла ошибка ввода-вывода при обработке запроса
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        // Чтение тела запроса
        BufferedReader reader = request.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        // Преобразование JSON тела запроса в объект ProductDTO
        Gson gson = new Gson();
        ProductDTO requestDTO = gson.fromJson(jsonRequest.toString(), ProductDTO.class);

        // Создание и обработка ответа
        ProductDTO responseDTO = new ProductDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setName("Processed " + requestDTO.getName());
        responseDTO.setPrice(requestDTO.getPrice() * 2);

        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

    /**
     * Обработка GET запросов для получения продукта по его идентификатору.
     *
     * @param request  HTTP запрос
     * @param response HTTP ответ
     * @throws ServletException если произошла ошибка при обработке запроса
     * @throws IOException      если произошла ошибка ввода-вывода при обработке запроса
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        // Получение идентификатора продукта из параметра запроса
        String productIdString = request.getParameter("id");

        // Проверка наличия идентификатора продукта в параметре запроса
        if (productIdString == null || productIdString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Попытка преобразовать идентификатор продукта в число
        long productId;
        try {
            productId = Long.parseLong(productIdString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Создание и обработка ответа
        ProductDTO responseDTO = new ProductDTO(productId, "TestProduct", 100);

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

}
