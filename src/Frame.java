import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.Color;
import javax.swing.*;
public class Frame extends JFrame implements ActionListener {
    /* declaração de variaveis */
    private int i;
    int numero;
    int[] tiposSorteados;
    String[] tipos = { "", "leite", "arroz", "manteiga", "ovos", "queijo", "alface", "repolho-roxo", "chocolate",
            "maçã", "cuscuz", "cenoura", "carne", "cafe", "açucar", "sal", "macarrão", "tomate", "feijão", "biscoito",
            "aranha" };
    JLabel pontuacao = new JLabel();
    JButton jButton = new JButton("começar");
    JButton lista = new JButton("Lista");

    public Frame() {
        /* Utillização do Jframe para a interface grafica do App */
        pontuacao.setVisible(false); // reinicia a pontuação anterior depois da segunda vez jogada
        lista.setBounds(190, 690, 100, 50);
        lista.setFont(new Font("Arial", Font.BOLD, 10));
        lista.setForeground(Color.black);
        lista.setBackground(Color.white);

        lista.addActionListener(this);
        lista.setVisible(true);

        ImageIcon imagem = new ImageIcon((getClass().getResource("image\\menu.png")));
        JLabel jLabel = new JLabel(imagem);

        jLabel.setVisible(true);

        jLabel.setBounds(-15, -55, 700, 800);

        setVisible(true);
        setSize(700, 800);
        setTitle("Spider Shopping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        add(jLabel);

        jButton.setBounds(370, 690, 100, 50);
        jButton.setFont(new Font("Arial", Font.BOLD, 10));
        jButton.setForeground(Color.black);
        jButton.setBackground(Color.white);

        jButton.addActionListener(this);
        jButton.setVisible(true);
        add(jButton);
        add(lista);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton) {
            // Exibe o JOptionPane para escolher a dificuldade
            String[] options = { "Normal", "Difícil" };
            int selectedOption = JOptionPane.showOptionDialog(null, "Escolha a dificuldade", "Dificuldade",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // Verifica a opção selecionada
            if (selectedOption == 1) {
                // Inicia o jogo na dificuldade "Dificil"
                dificil();
            } else {
                // Inicia o jogo na dificuldade "Normal"
                tiposSorteados = new int[6];
                jButton.setVisible(false);
                JOptionPane.showMessageDialog(null, "Os objetos a serem comprados são:", "Compras",
                        JOptionPane.INFORMATION_MESSAGE);

                Random random = new Random();
                /*
                 * HashSet serve para armazenar os numeros gerados por random e comprar, para
                 * que não tenha numeros repitidos
                 */
                Set<Integer> numerosGerados = new HashSet<>();

                for (i = 0; i < 6; i++) {

                    do {
                        numero = random.nextInt(19) + 1;
                        tiposSorteados[i] = numero;
                    } while (numerosGerados.contains(numero));
                    numerosGerados.add(numero);

                    String nomeImagem = String.format("%s", tipos[numero]);
                    String caminhoImagem = "image/" + nomeImagem + ".png";
                    ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));
                    JOptionPane.showConfirmDialog(null, i + 1 + " - " + tipos[numero], "Lista",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE, icone);

                }
                String nomeimagem = String.format("%s", tipos[20]);
                String caminhoImagem = "image/" + nomeimagem + ".png";
                ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));
                JOptionPane.showConfirmDialog(null, "Vamos começar!", "Spider Shopping", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, icone);

                ImageIcon imagem2 = new ImageIcon((getClass().getResource("image\\aranha.png")));
                JLabel jLabel2 = new JLabel(imagem2);
                add(jLabel2);
                jLabel2.setVisible(true);
                jLabel2.setBounds(69, 680, 64, 64);

                jogar(tiposSorteados);
                jLabel2.setVisible(false);
            }
        } else if (e.getSource() == lista) {
            new ListaDeItens();
        }
    }

    public void jogar(int[] tiposSorteados) {
        int j = 1;
        int ponto = 0;

        while (j < 20) {
            String nomeimagem = String.format("%s", tipos[j]);
            String caminhoImagem = "image/" + nomeimagem + ".png";
            ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));

            int opcao = JOptionPane.showConfirmDialog(null, tipos[j] + " - Você queria comprar esse objeto? ",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icone);

            if (opcao == 0) {
                /*
                 * esse if faz algo parecido com o Hash, ele compara a entrada da opcao == 0 e
                 * se j é igual tiposSosteados
                 */
                if (Arrays.stream(tiposSorteados).boxed().collect(Collectors.toList()).contains(Integer.valueOf(j))) {
                    ponto += 3;
                    System.out.println("comprou");
                } else {
                    ponto -= 3;
                     System.out.println(ponto); 
                }
            } else {
                 System.out.println("Você não comprou nada.");
            }

            j++;
        }
        /* mostra a pontuação ao final da execução */
        Font grade = new Font("Arial", Font.BOLD, 20);
        pontuacao.setFont(grade);
        add(pontuacao);
        pontuacao.setText("Pontuação: " + String.valueOf(ponto));
        pontuacao.setBounds(530, 680, 300, 60);
        pontuacao.setVisible(true);
        jButton.setVisible(true);
    }

    public void dificil() {
        /* dificuldade dificil */
        tiposSorteados = new int[9];
        jButton.setVisible(false);
        JOptionPane.showMessageDialog(null, "Os objetos a serem comprados são:", "Compras",
                JOptionPane.INFORMATION_MESSAGE);

        Random random = new Random();
        Set<Integer> numerosGerados = new HashSet<>();

        for (i = 0; i < 9; i++) {

            do {
                numero = random.nextInt(19) + 1;
                tiposSorteados[i] = numero;
            } while (numerosGerados.contains(numero));
            numerosGerados.add(numero);

            String nomeImagem = String.format("%s", tipos[numero]);
            String caminhoImagem = "image/" + nomeImagem + ".png";
            ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));
            JOptionPane.showConfirmDialog(null, i + 1 + " - " + tipos[numero], "Lista", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, icone);

        }
        String nomeimagem = String.format("%s", tipos[20]);
        String caminhoImagem = "image/" + nomeimagem + ".png";
        ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));
        JOptionPane.showConfirmDialog(null, "Vamos começar!", "Spider Shopping", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, icone);
        ImageIcon imagem2 = new ImageIcon((getClass().getResource("image\\aranha.png")));
        JLabel jLabel2 = new JLabel(imagem2);
        add(jLabel2);
        jLabel2.setVisible(true);
        jLabel2.setBounds(69, 680, 64, 64);
        /* executa o metodo jogar */
        jogar(tiposSorteados);
        jLabel2.setVisible(false);

    }
}