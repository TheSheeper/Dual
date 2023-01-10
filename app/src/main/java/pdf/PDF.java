/*
 * Copyright 2022 Chipa & Alan G.
 */
package pdf;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
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
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauri
 */
public class PDF {
//   Variables

    private static final String CUR_DIR = System.getProperty("user.dir");
    private static final String DOCUMENTS_DIR = "E:\\Usuario\\Documentos";      //    System.getProperty("user.home")+"C:\\AppData\\Local";
    private static final String REGULAR = CUR_DIR + "\\src\\main\\resources\\fonts\\Montserrat-Regular.ttf";
    private static final String BOLD = CUR_DIR + "\\src\\main\\resources\\fonts\\Montserrat-Bold.ttf";
    private static String nombre_alumno;
    private static String num_control;
    private static String carrera_alum;
    private static String num_seg_social;
    private static String institucion_destinatario;
    private static String nombre_destinatario;
    private static String cargo_destinatario;

    public static Text generarNegritas(String frase) {
        FontProgram fontProgram = null;
        try {
            fontProgram = FontProgramFactory.createFont(BOLD);
        } catch (IOException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfFont bold = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, true);
        return new Text(frase).setFont(bold);
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
        Image header = new Image(ImageDataFactory.create("src\\main\\resources\\images\\pdf\\header_pdf.jpg"));
        Image footer = new Image(ImageDataFactory.create("src\\main\\resources\\images\\pdf\\footer_pdf_color.jpg"));
        footer.setHeight(290);
        footer.setFixedPosition(53, 13);

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
        destinatario.setMultipliedLeading(1);
        destinatario.setFont(bold);

//            Datos programa y alumno
        Paragraph datosSaludo = new Paragraph();
        datosSaludo.setFontSize(10);
        datosSaludo.add("Por este conducto reciba un cordial saludo "
                + "y a la vez nos permita presentar a la C.");

        Paragraph datoNombreAlumno = new Paragraph(nombre_alumno);
        datoNombreAlumno.setFontSize(10);
        datoNombreAlumno.setFont(bold);
        datoNombreAlumno.setTextAlignment(TextAlignment.CENTER);

        Paragraph datosEspecialidad = new Paragraph();
        datosEspecialidad.setFontSize(10);
        datosEspecialidad.add("Con numero de control ");
        datosEspecialidad.add(generarNegritas(num_control));
        datosEspecialidad.add(", alumna de este Instituto quien cursa la Especialidad de:");

        Paragraph datoEspecialidad = new Paragraph(carrera_alum);
        datoEspecialidad.setFontSize(10);
        datoEspecialidad.setTextAlignment(TextAlignment.CENTER);
        datoEspecialidad.setFont(bold);

        Paragraph datosCursar = new Paragraph("quien desea realizar su ");
        datosCursar.setFontSize(10);
        datosCursar.setMultipliedLeading(1);
        datosCursar.add(generarNegritas("PROGRAMA DUAL"));
        datosCursar.add(" dentro de esa empresa, en el periodo ");
        datosCursar.add(generarNegritas(generarPeriodo()));
        datosCursar.add(", contando en la actualidad nuestro alumno con seguro facultativo IMSS ");
        datosCursar.add(generarNegritas("("+num_seg_social+")"));
        datosCursar.add(", vigente para tal efecto.");

        //            Añadir todos los objetos al documento
        document.setLeftMargin(65);
        document.setRightMargin(50);
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
    
    public static String generarPeriodo(){
        int anio = LocalDate.now().getYear();
        int mes = LocalDate.now().getMonth().getValue();
        String fecha;
        if(mes <= 6){
            fecha = "ENERO - JUNIO "+ anio;
        }
        else{
            fecha = "AGOSTO - DICIEMBRE "+ anio;
        }
        return fecha;
    }

    public static String generarFecha() {
        int anio = LocalDate.now().getYear();
        String mes = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        mes = mes.toUpperCase().charAt(0) + mes.substring(1);
        int dia = LocalDate.now().getDayOfMonth();
        return dia + "/" + mes + "/" + anio;
    }

    public static void createPDF(String nombreAlumno, String numControl, String carreraAlum, String numSeguroSocial,
            String institucionDest, String nombreDest, String cargoDest) throws IOException {
        nombre_alumno = nombreAlumno.toUpperCase();
        num_control = numControl.toUpperCase();
        carrera_alum = carreraAlum.toUpperCase();
        num_seg_social = numSeguroSocial.toUpperCase();
        institucion_destinatario = institucionDest.toUpperCase();
        nombre_destinatario = nombreDest.toUpperCase();
        cargo_destinatario = cargoDest.toUpperCase();

        PdfWriter pdfWriter = null;
        try {
            System.out.println(DOCUMENTS_DIR);
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

            try ( Document document = new Document(pdfDocument, new PageSize(new Rectangle(595, 770)))) {
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
