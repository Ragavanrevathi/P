import jdk.jfr.Event;
import net.sf.okapi.lib.xliff2.core.*;
import net.sf.okapi.lib.xliff2.writer.XLIFFWriter;

import java.io.*;
import java.lang.constant.DirectMethodHandleDesc;

class OKAPI {
    public static void main(String[] args) throws IOException {

        String rootPath = "C:\\Users\\rmsivakumar\\IdeaProjects\\Quarkus\\code-with-quarkus\\src\\main\\java\\CSV\\xl.xliff";
        String rootPath1 = "C:\\Users\\rmsivakumar\\IdeaProjects\\Quarkus\\code-with-quarkus\\src\\main\\java\\CSV\\Book1.CSV";
        FileReader fr = new FileReader(rootPath1);
        BufferedReader bf = new BufferedReader(fr); //To read excel File


        File f = new File(rootPath);
        FileWriter fw = new FileWriter(f);
        XLIFFWriter xw = new XLIFFWriter();
        xw.setUseIndentation(true);
        xw.create(fw,"en","en-gb");


        StartXliffData sxd = new StartXliffData("2.0");
        xw.writeStartDocument(sxd,"l");


        StartFileData sfd = new StartFileData("f1");
        sfd.setOriginal(rootPath1);
        xw.writeStartFile(sfd);

        Skeleton sk = new Skeleton();
        sk.setHref(rootPath1);
        xw.writeSkeleton(sk);

        StartGroupData sgd = new StartGroupData("e");
        xw.writeStartGroup(sgd);
        MidFileData m = new MidFileData();

        xw.writeMidFile(m);

        String line = bf.readLine();
        int row=0;
        while(line!=null){
            int col=0;
            String name[]=line.split(",");
            for(String i:name){

                String s = row+""+col;
                Unit u = new Unit(s,sfd);
                Segment f2 = u.appendSegment();
                f2.setSource(i);
                f2.setTarget(i);
                xw.writeUnit(u);
                col++;
            }
            row++;
            line = bf.readLine();
        }

       /*
        Note n = new Note("Hello");
        MidFileData md  = new MidFileData();
        md.addNote(n);
        xw.writeMidFile(md);
        StartGroupData sgd = new StartGroupData("e");
        xw.writeStartGroup(sgd);
        */
        xw.close();

    }


}
