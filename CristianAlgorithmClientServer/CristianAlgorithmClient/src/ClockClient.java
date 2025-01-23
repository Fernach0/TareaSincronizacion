import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockClient {
    public static void synchronizeTime() {
        try {
            // Crear socket para conectarse al servidor
            Socket socket = new Socket("127.0.0.1", 8000);

            // Registrar el tiempo de solicitud
            long requestTime = System.currentTimeMillis();

            // Leer la hora del servidor
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String serverTimeStr = br.readLine();

            // Registrar el tiempo de respuesta
            long responseTime = System.currentTimeMillis();

            // Ajustar el formato de la fecha con el patrón correcto
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Parsear la fecha del servidor
            Date serverTime = sdf.parse(serverTimeStr);

            // Calcular la latencia del proceso
            long processDelayLatency = responseTime - requestTime;

            // Obtener la hora actual del cliente
            long actualTimeMillis = System.currentTimeMillis();
            Date actualTime = new Date(actualTimeMillis);

            // Sincronizar la hora del cliente
            long synchronizedTimeMillis = serverTime.getTime() + processDelayLatency / 2;
            Date synchronizedTime = new Date(synchronizedTimeMillis);

            // Mostrar resultados
            System.out.println("Hora del servidor: " + serverTime);
            System.out.println("Latencia del proceso: " + processDelayLatency + " ms");
            System.out.println("Hora actual del cliente: " + actualTime);
            System.out.println("Hora sincronizada del cliente: " + synchronizedTime);

            // Cerrar el socket
            socket.close();

        } catch (IOException | java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        synchronizeTime();
    }
}
