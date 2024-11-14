import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PrintClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrintServer server = (PrintServer) registry.lookup("PrintServer");

            boolean isAuthenticated = server.login("user", "password");
            if (isAuthenticated) {
                System.out.println("Login successful!");

                server.print("document.pdf", "printer1");
                System.out.println(server.queue("printer1"));
                server.topQueue("printer1", 1);
                System.out.println(server.status("printer1"));
            } else {
                System.out.println("Authentication failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}