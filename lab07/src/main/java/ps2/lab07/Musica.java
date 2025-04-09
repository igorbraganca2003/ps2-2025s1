package ps2.lab07;

public class Musica {
    private String titulo;
    private String compositor;
    private int ano;

    public Musica(String titulo, String compositor, int ano) {
        this.titulo = titulo;
        this.compositor = compositor;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
