package tela;

import dados.Partida;
import dados.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGrusht {
    private JPanel grushtPanel;
    private JLabel gritoTime1Label;
    private JLabel gritoTime2Label;
    private JLabel timerLabel;
    private JProgressBar decibelTime1Bar;
    private JProgressBar decibelTime2Bar;
    private Timer timer;
    private int segundosRestantes;
    private final Partida partida;
    private final TelaGerenciamentoCampeonato gerenciamentoCampeonato;

    public TelaGrusht(Partida partida, TelaGerenciamentoCampeonato gerenciamentoCampeonato) {
        this.partida = partida;
        this.gerenciamentoCampeonato = gerenciamentoCampeonato;
        designTela();
    }

    private void designTela() {
        grushtPanel = new JPanel(new BorderLayout());
        grushtPanel.setBackground(new Color(248, 248, 248));

        JPanel gritosPanel = new JPanel(new GridBagLayout());
        gritosPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;

        Font timeFonte = new Font("SansSerif", Font.BOLD, 24);
        Font gritoFonte = new Font("SansSerif", Font.BOLD, 18);

        JPanel time1Panel = new JPanel(new GridLayout(3, 1));
        time1Panel.setOpaque(false);
        JLabel time1Label= criarLabel(partida.getTime1().getNome(), timeFonte, new Color(50, 18, 94));
        gritoTime1Label= criarLabel(partida.getTime1().getGritoDeGuerra(), gritoFonte, new Color(50, 18, 94,126));
        time1Panel.add(time1Label);
        time1Panel.add(gritoTime1Label);

        JPanel tituloPanel = new JPanel();
        tituloPanel.setBackground(new Color(50, 18, 94));
        JLabel tituloLabel = new JLabel("GRUSHT");
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        tituloPanel.add(tituloLabel);
        grushtPanel.add(tituloPanel, BorderLayout.NORTH);

        decibelTime1Bar = new JProgressBar(30, 130);
        decibelTime1Bar.setValue(50);
        decibelTime1Bar.setStringPainted(true);
        decibelTime1Bar.setForeground(new Color(90, 3, 3, 97));
        time1Panel.add(decibelTime1Bar);

        gbc.gridx= 0;
        gbc.gridy= 0;
        gbc.anchor= GridBagConstraints.EAST;
        gritosPanel.add(time1Panel, gbc);

        JLabel vsLabel = criarLabel("VS", new Font("SansSerif", Font.BOLD, 30), new Color(50, 18, 94)); // Alterado para preto
        gbc.gridx= 1;
        gbc.gridy= 0;
        gbc.anchor= GridBagConstraints.CENTER;
        gritosPanel.add(vsLabel, gbc);

        JPanel time2Panel= new JPanel(new GridLayout(3, 1));
        time2Panel.setOpaque(false);
        JLabel time2Label= criarLabel(partida.getTime2().getNome(), timeFonte, new Color(50, 18, 94));
        gritoTime2Label= criarLabel(partida.getTime2().getGritoDeGuerra(), gritoFonte, new Color(50, 18, 94, 126));
        time2Panel.add(time2Label);
        time2Panel.add(gritoTime2Label);

        decibelTime2Bar = new JProgressBar(30, 130);
        decibelTime2Bar.setValue(50);
        decibelTime2Bar.setStringPainted(true);
        decibelTime2Bar.setForeground(new Color(90, 3, 3, 97));
        time2Panel.add(decibelTime2Bar);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gritosPanel.add(time2Panel, gbc);

        grushtPanel.add(gritosPanel, BorderLayout.CENTER);

        timerLabel = criarLabel("Tempo Restante: 1:00", new Font("SansSerif", Font.BOLD, 24), Color.ORANGE); // Pode manter a cor laranja para destaque
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gritosPanel.add(timerLabel, gbc);

        iniciarTimer();
    }

    public JPanel getPainel() {
        return grushtPanel;
    }

    private JLabel criarLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void iniciarTimer() {
        segundosRestantes = 60;
        timerLabel.setText("Tempo Restante: 1:00");

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosRestantes--;
                int minutos = segundosRestantes / 60;
                int segundos = segundosRestantes % 60;
                timerLabel.setText(String.format("Tempo Restante: %d:%02d", minutos, segundos));

                // Atualiza as barras de decibéis com valores simulados
                decibelTime1Bar.setValue((int) (Math.random() * 100));
                decibelTime2Bar.setValue((int) (Math.random() * 100));

                if (segundosRestantes <= 0) {
                    timer.stop();
                    determinarVencedor();
                }
            }
        });
        timer.start();
    }

    private void determinarVencedor() {
        Time vencedor = partida.realizarGrusht();
        gerenciamentoCampeonato.concluirPartida(partida);
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "O vencedor após o Grusht é: " + vencedor.getNome(), "Vencedor", JOptionPane.INFORMATION_MESSAGE);

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(grushtPanel);
            frame.setContentPane(gerenciamentoCampeonato.getPainel());
            frame.revalidate();
            frame.repaint();
        });
    }
}




