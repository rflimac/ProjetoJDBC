package dao;

import java.util.List;

import model.Conta;
import model.Pessoa;

public interface PessoaDAO {
	
	public void salvar(Pessoa pessoa);
	public void alterar(Pessoa pessoa);
	public void remover(String cpf);
	public Conta pesquisar(String cpf);
	public List<Pessoa> listarTodos();
}
