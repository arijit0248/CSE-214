public class Main {
    public static void main(String[] args) {
        XmlDataProvider xmlProv= new XmlDataProvider();
        Report report = new XmlDataProviderAdapter(xmlProv);
        String data = "Arijit:2205138";
        Client client = new Client();
        client.getReport(report, data);
    }
    
}
