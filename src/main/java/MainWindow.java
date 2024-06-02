import javax.swing.*;

public class MainWindow {
    JFrame mainWindow;

    JLabel lEnterFileName;
    JTextField tfFileName;
    JButton bSearch;
    JButton bCancel;
    JTextArea lSearchPath;
    FileSearchTask fileSearchTask;

    public MainWindow() {
        mainWindow = new JFrame("File Search");

        mainWindow.setLayout(null);
        mainWindow.setSize(350, 240);

        lEnterFileName = new JLabel("Enter File Name:");
        lEnterFileName.setBounds(20, 27, 100, 14);
        mainWindow.add(lEnterFileName);

        tfFileName = new JTextField();
        tfFileName.setBounds(134, 20, 166, 25);
        mainWindow.add(tfFileName);

        bSearch = new JButton("Search");
        bSearch.setBounds(20, 60, 115, 25);
        bSearch.addActionListener((e) -> {
            // FileSearchTask's doBackground() method is invoked in a separate
            // Worker Thread
            if (fileSearchTask != null) {
                fileSearchTask.cancel(true);
            }
            fileSearchTask = new FileSearchTask(tfFileName.getText(), this);
            fileSearchTask.execute();
        });
        mainWindow.add(bSearch);

        bCancel = new JButton("Cancel");
        bCancel.setBounds(185, 60, 115, 25);
        bCancel.addActionListener((e) -> {
            // FileSearchTask's doBackground() method is invoked in a separate
            // Worker Thread
            if (fileSearchTask != null) {
                fileSearchTask.cancel(true);
            }
        });
        mainWindow.add(bCancel);

        lSearchPath = new JTextArea("");
        lSearchPath.setBounds(20, 112, 300, 80);
        lSearchPath.setLineWrap(true);
        lSearchPath.setEditable(false);
        mainWindow.add(lSearchPath);

        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }
}