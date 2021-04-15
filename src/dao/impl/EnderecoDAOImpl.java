package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.EnderecoDAO;
import model.Conta;
import model.Endereco;
import model.util.Conexao;

public class EnderecoDAOImpl implements EnderecoDAO{

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
			ed.setInt(3,endereco.getNumero());
			ed.setString(4, endereco.getComplemento());
			ed.execute();
			System.out.println("Endereco inserido com sucesso! ");
		}catch(SQLException e) {
			System.out.println("Erro ao inserir conta no banco " + e.getMessage());
		}finally{
			conexao.fecharConexao(conn);
			
		}
		
	}

	@Override
	public void alterar(Endereco endereco) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Conta pesquisar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Endereco> listarTodos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
