package io;


public class ReportWriterFactory {
    public static ReportWriter createReportWriter(String type)
    {
        if(type == null)
        {
            throw new IllegalArgumentException("Type can't be null!!!");
        }
        String tempType = type.toLowerCase();
        if(tempType.equals("txt") || tempType.equals("text"))
        {
            return new TxtReportWriter();
        }
        else if(tempType.equals("html"))
        {
            return new HtmlReportWriter();
        }
        else 
        {
            throw new IllegalArgumentException("Unsupported type" + type);
        }
    }
}
