package TaskTwo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Window extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final boolean IS_VISIBLE = true;
    private JPanel jPanel;

    public Window() {
        this.initializeComponent();
        this.setVisible(true);
    }

    private void initializeComponent() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(3);
        this.initializeJPanel();
        this.createOutButcherLabels(jPanel);
        this.createInButcherLabels(jPanel);
        this.add(jPanel);
    }

    private void initializeJPanel() {
        this.jPanel = new JPanel();
        this.jPanel.setSize(800, 600);
        this.jPanel.setLayout(new GridLayout(4, 2));
    }

    private void createOutButcherLabels(JPanel jPanel) {
        File file = new File("resources/img.png");
        File text = new File("resources/text.txt");

        try (FileInputStream stream = new FileInputStream(text.getAbsolutePath())) {
            JLabel picturelabel = new JLabel(new ImageIcon(file.getAbsolutePath()));
            TextArea textLabel = new TextArea(new String(stream.readAllBytes(), StandardCharsets.UTF_8));
            jPanel.add(picturelabel);
            jPanel.add(textLabel);
        } catch (Exception ex) {
            // ignore
        }

    }

    private void createInButcherLabels(JPanel jPanel) {
        try (InputStream imgStream = this.getClass().getResourceAsStream("/img.png");
             InputStream textStream = this.getClass().getResourceAsStream("/text.txt")) {

            JLabel picturelabel = new JLabel(new ImageIcon(imgStream.readAllBytes()));
            TextArea textLabel = new TextArea(new String(textStream.readAllBytes(), StandardCharsets.UTF_8));
            jPanel.add(picturelabel);
            jPanel.add(textLabel);
        } catch (Exception ex) {
            // ignore
        }
    }
}
