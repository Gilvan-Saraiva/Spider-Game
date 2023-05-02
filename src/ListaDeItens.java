import javax.swing.*;

public class ListaDeItens extends JFrame {
    
    private final String[] itens = {"leite", "arroz", "manteiga", "ovos", "queijo", "alface", "repolho-roxo", "chocolate",
    "maçã", "cuscuz", "cenoura", "carne", "cafe", "açucar", "sal", "macarrão", "tomate", "feijão", "biscoito"};

public ListaDeItens() {
super("Itens do Mercado");

JList<String> lista = new JList<>(itens);
JScrollPane scrollPane = new JScrollPane(lista);
add(scrollPane);

setSize(300, 400);

setLocationRelativeTo(null);
setVisible(true);
}

public static void main(String[] args) {
new ListaDeItens();
}
}
