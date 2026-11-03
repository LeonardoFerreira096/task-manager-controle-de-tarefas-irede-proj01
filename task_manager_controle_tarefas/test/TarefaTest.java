import com.leonardo.taskmanager.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void deveCriarTarefaComDadosCorretos() {
        Tarefa t = new Tarefa("Estudar", "Generics", "Leo",
                LocalDate.of(2026, 11, 10), Prioridade.ALTA, Status.EM_ANDAMENTO);

        assertEquals("Estudar", t.getTitulo());
        assertEquals(Prioridade.ALTA, t.getPrioridade());
        assertEquals(Status.EM_ANDAMENTO, t.getStatus());
    }

    @Test
    void deveConcluirTarefa() {
        Tarefa t = new Tarefa("Estudar", "Generics", "Leo",
                LocalDate.now(), Prioridade.MEDIA, Status.EM_ANDAMENTO);

        t.concluir();

        assertEquals(Status.CONCLUIDO, t.getStatus());
    }

    @Test
    void listaGenericaDeveAdicionarERemover() {
        ListaGenerica<Tarefa> lista = new ListaGenerica<>();
        Tarefa t = new Tarefa("A", "B", "C", LocalDate.now(),
                Prioridade.BAIXA, Status.EM_ANDAMENTO);

        lista.adicionar(t);
        assertEquals(1, lista.listar().size());

        lista.remover(t);
        assertTrue(lista.listar().isEmpty());
    }

    @Test
    void listaGenericaDeveAceitarSubclasseComExtends() {
        ListaGenerica<Tarefa> lista = new ListaGenerica<>();
        List<TarefaPrioritaria> prioritarias = new ArrayList<>();
        prioritarias.add(new TarefaPrioritaria("Urgente", "x", "Leo", LocalDate.now(),
                Prioridade.ALTA, Status.EM_ANDAMENTO, 24, "Cliente"));

        lista.adicionarTodos(prioritarias);

        assertEquals(1, lista.listar().size());
    }
}