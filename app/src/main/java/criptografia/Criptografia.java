package criptografia;



public class Criptografia {

    public static String criptografar(String mensagem) {

        String saida = "";
        String abecedario = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz@#$%&*-+!=<>[](){}/|0123456789";
        String chave = "IMTICKET";

        char[] chaveEquals = new char[mensagem.length()];

        char[] Msj = mensagem.toCharArray();

        int cont = 0;

        for (int c = 0; c < mensagem.length(); c++) {

            if (mensagem.charAt(c) == ' ') {
                c++;
            }

            chaveEquals[c] = chave.charAt(cont);
            cont++;
            if (cont == chave.length()) {
                cont = 0;
            }
        }

        int x = 0;
        int y = 0;
        int z;

        for (int c = 0; c < mensagem.length(); c++) {
            if (mensagem.charAt(c) == ' ') {
                saida += " ";
                c++;
            }
            for (int f = 0; f < abecedario.length(); f++) {
                if (Msj[c] == abecedario.charAt(f)) {
                    x = f;

                }
                if (chaveEquals[c] == abecedario.charAt(f)) {
                    y = f;
                }

            }
            z = (x + y) % 82;
            saida += abecedario.charAt(z);

        }

        return saida;
    }


    public static String descriptografar(String mensagem) {

        String saida = "";
        String abecedario = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz@#$%&*-+!=<>[](){}/|0123456789";
        String chave = "IMTICKET";

        char[] chaveEquals = new char[mensagem.length()];

        char[] Msj = mensagem.toCharArray();

        int cont = 0;

        for (int c = 0; c < mensagem.length(); c++) {

            if (mensagem.charAt(c) == ' ') {
                c++;
            }

            chaveEquals[c] = chave.charAt(cont);
            cont++;
            if (cont == chave.length()) {
                cont = 0;
            }

        }

        cont = 0;
        int x = 0;
        int y = 0;
        int z;

        for (int c = 0; c < mensagem.length(); c++) {
            if (mensagem.charAt(c) == ' ') {
                saida += " ";
                c++;
            }
            for (int f = 0; f < abecedario.length(); f++) {
                if (Msj[c] == abecedario.charAt(f)) {
                    x = f;
                }
                if (chaveEquals[c] == abecedario.charAt(f)) {
                    y = f;
                }

            }

            z = (y - x);

            if (z <= 0) {
                if (z == 0) {
                    saida += "A";
                } else {

                    for (int j = 1; j <= abecedario.length(); j++) {
                        cont++;
                        if (cont == (z * -1)) {
                            saida += abecedario.charAt(j);
                            break;
                        }
                    }
                }

            } else {
                for (int i = 81 ; i >= 0; i--) {
                    cont++;
                    if (cont == z) {
                        saida += abecedario.charAt(i);
                        break;
                    }
                }
            }

            cont = 0;

        }

        return saida;
    }
}

