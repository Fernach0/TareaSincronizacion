import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ClockServer {
    // Function used to initiate the Clock Server
    public static void initiateClockServer() throws IOException
    {

        // Create socket
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Socket successfully created");

        // Clock Server Running forever
        while (true)
        {

            // Start listening to requests
            System.out.println("Socket is listening...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server connected to " + clientSocket.getInetAddress());

            // Respond the client with server clock time
            Date now = new Date();

            // Create a SimpleDateFormat object and specify the desired format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Format the current date to the desired format
            String timeStr = sdf.format(now);

            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(timeStr + "\n");
            bw.flush();

            System.out.println(timeStr);

            // Close the connection with the client process
            clientSocket.close();
        }
    }
}
