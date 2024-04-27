package servlet_test;

import com.google.gson.Gson;
import org.example.dto.UserDTO;
import org.example.servlets.UserServlet;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тесты для класса UserServlet.
 */
public class UserServletTest {

    /**
     * Тест для метода doPost.
     *
     * @throws Exception если возникают ошибки при выполнении теста
     */
    @org.junit.jupiter.api.Test
    void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UserDTO requestDTO = new UserDTO(1L, "TestUser", "TestEmail");

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(requestDTO);

        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));

        when(request.getReader()).thenReturn(reader);

        StringWriter responseWriter = new StringWriter();

        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        new UserServlet().doPost(request, response);

        String jsonResponse = responseWriter.toString();

        UserDTO responseDTO = gson.fromJson(jsonResponse, UserDTO.class);

        assertEquals(1L, responseDTO.getId());
        assertEquals("Processed TestUser", responseDTO.getUsername());
        assertEquals("Processed TestEmail", responseDTO.getEmail());

    }

    /**
     * Тест для метода doGet.
     *
     * @throws Exception если возникают ошибки при выполнении теста
     */
    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("123");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        UserServlet userServlet = new UserServlet();

        userServlet.doGet(request, response);

        writer.flush();
        String jsonResponse = stringWriter.toString().trim();
        UserDTO expectedResponse = new UserDTO(123L, "TestUser", "TestEmail");
        String expectedJsonResponse = new Gson().toJson(expectedResponse);
        assert jsonResponse.equals(expectedJsonResponse) : "Response does not match expected JSON";
    }
}
