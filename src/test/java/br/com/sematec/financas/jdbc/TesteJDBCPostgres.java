package br.com.sematec.financas.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.sematec.financas.modelo.Conta;

public class TesteJDBCPostgres {

	public static void main(String[] args) throws SQLException {

		Conta conta = new Conta();
		conta.setTitular("Joao Ferreira");
		conta.setBanco("Itau");
		conta.setAgencia("0063");
		conta.setNumero("432561");

		Connection con = new ConnectionFactory().getConnectionPostgres();

		new BD(con).geraTabelaContas();

		con.setAutoCommit(false);

		ContaDAO dao = new ContaDAO(con);
		dao.adiciona(conta);

		List<Conta> contas = dao.lista();

		for (Conta c : contas) {
			System.out.println(c.getTitular());
		}

		con.commit();
		con.close();
	}

}
