package controller;

import java.util.List;

import dao.ContaDAO;
import dao.impl.ContaDAOImpl;
import model.Conta;

public class Principal {
	public static void main(String[] args) {
	
		// teste de conexao
		//Conexao conexao = new Conexao();
		//Connection conn = conexao.getConnection();
		
		//Conta conta =new Conta();
		//conta.setNumero(1005);
		//conta.setSaldo(450);
		//conta.setLimite(300);
		
		ContaDAO contaDAO = new ContaDAOImpl();
		//contaDao.salvar(conta);
		//contaDAO.remover(1001);
		
		Conta conta1 =contaDAO.pesquisar(1002);
		System.out.println(conta1.toString());
		
		List<Conta> listaConta = contaDAO.listarTodos();
		for (Conta conta : listaConta) {
			System.out.println(conta.toString());
		}
		
		
		
		
	}
	
	

}