package br.com.estoque.view;

import br.com.estoque.dao.ProdutoDAO;
import br.com.estoque.model.Produto;
import javax.swing.*;

public class EstoqueView extends JFrame {
    private final ProdutoDAO dao = new ProdutoDAO();
    private final JTextField txtNome = new JTextField(15);
    private final JTextField txtDescricao = new JTextField(15);
    private final JTextField txtQuantidade = new JTextField(5);
    private final JTextField txtPreco = new JTextField(5);

    public EstoqueView() {
        setTitle("Controle de Estoque");
        setSize(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel painel = new JPanel();

        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(new JLabel("Descrição:"));
        painel.add(txtDescricao);
        painel.add(new JLabel("Qtd:"));
        painel.add(txtQuantidade);
        painel.add(new JLabel("Preço:"));
        painel.add(txtPreco);

        JButton btnAdd = new JButton("Adicionar");
        JButton btnListar = new JButton("Listar");
        painel.add(btnAdd);
        painel.add(btnListar);

        add(painel, "Center");

        btnAdd.addActionListener(e -> adicionarProduto());
        btnListar.addActionListener(e -> abrirListaProdutos());
    }

    private void adicionarProduto() {
        try {
            String nome = txtNome.getText().trim();
            String desc = txtDescricao.getText().trim();
            
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Nome é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int qtd = parseInteger(txtQuantidade.getText());
            if (qtd < 0) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double preco = parseDouble(txtPreco.getText());
            if (preco < 0) {
                JOptionPane.showMessageDialog(this, "O preço deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Produto p = new Produto(nome, desc, qtd, preco);
            dao.adicionar(p);
            limparCampos();
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Verifique se os campos Quantidade e Preço contêm números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int parseInteger(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new NumberFormatException("Campo vazio");
        }
        // Remove espaços e caracteres não numéricos (exceto sinal negativo)
        String limpo = texto.trim().replaceAll("[^0-9-]", "");
        if (limpo.isEmpty() || limpo.equals("-")) {
            throw new NumberFormatException("Número inválido: " + texto);
        }
        return Integer.parseInt(limpo);
    }

    private double parseDouble(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new NumberFormatException("Campo vazio");
        }
        // Remove espaços e texto extra, mantendo números, vírgula/ponto e sinal negativo
        String limpo = texto.trim().replaceAll("[^0-9,.-]", "");
        // Converte vírgula para ponto (formato brasileiro)
        limpo = limpo.replace(",", ".");
        // Remove pontos extras (mantém apenas o separador decimal)
        String[] partes = limpo.split("\\.");
        if (partes.length > 2) {
            // Se houver mais de um ponto, mantém apenas o primeiro como separador decimal
            limpo = partes[0] + "." + String.join("", java.util.Arrays.copyOfRange(partes, 1, partes.length));
        }
        if (limpo.isEmpty() || limpo.equals("-") || limpo.equals(".")) {
            throw new NumberFormatException("Número inválido: " + texto);
        }
        return Double.parseDouble(limpo);
    }

    private void abrirListaProdutos() {
        new ListaProdutosView().setVisible(true);
    }

    private void limparCampos() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtQuantidade.setText("");
        txtPreco.setText("");
    }
}
