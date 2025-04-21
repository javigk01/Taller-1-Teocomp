//hola

import java.util.Scanner;
import java.util.function.Function;

/**
 * Taller 2 - Conversión de números a palabras en español utilizando programación funcional.
 * Integrantes: [Nombres del grupo]
 * Descripción: Este programa convierte un número entero o decimal a su representación textual
 * utilizando el paradigma funcional en Java (con Function y expresiones lambda).
 */
public class Taller2TeoC {

    // Arrays de palabras
    private static final String[] unidades = {
            "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve",
            "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"
    };

    private static final String[] decenas = {
            "", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"
    };

    private static final String[] centenas = {
            "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos",
            "seiscientos", "setecientos", "ochocientos", "novecientos"
    };

    // Declaración adelantada de las funciones para poder usarlas dentro de sí mismas
    private static Function<Long, String> convertirParteEntera;
    private static Function<Double, String> convertirParteDecimal;
    private static Function<Double, String> convertirNumeroAPalabras;

    static {
        convertirParteEntera = numero -> {
            if (numero == 0) return "";
            if (numero < 20) return unidades[(int) (long) numero];
            if (numero < 100) {
                String base = decenas[(int) (numero / 10)];
                String resto = numero % 10 != 0 ? " y " + unidades[(int) (numero % 10)] : "";
                return base + resto;
            }
            if (numero < 1000) {
                if (numero == 100) return "cien";
                String base = centenas[(int) (numero / 100)];
                String resto = numero % 100 != 0 ? " " + convertirParteEntera.apply(numero % 100) : "";
                return base + resto;
            }
            if (numero < 1000000) {
                String miles = numero / 1000 == 1 ? "mil" : convertirParteEntera.apply(numero / 1000) + " mil";
                String resto = numero % 1000 != 0 ? " " + convertirParteEntera.apply(numero % 1000) : "";
                return miles + resto;
            }
            long millones = numero / 1000000;
            String millonesTexto = millones == 1 ? "un millón" : convertirParteEntera.apply(millones) + " millones";
            String resto = numero % 1000000 != 0 ? " " + convertirParteEntera.apply(numero % 1000000) : "";
            return millonesTexto + resto;
        };

        convertirParteDecimal = decimal -> {
            String decimalStr = String.format("%.2f", decimal);
            String[] parts = decimalStr.split("\\.");
            if (parts.length < 2 || parts[1].equals("00")) {
                return "cero centavos";
            }
            int valor = Integer.parseInt(parts[1]);
            return convertirParteEntera.apply((long) valor) + " centavos";
        };
        

        convertirNumeroAPalabras = numero -> {
            if (numero == 0) return "cero";
            String resultado = (numero < 0 ? "menos " : "") +
                    convertirParteEntera.apply((long) Math.abs(numero)) +
                    (numero % 1 != 0 ? " con " + convertirParteDecimal.apply(Math.abs(numero % 1)) : "");
            return resultado.trim();
        };
    }

    public static String monto_escrito(double numero) {
        return convertirNumeroAPalabras.apply(numero);
    }

    public static void main(String[] args) {
        System.out.println(monto_escrito(0));
        System.out.println(monto_escrito(19));
        System.out.println(monto_escrito(100));
        System.out.println(monto_escrito(101));
        System.out.println(monto_escrito(999999));
        System.out.println(monto_escrito(1000000));
        System.out.println(monto_escrito(2000000));
        System.out.println(monto_escrito(4321.70));
        System.out.println(monto_escrito(-987.5));
        System.out.println(monto_escrito(123456.78));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número que desea convertir: ");
        double numero = scanner.nextDouble();
        System.out.println("Representación en palabras: " + monto_escrito(numero));
        scanner.close();
    }
}
