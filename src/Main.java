
import tela.TelaInicial;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BALLIT CHAMPIONSHIP");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            TelaInicial telaInicial = new TelaInicial();
            JPanel mainPanel = telaInicial.getPanel();
            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }
}


