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
        String nome = txtNome.getText();
        String desc = txtDescricao.getText();
        int qtd = Integer.parseInt(txtQuantidade.getText());
        double preco = Double.parseDouble(txtPreco.getText());

        Produto p = new Produto(nome, desc, qtd, preco);
        dao.adicionar(p);
        limparCampos();
        JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
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
