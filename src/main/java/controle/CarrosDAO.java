package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Carros;

public class CarrosDAO {
	
	public boolean inerir(Carros ca) {
		
		Conexao c = Conexao.getInstancia();
		
		Connection con = c.conectar();
		
		String query = "INSERT INTO Carros (idCarro, Modelo) VALUES (?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			// seta os parametros
			ps.setInt(1, ca.getIdCarro());
			ps.setString(2, ca.getModelo());
			
			// consolidar a execução do comando no banco
			ps.executeUpdate();
			
			// fecha a conexão
			c.fecharConexao();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
