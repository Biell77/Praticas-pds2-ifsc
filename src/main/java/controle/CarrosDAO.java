package controle;

import java.sql.Connection;

import modelo.Carros;

public class CarrosDAO {
	
	public boolean inerir(Carros ca) {
		
		Conexao c = Conexao.getInstancia();
		
		Connection con = c.conectar();
		
		return true;
	}

}
