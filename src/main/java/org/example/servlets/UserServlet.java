package org.example.servlets;

import com.google.gson.Gson;
import org.example.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для обработки запросов пользователей.
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    /**
     * Обработка POST запросов для создания пользователя.
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

        // Преобразование JSON тела запроса в объект UserDTO
        Gson gson = new Gson();
        UserDTO requestDTO = gson.fromJson(jsonRequest.toString(), UserDTO.class);

        // Создание и обработка ответа
        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setUsername("Processed " + requestDTO.getUsername());
        responseDTO.setEmail("Processed " + requestDTO.getEmail());

        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

    /**
     * Обработка GET запросов для получения пользователя по его идентификатору.
     *
     * @param request  HTTP запрос
     * @param response HTTP ответ
     * @throws ServletException если произошла ошибка при обработке запроса
     * @throws IOException      если произошла ошибка ввода-вывода при обработке запроса
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        // Получение идентификатора пользователя из параметра запроса
        String userIdString = request.getParameter("id");

        // Проверка наличия идентификатора пользователя в параметре запроса
        if (userIdString == null || userIdString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Попытка преобразовать идентификатор пользователя в число
        long userId;
        try {
            userId = Long.parseLong(userIdString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Создание и обработка ответа
        UserDTO responseDTO = new UserDTO(userId, "TestUser", "TestEmail");

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

}
