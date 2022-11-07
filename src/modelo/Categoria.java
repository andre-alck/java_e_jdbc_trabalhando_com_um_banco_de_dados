package modelo;

public class Categoria {
    private Integer id;
    private String nome;

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format("Categoria:\nID:\t%d\nNOME:\t%s\n", this.id, this.nome);
    }
}
