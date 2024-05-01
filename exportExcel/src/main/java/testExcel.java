
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

        
public class testExcel {
    public static void main(String[] args) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet("Review");
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(new File("C:\\Users\\user\\Desktop\\review.xlsx"));
            
            if (file != null){
            workbook.write(file);
        }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
