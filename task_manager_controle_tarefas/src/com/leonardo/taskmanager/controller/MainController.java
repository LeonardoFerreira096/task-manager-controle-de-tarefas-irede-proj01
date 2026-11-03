package com.leonardo.taskmanager.controller;

import com.leonardo.taskmanager.infra.TarefaDAO;
import com.leonardo.taskmanager.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML private TextField txtTitulo, txtDescricao, txtResponsavel;
    @FXML private DatePicker dpPrazo;
    @FXML private ComboBox<Prioridade> cbPrioridade;

    @FXML private TableView<Tarefa> tabela;
    @FXML private TableColumn<Tarefa, Integer> colId;
    @FXML private TableColumn<Tarefa, String> colTitulo, colResponsavel;
    @FXML private TableColumn<Tarefa, Object> colPrazo, colPrioridade, colStatus;

    @FXML private Button btnSalvar;
    private Tarefa emEdicao;

    private final TarefaDAO dao = new TarefaDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        colPrazo.setCellValueFactory(new PropertyValueFactory<>("prazo"));
        colPrioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        cbPrioridade.setItems(FXCollections.observableArrayList(Prioridade.values()));
        cbPrioridade.getSelectionModel().select(Prioridade.MEDIA);

        atualizarTabela();
    }

    private void atualizarTabela() {
        tabela.setItems(FXCollections.observableArrayList(dao.listarTodas()));
    }

    @FXML
    private void adicionarTarefa() {
        if (txtTitulo.getText().isBlank() || dpPrazo.getValue() == null) {
            alerta("Preencha pelo menos o título e o prazo.");
            return;
        }
        if (emEdicao != null) {
            emEdicao.setTitulo(txtTitulo.getText());
            emEdicao.setDescricao(txtDescricao.getText());
            emEdicao.setResponsavel(txtResponsavel.getText());
            emEdicao.setPrazo(dpPrazo.getValue());
            emEdicao.setPrioridade(cbPrioridade.getValue());
            dao.atualizar(emEdicao);
        } else {
            dao.inserir(new Tarefa(
                    txtTitulo.getText(),
                    txtDescricao.getText(),
                    txtResponsavel.getText(),
                    dpPrazo.getValue(),
                    cbPrioridade.getValue(),
                    Status.EM_ANDAMENTO));
        }
        limparCampos();
        atualizarTabela();
    }

    @FXML
    private void concluirTarefa() {
        Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            alerta("Selecione uma tarefa na tabela.");
            return;
        }
        dao.concluir(selecionada.getId());
        atualizarTabela();
    }

    @FXML
    private void removerTarefa() {
        Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            alerta("Selecione uma tarefa na tabela.");
            return;
        }
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION,
                "Remover a tarefa \"" + selecionada.getTitulo() + "\"?",
                ButtonType.YES, ButtonType.NO);
        confirmacao.showAndWait().ifPresent(botao -> {
            if (botao == ButtonType.YES) {
                dao.remover(selecionada.getId());
                atualizarTabela();
            }
        });
    }

    @FXML
    private void editarTarefa() {
        Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            alerta("Selecione uma tarefa na tabela.");
            return;
        }
        emEdicao = selecionada;
        txtTitulo.setText(selecionada.getTitulo());
        txtDescricao.setText(selecionada.getDescricao());
        txtResponsavel.setText(selecionada.getResponsavel());
        dpPrazo.setValue(selecionada.getPrazo());
        cbPrioridade.setValue(selecionada.getPrioridade());
        btnSalvar.setText("Salvar alterações");
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtDescricao.clear();
        txtResponsavel.clear();
        dpPrazo.setValue(null);
        emEdicao = null;
        btnSalvar.setText("Adicionar tarefa");
    }

    private void alerta(String mensagem) {
        new Alert(Alert.AlertType.WARNING, mensagem).showAndWait();
    }
}