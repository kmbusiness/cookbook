/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.Part;
/**
 *
 * @author Chi Phan
 */
@ManagedBean(name = "Bean")
@SessionScoped
public class Upload implements Serializable{
    private Part image;

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
    public void doUpload()
    {
        try
        {
            InputStream in = image.getInputStream();
            
            File f = new File("/Users/johnkmnguyen/Desktop/cook/new" + image.getSubmittedFileName());    
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            
            byte[] buffer = new byte[1024];
            int length;
            
            while((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }
            
            out.close();
            in.close();
        }catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
}
