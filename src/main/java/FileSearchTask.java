import javax.swing.*;
import java.io.File;
import java.util.List;

public class FileSearchTask extends SwingWorker<Void, String> {
    String fileName;
    MainWindow mainWindow;

    // Capture fileName and mainWindow reference so that,
    // fileName is used in doInBackground() method
    // mainWindow is used in process method to update the UI
    public FileSearchTask(String fileName, MainWindow mainWindow) {
        this.fileName = fileName;
        this.mainWindow = mainWindow;
    }

    // This is done in a separate thread. Documentation of SwingWorker class
    // calls it Worker Thread
    @Override
    protected Void doInBackground() throws Exception {
        performSearch(fileName);
        return null;
    }

    // This is automatically called in Event Dispatch Thread, so safe to update
    // the UI. This method is triggered when publish() is called in doInBackground()
    @Override
    protected void process(List<String> paths) {
        for (String path : paths) {
            mainWindow.lSearchPath.setText(path);
        }
    }

    // Core logic of iterating the entire file system
    public void performSearchUtil(File path, String fileName) {
        try {
            if (isCancelled()) {
                return;
            }
            if (!path.isDirectory()) {
                return;
            }

            for (File file : path.listFiles()) {
                if (isCancelled()) {
                    return;
                }
                if (file.isFile()) {
                    System.out.println(file.getPath());
                    publish(file.getPath());
                } else {
                    performSearchUtil(file, fileName);
                }
            }
        } catch (Exception e) {
        }
    }

    public void performSearch(String fileName) {
        File roots[] = File.listRoots();
        for (File root : roots) {
            performSearchUtil(root, fileName);
        }
    }
}