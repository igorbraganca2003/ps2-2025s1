package ps2.lab07;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final MusicaDAO dao = new MusicaDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Adicionar música");
            System.out.println("2. Listar músicas");
            System.out.println("3. Atualizar música");
            System.out.println("4. Deletar música");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> adicionarMusica();
                case 2 -> listarMusicas();
                case 3 -> atualizarMusica();
                case 4 -> deletarMusica();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void adicionarMusica() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Compositor: ");
        String compositor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        Musica m = new Musica(titulo, compositor, ano);
        dao.adicionar(m);
        System.out.println("Música adicionada!");
    }

    private static void listarMusicas() {
        List<Musica> musicas = dao.listar();
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
        } else {
            for (Musica m : musicas) {
                System.out.println(m.getTitulo() + " - " + m.getCompositor() + " (" + m.getAno() + ")");
            }
        }
    }

    private static void atualizarMusica() {
        System.out.print("Título da música a atualizar: ");
        String titulo = scanner.nextLine();
        System.out.print("Novo compositor: ");
        String compositor = scanner.nextLine();
        System.out.print("Novo ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        Musica m = new Musica(titulo, compositor, ano);
        dao.atualizar(m);
        System.out.println("Música atualizada!");
    }

    private static void deletarMusica() {
        System.out.print("Título da música a deletar: ");
        String titulo = scanner.nextLine();
        dao.deletar(titulo);
        System.out.println("Música deletada!");
    }
}
