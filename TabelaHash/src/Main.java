import java.util.Scanner;

public class Main {

    /* Cria a tabela hash */
    private static final HashTableMap<String, String> usersTable = new HashTableMap<>(10, 31);

    /* Menu */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("---- UNIT AUTHENTICATOR ----");
            System.out.println("1. Inserção");
            System.out.println("2. Remoção");
            System.out.println("3. Busca");
            System.out.println("4. Login");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                insertUser(scanner);
            } else if (opcao == 2) {
                removeUser(scanner);
            } else if (opcao == 3) {
                checkUserExists(scanner);
            } else if (opcao == 4) {
                authenticateUser(scanner);
            } else if (opcao == 5) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println(" ");

        } while (opcao != 5);
    }

    /* Inserir usuário */
    public static void insertUser(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.println(" ");
        if (usersTable.get(email) != null) {
            System.out.println("Conta já existente no sistema");
        } else {
            usersTable.put(email, password);
            System.out.println("Sucesso no cadastro.");
        }
    }

    /* Remover usuário */
    public static void removeUser(Scanner scanner) {
        System.out.print("Email para remoção do sistema: ");
        String email = scanner.nextLine();

        String removedUser = usersTable.remove(email);

        System.out.println(" ");
        if (removedUser != null) {
            System.out.println("Conta removida com sucesso");
        } else {
            System.out.println("Conta não encontrada");
        }
    }

    /* Verificar se usuário existe */
    public static void checkUserExists(Scanner scanner) {
        System.out.print("Email para verificação: ");
        String email = scanner.nextLine();

        System.out.println(" ");
        if (usersTable.get(email) != null) {
            System.out.println("Email já registrado.");
        } else {
            System.out.println("Email não está registrado.");
        }
    }

    /* Conectar usuário */
    public static void authenticateUser(Scanner scanner) {
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        String storedPassword = usersTable.get(email);

        System.out.println(" ");
        if (storedPassword != null) {
            if (storedPassword.equals(password)) {
                System.out.println("Sucesso no login.");
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}