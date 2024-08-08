package tela;

import dados.Campeonato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial {
    private final JPanel mainPanel;

    public TelaInicial() {
        this.mainPanel = new JPanel();

        Font titleFont = new Font("SansSerif", Font.BOLD, 36);
        Color primaryColor = new Color(50, 18, 94);
        Color backgroundColor = new Color(248, 248, 248);

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("BALLIT CHAMPIONSHIP");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        JButton iniciarButton = new JButton("Iniciar");
        iniciarButton.setBackground(primaryColor);
        iniciarButton.setForeground(Color.WHITE);
        iniciarButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        iniciarButton.setFocusPainted(false);
        iniciarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        iniciarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarCadastro();
            }
        });
        iniciarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iniciarButton.setBackground(primaryColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                iniciarButton.setBackground(primaryColor);
            }
        });

        gbc.gridy = 1;
        mainPanel.add(iniciarButton, gbc);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void iniciarCadastro() {
        new TelaCadastroTimes(mainPanel, new Campeonato());
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
