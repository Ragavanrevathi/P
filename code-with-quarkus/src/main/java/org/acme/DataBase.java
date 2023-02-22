package org.acme;
import net.sf.okapi.lib.xliff2.core.Note;
import net.sf.okapi.lib.xliff2.core.Segment;
import net.sf.okapi.lib.xliff2.core.Unit;
import net.sf.okapi.lib.xliff2.writer.XLIFFWriter;

import java.io.*;
import java.sql.*;
import java.util.Properties;
public class DataBase {

     private FileReader reader;
     private Properties p;
     private Connection con ;
     private Statement stmt;

    public DataBase() throws SQLException, IOException {
        LoadPropertyFile();
        ConnectDB();

    }


    private void ConnectDB() throws SQLException {
        con = DriverManager.getConnection(getURL(),getUserName(),getPassword());
        stmt = con.createStatement();
        System.out.println("Connected to DataBase");
    }

    private void LoadPropertyFile() throws IOException {
        reader=new FileReader("info.properties");
        p=new Properties();
        p.load(reader);
    }

    public void EnterUnit(XLIFFWriter xw) throws SQLException, IOException {
        String query = "SELECT * FROM RTM_XLIFF_NOTES";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()){

            String NOTE_FROM = rs.getString(4);
            String NOTE_VALUE = ClobToString(rs.getClob(5));
            Note note = new Note(NOTE_FROM);
            note.setText("Hello");
            Unit u = new Unit(rs.getInt(1)+"");
            Segment seg = u.appendSegment();
            seg.setSource(NOTE_VALUE);
            seg.setTarget(NOTE_VALUE);
            u.addNote(note);
            xw.writeUnit(u);
        }
    }

    public  String ClobToString(Clob clob) throws SQLException, IOException {
        InputStream in = clob.getAsciiStream();
        Reader read = new InputStreamReader(in);
        StringWriter write = new StringWriter();

        int c = -1;
        while ((c = read.read()) != -1)
        {
            write.write(c);
        }
        write.flush();
        String s = write.toString();
        return s;
    }

    public String getURL() {
        return p.getProperty("URL");
    }

    public void setURL(String URL) {
         p.setProperty("URL",URL);
    }

    public String getUserName() {
        return p.getProperty("UserName");
    }

    public void setUserName(String userName) {
        p.setProperty("UserName",userName);
    }

    public String getPassword() {
        return p.getProperty("Password");
    }

    public void setPassword(String password) {
        p.setProperty("Password",password);
    }





}
