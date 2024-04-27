package org.example.Servlets;
import com.google.gson.Gson;
import org.example.DTO.CategoryDTO;
import org.example.DTO.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        BufferedReader reader = request.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        Gson gson = new Gson();
        UserDTO requestDTO = gson.fromJson(jsonRequest.toString(), UserDTO.class);

        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setUsername("Processed " + requestDTO.getUsername());
        responseDTO.setEmail("Processed " + requestDTO.getEmail());

        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String userIdString = request.getParameter("id");

        if (userIdString == null || userIdString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        long userId;
        try {
            userId = Long.parseLong(userIdString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserDTO responseDTO = new UserDTO(userId, "TestUser", "TestEmail");

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

}
