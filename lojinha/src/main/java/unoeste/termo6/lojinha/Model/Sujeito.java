package unoeste.termo6.lojinha.Model;

public interface Sujeito {
    void registrarObserver(Observer o);
    void removerObserver(Observer o);
    void notificarObservers();
    void removeObservers();
}
