import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PrintServerMain {
    public static void main(String[] args) {
        try {
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Create an instance of PrintServerImpl and bind it to the registry
            PrintServerImpl server = new PrintServerImpl();
            Naming.rebind("PrintServer", server);

            System.out.println("Print Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}