package servlet_test;

import com.google.gson.Gson;
import org.example.dto.CategoryDTO;
import org.example.servlets.CategoryServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Тесты для класса CategoryServlet.
 */
public class CategoryServletTest {

    /**
     * Тест для метода doPost.
     *
     * @throws Exception если возникают ошибки при выполнении теста
     */
    @org.junit.jupiter.api.Test
    void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        CategoryDTO requestDTO = new CategoryDTO(1L, "TestCategory");

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(requestDTO);

        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));

        when(request.getReader()).thenReturn(reader);

        StringWriter responseWriter = new StringWriter();

        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        new CategoryServlet().doPost(request, response);

        String jsonResponse = responseWriter.toString();

        CategoryDTO responseDTO = gson.fromJson(jsonResponse, CategoryDTO.class);

        assertEquals(1L, responseDTO.getId());
        assertEquals("Processed TestCategory", responseDTO.getName());
    }

    /**
     * Тест для метода doGet.
     *
     * @throws Exception если возникают ошибки при выполнении теста
     */
    @org.junit.jupiter.api.Test
    public void testDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("123");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        CategoryServlet categoryServlet = new CategoryServlet();

        categoryServlet.doGet(request, response);

        writer.flush();
        String jsonResponse = stringWriter.toString().trim();
        CategoryDTO expectedResponse = new CategoryDTO(123L, "TestCategory");
        String expectedJsonResponse = new Gson().toJson(expectedResponse);
        assert jsonResponse.equals(expectedJsonResponse) : "Response does not match expected JSON";
    }
}
