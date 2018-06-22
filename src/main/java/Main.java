import model.Format;
import model.Image;
import model.MimeType;
import model.Project;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main( String[] args) throws IOException, SQLException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Select picture file(s)");
        int retour = fileChooser.showOpenDialog(null);
        if(retour == JFileChooser.APPROVE_OPTION) {
            // des fichiers ont été choisis
            File file = fileChooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getParent());
            String basename = FilenameUtils.getBaseName(file.getName());
            String extension = FilenameUtils.getExtension(file.getName());

            String displayCommand = "display ";
            String convertCommand = "convert "+ file.getAbsolutePath() + " -resize 50% " + file.getParent() + "/converted_"+file.getName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(convertCommand);
            //System.out.println(basename);
            //System.out.println(extension);
            /*
            File[] fs = fileChooser.getSelectedFiles();
            for( int i = 1; i<file.length; ++i){
                // nom du fichier
                file[i].getName();
                // chemin absolu du fichier
                file[i].getAbsolutePath();
            }
            */
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Project project = new Project("ProjectPicture1", "transform picture size");
            Format format = new Format("XL", MimeType.png, true, null, project);
            Image image = new Image(basename, format, extension);

            byte[] fileContent = new byte[(int) file.length()];
            FileInputStream inputStream = new FileInputStream(file);
            //Blob blob = Hibernate.getLobCreator(session).createBlob(inputStream, file.length());
            inputStream.read(fileContent);
            inputStream.close();
            Blob blob = Hibernate.getLobCreator(session).createBlob(fileContent);
            image.setData(blob);

            //TODO Persistence Entity
            session.save(project);
            session.save(format);
            session.save(image);
            //blob.free();

            session.getTransaction().commit(); //Commit pour l'ectriture. Pas necessaire pour la lecture

            List<Image> list = session.createQuery("from Image").list();
            for(Image img : list){
                System.out.println(img.toString());
            }

            session.close();
            session.getSessionFactory().close();


        }
    }

}
