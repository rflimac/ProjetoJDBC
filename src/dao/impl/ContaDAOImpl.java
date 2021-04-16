package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ContaDAO;
import model.Conta;
import model.util.Conexao;

public class ContaDAOImpl implements ContaDAO {

	Conexao conexao = new Conexao();

	@Override
	public void salvar(Conta conta) {
		// TODO Auto-generated method stub

		Connection conn = conexao.getConnection();

		String sql = "INSERT INTO CONTA(NUMERO, SALDO, LIMITE) VALUES (?, ?, ?)";

		try {
			PreparedStatement cont = conn.prepareStatement(sql);
			cont.setInt(1, conta.getNumero());
			cont.setDouble(3, conta.getLimite());
			cont.setDouble(2, conta.getSaldo());
			cont.execute();
			System.out.println("Conta inserida com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir conta no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	@Override
	public void alterar(Conta conta) {
		// TODO Auto-generated method stub

		Connection conn = conexao.getConnection();

		String sql = "UPDATE CONTA SET SALDO = ? , LIMITE = ? WHERE NUMERO = ?";

		try {
			PreparedStatement cont = conn.prepareStatement(sql);
			cont.setDouble(1, conta.getSaldo());
			cont.setDouble(2, conta.getLimite());
			cont.setInt(3, conta.getNumero());
			cont.executeUpdate();
			System.out.println("Conta atualizada com sucesso ");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar a conta do banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public void remover(int numero) {
		// TODO Auto-generated method stub

		Connection conn = conexao.getConnection();

		String sql = "DELETE FROM CONTA WHERE NUMERO =?";

		try {
			PreparedStatement cont = conn.prepareStatement(sql);
			cont.setInt(1, numero);
			cont.execute();
			System.out.println("Conta deletada com sucesso ");
		} catch (Exception e) {
			System.out.println("Erro ao deletar conta no Banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public Conta pesquisar(int numero) {

		Connection conn = conexao.getConnection();
		Conta conta = new Conta();

		String sql = "SELECT * FROM CONTA WHERE NUMERO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return conta;
	}

	@Override
	public List<Conta> listarTodos() {

		Connection conn = conexao.getConnection();

		List<Conta> retorno = new ArrayList<Conta>();

		String sql = "SELECT * FROM CONTA";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
				retorno.add(conta);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar conta " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return retorno;

	}
}
