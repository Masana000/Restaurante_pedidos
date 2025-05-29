package app;

import api.controller.impl.ItemPedidoController;
import api.controller.impl.PedidoController;
import api.controller.impl.PratoController;
import api.model.ItemPedido;
import api.model.Pedido;
import api.model.Prato;
import api.model.StatusPedido;
import api.repository.impl.CachedPratoRepository;
import api.repository.impl.InMemoryRepository;
import api.service.impl.ItemPedidoService;
import api.service.impl.PedidoService;
import api.service.impl.PratoService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PratoController pratoController;
    private static PedidoController pedidoController;
    private static ItemPedidoController itemPedidoController;

    public static void main(String[] args) {
        InMemoryRepository<Prato> pratoRepository = new InMemoryRepository<>();
        CachedPratoRepository cachedPratoRepository = new CachedPratoRepository(pratoRepository);
        InMemoryRepository<Pedido> pedidoRepository = new InMemoryRepository<>();
        InMemoryRepository<ItemPedido> itemPedidoRepository = new InMemoryRepository<>();
        PratoService pratoService = new PratoService(cachedPratoRepository);
        PedidoService pedidoService = new PedidoService(pedidoRepository);
        ItemPedidoService itemPedidoService = new ItemPedidoService(itemPedidoRepository);

        pratoController = new PratoController(pratoService);
        pedidoController = new PedidoController(pedidoService);
        itemPedidoController = new ItemPedidoController(itemPedidoService);

        System.out.println("Bem-vindo ao Sistema de Gerenciamento de Pedidos do Restaurante!");
        popularDadosIniciais(); 
        exibirMenuPrincipal();
    }
    private static void popularDadosIniciais() {
        System.out.println("\nPopuando dados iniciais...");
        pratoController.add(new Prato(UUID.randomUUID().toString(), "Pizza Calabresa", "Pizza com molho, queijo e calabresa", 40.00));
        pratoController.add(new Prato(UUID.randomUUID().toString(), "Macarrão à Carbonara", "Massa com ovos, queijo, bacon e pimenta", 55.00));
        pratoController.add(new Prato(UUID.randomUUID().toString(), "Refrigerante Lata", "Coca-cola, Pepsi, Guaraná", 7.00));
        System.out.println("Dados iniciais populados.");
    }
    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gerenciar Pratos");
            System.out.println("2. Gerenciar Pedidos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        menuGerenciarPratos();
                        break;
                    case 2:
                        menuGerenciarPedidos();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Obrigado!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); 
                opcao = -1; 
            }
        } while (opcao != 0);
        scanner.close();
    }
    private static void menuGerenciarPratos() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR PRATOS ---");
            System.out.println("1. Adicionar Prato");
            System.out.println("2. Listar Todos os Pratos");
            System.out.println("3. Buscar Prato por ID");
            System.out.println("4. Atualizar Prato");
            System.out.println("5. Excluir Prato");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        adicionarPrato();
                        break;
                    case 2:
                        listarPratos();
                        break;
                    case 3:
                        buscarPratoPorId();
                        break;
                    case 4:
                        atualizarPrato();
                        break;
                    case 5:
                        excluirPrato();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); 
                opcao = -1;
            }
        } while (opcao != 0);
    }
    private static void menuGerenciarPedidos() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR PEDIDOS ---");
            System.out.println("1. Criar Novo Pedido");
            System.out.println("2. Listar Todos os Pedidos");
            System.out.println("3. Buscar Pedido por ID");
            System.out.println("4. Adicionar Item ao Pedido");
            System.out.println("5. Remover Item do Pedido");
            System.out.println("6. Atualizar Status do Pedido");
            System.out.println("7. Excluir Pedido");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcao) {
                    case 1:
                        criarPedido();
                        break;
                    case 2:
                        listarPedidos();
                        break;
                    case 3:
                        buscarPedidoPorId();
                        break;
                    case 4:
                        adicionarItemAoPedido();
                        break;
                    case 5:
                        removerItemDoPedido();
                        break;
                    case 6:
                        atualizarStatusPedido();
                        break;
                    case 7:
                        excluirPedido();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); 
                opcao = -1;
            }
        } while (opcao != 0);
    }
    private static void adicionarPrato() {
        System.out.println("\n--- ADICIONAR PRATO ---");
        System.out.print("Nome do Prato: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do Prato: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço do Prato: ");
        double preco = 0.0;
        try {
            preco = scanner.nextDouble();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Preço inválido. Operação cancelada.");
            scanner.nextLine(); 
            return;
        }

        Prato novoPrato = new Prato(UUID.randomUUID().toString(), nome, descricao, preco);
        pratoController.add(novoPrato);
        System.out.println("Prato '" + nome + "' adicionado com sucesso!");
    }

    private static void listarPratos() {
        System.out.println("\n--- LISTA DE PRATOS ---");
        List<Prato> pratos = pratoController.getAll();
        if (pratos.isEmpty()) {
            System.out.println("Nenhum prato cadastrado.");
            return;
        }
        pratos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Descrição: " + p.getDescricao() + " | Preço: R$" + String.format("%.2f", p.getPreco())));
    }

    private static void buscarPratoPorId() {
        System.out.println("\n--- BUSCAR PRATO POR ID ---");
        System.out.print("Digite o ID do Prato: ");
        String id = scanner.nextLine();
        Prato prato = pratoController.getById(id);
        if (prato != null) {
            System.out.println("Prato encontrado: ID: " + prato.getId() + " | Nome: " + prato.getNome() + " | Descrição: " + prato.getDescricao() + " | Preço: R$" + String.format("%.2f", prato.getPreco()));
        } else {
            System.out.println("Prato com ID '" + id + "' não encontrado.");
        }
    }

    private static void atualizarPrato() {
        System.out.println("\n--- ATUALIZAR PRATO ---");
        System.out.print("Digite o ID do Prato a ser atualizado: ");
        String id = scanner.nextLine();
        Prato pratoExistente = pratoController.getById(id);

        if (pratoExistente == null) {
            System.out.println("Prato com ID '" + id + "' não encontrado.");
            return;
        }

        System.out.println("Prato atual: " + pratoExistente.getNome() + " (R$" + String.format("%.2f", pratoExistente.getPreco()) + ")");
        System.out.print("Novo Nome (deixe em branco para manter '" + pratoExistente.getNome() + "'): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) {
            pratoExistente.setNome(novoNome);
        }

        System.out.print("Nova Descrição (deixe em branco para manter '" + pratoExistente.getDescricao() + "'): ");
        String novaDescricao = scanner.nextLine();
        if (!novaDescricao.isEmpty()) {
            pratoExistente.setDescricao(novaDescricao);
        }

        System.out.print("Novo Preço (digite 0 para manter " + String.format("%.2f", pratoExistente.getPreco()) + "): ");
        double novoPreco = 0.0;
        try {
            novoPreco = scanner.nextDouble();
            scanner.nextLine(); 
            if (novoPreco > 0) {
                pratoExistente.setPreco(novoPreco);
            }
        } catch (InputMismatchException e) {
            System.out.println("Preço inválido. Mantendo preço original.");
            scanner.nextLine(); 
        }

        if (pratoController.update(id, pratoExistente)) {
            System.out.println("Prato atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar o prato.");
        }
    }

    private static void excluirPrato() {
        System.out.println("\n--- EXCLUIR PRATO ---");
        System.out.print("Digite o ID do Prato a ser excluído: ");
        String id = scanner.nextLine();
        if (pratoController.delete(id)) {
            System.out.println("Prato excluído com sucesso!");
        } else {
            System.out.println("Prato com ID '" + id + "' não encontrado ou falha ao excluir.");
        }
    }
    private static void criarPedido() {
    System.out.println("\n--- CRIAR NOVO PEDIDO ---");
    System.out.print("Digite a mesa do pedido: ");
    String mesa = scanner.nextLine();
    
    // Gerar ID sequencial para pedidos (PE001, PE002, etc.)
    int nextPedidoId = pedidoController.getAll().size() + 1;
    String pedidoId = "PE" + String.format("%03d", nextPedidoId);
    
    Pedido novoPedido = new Pedido(pedidoId, mesa);
    pedidoController.add(novoPedido);
    System.out.println("Pedido criado para a mesa '" + mesa + "' com ID: " + novoPedido.getId());
}

    private static void listarPedidos() {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        List<Pedido> pedidos = pedidoController.getAll();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println("ID: " + p.getId() + " | Mesa: " + p.getMesa() + " | Hora: " + p.getHora() + " | Status: " + p.getStatus().getDescricao());
            List<ItemPedido> itensDoPedido = itemPedidoController.getAll().stream()
                    .filter(item -> item.getPedidoId().equals(p.getId()))
                    .toList();
            if (!itensDoPedido.isEmpty()) {
                System.out.println("  Itens:");
                for (ItemPedido item : itensDoPedido) {
                    Prato prato = pratoController.getById(item.getPratoId());
                    String nomePrato = (prato != null) ? prato.getNome() : "Prato Desconhecido";
                    System.out.println("    - " + item.getQuantidade() + "x " + nomePrato + " (R$" + String.format("%.2f", item.getPrecoUnitario()) + ") | ID Item: " + item.getId());
                }
            } else {
                System.out.println("  Nenhum item neste pedido.");
            }
            System.out.println("------------------------------------");
        }
    }

    private static void buscarPedidoPorId() {
        System.out.println("\n--- BUSCAR PEDIDO POR ID ---");
        System.out.print("Digite o ID do Pedido: ");
        String id = scanner.nextLine();
        Pedido pedido = pedidoController.getById(id);
        if (pedido != null) {
            System.out.println("Pedido encontrado: ID: " + pedido.getId() + " | Mesa: " + pedido.getMesa() + " | Hora: " + pedido.getHora() + " | Status: " + pedido.getStatus().getDescricao());
            List<ItemPedido> itensDoPedido = itemPedidoController.getAll().stream()
                    .filter(item -> item.getPedidoId().equals(pedido.getId()))
                    .toList();
            if (!itensDoPedido.isEmpty()) {
                System.out.println("  Itens:");
                for (ItemPedido item : itensDoPedido) {
                    Prato prato = pratoController.getById(item.getPratoId());
                    String nomePrato = (prato != null) ? prato.getNome() : "Prato Desconhecido";
                    System.out.println("    - " + item.getQuantidade() + "x " + nomePrato + " (R$" + String.format("%.2f", item.getPrecoUnitario()) + ") | ID Item: " + item.getId());
                }
            } else {
                System.out.println("  Nenhum item neste pedido.");
            }
        } else {
            System.out.println("Pedido com ID '" + id + "' não encontrado.");
        }
    }

    private static void adicionarItemAoPedido() {
    System.out.println("\n--- ADICIONAR ITEM AO PEDIDO ---");
    System.out.print("Digite o ID do Pedido: ");
    String pedidoId = scanner.nextLine();
    Pedido pedido = pedidoController.getById(pedidoId);

    if (pedido == null) {
        System.out.println("Pedido com ID '" + pedidoId + "' não encontrado.");
        return;
    }

    listarPratos();
    System.out.print("Digite o ID do Prato a ser adicionado: ");
    String pratoId = scanner.nextLine();
    Prato prato = pratoController.getById(pratoId);

    if (prato == null) {
        System.out.println("Prato com ID '" + pratoId + "' não encontrado.");
        return;
    }

    System.out.print("Digite a quantidade: ");
    try {
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        // Gerar ID sequencial para itens (IT001, IT002, etc.)
        int nextItemId = itemPedidoController.getAll().size() + 1;
        String itemId = "IT" + String.format("%03d", nextItemId);
        
        ItemPedido novoItem = new ItemPedido(itemId, pedidoId, pratoId, quantidade, prato.getPreco());
        itemPedidoController.add(novoItem);
        System.out.println(quantidade + "x '" + prato.getNome() + "' adicionado ao pedido " + pedidoId + ".");
    } catch (InputMismatchException e) {
        System.out.println("Quantidade inválida. Digite um número inteiro.");
        scanner.nextLine();
    }
}

    private static void removerItemDoPedido() {
        System.out.println("\n--- REMOVER ITEM DO PEDIDO ---");
        System.out.print("Digite o ID do Item de Pedido a ser removido: ");
        String itemId = scanner.nextLine();

        if (itemPedidoController.delete(itemId)) {
            System.out.println("Item de pedido excluído com sucesso!");
        } else {
            System.out.println("Item de pedido com ID '" + itemId + "' não encontrado ou falha ao excluir.");
        }
    }

    private static void atualizarStatusPedido() {
        System.out.println("\n--- ATUALIZAR STATUS DO PEDIDO ---");
        System.out.print("Digite o ID do Pedido: ");
        String pedidoId = scanner.nextLine();
        Pedido pedido = pedidoController.getById(pedidoId);

        if (pedido == null) {
            System.out.println("Pedido com ID '" + pedidoId + "' não encontrado.");
            return;
        }

        System.out.println("Status atual do pedido " + pedido.getId() + ": " + pedido.getStatus().getDescricao());
        System.out.println("Opções de status:");
        for (StatusPedido status : StatusPedido.values()) {
            System.out.println("- " + status.getDescricao());
        }
        System.out.print("Digite o novo status: ");
        String novoStatusDesc = scanner.nextLine();
        StatusPedido novoStatus = StatusPedido.fromDescricao(novoStatusDesc);

        if (novoStatus == null) {
            System.out.println("Status inválido. Operação cancelada.");
            return;
        }

        pedido.setStatus(novoStatus);
        if (pedidoController.update(pedidoId, pedido)) {
            System.out.println("Status do pedido " + pedidoId + " atualizado para '" + novoStatus.getDescricao() + "'.");
        } else {
            System.out.println("Falha ao atualizar o status do pedido.");
        }
    }

    private static void excluirPedido() {
        System.out.println("\n--- EXCLUIR PEDIDO ---");
        System.out.print("Digite o ID do Pedido a ser excluído: ");
        String pedidoId = scanner.nextLine();
        List<ItemPedido> itensAssociados = itemPedidoController.getAll().stream()
                .filter(item -> item.getPedidoId().equals(pedidoId))
                .toList();

        for (ItemPedido item : itensAssociados) {
            itemPedidoController.delete(item.getId());
            System.out.println("  Item de pedido " + item.getId() + " removido.");
        }

        if (pedidoController.delete(pedidoId)) {
            System.out.println("Pedido excluído com sucesso!");
        } else {
            System.out.println("Pedido com ID '" + pedidoId + "' não encontrado ou falha ao excluir.");
        }
    }
}
