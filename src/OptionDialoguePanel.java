import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
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
            isFirst = false;
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
    String password;
    private void makePasswordState()
    {
        JLabel label = new JLabel(messagesProps.getProperty("pass"));
        this.add(label);
        //Пароль должен быть больше 8 символов,
        // не должен содержать пробелов
        // и должен содержать хотя бы одну цифру и хотя бы одну букву.
        JPasswordField field = new JPasswordField("********", 8);
        field.addActionListener(e -> {
            var inputValue = field.getPassword();
            boolean isLetter = false, isDigit = false, isSpaceFree = true;
            if(inputValue.length > 8)
            {
                for (char symb: inputValue) {
                    if(symb == ' ')
                        isSpaceFree = false;
                    if(Character.isDigit(symb))
                        isDigit = true;
                    if(Character.isLetter(symb))
                        isLetter = true;

                    if(isSpaceFree && isDigit && isLetter)
                    {
                        password = String.valueOf(inputValue);
                        System.out.println(password);
                        makeRepeatPasswordState();
                        return;
                    }
                }
            }
        });
        this.add(field);
        repaint();
    }
    private void makeRepeatPasswordState()
    {
        if(password.isEmpty())
            return;
        String inputValue = "";
        while(!Objects.equals(inputValue, password)) {
            inputValue = JOptionPane.showInputDialog(this, messagesProps.getProperty("repeatpass"));
        }
        System.out.println("pass!");
        makeSuccessState();
    }
    private void makeSuccessState()
    {
        JOptionPane.showMessageDialog(this, messagesProps.getProperty("success"));
    }

}
