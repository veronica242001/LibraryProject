package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AuditWriteCsv {
    private static AuditWriteCsv  auditWrite = null;
    private AuditWriteCsv(){
    }

    public static AuditWriteCsv getInstance(){
        if(auditWrite == null){
            auditWrite = new AuditWriteCsv();
        }
        return auditWrite;
    }

    public void writeAudit( String action,String timestamp ) throws IOException {
        File f = new File("src/audit_data.csv");
        BufferedWriter output = new BufferedWriter(new FileWriter(f,true));
        output.append(action);
        output.append(",");
        output.append(timestamp);
        output.newLine();
        output.flush();

    }
}
