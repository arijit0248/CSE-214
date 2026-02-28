package io;

import java.io.IOException;
import service.ExpenseRepository;

public interface ReportWriter {
    void writeReport(String filePath,ExpenseRepository repository) throws IOException;
}
