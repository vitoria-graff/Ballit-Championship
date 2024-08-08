package tela;

import dados.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class TelaTabela extends JPanel {
    private final List<Time> todosOsTimes;

    public TelaTabela(List<Time> todosOsTimes) {
        this.todosOsTimes = todosOsTimes;
        setLayout(new BorderLayout());

        JTextArea textArea = criarTextAreaClassificacao(todosOsTimes);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton exportButton = new JButton("Exportar para CSV");
        exportButton.setBackground(new Color(50, 18, 94));
        exportButton.setForeground(Color.WHITE);
        exportButton.setFocusPainted(false);
        exportButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        exportButton.setPreferredSize(new Dimension(200, 50));
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarParaCSV();
            }
        });

        JButton voltarButton = new JButton("Voltar à Tela Inicial");
        voltarButton.setBackground(new Color(50, 18, 94));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setFocusPainted(false);
        voltarButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        voltarButton.setPreferredSize(new Dimension(200, 50));
        voltarButton.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            JPanel painelInicial = telaInicial.getPanel();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TelaTabela.this);
            frame.setContentPane(painelInicial);
            frame.revalidate();
            frame.repaint();
        });

        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exportButton);
        buttonPanel.add(voltarButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextArea criarTextAreaClassificacao(List<Time> todosOsTimes) {
        List<Time> timesClassificados = todosOsTimes.stream().sorted((t1, t2) -> Integer.compare(t2.getPontos(), t1.getPontos())).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sb.append("Tabela de Classificação:\n");
        sb.append(String.format("%-20s %-10s %-10s %-10s %-10s\n", "Time", "Blots", "Plifs", "Advrunghs", "Pontos"));
        for (Time time : timesClassificados) {
            sb.append(String.format("%-20s %-10d %-10d %-10d %-10d\n", time.getNome(), time.getTotalBlots(), time.getTotalPlifs(), time.getTotalAdvrunghs(), time.getPontos() + 50));
        }
        Time campeao = timesClassificados.get(0);
        sb.append("\nTime Campeão: ").append(campeao.getNome()).append("\n");
        sb.append("Grito de Guerra: ").append(campeao.getGritoDeGuerra());

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setBackground(new Color(248, 248, 248));
        textArea.setForeground(Color.DARK_GRAY);
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return textArea;
    }

    private void exportarParaCSV() {
        String nomeArquivo = JOptionPane.showInputDialog(this, "Digite o nome do arquivo (sem extensão):", "Exportar para CSV", JOptionPane.PLAIN_MESSAGE);

        if (nomeArquivo != null && !nomeArquivo.trim().isEmpty()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar como");
            fileChooser.setSelectedFile(new java.io.File(nomeArquivo + ".csv"));

            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    writer.println("Nome,Blots,Plifs,Advrunghs,Pontos");
                    for (Time time : todosOsTimes) {
                        writer.printf("%s,%d,%d,%d,%d%n", time.getNome(), time.getTotalBlots(), time.getTotalPlifs(), time.getTotalAdvrunghs(), time.getPontos() + 50);
                    }
                    JOptionPane.showMessageDialog(this, "Dados exportados com sucesso!", "Exportar CSV", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao exportar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nome do arquivo inválido. Exportação cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}





