package org.example.Servlets;
import com.google.gson.Gson;
import org.example.DTO.CategoryDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

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
        CategoryDTO requestDTO = gson.fromJson(jsonRequest.toString(), CategoryDTO.class);

        CategoryDTO responseDTO = new CategoryDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setName("Processed " + requestDTO.getName());

        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String categoryIdString = request.getParameter("id");

        if (categoryIdString == null || categoryIdString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        long categoryId;
        try {
            categoryId = Long.parseLong(categoryIdString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        CategoryDTO responseDTO = new CategoryDTO(categoryId, "TestCategory");

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseDTO);

        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }


}
