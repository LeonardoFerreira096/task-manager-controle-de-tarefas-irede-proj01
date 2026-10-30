//package com.leonardo.taskmanager.controller;
//
//import com.leonardo.taskmanager.infra.TarefaDAO;
//import com.leonardo.taskmanager.model.*;
//import javafx.collections.FXCollections;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//public class MainController {
//
//    @FXML private TextField txtTitulo, txtDescricao, txtResponsavel;
//    @FXML private DatePicker dpPrazo;
//    @FXML private ComboBox<Prioridade> cbPrioridade;
//
//    @FXML private TableView<Tarefa> tabela;
//    @FXML private TableColumn<Tarefa, Integer> colId;
//    @FXML private TableColumn<Tarefa, String> colTitulo, colResponsavel;
//    @FXML private TableColumn<Tarefa, Object> colPrazo, colPrioridade, colStatus;
//
//    private final TarefaDAO dao = new TarefaDAO();
//
//    @FXML
//    public void initialize() {
//        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
//        colResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
//        colPrazo.setCellValueFactory(new PropertyValueFactory<>("prazo"));
//        colPrioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
//        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//        cbPrioridade.setItems(FXCollections.observableArrayList(Prioridade.values()));
//        cbPrioridade.getSelectionModel().select(Prioridade.MEDIA);
//
//        atualizarTabela();
//    }
//
//    private void atualizarTabela() {
//        tabela.setItems(FXCollections.observableArrayList(dao.listarTodas()));
//    }
//
//    @FXML
//    private void adicionarTarefa() {
//        if (txtTitulo.getText().isBlank() || dpPrazo.getValue() == null) {
//            alerta("Preencha pelo menos o título e o prazo.");
//            return;
//        }
//        Tarefa t = new Tarefa(
//                txtTitulo.getText(),
//                txtDescricao.getText(),
//                txtResponsavel.getText(),
//                dpPrazo.getValue(),
//                cbPrioridade.getValue(),
//                Status.EM_ANDAMENTO);
//        dao.inserir(t);
//        limparCampos();
//        atualizarTabela();
//    }
//
//    @FXML
//    private void concluirTarefa() {
//        Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();
//        if (selecionada == null) {
//            alerta("Selecione uma tarefa na tabela.");
//            return;
//        }
//        dao.concluir(selecionada.getId());
//        atualizarTabela();
//    }
//
//    @FXML
//    private void removerTarefa() {
//        Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();
//        if (selecionada == null) {
//            alerta("Selecione uma tarefa na tabela.");
//            return;
//        }
//        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION,
//                "Remover a tarefa \"" + selecionada.getTitulo() + "\"?",
//                ButtonType.YES, ButtonType.NO);
//        confirmacao.showAndWait().ifPresent(botao -> {
//            if (botao == ButtonType.YES) {
//                dao.remover(selecionada.getId());
//                atualizarTabela();
//            }
//        });
//    }
//
//    private void limparCampos() {
//        txtTitulo.clear();
//        txtDescricao.clear();
//        txtResponsavel.clear();
//        dpPrazo.setValue(null);
//    }
//
//    private void alerta(String mensagem) {
//        new Alert(Alert.AlertType.WARNING, mensagem).showAndWait();
//    }
//}