import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gnu.io.*;

@WebServlet("/SemaforoServlet")
public class SemaforoServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     String portName = "COM5";
     String estado = request.getParameter("estado");
     
        System.out.println(estado);
        System.out.println(estado);
        System.out.println(estado);
        

     try {
         
         serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
         serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

         char[] estadoArray = { estado.charAt(0) };
         serialPort.getOutputStream().write(new String(estadoArray).getBytes());
         serialPort.close();

         response.getWriter().write("Estado enviado a " + portName + ": " + estado);
     } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         response.getWriter().write("Error al enviar estado a " + portName + ": " + e.getMessage());
     }
 }
}
