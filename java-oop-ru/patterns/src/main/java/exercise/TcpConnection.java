package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private Connection connection;
    private String ip;
    private int port;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public String getCurrentState() {
        return connection.getCurrentState();
    }

    public void setState(Connection connection) {
        this.connection = connection;
    }

    public void connect() {
        connection.connect();
    }

    public void disconnect() {
        connection.disconnect();
    }

    public void write(String data) {
        connection.write(data);
    }
}
// END
