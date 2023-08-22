package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Carros;

public class CarrosDAO {
	
	//criando um ojb lista para guardar o que o banco trouxer
	public ArrayList<Carros> listar() {

		Conexao c = Conexao.getInstancia();
		
		Connection con = c.conectar();
		
		ArrayList<Carros> carro = new ArrayList();
		
		String query = "SELECT * FROM Carros";
		
		try {
			//prepara a nossa query SQl acima em um obj java
			PreparedStatement ps = con.prepareStatement(query);
			// executa nossa query de fato
			ResultSet rs = ps.executeQuery();	
			// enquanto tive registro, faça esse loop
			while(rs.next()) {
				int idCarro = rs.getInt("idCarro");
				String Modelo = rs.getString("Modelo");
				
			// cria um obj organizadinho de carro
				Carros C = new Carros();
				C.setIdCarro(idCarro);
				C.setModelo(Modelo);

			// adiciona carro na lista
				carro.add(C);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		c.fecharConexao();
		
		return carro;

	}

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
	
	public boolean excluir(Carros ca) {
		
		Conexao c = Conexao.getInstancia();

		Connection con = c.conectar();
		
		String query = "DELETE FROM Carros WHERE idCarro = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, ca.getIdCarro());
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean atualizar(Carros ca) {
		
		Conexao c = Conexao.getInstancia();

		Connection con = c.conectar();
		
		String query = "UPDATE Carros SET Modelo = ? WHERE idCarro = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, ca.getModelo());
			ps.setInt(2, ca.getIdCarro());
			
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
