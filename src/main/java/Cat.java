import java.io.Serializable;

public class Cat implements Serializable {
    private String nome;

    public Cat(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}