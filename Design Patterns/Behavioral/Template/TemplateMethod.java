public abstract class TemplateMethod {
    abstract void loadData();
    abstract void preProcess();
    abstract void train();
    abstract void evaluate();
    abstract void save();
    public void method()
    {
        loadData();
        preProcess();
        train();
        evaluate();
        save();
    }
}
