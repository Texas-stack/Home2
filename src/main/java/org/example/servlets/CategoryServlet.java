package org.example.servlets;

import com.google.gson.Gson;
import org.example.dto.CategoryDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для обработки запросов категорий.
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    /**
     * Обработка POST запросов для создания категории.
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

        // Преобразование JSON тела запроса в объект CategoryDTO
        Gson gson = new Gson();
        CategoryDTO requestDTO = gson.fromJson(jsonRequest.toString(), CategoryDTO.class);

        // Создание и обработка ответа
        CategoryDTO responseDTO = new CategoryDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setName("Processed " + requestDTO.getName());

        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

    /**
     * Обработка GET запросов для получения категории по её идентификатору.
     *
     * @param request  HTTP запрос
     * @param response HTTP ответ
     * @throws ServletException если произошла ошибка при обработке запроса
     * @throws IOException      если произошла ошибка ввода-вывода при обработке запроса
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        // Получение идентификатора категории из параметра запроса
        String categoryIdString = request.getParameter("id");

        // Проверка наличия идентификатора категории в параметре запроса
        if (categoryIdString == null || categoryIdString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Попытка преобразовать идентификатор категории в число
        long categoryId;
        try {
            categoryId = Long.parseLong(categoryIdString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Создание и обработка ответа
        CategoryDTO responseDTO = new CategoryDTO(categoryId, "TestCategory");

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }
}
