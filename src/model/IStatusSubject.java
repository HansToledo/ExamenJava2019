package model;

public interface IStatusSubject {
    public void addVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver);
    public void removeVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver);
    public void notifyVerkeerstorenObservers();
}
