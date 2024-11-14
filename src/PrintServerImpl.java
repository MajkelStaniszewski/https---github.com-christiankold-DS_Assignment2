import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;

public class PrintServerImpl extends UnicastRemoteObject implements PrintServer {
    private static final HashMap<String, String> jobQueue = new HashMap<>();

    public PrintServerImpl() throws RemoteException {}

    @Override
    public boolean login(String username, String password) throws RemoteException {
        if (Authentication.verifyCredentials(username, password)) {
            String sessionToken = SessionManager.createSession(username);
            System.out.println("User logged in: " + username);
            return true;
        }
        return false;
    }

    @Override
    public void print(String filename, String printer) throws RemoteException {
        System.out.println("Print job added: " + filename + " on " + printer);
        jobQueue.put(filename, printer);
    }

    @Override
    public String queue(String printer) throws RemoteException {
        StringBuilder queue = new StringBuilder("Queue for printer " + printer + ":\n");
        jobQueue.forEach((file, pr) -> {
            if (pr.equals(printer)) queue.append(file).append("\n");
        });
        return queue.toString();
    }

    @Override
    public void topQueue(String printer, int job) throws RemoteException {
        System.out.println("Job " + job + " moved to top on printer " + printer);
    }

    @Override
    public void start() throws RemoteException {
        System.out.println("Print server started.");
    }

    @Override
    public void stop() throws RemoteException {
        System.out.println("Print server stopped.");
    }

    @Override
    public void restart() throws RemoteException {
        stop();
        jobQueue.clear();
        start();
    }

    @Override
    public String status(String printer) throws RemoteException {
        return "Status of printer " + printer + ": Operational";
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return "Config value for " + parameter + ": [Placeholder]";
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        System.out.println("Set config " + parameter + " to " + value);
    }
}