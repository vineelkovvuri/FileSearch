import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}