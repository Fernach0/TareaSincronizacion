import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ClockServer.initiateClockServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}