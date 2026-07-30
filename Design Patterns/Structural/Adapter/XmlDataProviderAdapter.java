public class XmlDataProviderAdapter implements Report{
    private XmlDataProvider pro;
    public XmlDataProviderAdapter(XmlDataProvider pro)
    {
        this.pro = pro;
    }
    @Override
    public String getJsonData(String data) {
        String xml = pro.getXmlData(data);
        int startName = xml.indexOf("<name>")+6;
        int endName = xml.indexOf("</name>");
        String name = xml.substring(startName,endName);

        int startId = xml.indexOf("<id>")+4;
        int endId = xml.indexOf("</id>");
        String id = xml.substring(startId,endId);

        return "{\"name\":\""+name+"\", \"id\":"+id+"}";
    }
    
}