import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;

public class Main {

    private static final String URL = "jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.xgrlkrxyttwogcpyzozc&password=sakvyz-sucti7-Didjyr";

    public static void main(String[] args) throws SQLException {
        read();
        create();
        update();
        delete();
    }

    public static void delete() throws SQLException {
        System.out.print("Número de uma conta já existente: ");
        long nro = Long.parseLong(System.console().readLine());

        try (Connection c = DriverManager.getConnection(URL)) {
            String sql = "DELETE FROM contas WHERE nro_conta=?";
            try (PreparedStatement prepstm = c.prepareStatement(sql)) {
                prepstm.setLong(1, nro);
                int ret = prepstm.executeUpdate();
                System.out.println("Número de registros apagados: " + ret);
            }
        }
    }

    public static void update() throws SQLException {
        System.out.print("Número de uma conta já existente: ");
        long nro = Long.parseLong(System.console().readLine());

        System.out.print("Novo saldo para esta conta: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        try (Connection c = DriverManager.getConnection(URL)) {
            String sql = "UPDATE contas SET saldo=? WHERE nro_conta=?";
            try (PreparedStatement prepstm = c.prepareStatement(sql)) {
                prepstm.setBigDecimal(1, saldo);
                prepstm.setLong(2, nro);
                int ret = prepstm.executeUpdate();
                System.out.println("Número de registros alterados: " + ret);
            }
        }
    }

    public static void create() throws SQLException {
        System.out.print("Número para a nova conta: ");
        long nro = Long.parseLong(System.console().readLine());

        System.out.print("Saldo da nova conta: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        try (Connection c = DriverManager.getConnection(URL)) {
            String sql = "INSERT INTO contas (nro_conta, saldo) VALUES (?, ?)";
            try (PreparedStatement prepstm = c.prepareStatement(sql)) {
                prepstm.setLong(1, nro);
                prepstm.setBigDecimal(2, saldo);
                int ret = prepstm.executeUpdate();
                System.out.println("Número de registros inseridos: " + ret);
            }
        }
    }

    public static void read() throws SQLException {
        try (Connection c = DriverManager.getConnection(URL)) {
            String sql = "SELECT * FROM contas";
            try (PreparedStatement stm = c.prepareStatement(sql)) {
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        long nro = rs.getLong("nro_conta");
                        BigDecimal saldo = rs.getBigDecimal("saldo");
                        System.out.println("Conta número: " + nro + " tem saldo de R$ " + saldo);
                    }
                }
            }
        }
    }
}
