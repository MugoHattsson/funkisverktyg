package funkis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.LinkedList;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Table;

public final class PdfMaker {

    public static void createPdf(final File dir) {
        final String path = dir.getPath() + "/";

        // Creating a PdfWriter
        PdfWriter writer;
        try {
            writer = new PdfWriter(new File(path + "output.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Creating a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(10, 10, 10, 10);

        float columnWidth = (pdf.getDefaultPageSize().getWidth()
                - (document.getLeftMargin() + document.getRightMargin())) / 3;
        float[] columnWidths = new float[] {
                columnWidth, columnWidth, columnWidth
        };

        final String[] EXTENSIONS = new String[] {
                "png", "jpg", "jpeg"
        };

        final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
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

        System.out.println("\nSkapar PDF");

        if (dir.isDirectory()) {
            LinkedList<File> files = new LinkedList<>(Arrays.asList(dir.listFiles(IMAGE_FILTER)));
            while (!files.isEmpty()) {
                Table table = new Table(columnWidths);
                table.useAllAvailableWidth();

                for (File f : pop3(files)) {
                    Image img;
                    try {
                        img = new Image(ImageDataFactory.create(f.getPath()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        document.close();
                        return;
                    }
                    img.setAutoScaleWidth(true);

                    List list = new List();
                    list.setListSymbol(" ");
                    list.setFontSize(25f);
                    list.add(formatFileName(f));

                    Cell cell = new Cell();
                    cell.add(img);
                    cell.add(list);
                    cell.setMaxWidth(columnWidth);
                    cell.setBorder(Border.NO_BORDER);

                    table.addCell(cell);
                    System.out.print(".");
                }
                document.add(table);
                if (!files.isEmpty()) {
                    document.add(new AreaBreak());
                }
            }
        }

        // Closing the document
        document.close();

        System.out.println("\nPDF skapad");
    }

    // Takes a filename and formats it so to be nicely included in the final pdf.
    // Currently returns the filename as a string without the extension.
    private static String formatFileName(File file) {
        return file.getName().split("\\.")[0];
    }

    // Returns the first three files from a list of files, or the remaining amount
    // if there's fewer than three left.
    private static LinkedList<File> pop3(LinkedList<File> list) {
        LinkedList<File> result = new LinkedList<>();
        int iterations = list.size() > 2 ? 3 : list.size();
        for (int i = 0; i < iterations; i++) {
            result.add(list.pop());
        }
        return result;
    }
}
