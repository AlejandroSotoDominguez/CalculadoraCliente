
package calculadoracliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class CalculadoraCliente {
    
    private static String numero1;
    private static String signo;

    public static void main(String[] args) {
        //Se hace la conexion con el servidor
        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexion");
            //Se establece la conexion
            String puertoS=JOptionPane.showInputDialog("Introduce el puerto");
            int puerto=Integer.parseInt(puertoS);
            InetSocketAddress addr = new InetSocketAddress("localhost", puerto);

            clienteSocket.connect(addr);
            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();
            numero1 = JOptionPane.showInputDialog("Introduce el número 1");
            signo = JOptionPane.showInputDialog("Elige la operación:  +  -  x  /  r");

            //Si elegimos raiz cuadrada no se pedirá el número 2
            if(signo.equalsIgnoreCase("r")){
                os.write(numero1.getBytes());
                os.write(signo.getBytes());
                String numero2 = "0";
                os.write(numero2.getBytes());
            }else{
                String numero2 = JOptionPane.showInputDialog("Introduce el número 2");
                os.write(numero1.getBytes());
                os.write(signo.getBytes());
                System.out.println(signo);
                os.write(numero2.getBytes());
            }

            byte[] mensaje2 = new byte[20];
            is.read(mensaje2);
            System.out.println("Resultado: " + new String(mensaje2));
            System.out.println("Cerrando el socket cliente");
            clienteSocket.close();
            System.out.println("Terminado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
