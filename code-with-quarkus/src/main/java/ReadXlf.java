
import net.sf.okapi.lib.xliff2.reader.XLIFFReader;

import java.io.File;

public class ReadXlf {

    public static  void  main(String[] args){

        String rootPath = "C:\\Users\\rmsivakumar\\IdeaProjects\\Quarkus\\code-with-quarkus\\src\\main\\java\\CSV\\xl.xliff";
        File f = new File(rootPath);
        XLIFFReader xr = new XLIFFReader();
        xr.open(f);
        XLIFFReader.validate(f);
        while ((xr.hasNext())) {
             if((xr.next().getType()+"").equals("MID_FILE")) {
                    ReadUnit(xr);
             }
        }
    }

    public static void ReadUnit(XLIFFReader xr){
        boolean f = true;
        try {
            String columnName = xr.next().getStartGroupData().getId();
            System.out.println(columnName);
        }catch (Exception e){
            System.out.println("File readed Successfully");
            f = false;
        }
        while (f) {
            try {
                System.out.println(xr.next().getUnit().getId());
            } catch (Exception e) {
                f=false;
                ReadUnit(xr);
            }
        }
    }
}
