/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauri
 */
public class PDF {
//   Variables

    private static final String CUR_DIR = System.getProperty("user.dir");
    private static final String DOCUMENTS_DIR = System.getProperty("user.home") + "\\Documents";
    private static final String REGULAR = CUR_DIR + "\\src\\main\\java\\fonts\\calibri.ttf";
    private static final String BOLD = CUR_DIR + "\\src\\main\\java\\fonts\\calibrib.ttf";
    private static String nombre_alumno;
    private static String num_control;
    private static String carrera_alum;
    private static String institucion_destinatario;
    private static String nombre_destinatario;
    private static String cargo_destinatario;

    public static Text generarNegritas(String frase) {
        return new Text(frase).setBold();
    }

    public static Text generarSubrayado(String frase) {
        return new Text(frase).setUnderline();
    }

    public static Document generarParrafos(Document document) throws IOException {

        //            Creacion de las fuentes del documento
        FontProgram fontProgram = FontProgramFactory.createFont(REGULAR);
        PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, true);
        fontProgram = FontProgramFactory.createFont(BOLD);
        PdfFont bold = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, true);

//            Fuente general del documento
        document.setFont(font);

        //           Header y footer
        Image header = new Image(ImageDataFactory.create("src\\main\\java\\imagenes\\pdf\\header_pdf.jpg"));
        Image footer = new Image(ImageDataFactory.create("src\\main\\java\\imagenes\\pdf\\footer_pdf.jpg"));
        footer.setHeight(290);
        footer.setFixedPosition(53, 15);

        //            Fecha y lugar 
        Paragraph fecha = new Paragraph("Saltillo, Coahuila, ").add(generarSubrayado(generarFecha()));
        fecha.setFontSize(10);
        fecha.setTextAlignment(TextAlignment.RIGHT);

        //            Asunto
        Paragraph asunto = new Paragraph("Asunto: Programa Dual");
        asunto.setFontSize(10);
        asunto.setTextAlignment(TextAlignment.RIGHT);
//            Destinatario
        Paragraph destinatario = new Paragraph(nombre_destinatario + "\n" + cargo_destinatario + "\n"
                + institucion_destinatario + "\nP R E S E N T E.-\n\n");
        destinatario.setFont(bold);

//            Datos programa y alumno
        Paragraph datosSaludo = new Paragraph();
        datosSaludo.setFontSize(10);
        datosSaludo.add("Por este conducto reciba un cordial saludo "
                + "y a la vez nos permita presentar a la C.");

        Paragraph datoNombreAlumno = new Paragraph(nombre_alumno);
        datoNombreAlumno.setFont(bold);
        datoNombreAlumno.setTextAlignment(TextAlignment.CENTER);

        Paragraph datosEspecialidad = new Paragraph();
        datosEspecialidad.add("Con numero de control ");
        datosEspecialidad.add(generarNegritas(num_control));
        datosEspecialidad.add(", alumna de este Instituto quien cursa la especialidad de:");

        Paragraph datoEspecialidad = new Paragraph(carrera_alum);
        datoEspecialidad.setTextAlignment(TextAlignment.CENTER);
        datoEspecialidad.setBold();

        Paragraph datosCursar = new Paragraph("quien desea realizar su ");
        datosCursar.add(generarNegritas("PROGRAMA DUAL"));
        datosCursar.add(" dentro de esa empresa, en el periodo ");
        datosCursar.add(generarNegritas("PERIODO"));
        datosCursar.add(", contando en la actualidad nuestro alumno con seguro facultativo IMSS ");
        datosCursar.add(generarNegritas("(05229936520)"));
        datosCursar.add(", vigente para tal efecto.");

        //            Añadir todos los objetos al documento
        document.setLeftMargin(53);
        document.setRightMargin(55);
        document.add(header)
                .add(fecha)
                .add(asunto)
                .add(destinatario)
                .add(datosSaludo)
                .add(datoNombreAlumno)
                .add(datosEspecialidad)
                .add(datoEspecialidad)
                .add(datosCursar)
                .add(footer);

        return document;
    }

    public static String generarMes(int mes) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes - 1];
    }

    public static String generarFecha() {
        String fecha = LocalDate.now().toString();
        String anio = "";
        String mes = "";
        String dia = "";
        int num = 0;

        for (int i = 0; i < fecha.length(); i++) {
            if (fecha.charAt(i) == '-') {
                i++;
                num++;
            }
            switch (num) {
                case 0 ->
                    anio += fecha.charAt(i);
                case 1 ->
                    mes += fecha.charAt(i);
                case 2 ->
                    dia += fecha.charAt(i);
            }
        }
        mes = generarMes(Integer.parseInt(mes));
        return dia + "/" + mes + "/" + anio;
    }

    public static void createPDF(String nombreAlumno, String numControl, String carreraAlum, String institucionDest, String nombreDest, String cargoDest) throws IOException {
        nombre_alumno = nombreAlumno;
        num_control = numControl;
        carrera_alum = carreraAlum;
        institucion_destinatario = institucionDest;
        nombre_destinatario = nombreDest;
        cargo_destinatario = cargoDest;

        PdfWriter pdfWriter = null;
        try {
//            Creacion del documento y carpeta de alumnos
            File doc = new File(DOCUMENTS_DIR + "\\archivos_alumnos");
            if (!doc.exists()) {
                doc.mkdir();
            }
            
            //Verificar si existe el archivo del alumno
            String [] listaArchivos = doc.list();
            int numFile = 1;
            for(int i = 0; i < listaArchivos.length; i++){
                System.out.println(listaArchivos[i]);
                if(listaArchivos[i].equals(numControl+"_"+numFile+".pdf")){
                    numFile++;
                }
            }
            
            File file = new File(DOCUMENTS_DIR + "\\archivos_alumnos\\" + numControl +"_"+numFile+ ".pdf");
            pdfWriter = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            try ( Document document = new Document(pdfDocument)) {
                generarParrafos(document);
            }

            pdfWriter.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pdfWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
