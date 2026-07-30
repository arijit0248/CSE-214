public class XmlDataProvider {
    public String getXmlData(String data)
    {
        int sep = data.indexOf(":");
        String name = data.substring(0,sep);
        String id = data.substring(sep+1);
        return "<user>"+"<name>"+name+"</name>"+"<id>"+id+"</id>"+"</user>";
    }
}
