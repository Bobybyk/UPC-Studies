public interface Observable {
    void subscribe(Observer obs);
    void notifyObserver();
}
