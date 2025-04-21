import java.util.function.Function;
import java.util.List;

public class Main {

    static Function<Integer, String> unidades = (n) -> {
        List<String> digitos = List.of("cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve");
        return digitos.get(n);
    };

    static Function<Integer, String> especiales = (n) -> {
        return List.of("diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve").get(n - 10);
    };

    static Function<Integer, String> decenas = (n) -> {
        List<String> decenasList = List.of("diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa");
        if (n < 10) {
            return unidades.apply(n);
        } else if (n < 20) {
            return especiales.apply(n);
        } else {
            int d = n / 10;
            int u = n % 10;
            if (u == 0) {
                return decenasList.get(d - 1);
            } else {
                return decenasList.get(d - 1) + " y " + unidades.apply(u);
            }
        }
    };

    static Function<Integer, String> centenas = (n) -> {
        List<String> centenasList = List.of("", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos",
                "seiscientos", "setecientos", "ochocientos", "novecientos");

        if (n == 100) {
            return "cien";
        }

        int c = n / 100;
        int resto = n % 100;

        String textoCentena = centenasList.get(c);
        if (resto == 0) {
            return textoCentena;
        } else {
            return textoCentena + " " + decenas.apply(resto);
        }
    };

    static Function<Integer, String> menoresDeMil = (n) -> {
        if (n < 100) {
            return decenas.apply(n);
        } else {
            return centenas.apply(n);
        }
    };

    static Function<Integer, String> convertir = (n) -> {
        if (n == 0) {
            return "cero";
        }

        StringBuilder resultado = new StringBuilder();

        int millones = n / 1_000_000;
        int miles = (n % 1_000_000) / 1_000;
        int resto = n % 1_000;

        if (millones > 0) {
            if (millones == 1) {
                resultado.append("un millón");
            } else {
                resultado.append(convertir.apply(millones)).append(" millones");
            }
        }

        if (miles > 0) {
            if (resultado.length() > 0) resultado.append(" ");
            if (miles == 1) {
                resultado.append("mil");
            } else {
                resultado.append(convertir.apply(miles)).append(" mil");
            }
        }

        if (resto > 0) {
            if (resultado.length() > 0) resultado.append(" ");
            resultado.append(menoresDeMil.apply(resto));
        }

        return resultado.toString();
    };

    public static void main(String[] args) {
        int[] numeros = {0, 5, 13, 21, 100, 115, 230, 999, 1000, 1050, 11000, 500000, 999999, 1000000, 2345678, 999999999};

        for (int num : numeros) {
            System.out.println(num + " -> " + convertir.apply(num));
        }
    }
}
