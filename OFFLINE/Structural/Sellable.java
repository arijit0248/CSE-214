public interface Sellable {
    String getName();
    double getPrice();
    double getDuration();
    void print(String indent);
    int countModules();
}
