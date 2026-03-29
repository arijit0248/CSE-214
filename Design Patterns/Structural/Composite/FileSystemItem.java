public interface FileSystemItem{
    void ls();
    int getSize();
    String getName();
    void openAll();
    FileSystemItem cd(String dest);
    boolean isFolder();
}