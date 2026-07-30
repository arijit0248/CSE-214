public class File implements FileSystemItem{
    private String name;
    private int size;

    public File(String name,int size)
    {
        this.name = name;
        this.size = size;
    }

    @Override
    public void ls() {
        System.out.println(name);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void openAll() {
        System.out.println(name);
    }

    @Override
    public FileSystemItem cd(String dest) {
        return null;
    }

    @Override
    public boolean isFolder() {
        return false;
    }

}