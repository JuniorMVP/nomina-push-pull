import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivoNomina {

    public static void main(String[] args) {
        String url = "jdbc:derby://localhost:1527/apap";
        String user = "root";
        String password = "1234";
        String archivo = "nomina_unapec.txt";
        String linea;

        try (Connection con = DriverManager.getConnection(url, user, password);
             BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            // Leer y procesar el encabezado
            br.readLine(); // Saltar línea RNC
            br.readLine(); // Saltar línea Cuenta a Acreditar
            br.readLine(); // Saltar línea Periodo o Mes
            br.readLine(); // Saltar línea en blanco
            br.readLine(); // Saltar línea de encabezados del detalle

            String sql = "INSERT INTO PAGOS (CEDULA, NUMEROCUENTA, SUELDOBRUTO) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String cedula = datos[0].trim();
                String numeroCuenta = datos[1].trim();
                double sueldoBruto = Double.parseDouble(datos[2].trim());

                pstmt.setString(1, cedula);
                pstmt.setString(2, numeroCuenta);
                pstmt.setDouble(3, sueldoBruto);
                pstmt.executeUpdate();
            }
            System.out.println("Datos insertados en la BD del Banco con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
