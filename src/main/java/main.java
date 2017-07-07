import java.io.*;

public class main {
    public static void main(String[] args) {

        final String arquivoTexto = "texto.txt";
        final String texto = "Olá aqui há uma mensagem!";
        final String textoRetorno;

        final String nomeArquivo = "cat.ser";
        final Cat gatoSerializar = new Cat("Catizildo");
        final Cat gatoDeserializado;

        try {
            gravarTextoNoArquivo(arquivoTexto, texto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            textoRetorno = lerTextoArquivo(arquivoTexto);

            System.out.println("Mensagem do arquivo " + arquivoTexto + ": " + textoRetorno);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            serializarGato(nomeArquivo, gatoSerializar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            gatoDeserializado = deserializarGato(nomeArquivo);

            System.out.println("Gato '" + gatoDeserializado.getNome() + "' deserializado!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static String lerTextoArquivo(String arquivoTexto) throws IOException {
        byte[] buffer = new byte[1];
        StringBuilder retorno = new StringBuilder();

        final FileInputStream inp = new FileInputStream(arquivoTexto);

        while (inp.read(buffer) != -1)
            retorno.append(new String(buffer));

        inp.close();

        return retorno.toString();
    }

    private static void gravarTextoNoArquivo(String arquivoTexto, String texto) throws IOException {
        final FileOutputStream out = new FileOutputStream(arquivoTexto);

        out.write(texto.getBytes());

        out.close();

        System.out.println("Arquivo " + arquivoTexto + " salvo com sucesso!");
    }

    private static Cat deserializarGato(String nomeArquivo) throws IOException, ClassNotFoundException {
        final FileInputStream fileInputStream = new FileInputStream(nomeArquivo);
        final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        final Cat gatoRetorno = (Cat) objectInputStream.readObject();
        objectInputStream.close();

        return gatoRetorno;
    }

    private static void serializarGato(String nomeArquivo, Cat gatoSerializar) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(nomeArquivo);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(gatoSerializar);
        objectOutputStream.close();

        System.out.println("Gato '" + gatoSerializar.getNome() + "' serializado!");
    }

}