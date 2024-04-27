package servlet_test;

import com.google.gson.Gson;
import org.example.dto.ProductDTO;
import org.example.servlets.ProductServlet;
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
 * Тесты для класса ProductServlet.
 */
public class ProductServletTest {

    /**
     * Тест для метода doPost.
     *
     * @throws Exception если возникают ошибки при выполнении теста
     */
    @Test
    void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ProductDTO requestDTO = new ProductDTO(1L, "TestProduct", 100);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(requestDTO);

        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));

        when(request.getReader()).thenReturn(reader);

        StringWriter responseWriter = new StringWriter();

        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        new ProductServlet().doPost(request, response);

        String jsonResponse = responseWriter.toString();

        ProductDTO responseDTO = gson.fromJson(jsonResponse, ProductDTO.class);

        assertEquals(1L, responseDTO.getId());
        assertEquals("Processed TestProduct", responseDTO.getName());
        assertEquals(200, responseDTO.getPrice());
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

        ProductServlet productServlet = new ProductServlet();

        productServlet.doGet(request, response);

        writer.flush();
        String jsonResponse = stringWriter.toString().trim();
        ProductDTO expectedResponse = new ProductDTO(123L, "TestProduct", 100);
        String expectedJsonResponse = new Gson().toJson(expectedResponse);
        assert jsonResponse.equals(expectedJsonResponse) : "Response does not match expected JSON";
    }
}
