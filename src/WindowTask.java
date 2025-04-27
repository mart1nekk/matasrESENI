

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Trida pro cele okno, v pripade nutnosti je mozne vytvorit vlastni tridy
 */
public class WindowTask extends JFrame {

    WindowTask() {
        this.setTitle("Darovani krve");
        //Zde si muzete kreslit, kdo si hraje, nezlobi
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(5, 2, 25, 20));

        setLayout(new BorderLayout());
        JButton but = new JButton("Mohu dnes darovat?");
        add(but, BorderLayout.SOUTH);

        Mylabel vek = new Mylabel("Věk");
        MyText textVek = new MyText();
        centerPanel.add(vek);
        centerPanel.add(textVek);

        Mylabel vyska = new Mylabel("Výška");
        MyText textVyska = new MyText();
        centerPanel.add(vyska);
        centerPanel.add(textVyska);

        Mylabel vaha = new Mylabel("Váha");
        MyText textVaha = new MyText();
        centerPanel.add(vaha);
        centerPanel.add(textVaha);

        Mylabel pohlavi = new Mylabel("Pohlaví");
        Panel buttonPanel = new Panel();
        ButtonGroup bt = new ButtonGroup();
        MyRadio m = new MyRadio("Muž");
        MyRadio w = new MyRadio("Žena");
        bt.add(m);
        bt.add(w);
        buttonPanel.add(m);
        buttonPanel.add(w);
        centerPanel.add(pohlavi);
        centerPanel.add(buttonPanel);

        Mylabel lastDate = new Mylabel("Poslední darování");
        MyText textLastDate = new MyText();
        centerPanel.add(lastDate);
        centerPanel.add(textLastDate);

        add(centerPanel, BorderLayout.CENTER);
        but.addActionListener(a -> {
            try {
                if (Integer.parseInt(textVek.getText()) >= 18 && Integer.parseInt(textVek.getText()) <= 65 && Integer.parseInt(textVaha.getText()) > 50 && Integer.parseInt(textVaha.getText()) < 190) {
                    if (textLastDate.getText().equals("\"")) {
                        JOptionPane.showMessageDialog(null, "Můžete darovat krev");
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate date = LocalDate.parse(textLastDate.getText(), formatter);
                        if (m.isSelected() && date.plusDays(89).isBefore(LocalDate.now())) { // 89 aby to za
                            JOptionPane.showMessageDialog(null, "Můžete darovat krev");
                        } else if (w.isSelected() && date.plusDays(119).isBefore(LocalDate.now())) {
                            JOptionPane.showMessageDialog(null, "Můžete darovat krev");
                        } else {
                            JOptionPane.showMessageDialog(null, "Krev darovat nemůžete ");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Krev darovat nemůžete ++");
                }
            } catch (NumberFormatException | DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "spatne zadane cisla");
            }

        });
    }

    static void init() {
        WindowTask task = new WindowTask();
        task.setVisible(true);
    }

    static class Mylabel extends JLabel {
        public Mylabel(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 17));
        }
    }

    static class MyText extends JTextField {
        public MyText() {
            setFont(new Font("Arial", Font.PLAIN, 14));
            setHorizontalAlignment(CENTER);
            setPreferredSize(new Dimension(200, 30));
        }
    }

    static class MyRadio extends JRadioButton {
        public MyRadio(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 17));
            setSelected(true);
        }
    }
}
