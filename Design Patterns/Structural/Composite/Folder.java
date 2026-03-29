
import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemItem{
    private String  name;
    private List<FileSystemItem> children;
    public Folder(String name)
    {
        this.name = name;
        this.children = new ArrayList<>();
    }
    public void add(FileSystemItem item)
    {
        children.add(item);
    }
    @Override
    public void ls() {
        for(FileSystemItem child:children)
        {
            System.out.println(child.getName());
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        for(FileSystemItem child:children)
        {
            size += child.getSize();
        }
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void openAll() {
        System.out.println(name);
        for(FileSystemItem child:children)
        {
            child.openAll();
        }
    }

    @Override
    public FileSystemItem cd(String dest) {
        for(FileSystemItem child:children)
        {
            if(child.isFolder() && child.getName().equals(dest))
            {
                return child;
            }
        }
        return null;
    }

    @Override
    public boolean isFolder() {
        return true;
    }
    
}
