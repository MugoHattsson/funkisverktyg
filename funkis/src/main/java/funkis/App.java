package funkis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Table;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, MalformedURLException
    {
        System.out.println(System.getProperty("user.dir"));
        // Creating a PdfWriter
        PdfWriter writer = new PdfWriter(new File("funkis/resources/mypdf.pdf"));
  
        // Creating a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);
  
        // Creating a Document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(10, 10, 10, 10);
  
        // Creating an ImageData object
        ImageData data = ImageDataFactory.create("funkis/resources/dsims.png");
        
  
        // Creating an Image object
        Image image = new Image(data);
        // image.setFixedPosition(5, 5);
        // image.setAutoScale(true);
        // image.scale(0.2f, 0.2f);
  
        // Adding image to the document
        document.add(image);

        document.add(new AreaBreak());
        
        
        float columnWidth = (pdf.getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin()))/3;
        Table table = new Table(new float[] {columnWidth, columnWidth, columnWidth});
        // table.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        table.useAllAvailableWidth();

        

        final File dir = new File("funkis/resources/");
        final String[] EXTENSIONS = new String[]{
            "png", "jpg", "jpeg"
        };
        final FilenameFilter IMAGE_FILTER = new FilenameFilter(){
            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return true;
                    }
                }
                return false;
            }
        };

        if (dir.isDirectory()) {
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                Image img = new Image(ImageDataFactory.create(f.getPath()));
                img.setAutoScaleWidth(true);
                                
                List list = new List();
                list.setListSymbol(" ");
                list.setFontSize(25f);
                list.add(f.getName());
                list.add("HP, PP, P");
                list.add("8p");

                Cell cell = new Cell();
                cell.add(img);
                cell.add(list);
                cell.setMaxWidth(columnWidth);

                table.addCell(cell);
            }
        }

        
        document.add(table);

        // // image.setAutoScale(true);
        // image.setRelativePosition(-0.5f, 0, 0.5f, 0.3f);
        // document.setMargins(0, 0, 0, 0);
        // document.add(image);
  
        // Closing the document
        document.close();
  
        System.out.println("Image added successfully!!");
    }
}
