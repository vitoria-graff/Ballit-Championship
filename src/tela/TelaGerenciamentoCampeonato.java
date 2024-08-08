package tela;

import dados.Campeonato;
import dados.Partida;
import dados.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TelaGerenciamentoCampeonato {
    private final JPanel principalPanel;
    private final JPanel partidasPanel;
    private final JPanel controlPanel;
    private final JTextArea statusArea;
    private final Campeonato campeonato;
    private final List<Partida> partidasAtual;
    private final List<Time> todosOsTimes;
    private final Random random;

    public TelaGerenciamentoCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
        this.partidasAtual = new ArrayList<>();
        this.todosOsTimes = new ArrayList<>(campeonato.getTimes());
        this.random = new Random();
        principalPanel = new JPanel(new BorderLayout());
        principalPanel.setBackground(new Color(255, 255, 255));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setBackground(new Color(50, 18, 94));
        JLabel tituloLabel = new JLabel("BALLIT CHAMPIONSHIP");
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        tituloPanel.add(tituloLabel);
        principalPanel.add(tituloPanel, BorderLayout.NORTH);

        UIManager.put("TextArea.background", new Color(255, 255, 255));
        UIManager.put("TextArea.foreground", Color.BLACK);
        UIManager.put("TextArea.font", new Font("SansSerif", Font.PLAIN, 14));
        UIManager.put("Button.background", new Color(50, 18, 94));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 16));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("ScrollPane.background", new Color(255, 255, 255));

        statusArea = new JTextArea(20, 50);
        statusArea.setEditable(false);
        statusArea.setLineWrap(true);
        statusArea.setWrapStyleWord(true);
        statusArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(statusArea);
        principalPanel.add(scrollPane, BorderLayout.CENTER);

        partidasPanel = new JPanel();
        partidasPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        partidasPanel.setBackground(new Color(255, 255, 255));

        JScrollPane partidasScrollPane = new JScrollPane(partidasPanel);
        partidasScrollPane.setPreferredSize(new Dimension(250, 600));
        principalPanel.add(partidasScrollPane, BorderLayout.EAST);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBackground(new Color(255, 255, 255));

        JButton penalizarTimeButton = new JButton("Penalizar Time");
        penalizarTimeButton.setFocusPainted(false);
        penalizarTimeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        penalizarTimeButton.setBackground(new Color(50, 18, 94));
        penalizarTimeButton.setForeground(Color.WHITE);
        penalizarTimeButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        penalizarTimeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        penalizarTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                penalizarTime();
            }
        });
        penalizarTimeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                penalizarTimeButton.setBackground(new Color(50, 18, 94));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                penalizarTimeButton.setBackground(new Color(50, 18, 94));
            }
        });

        controlPanel.add(penalizarTimeButton);
        principalPanel.add(controlPanel, BorderLayout.SOUTH);
        iniciarProximaFase();
    }

    public JPanel getPainel() {
        return principalPanel;
    }

    public void concluirPartida(Partida partida) {
        statusArea.append("Partida concluída: " +
                partida.getTime1().getNome()+ " " +partida.getPontosTime1() + " - " +
                partida.getTime2().getNome()+ " " +partida.getPontosTime2() + "\n");
        partidasAtual.remove(partida);
        if (partidasAtual.isEmpty()) {
            List<Time>vencedores =new ArrayList<>();
            for (Partida p:campeonato.getPartidas()) {
                vencedores.add(p.getVencedor());
            }
            campeonato.getTimes().clear();
            campeonato.getTimes().addAll(vencedores);
            campeonato.limparPartidas();
            iniciarProximaFase();
        } else {
            atualizarPartidasPanel();
        }
    }

    private void iniciarProximaFase() {
        List<Time>times = new ArrayList<>(campeonato.getTimes());
        if (times.size()==1) {
            Time vencedor= times.get(0);
            statusArea.append("Campeonato concluído! O time vencedor é: "+vencedor.getNome() +"\n");
            exibirClassificacao(principalPanel);
            return;
        }
        Collections.shuffle(times, random);
        partidasAtual.clear();
        partidasPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        List<Time> timesParaProximaFase = new ArrayList<>();
        Time timeComBye = null;
        if (times.size() % 2 != 0) {
            timeComBye = times.remove(times.size() - 1);
            timesParaProximaFase.add(timeComBye);
        }
        for (int i = 0; i < times.size(); i += 2) {
            Time time1 = times.get(i);
            Time time2 = times.get(i + 1);
            Partida partida = new Partida(time1, time2);
            partidasAtual.add(partida);
            campeonato.adicionarPartida(partida);
            JButton partidaButton = new JButton(time1.getNome() + " vs " + time2.getNome());
            partidaButton.setBackground(new Color(50, 18, 94));
            partidaButton.setForeground(Color.WHITE);
            partidaButton.setFont(new Font("SansSerif", Font.BOLD, 16));
            partidaButton.setFocusPainted(false);
            partidaButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            partidaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            partidaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(principalPanel);
                    TelaAdministracaoPartidas telaAdmin = new TelaAdministracaoPartidas(partida, TelaGerenciamentoCampeonato.this);
                    frame.setContentPane(telaAdmin.getPainel());
                    frame.revalidate();
                    frame.repaint();
                }
            });
            partidaButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    partidaButton.setBackground(new Color(50, 18, 94));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    partidaButton.setBackground(new Color(50, 18, 94));
                }
            });
            gbc.gridy = i / 2;
            partidasPanel.add(partidaButton, gbc);
            timesParaProximaFase.add(time1);
            timesParaProximaFase.add(time2);
        }
        campeonato.setTimes(timesParaProximaFase);
        statusArea.append("Nova fase iniciada! Partidas:\n");
        for (Partida partida : partidasAtual) {
            statusArea.append(partida.getTime1().getNome() + " vs " + partida.getTime2().getNome() + "\n");
        }
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    public void penalizarTime() {
        String timeNome = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(principalPanel), "Digite o nome do time a sofrer o advrungh:");
        Time time = todosOsTimes.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(timeNome))
                .findFirst()
                .orElse(null);

        if (time != null) {
            for (Partida p : partidasAtual) {
                if (p.getTime1().equals(time) || p.getTime2().equals(time)) {
                    p.aplicarAdvrungh(time);
                    statusArea.append("Advrungh ao time: " + time.getNome() + "\n");
                    atualizarPartidasPanel();
                    return;
                }
            }
            statusArea.append("O time " + time.getNome() + " não está em nenhuma partida atual.\n");
        } else {
            statusArea.append("Time não encontrado.\n");
        }
    }

    private void atualizarPartidasPanel() {
        partidasPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        for (Partida partida : partidasAtual) {
            JButton partidaButton = new JButton(partida.getTime1().getNome() + " vs " + partida.getTime2().getNome());
            partidaButton.setBackground(new Color(50, 18, 94));
            partidaButton.setForeground(Color.WHITE);
            partidaButton.setFont(new Font("SansSerif", Font.BOLD, 16));
            partidaButton.setFocusPainted(false);
            partidaButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            partidaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            partidaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(principalPanel);
                    TelaAdministracaoPartidas telaAdmin = new TelaAdministracaoPartidas(partida, TelaGerenciamentoCampeonato.this);
                    frame.setContentPane(telaAdmin.getPainel());
                    frame.revalidate();
                    frame.repaint();
                }
            });
            partidaButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    partidaButton.setBackground(new Color(50, 18, 94));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    partidaButton.setBackground(new Color(50, 18, 94));
                }
            });
            gbc.gridy = partidasPanel.getComponentCount();
            partidasPanel.add(partidaButton, gbc);
        }
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    private void exibirClassificacao(JPanel painelAtual) {
        TelaTabela telaTabela= new TelaTabela(todosOsTimes);
        painelAtual.removeAll();
        painelAtual.add(telaTabela);
        painelAtual.revalidate();
        painelAtual.repaint();
    }
}



