import javax.swing.*;

public class Main {
    static JFrame getFrame(int width, int height) {
        JFrame var2 = new JFrame();
        var2.setVisible(true);
        var2.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        var2.setBounds(0, 0, width, height);
        return var2;
    }
    public static void main(String[] args) {
        JFrame frame = getFrame(500, 500);
        frame.setContentPane(new OptionDialoguePanel());
        frame.setVisible(true);
    }
}