public class Client{
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new File("file1",10));
        root.add(new File("file2",5));

        Folder docs = new Folder("docs");
        docs.add(new File("resume.pdf",9));
        docs.add(new File("notes.txt",10));
        root.add(docs);

        Folder images = new Folder("images");
        images.add(new File("photo.jpg",1));
        root.add(images);

        root.ls();
        docs.ls();
        root.openAll();
        FileSystemItem cwd = root.cd("docs");
        if(cwd != null)
        {
            cwd.ls();
        }
        else
        {
            System.out.println("Couldnt cd");
        }
        System.out.println(root.getSize());
    }
}