package tela;

import dados.Campeonato;
import dados.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaCadastroTimes {
    private final JPanel mainPanel;
    private final JTextField nomeField;
    private final JTextField gritoDeGuerraField;
    private final JTextField anoFundacaoField;
    private final JTextArea listaTimesArea;
    private final Campeonato campeonato;
    private final List<Time> timesCadastrados;

        public TelaCadastroTimes(JPanel mainPanel, Campeonato campeonato) {
            this.mainPanel = mainPanel;
            this.campeonato = campeonato;
            this.timesCadastrados = new ArrayList<>();

            mainPanel.removeAll();
            mainPanel.setLayout(new BorderLayout());

            Font labelFonte = new Font("SansSerif", Font.BOLD, 16);
            Font fieldFonte = new Font("SansSerif", Font.PLAIN, 14);
            Color tema = new Color(50, 18, 94);
            Color backgroundColor = new Color(248, 248, 248);

            JPanel tituloPanel = new JPanel();
            tituloPanel.setBackground(new Color(50, 18, 94));
            JLabel tituloLabel = new JLabel("Cadastro de Times - BALLIT CHAMPIONSHIP");
            tituloLabel.setForeground(Color.WHITE);
            tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            tituloPanel.add(tituloLabel);
            mainPanel.add(tituloPanel, BorderLayout.NORTH);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridBagLayout());
            inputPanel.setBackground(backgroundColor);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel nomeLabel = new JLabel("Nome do Time:");
            nomeLabel.setFont(labelFonte);
            nomeLabel.setForeground(tema);
            gbc.gridx = 0;
            gbc.gridy = 0;
            inputPanel.add(nomeLabel, gbc);

            nomeField = new JTextField(20);
            nomeField.setFont(fieldFonte);
            nomeField.setBackground(tema);
            nomeField.setForeground(Color.WHITE);
            nomeField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            gbc.gridx = 1;
            gbc.gridy = 0;
            inputPanel.add(nomeField, gbc);

            JLabel gritoDeGuerraLabel = new JLabel("Grito de Guerra:");
            gritoDeGuerraLabel.setFont(labelFonte);
            gritoDeGuerraLabel.setForeground(tema);
            gbc.gridx = 0;
            gbc.gridy = 1;
            inputPanel.add(gritoDeGuerraLabel, gbc);

            gritoDeGuerraField = new JTextField(20);
            gritoDeGuerraField.setFont(fieldFonte);
            gritoDeGuerraField.setBackground(tema);
            gritoDeGuerraField.setForeground(Color.WHITE);
            gritoDeGuerraField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            gbc.gridx = 1;
            gbc.gridy = 1;
            inputPanel.add(gritoDeGuerraField, gbc);

            JLabel anoFundacaoLabel = new JLabel("Ano de Fundação:");
            anoFundacaoLabel.setFont(labelFonte);
            anoFundacaoLabel.setForeground(tema);
            gbc.gridx = 0;
            gbc.gridy = 2;
            inputPanel.add(anoFundacaoLabel, gbc);

            anoFundacaoField = new JTextField(20);
            anoFundacaoField.setFont(fieldFonte);
            anoFundacaoField.setBackground(tema);
            anoFundacaoField.setForeground(Color.WHITE);
            anoFundacaoField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            gbc.gridx = 1;
            gbc.gridy = 2;
            inputPanel.add(anoFundacaoField, gbc);

            JButton cadastrarButton = new JButton("Cadastrar Time");
            cadastrarButton.setBackground(tema);
            cadastrarButton.setForeground(Color.WHITE);
            cadastrarButton.setFont(new Font("SansSerif", Font.BOLD, 16));
            cadastrarButton.setFocusPainted(false);
            cadastrarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            cadastrarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            cadastrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cadastrarTime();
                }
            });
            cadastrarButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    cadastrarButton.setBackground(tema);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    cadastrarButton.setBackground(tema);
                }
            });

            JButton iniciarButton = new JButton("Iniciar Campeonato");
            iniciarButton.setBackground(tema);
            iniciarButton.setForeground(Color.WHITE);
            iniciarButton.setFont(new Font("SansSerif", Font.BOLD, 16));
            iniciarButton.setFocusPainted(false);
            iniciarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            iniciarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            iniciarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    iniciarCampeonato();
                }
            });
            iniciarButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    iniciarButton.setBackground(tema);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    iniciarButton.setBackground(tema);
                }
            });

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            inputPanel.add(cadastrarButton, gbc);

            gbc.gridy = 4;
            inputPanel.add(iniciarButton, gbc);

            mainPanel.add(inputPanel, BorderLayout.CENTER);

            listaTimesArea = new JTextArea(10, 50);
            listaTimesArea.setEditable(false);
            listaTimesArea.setFont(fieldFonte);
            listaTimesArea.setLineWrap(true);
            listaTimesArea.setWrapStyleWord(true);
            listaTimesArea.setBackground(Color.WHITE);
            listaTimesArea.setForeground(Color.DARK_GRAY);
            listaTimesArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            JScrollPane scrollPane = new JScrollPane(listaTimesArea);

            mainPanel.add(scrollPane, BorderLayout.SOUTH);

            mainPanel.revalidate();
            mainPanel.repaint();
        }

        private void cadastrarTime() {
            String nome = nomeField.getText();
            String gritoDeGuerra = gritoDeGuerraField.getText();
            int anoFundacao;

            try {
                anoFundacao = Integer.parseInt(anoFundacaoField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainPanel, "Ano de Fundação inválido. Digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nome.isEmpty() || gritoDeGuerra.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Nome e Grito de Guerra são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nomeDuplicado(nome)) {
                JOptionPane.showMessageDialog(mainPanel, "Nome do Time já cadastrado. Escolha um nome diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (gritoDeGuerraDuplicado(gritoDeGuerra)) {
                JOptionPane.showMessageDialog(mainPanel, "Grito de Guerra já cadastrado. Escolha um grito diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!campeonato.podeAdicionarTime()) {
                JOptionPane.showMessageDialog(mainPanel, "Número máximo de times já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Time time = new Time(nome, gritoDeGuerra, anoFundacao);
            campeonato.adicionarTime(time);
            timesCadastrados.add(time);
            atualizarListaTimes();
        }

        private boolean nomeDuplicado(String nome) {
            return timesCadastrados.stream().anyMatch(t -> t.getNome().equalsIgnoreCase(nome));
        }

        private boolean gritoDeGuerraDuplicado(String gritoDeGuerra) {
            return timesCadastrados.stream().anyMatch(t -> t.getGritoDeGuerra().equalsIgnoreCase(gritoDeGuerra));
        }

        private void atualizarListaTimes() {
            StringBuilder sb = new StringBuilder();
            sb.append("Times Cadastrados (").append(timesCadastrados.size()).append("):\n");

            for (Time time : timesCadastrados) {
                sb.append("- ").append(time.getNome()).append("\n");
            }

            listaTimesArea.setText(sb.toString());
        }

        private void iniciarCampeonato() {
            if (!campeonato.numeroDeTimesValido()) {
                JOptionPane.showMessageDialog(mainPanel, "O número total de times deve ser entre 8 e 16 e deve ser par para iniciar o campeonato.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            mainPanel.removeAll();
            TelaGerenciamentoCampeonato telaGerenciamento = new TelaGerenciamentoCampeonato(campeonato);
            mainPanel.add(telaGerenciamento.getPainel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }