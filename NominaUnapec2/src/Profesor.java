public class Profesor {
    private String cedula;
    private String numeroCuenta;
    private double sueldoBruto;

    public Profesor(String cedula, String numeroCuenta, double sueldoBruto) {
        this.cedula = cedula;
        this.numeroCuenta = numeroCuenta;
        this.sueldoBruto = sueldoBruto;
    }

    @Override
    public String toString() {
        return cedula + "," + numeroCuenta + "," + sueldoBruto;
    }
}

//La Universidad Apec procesa y paga mensualmente la nómina de los Profesores 
//de Carrera (entre otras nóminas), se requiere desarrollar un programa (push) 
//que genere el archivo de texto que Unapec sube al banco y otro (pull) que 
//permita al Banco (Asociación Popular de Ahorros y Préstamos) leer y guardar 
//en su BD los pagos realizados por Unapec a sus profesores.

