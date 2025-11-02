package br.com.estoque;

import br.com.estoque.view.EstoqueView;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new EstoqueView().setVisible(true);
        });
    }
}
    