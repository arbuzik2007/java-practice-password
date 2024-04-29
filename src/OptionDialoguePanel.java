import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OptionDialoguePanel extends JPanel {
    boolean isFirst;
    Properties messagesProps;

    public OptionDialoguePanel(){
        this.isFirst = true;
        messagesProps = new Properties();
        loadProps();
    }

    private void loadProps()
    {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propertyConfigPath = rootPath + "messages.properties";
        System.out.println(propertyConfigPath);
        try {
            messagesProps.load(new FileInputStream(propertyConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(isFirst)
        {
            makeFirstInitState();
        }
        super.paintComponent(g);
    }

    private void makeFirstInitState()
    {
        int response = JOptionPane.showConfirmDialog(this, messagesProps.getProperty("greet"));
        if(response == JOptionPane.YES_OPTION)
            makeLoginState();
        else
            System.exit(0);

    }
    String login;
    private void makeLoginState()
    {

        String inputValue = "";
        while(inputValue.contains(" ") || inputValue.length() < 5) {
            inputValue = JOptionPane.showInputDialog(this, messagesProps.getProperty("login"));
        }
        login = inputValue;
        System.out.println(login);
        makePasswordState();
    }

    private void makePasswordState()
    {

    }
}
