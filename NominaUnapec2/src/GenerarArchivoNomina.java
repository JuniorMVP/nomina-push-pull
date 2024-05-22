import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;

public class GenerarArchivoNomina {

    public static void main(String[] args) {
        String url = "jdbc:derby://localhost:1527/unapec";
        String user = "root";
        String password = "1234"; 

        String rnc = "1-01-12345-6";
        String cuentaAcreditar = "123-456-789";
        String periodoMes = "052024"; // Mayo 2024

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM NOMINA");
             FileWriter writer = new FileWriter("nomina_unapec.txt")) {

            // Escribir el encabezado
            writer.append("RNC: ").append(rnc).append("\n");
            writer.append("Cuenta a Acreditar: ").append(cuentaAcreditar).append("\n");
            writer.append("Periodo o Mes: ").append(periodoMes).append("\n");
            writer.append("\n");

            // Escribir el detalle
            writer.append("Cédula, Número de Cuenta, Sueldo Bruto\n");
            while (rs.next()) {
                String cedula = rs.getString("CEDULA");
                String numeroCuenta = rs.getString("NUMEROCUENTA");
                double sueldoBruto = rs.getDouble("SUELDOBRUTO");
                writer.append(cedula).append(",").append(numeroCuenta).append(",").append(String.valueOf(sueldoBruto)).append("\n");
            }
            System.out.println("Archivo generado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

