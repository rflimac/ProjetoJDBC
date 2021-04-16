package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PessoaDAO;

import model.Pessoa;
import model.util.Conexao;

public class PessoaDAOImpl implements PessoaDAO {

	Conexao conexao = new Conexao();

	@Override
	public void salvar(Pessoa pessoa) {
		Connection conn = conexao.getConnection();

		String sql = "INSERT INTO PESSOA(NOME, IDADE, SEXO, CPF, ID_ENDERECO, NUMERO_CONTA) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pess = conn.prepareStatement(sql);
			pess.setString(1, pessoa.getNome());
			pess.setInt(2, pessoa.getIdade());
			pess.setString(3, pessoa.getSexo());
			pess.setString(4, pessoa.getCpf());
			// pess.setString(5, pessoa.getId_endereco());
			// pess.setInt(6, pessoa.getNumero_conta());
			pess.execute();
			System.out.println("Pessoa inserida com sucesso! ");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o correntista" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);

		}

	}

	@Override
	public void alterar(Pessoa pessoa) {
		Connection conn = conexao.getConnection();

		String sql = "UPDATE PESSOA SET NOME = ?, IDADE = ?, SEXO = ?, WHERE CPF = ?";

		try {
			PreparedStatement pess = conn.prepareStatement(sql);
			pess.setString(1, pessoa.getNome());
			pess.setInt(2, pessoa.getIdade());
			pess.setString(3, pessoa.getSexo());
			pess.setString(4, pessoa.getCpf()); // CPF é o id, imutavel????
			System.out.println("Correntista atualizado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o correntista" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public void remover(String cpf) {
		Connection conn = conexao.getConnection();

		String sql = "DELETE FROM PESSOA WHERE CPF= ?";
		try {
			PreparedStatement pess = conn.prepareStatement(sql);
			pess.setString(1, cpf);
			System.out.println("Correntista excluido com sucesso");
		} catch (Exception e) {
			System.out.println("Correntista não apagado" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public Pessoa pesquisar(String cpf) {
		Connection conn = conexao.getConnection();
		Pessoa pessoa = new Pessoa();

		String sql = "SELECT * FROM PESSOA WHERE CPF = ?";

		try {
			PreparedStatement pess = conn.prepareStatement(sql);
			pess.setString(1, cpf);
			ResultSet rs = pess.executeQuery();

			while (rs.next()) {
				pessoa.setCpf(rs.getString("CPF"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return pessoa;
	}

	@Override
	public List<Pessoa> listarTodos() {
		Connection conn = conexao.getConnection();

		List<Pessoa> retorno = new ArrayList<Pessoa>();

		String sql = "SELECT * FROM PESSOA";

		try {
			PreparedStatement pess = conn.prepareStatement(sql);

			ResultSet rs = pess.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setIdade(rs.getInt("IDADE"));
				pessoa.setSexo(rs.getString("SEXO"));
				pessoa.setCpf(rs.getString("CPF"));
				// pessoa.setEndereco(rs.getString("ID_ENDERECO"));
				// pessoa.setConta(rs.getInt("NUMERO_CONTA"));
				retorno.add(pessoa);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar o correntista" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
	}

}
