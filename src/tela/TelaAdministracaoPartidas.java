package tela;

import dados.Partida;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdministracaoPartidas {
    private final JPanel partidaPanel;
    private final Partida partida;
    private final TelaGerenciamentoCampeonato gerenciamentoCampeonato;
    private final JLabel time1Label;
    private final JLabel time2Label;
    private final JLabel pontosTime1Label;
    private final JLabel pontosTime2Label;
    private final JLabel vsLabel;
    private final JLabel timerLabel;
    private Timer timer;
    private int segundosRestantes;

    public TelaAdministracaoPartidas(Partida partida, TelaGerenciamentoCampeonato gerenciamentoCampeonato) {
        this.partida = partida;
        this.gerenciamentoCampeonato = gerenciamentoCampeonato;

        partidaPanel = new JPanel(new GridBagLayout());
        partidaPanel.setBackground(new Color(248, 248, 248));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        time1Label = new JLabel(partida.getTime1().getNome());
        time1Label.setFont(new Font("SansSerif", Font.BOLD, 24));
        time1Label.setForeground(new Color(50, 18, 94));
        gbc.gridx = 0;
        gbc.gridy = 0;
        partidaPanel.add(time1Label, gbc);

        pontosTime1Label = new JLabel("Pontos: " + partida.getPontosTime1());
        pontosTime1Label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        pontosTime1Label.setForeground(new Color(50, 18, 94));
        gbc.gridx = 0;
        gbc.gridy = 1;
        partidaPanel.add(pontosTime1Label, gbc);

        vsLabel = new JLabel("VS");
        vsLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        vsLabel.setForeground(new Color(50, 18, 94));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        partidaPanel.add(vsLabel, gbc);

        time2Label = new JLabel(partida.getTime2().getNome());
        time2Label.setFont(new Font("SansSerif", Font.BOLD, 24));
        time2Label.setForeground(new Color(50, 18, 94));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        partidaPanel.add(time2Label, gbc);

        pontosTime2Label = new JLabel("Pontos: " + partida.getPontosTime2());
        pontosTime2Label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        pontosTime2Label.setForeground(new Color(50, 18, 94));
        gbc.gridx = 2;
        gbc.gridy = 1;
        partidaPanel.add(pontosTime2Label, gbc);

        timerLabel = new JLabel("Tempo Restante: 1:00");
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        timerLabel.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        partidaPanel.add(timerLabel, gbc);

        JButton blotTime1Button = new JButton("Blot Time 1");
        blotTime1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida.adicionarBlotTime1();
                atualizarPontuacao();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        partidaPanel.add(blotTime1Button, gbc);

        JButton blotTime2Button = new JButton("Blot Time 2");
        blotTime2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida.adicionarBlotTime2();
                atualizarPontuacao();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 3;
        partidaPanel.add(blotTime2Button, gbc);

        JButton plifTime1Button = new JButton("Plif Time 1");
        plifTime1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida.adicionarPlifTime1();
                atualizarPontuacao();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        partidaPanel.add(plifTime1Button, gbc);

        JButton plifTime2Button = new JButton("Plif Time 2");
        plifTime2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida.adicionarPlifTime2();
                atualizarPontuacao();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 4;
        partidaPanel.add(plifTime2Button, gbc);

        JButton encerrarPartidaButton = new JButton("Encerrar Partida");
        encerrarPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarPartida();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        partidaPanel.add(encerrarPartidaButton, gbc);

        iniciarTimer();
        atualizarPontuacao();
    }

    public JPanel getPainel() {
        return partidaPanel;
    }

    private void atualizarPontuacao() {
        pontosTime1Label.setText("Pontos: " + partida.getPontosTime1());
        pontosTime2Label.setText("Pontos: " + partida.getPontosTime2());
    }

    private void encerrarPartida() {
        if (partida.getPontosTime1() == partida.getPontosTime2()) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(partidaPanel);
            TelaGrusht telaGrusht = new TelaGrusht(partida, gerenciamentoCampeonato);
            frame.setContentPane(telaGrusht.getPainel());
            frame.revalidate();
            frame.repaint();
        } else {
            gerenciamentoCampeonato.concluirPartida(partida);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(partidaPanel);
            frame.setContentPane(gerenciamentoCampeonato.getPainel());
            frame.revalidate();
            frame.repaint();
        }
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

                if (segundosRestantes <= 0) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
}



