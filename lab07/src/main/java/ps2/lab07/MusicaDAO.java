package ps2.lab07;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {
    private final String URL = "jdbc:sqlite:musicas.db"; 

    public MusicaDAO() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS musicas (" +
                         "titulo VARCHAR(100) NOT NULL PRIMARY KEY, " +
                         "compositor VARCHAR(50) NOT NULL, " +
                         "ano INT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Musica musica) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sql = "INSERT INTO musicas (titulo, compositor, ano) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, musica.getTitulo());
            pstmt.setString(2, musica.getCompositor());
            pstmt.setInt(3, musica.getAno());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Musica> listar() {
        List<Musica> musicas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM musicas");
            while (rs.next()) {
                musicas.add(new Musica(rs.getString("titulo"),
                                       rs.getString("compositor"),
                                       rs.getInt("ano")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }

    public void atualizar(Musica musica) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sql = "UPDATE musicas SET compositor = ?, ano = ? WHERE titulo = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, musica.getCompositor());
            pstmt.setInt(2, musica.getAno());
            pstmt.setString(3, musica.getTitulo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(String titulo) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sql = "DELETE FROM musicas WHERE titulo = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, titulo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
