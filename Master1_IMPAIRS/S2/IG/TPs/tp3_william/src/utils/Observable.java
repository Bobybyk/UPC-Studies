package utils;

public interface Observable {

	void subscribe(Observer observer);
	void notifyObserver();
}
