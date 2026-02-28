public class TreeMethod extends TemplateMethod{

    @Override
    void loadData() {
        System.out.println("Loading data for tree method");
    }

    @Override
    void preProcess() {
        System.out.println("Pre processing data for tree method");
    }

    @Override
    void train() {
        System.out.println("Training data for tree method");
    }

    @Override
    void evaluate() {
        System.out.println("Evaluating data for tree method");
    }

    @Override
    void save() {
        System.out.println("Saving data for tree method");
    }
    
}
