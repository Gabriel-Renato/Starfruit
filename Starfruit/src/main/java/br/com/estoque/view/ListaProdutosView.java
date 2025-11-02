package br.com.estoque.view;

import br.com.estoque.dao.ProdutoDAO;
import br.com.estoque.model.Produto;
import java.util.List;
import javax.swing.*;

public class ListaProdutosView extends JFrame {
    private final ProdutoDAO dao = new ProdutoDAO();
    private final DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Descrição", "Qtd", "Preço"}, 0);
    private final JTable tabela = new JTable(modelo);

    public ListaProdutosView() {
        setTitle("Lista de Produtos");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, "Center");

        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnApagar = new JButton("Apagar");
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnApagar);
        add(painelBotoes, "South");

        btnAtualizar.addActionListener(e -> carregarTabela());
        btnEditar.addActionListener(e -> editarProduto());
        btnApagar.addActionListener(e -> apagarProduto());
        
        // Carregar produtos ao abrir a janela
        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        List<Produto> produtos = dao.listar();
        for (Produto p : produtos) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getDescricao(), p.getQuantidade(), p.getPreco()});
        }
    }

    private void editarProduto() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modelo.getValueAt(linhaSelecionada, 0);
        Produto produto = dao.buscarPorId(id);
        
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar campos de edição
        JTextField txtNome = new JTextField(produto.getNome(), 20);
        JTextField txtDescricao = new JTextField(produto.getDescricao(), 20);
        JTextField txtQuantidade = new JTextField(String.valueOf(produto.getQuantidade()), 10);
        JTextField txtPreco = new JTextField(String.valueOf(produto.getPreco()), 10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(new JLabel("Descrição:"));
        painel.add(txtDescricao);
        painel.add(new JLabel("Quantidade:"));
        painel.add(txtQuantidade);
        painel.add(new JLabel("Preço:"));
        painel.add(txtPreco);

        int resultado = JOptionPane.showConfirmDialog(
            this, 
            painel, 
            "Editar Produto", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                produto.setNome(txtNome.getText());
                produto.setDescricao(txtDescricao.getText());
                produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                produto.setPreco(Double.parseDouble(txtPreco.getText()));
                
                dao.atualizar(produto);
                carregarTabela();
                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Quantidade e Preço devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void apagarProduto() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para apagar!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modelo.getValueAt(linhaSelecionada, 0);
        String nome = (String) modelo.getValueAt(linhaSelecionada, 1);

        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente apagar o produto \"" + nome + "\"?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            dao.deletar(id);
            carregarTabela();
            JOptionPane.showMessageDialog(this, "Produto apagado com sucesso!");
        }
    }
}

