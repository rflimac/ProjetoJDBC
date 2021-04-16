package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EnderecoDAO;
import model.Conta;
import model.Endereco;
import model.util.Conexao;

public class EnderecoDAOImpl implements EnderecoDAO {

	Conexao conexao = new Conexao();

	@Override
	public int getSequence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void salvar(Endereco endereco) {
		// TODO Auto-generated method stub
		Connection conn = conexao.getConnection();

		String sql = "INSERT INTO ENDERECO(ID, NUMERO, RUA, COMPLEMENTO) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement ed = conn.prepareStatement(sql);
			ed.setInt(1, endereco.getId());
			ed.setString(2, endereco.getRua());
			ed.setInt(3, endereco.getNumero());
			ed.setString(4, endereco.getComplemento());
			ed.execute();
			System.out.println("Endereco inserido com sucesso! ");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir conta no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);

		}

	}

	@Override
	public void alterar(Endereco endereco) {
		Connection conn = conexao.getConnection();

		String sql = "UPDATE ENDERECO SET RUA = ?, NUMERO = ?, COMPLEMENTO = ? WHERE ID_ENDERECO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getRua());
			ps.setInt(2, endereco.getNumero());
			ps.setString(3, endereco.getComplemento());
			ps.setInt(4, endereco.getId());
			System.out.println("Endereço atualizado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o endereço" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public void remover(int id) {
		Connection conn = conexao.getConnection();

		String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println("Endereço apagado com sucesso");
		} catch (Exception e) {
			System.out.println("Endereço não apagado" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public Endereco pesquisar(int id) {
		Connection conn = conexao.getConnection();
		Endereco endereco = new Endereco();

		String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				endereco.setId(rs.getInt("ID_ENDERECO"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return endereco;
	}

	@Override
	public List<Endereco> listarTodos(int id) {
		Connection conn = conexao.getConnection();

		List<Endereco> retorno = new ArrayList<Endereco>();

		String sql = "SELECT * FROM ENDERECO";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setRua(rs.getString("RUA"));
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				retorno.add(endereco);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar o endereco" + e.getMessage());
		}finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
	}

}
